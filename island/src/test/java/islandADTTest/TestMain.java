package islandADTTest;


import islandADTTest.tests.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class TestMain {

    List<Class> testClasses = new ArrayList<>();
    @Test
    public void runTests() {
        System.out.println("Testing");

        addTestClasses();
        TestReport report  = new TestReport();
        TestFactory scanner = new TestFactory();

        for (Class testClass: testClasses){
            scanner.build(testClass).run(report);
        }

        System.out.println(report);
    }

    private void addTestClasses(){
        testClasses.add(IslandGeneratorTest.class);
        testClasses.add(MathUtilsTest.class);
        testClasses.add(RiverTest.class);
        testClasses.add(ShapeTest.class);
        testClasses.add(ConcurrentModificationException.class);
        testClasses.add(HeightRenderTest.class);
        testClasses.add(NormalRenderTest.class);
        testClasses.add(MoistureRenderTest.class);
    }

}
