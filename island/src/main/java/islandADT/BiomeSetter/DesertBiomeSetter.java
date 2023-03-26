package islandADT.BiomeSetter;

import java.util.Map;

import islandADT.GeometryContainer;
import islandADT.GeometryWrappers.PolygonWrapper;
import islandADT.TypeWrappers.TileTypeWrapper;
import islandADT.Specifications.IslandSpecifications;

public class DesertBiomeSetter implements BiomeInterface{
    private IslandSpecifications islandSpecifications;

    public DesertBiomeSetter(IslandSpecifications islandSpecifications) {
        this.islandSpecifications = islandSpecifications;
    }

    public void calculateDesertBiome(PolygonWrapper p) {
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

        TileTypeWrapper Grassland = new TileTypeWrapper("Grassland");
        TileTypeWrapper Swamp = new TileTypeWrapper("Swamp");
        TileTypeWrapper Mushroom = new TileTypeWrapper("Mushroom");
        TileTypeWrapper Rainforest = new TileTypeWrapper("Rainforest");
        TileTypeWrapper Savannah = new TileTypeWrapper("Savannah");
        TileTypeWrapper Desert = new TileTypeWrapper("Desert");
        
        if (coords[0] <= 2) {
            p.setTileType(Desert);
        }
        else if (coords[0] == 3) {
            if (coords[1] <= 3) {
                p.setTileType(Savannah);
            }
            else {
                p.setTileType(Grassland);
            }
        }
        else if (coords[0] == 4) {
            if (coords[1] == 1) {
                p.setTileType(Rainforest);
            }
            else if (coords[1] <= 3) {
                p.setTileType(Swamp);
            }
            else if (coords[1] == 4) {
                p.setTileType(Savannah);
            }
            else {
                p.setTileType(Mushroom);
            }
        }
        else {
            if (coords[1] <= 3) {
                p.setTileType(Rainforest);
            }
            else {
                p.setTileType(Mushroom);
            }
        }

    }

    public void setBiomes(GeometryContainer geometryContainer) {
        Map<Integer, PolygonWrapper> polygons = geometryContainer.get_polygons();
        for (PolygonWrapper p: polygons.values()) {
            if (!p.isWaterOrNah() && p.isLandornah()) {
                calculateDesertBiome(p);
            }
        }
    }
}
