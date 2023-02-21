package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DotGenTest {

    @Test
    public void meshIsNotNull() {
        ArrayList<Structs.Vertex> vertices = new ArrayList<>();
        ArrayList<Structs.Segment> horizontal_segments = new ArrayList<>();
        ArrayList<Structs.Segment> vertical_segments = new ArrayList<>();
        ArrayList<Structs.Polygon> polygons = new ArrayList<>();
        OurMesh generator = new OurMesh(500, 500, 25, 1.00f, 1, vertices,horizontal_segments, vertical_segments, polygons);
        Structs.Mesh aMesh = generator.generate();
        assertNotNull(aMesh);
    }

}
