package islandADT.Shapes;

import islandADT.GeometryContainer;
import org.locationtech.jts.awt.PolygonShape;
import org.locationtech.jts.geom.Geometry;

public interface Shape {

    Geometry generateIsland(int radius, GeometryContainer geometryContainer);
}
