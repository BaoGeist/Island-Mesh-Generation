import ca.mcmaster.cas.se2aa4.a2.generator.*;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        boolean debug = false;
        for(String arg: args) {
            if(arg.equals("-X")) debug = true;
        }
        ArrayList<Structs.Vertex> vertices = new ArrayList<>();
        ArrayList<Structs.Segment> segments = new ArrayList<>();
        OurMesh ourMesh = new OurMesh(500, 500, 20, 1.00f, 1, vertices,segments);
        Mesh myMesh = ourMesh.generate();
        MeshFactory factory = new MeshFactory();
        factory.write(myMesh, args[0]);
    }

}