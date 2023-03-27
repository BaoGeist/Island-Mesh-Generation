package islandADT.Resources;

import islandADT.Container.GeometryContainer;
import islandADT.GeometryWrappers.PolygonWrapper;
import islandADT.TypeWrappers.*;
import islandADT.Specifications.IslandSpecifications;

import java.util.Map;

public class Resources {
    private IslandSpecifications islandSpecifications;

    public Resources(IslandSpecifications islandSpecifications) {
        this.islandSpecifications = islandSpecifications;
    }
    
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
            if (p.getTileType().equals(Desert)) {
                p.setResource("sand");
            }
            else if (p.getTileType().equals(Taiga) || p.getTileType().equals(Forest) || p.getTileType().equals(Rainforest)) {
                p.setResource("wood");
            }
            else if (p.getTileType().equals(Tundra) || p.getTileType().equals(Mountain)) {
                p.setResource("snow"); 
            }
            else if (p.getTileType().equals(Savannah) || p.getTileType().equals(Grassland)) {
                p.setResource("livestock");
            }
            else if (p.getTileType().equals(Swamp)) {
                p.setResource("berries"); 
            }
            else if (p.getTileType().equals(Mushroom)) {
                p.setResource("mushrooms"); //like we said we like minecraft
            }
            else {
                p.setResource("bamboo");
            }
        }
    }

}
