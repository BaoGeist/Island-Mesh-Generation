package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.generator.ADT.OurSegment;
import ca.mcmaster.cas.se2aa4.a2.generator.ADT.OurVertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OurSegmentTest {
    @Test
    public void create_geometry_segment_test() {
        OurVertex vertexFactory = new OurVertex();
        ArrayList<Object> send_array = new ArrayList<>();
        send_array.add((float) 0.00);
        send_array.add((float) 0.00);
        ArrayList<Object> returned_array = vertexFactory.create_geometry(0, send_array, 1.00f, 1, 1);
        Structs.Vertex vertexTestOne = (Structs.Vertex) returned_array.get(0);

        send_array.clear();
        send_array.add((float) 3.00);
        send_array.add((float) 6.00);
        returned_array = vertexFactory.create_geometry(1, send_array, 1.00f, 1, 1);
        Structs.Vertex vertexTestTwo = (Structs.Vertex) returned_array.get(0);

        OurSegment segmentFactory = new OurSegment();
        ArrayList<Object> inputVertex = new ArrayList<>();
        inputVertex.add(vertexTestOne);
        inputVertex.add(vertexTestTwo);
        returned_array = segmentFactory.create_geometry(0, inputVertex, 1.00f, 1, 0);
        Structs.Segment segmentTest = (Structs.Segment) returned_array.get(0);

        assertNotNull(segmentTest);

    }
}
