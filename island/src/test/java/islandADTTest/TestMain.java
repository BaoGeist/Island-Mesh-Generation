package islandADTTest;


import islandADTTest.tests.MathUtilsTest;
import org.junit.jupiter.api.Test;

import static islandADTTest.Assertions.assertEquals;

public class TestMain {
    @Test
    public void runTests() {
        System.out.println("Testing");

        TestReport report  = new TestReport();
        TestFactory scanner = new TestFactory();
        scanner.build(IslandGeneratorTest.class).run(report);
        scanner.build(MathUtilsTest.class).run(report);
        System.out.println(report);
    }
}
