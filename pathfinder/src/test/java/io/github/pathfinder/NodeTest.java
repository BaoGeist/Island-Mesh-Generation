package io.github.pathfinder;

import io.github.pathfinder.Graphs.Parts.Edge;
import io.github.pathfinder.Graphs.Parts.Node;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NodeTest {
    Node testNode;
    private void set_up() {
        testNode = new Node(0);
        testNode.setCost(3);
    }

    @Test
    public void cost_id_test() {
        set_up();

        assertEquals(3, testNode.getCost());
        assertEquals(0, testNode.getId());
    }

    @Test
    public void property_test() {
        set_up();

        int[] testInt = {3, 3, 5};
        double[] testDouble = {0.03, 3.02, 7.03};
        String testString = "this is a property";
        testNode.add_property("intArray", testInt);
        testNode.add_property("doubleArray", testDouble);
        testNode.add_property("String", testString);

        Map<String,Object> property = testNode.get_properties();

        assertEquals(testInt, property.get("intArray"));
        assertEquals(testDouble, property.get("doubleArray"));
        assertEquals(testString, property.get("String"));
    }

    @Test
    public void equals_test() {
        set_up();

        Node newNode = new Node(0);

        assertTrue(testNode.equals(newNode));
    }
}
