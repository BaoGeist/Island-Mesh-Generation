package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import org.junit.jupiter.api.Test;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OurPolygonTest {

    @Test
    public void create_polygon_test() {
        OurVertex vertexFactory = new OurVertex();
        Vertex vertexTestOne = vertexFactory.makeVertex(0.00, 0.00, 0);
        Vertex vertexTestTwo = vertexFactory.makeVertex(3.00, 6.00, 1);
        Vertex vertexTestThree = vertexFactory.makeVertex(2.00, 2.00, 2);

        OurSegment segmentFactory = new OurSegment();
        ArrayList<Vertex> inputVertex = new ArrayList<>();
        inputVertex.add(vertexTestOne);
        inputVertex.add(vertexTestTwo);
        Segment segmentTestOne = segmentFactory.create_segment(inputVertex, 1.00f, 1, 0);
        inputVertex.clear();
        inputVertex.add(vertexTestTwo);
        inputVertex.add(vertexTestThree);
        Segment segmentTestTwo = segmentFactory.create_segment(inputVertex, 1.00f, 1, 1);
        inputVertex.clear();
        inputVertex.add(vertexTestThree);
        inputVertex.add(vertexTestOne);
        Segment segmentTestThree = segmentFactory.create_segment(inputVertex, 1.00f, 1, 2);
        ArrayList<Segment> segmentListTest = new ArrayList<>();
        segmentListTest.add(segmentTestOne);
        segmentListTest.add(segmentTestTwo);
        segmentListTest.add(segmentTestThree);
        OurPolygon polygonFactory = new OurPolygon();
        assertNotNull(polygonFactory.create_polygon(0, 0, segmentListTest));

    }
}
