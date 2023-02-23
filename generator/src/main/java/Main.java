import ca.mcmaster.cas.se2aa4.a2.generator.*;
import ca.mcmaster.cas.se2aa4.a2.generator.*;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ArrayList;

public class Main {

    // generate irregular and regular mesh at the same time
    public static void main(String[] args) throws IOException {
        ArrayList<Structs.Vertex> vertices = new ArrayList<>();
        ArrayList<Structs.Segment> horizontal_segments = new ArrayList<>();
        ArrayList<Structs.Segment> vertical_segments = new ArrayList<>();
        ArrayList<Structs.Polygon> polygons = new ArrayList<>();
        OurMesh generator = new OurMesh(500, 500, 25, 1.00f, 1, vertices,horizontal_segments, vertical_segments, polygons);
        OurIrregular generator2 = new OurIrregular();
        Mesh myMesh2 = generator2.generate();
        Mesh myMesh = generator.generate();
        MeshFactory factory = new MeshFactory();
        // this line decides whether myMesh or myMesh2 is written
        factory.write(myMesh, args[0]);
    }

}