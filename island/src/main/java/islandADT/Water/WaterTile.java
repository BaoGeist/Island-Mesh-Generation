package islandADT.Water;

import islandADT.Generator.RandomSeed;
import islandADT.GeometryContainer;
import islandADT.Wrappers.PolygonWrapper;

import java.util.Map;

public abstract class WaterTile implements WaterBody{
    protected int random_start(GeometryContainer geometryContainer) {


        while(true) {
            int next = RandomSeed.randomInt(geometryContainer.get_polygons().size());
            if(geometryContainer.polygon_no_ocean_neighbours(next)) {
                return next;
            }
        }

    }
}
