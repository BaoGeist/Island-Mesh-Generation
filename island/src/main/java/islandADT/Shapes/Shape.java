package islandADT.Shapes;

import org.locationtech.jts.awt.PolygonShape;
import org.locationtech.jts.geom.Geometry;

public interface Shape {

    public Geometry generateIsland(int radius);

}
