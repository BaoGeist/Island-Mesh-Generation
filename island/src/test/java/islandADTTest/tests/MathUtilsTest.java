package islandADTTest.tests;

import islandADTTest.tags.Before;
import islandADTTest.tags.TestActual;

import java.util.ArrayList;
import java.util.List;

import static islandADT.Utils.MathUtils.average;
import static islandADTTest.Assertions.assertTrue;

public class MathUtilsTest {
    List<Integer> integers = new ArrayList<>();

    @Before
    public void SetUpIntegers() {
        integers.add(0);
        integers.add(10);
        integers.add(20);
        integers.add(10);
        integers.add(5);
    }

    @TestActual
    public void test_average() {
        assertTrue(average(integers) == 9);
    }
}
