package islandADT.Water;

import islandADT.GeometryContainer;
import islandADT.RandomSeed;
import islandADT.Wrappers.PolygonWrapper;

import java.util.Map;

public abstract class WaterTile implements WaterBody{
    private PolygonWrapper random_start(GeometryContainer geometryContainer) {
        Map<Integer, PolygonWrapper> polygons = geometryContainer.get_polygons();
        for(PolygonWrapper p: polygons.values()) {

        }
    }
}
