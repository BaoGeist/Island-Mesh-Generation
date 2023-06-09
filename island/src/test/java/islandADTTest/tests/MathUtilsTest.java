package islandADTTest.tests;

import islandADTTest.tags.Before;
import islandADTTest.tags.TestActual;

import java.util.ArrayList;
import java.util.List;

import static islandADT.Utils.MathUtils.*;
import static islandADTTest.tests.Assertions.assertTrue;

public class MathUtilsTest {
    List<Integer> integers = new ArrayList<>();
    double[] coords1 = new double[2];
    double[] coords2 = new double[2];

    double[] coords = new double[2];

    @Before
    public void SetUpIntegersAndCoords() {
        integers.add(0);
        integers.add(10);
        integers.add(20);
        integers.add(10);
        integers.add(5);
        coords1[0] = 2;
        coords1[1] = 4;
        coords2[0] = 2;
        coords2[1] = 2;
        coords[0] = 1;
        coords[1] = Math.PI;
    }
    @TestActual
    public void test_average() {
        assertTrue(average(integers) == 9);
    }

    @TestActual
    public void test_distance_calc(){assertTrue(distance_between_points(coords1, coords2) == 2);}

    @TestActual
    public void test_cartesian_from_polar(){assertTrue(cartesian_from_polar(coords)[0] == 249 && cartesian_from_polar(coords)[1] == 250);}
}
