package islandADT.Water;

import islandADT.Generator.RandomSeed;
import islandADT.GeometryContainer;
import islandADT.GeometryContainerLibrarian;

public abstract class WaterTile implements WaterBody{
    protected int random_start(GeometryContainer geometryContainer) {
        GeometryContainerLibrarian librarian = new GeometryContainerLibrarian();
        while(true) {
            int next = RandomSeed.randomInt(geometryContainer.get_polygons().size());
            if(librarian.polygon_no_ocean_neighbours(geometryContainer, next)) {
                return next;
            }
        }

    }
}
