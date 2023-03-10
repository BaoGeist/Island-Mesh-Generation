package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.generator.ADT.OurPolygon;
import ca.mcmaster.cas.se2aa4.a2.generator.ADT.OurSegment;
import ca.mcmaster.cas.se2aa4.a2.generator.ADT.OurVertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import org.junit.jupiter.api.Test;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class OurPolygonTest {

    @Test
    public void create_geometry_polygon_test() {
        OurVertex vertexFactory = new OurVertex();
        ArrayList<Object> send_array = new ArrayList<>();
        send_array.add((float) 0.00);
        send_array.add((float) 0.00);
        ArrayList<Object> returned_array = vertexFactory.create_geometry(0, send_array, 1.00f, 1, 1);
        Vertex vertexTestOne = (Structs.Vertex) returned_array.get(0);

        send_array.clear();
        send_array.add((float) 3.00);
        send_array.add((float) 6.00);
        returned_array = vertexFactory.create_geometry(1, send_array, 1.00f, 1, 1);
        Vertex vertexTestTwo = (Structs.Vertex) returned_array.get(0);

        send_array.clear();
        send_array.add((float) 2.00);
        send_array.add((float) 2.00);
        returned_array = vertexFactory.create_geometry(2, send_array, 1.00f, 1, 1);
        Vertex vertexTestThree = (Structs.Vertex) returned_array.get(0);

        OurSegment segmentFactory = new OurSegment();
        ArrayList<Object> inputVertex = new ArrayList<>();
        inputVertex.add(vertexTestOne);
        inputVertex.add(vertexTestTwo);
        returned_array = segmentFactory.create_geometry(0, inputVertex, 1.00f, 1, 0);
        Segment segmentTestOne = (Structs.Segment) returned_array.get(0);

        inputVertex.clear();
        inputVertex.add(vertexTestTwo);
        inputVertex.add(vertexTestThree);
        returned_array = segmentFactory.create_geometry(1, inputVertex, 1.00f, 1, 0);
        Segment segmentTestTwo = (Structs.Segment) returned_array.get(0);

        inputVertex.clear();
        inputVertex.add(vertexTestThree);
        inputVertex.add(vertexTestOne);
        returned_array = segmentFactory.create_geometry(2, inputVertex, 1.00f, 1, 0);
        Segment segmentTestThree = (Structs.Segment) returned_array.get(0);

        ArrayList<Object> segmentListTest = new ArrayList<>();
        segmentListTest.add(segmentTestOne);
        segmentListTest.add(segmentTestTwo);
        segmentListTest.add(segmentTestThree);
        OurPolygon polygonFactory = new OurPolygon();
        Structs.Polygon polygonTest = (Structs.Polygon) polygonFactory.create_geometry(0, segmentListTest, 1.00f, 1, 0).get(0);
        assertNotNull(polygonTest);

    }
}
