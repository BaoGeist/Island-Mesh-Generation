package io.github.pathfinder;

import io.github.pathfinder.Graphs.Parts.Edge;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EdgeTest {
    Edge testEdge;
    private void set_up() {
        testEdge = new Edge(0, 5);
    }

    @Test
    public void property_test() {
        set_up();

        int[] testInt = {3, 3, 5};
        double[] testDouble = {0.03, 3.02, 7.03};
        String testString = "this is a property";
        testEdge.add_property("intArray", testInt);
        testEdge.add_property("doubleArray", testDouble);
        testEdge.add_property("String", testString);

        Map<String,Object> property = testEdge.get_properties();

        assertEquals(testInt, property.get("intArray"));
        assertEquals(testDouble, property.get("doubleArray"));
        assertEquals(testString, property.get("String"));
    }
}
