package islandADT.BiomeSetter;

import java.util.Map;

import islandADT.GeometryContainer;
import islandADT.GeometryWrappers.PolygonWrapper;
import islandADT.TypeWrappers.TileTypeWrapper;
import islandADT.Specifications.IslandSpecifications;

public class ForestBiomeSetter implements BiomeInterface {
    private IslandSpecifications islandSpecifications;

    public ForestBiomeSetter(IslandSpecifications islandSpecifications) {
        this.islandSpecifications = islandSpecifications;
    }

    private void calculateForestBiome(PolygonWrapper p) {
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

    public void setBiomes(GeometryContainer geometryContainer) {
        Map<Integer, PolygonWrapper> polygons = geometryContainer.get_polygons();
        for (PolygonWrapper p: polygons.values()) {
            if (!p.isWaterOrNah() && p.isLandornah()) {
                calculateForestBiome(p);
            }
        }
    }
}
