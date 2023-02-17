package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DotGenTest {

    @Test
    public void meshIsNotNull() {
        ArrayList<Structs.Vertex> vertices = new ArrayList<>();
        ArrayList<Structs.Segment> segments = new ArrayList<>();
        OurMesh generator = new OurMesh(500, 500, 20, 1.00f, 1, vertices,segments);
        Structs.Mesh aMesh = generator.generate();
        assertNotNull(aMesh);
    }

}
