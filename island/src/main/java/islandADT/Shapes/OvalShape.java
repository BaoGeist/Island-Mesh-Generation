package islandADT.Shapes;

import org.locationtech.jts.geom.*;

import java.util.Random;

public class OvalShape implements Shape{

    private Polygon Oval;

    public Geometry generateIsland(int radius) {
        Random random = new Random();
        GeometryFactory factory = new GeometryFactory();
        Coordinate center = new Coordinate(250, 250);
        int numPoints = 1000;
        double radiusX = random.nextDouble(radius-50, radius+50);
        double radiusY = (radiusX > radius ? radiusX-50 : radiusX+50);
        Coordinate[] vertices = new Coordinate[numPoints];
        for (int i = 0; i < numPoints; i++) {
            double angle = ((double)i / numPoints) * Math.PI * 2.0;
            double x = center.x + Math.cos(angle) * radiusX;
            double y = center.y + Math.sin(angle) * radiusY;
            vertices[i] = new Coordinate(x, y);
        }
        vertices[numPoints-1] = vertices[0]; // close the ring by adding the first vertex again
        LinearRing shell = factory.createLinearRing(vertices);
        Oval = factory.createPolygon(shell, null);
        return Oval;
    }
}
