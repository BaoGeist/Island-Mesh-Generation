package islandADT.Water;

import islandADT.Generator.RandomSeed;
import islandADT.Container.GeometryContainer;
import islandADT.Container.GeometryContainerCalculator;

public abstract class WaterSourceFactory implements WaterBody{
    protected int random_start(GeometryContainer geometryContainer) {
        RandomSeed instanceRandom = RandomSeed.getInstance();

        GeometryContainerCalculator librarian = new GeometryContainerCalculator();
        while(true) {
            int next = instanceRandom.randomInt(geometryContainer.get_polygons().size());
            if(librarian.polygon_no_ocean_neighbours(geometryContainer, next)) {
                return next;
            }
        }

    }
}
