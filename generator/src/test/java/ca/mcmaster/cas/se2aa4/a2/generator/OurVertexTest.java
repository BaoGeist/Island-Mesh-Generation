package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.generator.ADT.OurVertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OurVertexTest {
    public void create_geometry_segment_test() {
        OurVertex vertexFactory = new OurVertex();
        ArrayList<Object> send_array = new ArrayList<>();
        send_array.add((float) 0.00);
        send_array.add((float) 0.00);
        ArrayList<Object> returned_array = vertexFactory.create_geometry(0, send_array, 1.00f, 1, 1);
        Structs.Vertex vertexTest = (Structs.Vertex) returned_array.get(0);


        assertNotNull(vertexTest);

    }

}
