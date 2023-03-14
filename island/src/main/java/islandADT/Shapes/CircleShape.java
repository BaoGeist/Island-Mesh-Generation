package islandADT.Shapes;

import org.locationtech.jts.geom.*;

public class CircleShape implements Shape{
    private Polygon circle;
    @Override
    public Geometry generateIsland(int radius) {
        GeometryFactory factory = new GeometryFactory();
        Coordinate center = new Coordinate(250, 250);
        int numPoints = 1000;
        Coordinate[] vertices = new Coordinate[numPoints];
        for (int i = 0; i < numPoints; i++) {
            double angle = ((double)i / numPoints) * Math.PI * 2.0;
            double x = center.x + Math.cos(angle) * radius;
            double y = center.y + Math.sin(angle) * radius;
            vertices[i] = new Coordinate(x, y);
        }
        vertices[numPoints-1] = vertices[0];
        LinearRing shell = factory.createLinearRing(vertices);
        circle = factory.createPolygon(shell, null);
        return circle;
    }
}
