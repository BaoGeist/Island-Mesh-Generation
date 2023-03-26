package islandADT.Resources;

import islandADT.BiomeSetter.*;
import islandADT.GeometryContainer;
import islandADT.GeometryWrappers.PolygonWrapper;
import islandADT.TypeWrappers.*;

import java.util.Map;

public class Resources {
    String resourceType;
    
    public void setResources(GeometryContainer geometryContainer) {
        Map<Integer, PolygonWrapper> polygons= geometryContainer.get_polygons();

        TileTypeWrapper Desert = new TileTypeWrapper("Desert");
        TileTypeWrapper Taiga = new TileTypeWrapper("Taiga");
        TileTypeWrapper Tundra = new TileTypeWrapper("Tundra");
        TileTypeWrapper Savannah = new TileTypeWrapper("Savannah");
        TileTypeWrapper Grassland = new TileTypeWrapper("Grassland");
        TileTypeWrapper Swamp = new TileTypeWrapper("Swamp");
        TileTypeWrapper Mushroom = new TileTypeWrapper("Mushroom");
        TileTypeWrapper Mountain = new TileTypeWrapper("Mountain");
        TileTypeWrapper Rainforest = new TileTypeWrapper("Rainforest");
        TileTypeWrapper Forest = new TileTypeWrapper("Forest");
        TileTypeWrapper Bamboo = new TileTypeWrapper("Bamboo Mountain");


        for (PolygonWrapper p: polygons.values()) {
            if (p.getTileType() == Desert) {
                resourceType = "sand";
            }
            else if (p.getTileType() == Taiga || p.getTileType() == Forest || p.getTileType() == Rainforest) {
                resourceType = "wood";
            }
            else if (p.getTileType() == Tundra || p.getTileType() == Mountain) {
                resourceType = "snow"; 
            }
            else if (p.getTileType() == Savannah || p.getTileType() == Grassland) {
                resourceType = "livestock";
            }
            else if (p.getTileType() == Swamp) {
                resourceType = "berries"; 
            }
            else if (p.getTileType() == Mushroom) {
                resourceType = "mushrooms"; //like we said we like minecraft
            }
            else {
                resourceType = "bamboo";
            }
        }
    }

    public String getResources() {
        return resourceType;
    }

}
