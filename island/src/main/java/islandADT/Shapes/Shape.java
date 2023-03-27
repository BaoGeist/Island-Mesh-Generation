package islandADT.Shapes;

import islandADT.Container.GeometryContainer;
import org.locationtech.jts.geom.Geometry;

public interface Shape {

    Geometry generateIsland(int radius, GeometryContainer geometryContainer);
}
