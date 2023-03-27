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
            if (p.getTileType().isEquals(Desert)) {
                p.setResource("sand");
            }
            else if (p.getTileType().isEquals(Taiga) || p.getTileType().isEquals(Forest) || p.getTileType().isEquals(Rainforest)) {
                p.setResource("wood");
            }
            else if (p.getTileType().isEquals(Tundra) || p.getTileType().isEquals(Mountain)) {
                p.setResource("snow"); 
            }
            else if (p.getTileType().isEquals(Savannah) || p.getTileType().isEquals(Grassland)) {
                p.setResource("livestock");
            }
            else if (p.getTileType().isEquals(Swamp)) {
                p.setResource("berries"); 
            }
            else if (p.getTileType().isEquals(Mushroom)) {
                p.setResource("mushrooms"); //like we said we like minecraft
            }
            else {
                p.setResource("bamboo");
            }
        }
    }

}
