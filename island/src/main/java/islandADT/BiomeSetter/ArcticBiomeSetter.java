package islandADT.BiomeSetter;

import java.util.Map;

import islandADT.GeometryContainer;
import islandADT.GeometryWrappers.PolygonWrapper;
import islandADT.TypeWrappers.TileTypeWrapper;
import islandADT.Specifications.IslandSpecifications;

public class ArcticBiomeSetter{
    private IslandSpecifications islandSpecifications;

    public ArcticBiomeSetter(IslandSpecifications islandSpecifications) {
        this.islandSpecifications = islandSpecifications;
    }

    private void calculateArcticBiome(PolygonWrapper p) {
        double moisture = p.getMoisture();
        int height = p.getHeight();
        int[] coords = new int[2];

        if (height < 100) {coords[0] = 1;}
        else if (height < 200) {coords[0] = 2;}
        else if (height < 300) {coords[0] = 3;}
        else if (height < 400) {coords[0] = 4;}
        else {coords[0] = 5;}

        if (moisture < 4) {coords[1] = 1;}
        else if (moisture < 8) {coords[1] = 2;}
        else if (moisture < 12) {coords[1] = 3;}
        else if (moisture < 16) {coords[1] = 4;}
        else if (moisture < 20) {coords[1] = 5;}

        TileTypeWrapper Taiga = new TileTypeWrapper("Taiga");
        TileTypeWrapper Tundra = new TileTypeWrapper("Tundra");
        TileTypeWrapper Mountain = new TileTypeWrapper("Mountain");
        TileTypeWrapper Bamboo = new TileTypeWrapper("Bamboo Mountain");
        
        if (coords[0] <= 3) {
            if (coords[1] <= 2) {
                p.setTileType(Taiga);
            }
            else {
                p.setTileType(Tundra);
            }
        }
        else {
            if (coords[1] == 1) {
                p.setTileType(Bamboo);
            }
            else {
                p.setTileType(Mountain);
            }
        }

    }

    public void setBiomes (GeometryContainer geometryContainer) {
        Map<Integer, PolygonWrapper> polygons = geometryContainer.get_polygons();
        for (PolygonWrapper p: polygons.values()) {
            if (!p.isWaterOrNah() && p.isLandornah()) {
                calculateArcticBiome(p);
            }
        }
    }
}