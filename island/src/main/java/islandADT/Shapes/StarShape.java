package islandADT.Shapes;

import org.locationtech.jts.geom.*;

import java.util.Random;

public class StarShape implements Shape{

    private Polygon Star;

    public Geometry generateIsland(int radius) {
        Random random = new Random();
        GeometryFactory factory = new GeometryFactory();
        Coordinate center = new Coordinate(250, 250);
        int numPoints = random.nextInt(5, 12);  // number of points in the star
        double innerRadius = radius/2;  // radius of the inner points
        Coordinate[] vertices = new Coordinate[numPoints * 2+1];
        for (int i = 0; i <= numPoints * 2; i++) {
            double angle = ((double)i / numPoints) * Math.PI;
            double r = i % 2 == 0 ? radius : innerRadius;
            double x = center.x + Math.cos(angle + Math.PI/numPoints) * r;
            double y = center.y + Math.sin(angle + Math.PI/numPoints) * r;
            vertices[i] = new Coordinate(x, y);
        }
        vertices[numPoints * 2] = vertices[0]; // close the ring by adding the first vertex again
        LinearRing shell = factory.createLinearRing(vertices);
        Star = factory.createPolygon(shell, null);
        return Star;
    }
}
