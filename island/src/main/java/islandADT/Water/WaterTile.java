package islandADT.Water;

import islandADT.Generator.RandomSeed;
import islandADT.GeometryContainer;
import islandADT.GeometryContainerCalculator;

public abstract class WaterTile implements WaterBody{
    protected int random_start(GeometryContainer geometryContainer) {
        GeometryContainerCalculator librarian = new GeometryContainerCalculator();
        while(true) {
            int next = RandomSeed.randomInt(geometryContainer.get_polygons().size());
            if(librarian.polygon_no_ocean_neighbours(geometryContainer, next)) {
                return next;
            }
        }

    }

    protected void moisturize(GeometryContainer geometryContainer) {

    }
}
