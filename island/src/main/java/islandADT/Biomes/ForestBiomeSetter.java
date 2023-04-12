package islandADT.Biomes;

import java.util.Map;

import islandADT.Container.GeometryContainer;
import islandADT.GeometryWrappers.PolygonWrapper;
import islandADT.TypeWrappers.TileTypeWrapper;
import islandADT.Specifications.IslandSpecifications;

public class ForestBiomeSetter extends BiomeSetterAbstract {
    private IslandSpecifications islandSpecifications;

    public ForestBiomeSetter(IslandSpecifications islandSpecifications) {
        this.islandSpecifications = islandSpecifications;
    }

    private void calculateForestBiome(PolygonWrapper p) {
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

        TileTypeWrapper Taiga = new TileTypeWrapper("Taiga");
        TileTypeWrapper Grassland = new TileTypeWrapper("Grassland");
        TileTypeWrapper Swamp = new TileTypeWrapper("Swamp");
        TileTypeWrapper Mushroom = new TileTypeWrapper("Mushroom");
        TileTypeWrapper Rainforest = new TileTypeWrapper("Rainforest");
        TileTypeWrapper Forest = new TileTypeWrapper("Forest");
        TileTypeWrapper Bamboo = new TileTypeWrapper("Bamboo Mountain");
        
        if (coords[0] == 1) {
            if (coords[1] == 1) {
                p.setTileType(Swamp);
            }
            else if (coords[1] <= 4) {
                p.setTileType(Grassland);
            }
            else {
                p.setTileType(Taiga);
            }
        }
        else if (coords[0] == 2) {
            if (coords[1] == 1) {
                p.setTileType(Swamp);
            }
            else if (coords[1] <= 4) {
                p.setTileType(Mushroom);
            }
            else {
                p.setTileType(Taiga);
            }
        }
        else {
            if (coords[1] == 1) {
                p.setTileType(Rainforest);
            }
            else if (coords[1] <= 4) {
                p.setTileType(Forest);
            }
            else {
                p.setTileType(Bamboo);
            }
        }

    }

    public void generate(GeometryContainer geometryContainer) {
        Map<Integer, PolygonWrapper> polygons = geometryContainer.get_polygons();
        for (PolygonWrapper p: polygons.values()) {
            if (!p.isWaterOrNah() && p.isLandornah()) {
                calculateForestBiome(p);
            }
        }
    }
}
