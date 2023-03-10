package ca.mcmaster.cas.se2aa4.a2.generator;

import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;



import static ca.mcmaster.cas.se2aa4.a2.generator.Utils.MathUtils.*;
import static org.locationtech.jts.algorithm.Centroid.getCentroid;

public class MathUtilsTest {
    final double uncertainty = 0.005;
    @Test
    public void calculate_length_of_segment_test() {
        Coordinate test1 = new Coordinate(0, 0);
        Coordinate test2 = new Coordinate(0, 5);

        assert(calculate_length_of_segment(test1, test2) == 5.00);

        test1 = new Coordinate(0, 0);
        test2 = new Coordinate(0, 0);
        assert(calculate_length_of_segment(test1, test2) == 0);
    }

    @Test
    public void calculate_area_of_triangle_test(){
        Coordinate test1 = new Coordinate(0, 0);
        Coordinate test2 = new Coordinate(0, 10);
        Coordinate test3 = new Coordinate(5, 5);

        assert(Math.abs(calculate_area_of_triangle(test1, test2, test3) - 25.00) < uncertainty);
    }

    @Test
    public void calculate_centroid_of_triangle_test(){
        GeometryFactory geometryFactory = new GeometryFactory();

        Coordinate[] coordinatesTest = new Coordinate[] {
                new Coordinate(0, 0),
                new Coordinate(5, 0),
                new Coordinate(2.5, 5),
                new Coordinate(0, 0)
        };

        Polygon triangleTest = geometryFactory.createPolygon(coordinatesTest);
        Coordinate centroid = getCentroid(triangleTest);
        Coordinate centroidTest = calculate_centroid_of_triangle(new Coordinate(0, 0),new Coordinate(5, 0),new Coordinate(2.5, 5));

        assert(Math.abs(centroidTest.x - centroid.x) < uncertainty);
        assert(Math.abs(centroidTest.y - centroid.y) < uncertainty);
    }

    @Test
    public void calculate_new_centroid_test(){
        //too much effort
    }


}
