package islandADT.BiomeSetter;

import java.util.Map;

import islandADT.GeometryContainer;
import islandADT.GeometryWrappers.PolygonWrapper;
import islandADT.TypeWrappers.TileTypeWrapper;
import islandADT.Specifications.IslandSpecifications;

public class DesertBiomeSetter extends BiomeSetterAbstract{
    private IslandSpecifications islandSpecifications;

    public DesertBiomeSetter(IslandSpecifications islandSpecifications) {
        this.islandSpecifications = islandSpecifications;
    }

    public void calculateDesertBiome(PolygonWrapper p) {
        double moisture = p.getMoisture();
        int height = p.getHeight();
        int[] coords = new int[2];

        if (height < 2*height_increment) {coords[0] = 1;}
        else if (height < 4*height_increment) {coords[0] = 2;}
        else if (height < 6*height_increment) {coords[0] = 3;}
        else if (height < 8*height_increment) {coords[0] = 4;}
        else {coords[0] = 5;}

        if (moisture < 2*moisture_increment) {coords[1] = 1;}
        else if (moisture < 4*moisture_increment) {coords[1] = 2;}
        else if (moisture < 6*moisture_increment) {coords[1] = 3;}
        else if (moisture < 8*moisture_increment) {coords[1] = 4;}
        else if (moisture <= 10*moisture_increment) {coords[1] = 5;}

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
        set_increments(geometryContainer.get_polygons());
        Map<Integer, PolygonWrapper> polygons = geometryContainer.get_polygons();
        for (PolygonWrapper p: polygons.values()) {
            if (!p.isWaterOrNah() && p.isLandornah()) {
                calculateDesertBiome(p);
            }
        }
    }
}
