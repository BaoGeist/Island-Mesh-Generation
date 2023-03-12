package islandADT.Exporter;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import meshcore.ADT.OurVertex;

import java.util.ArrayList;

public class OurVertexIsland{

    public Structs.Vertex create_geometry(boolean centroid_or_nah, double[] coords) {
        Structs.Property centroid_or_nah_p = Structs.Property.newBuilder().setKey("centroid_or_nah").setValue(String.valueOf(centroid_or_nah)).build();
        Structs.Vertex vertex = Structs.Vertex.newBuilder().setX(coords[0]).setY(coords[1]).addProperties(centroid_or_nah_p).build();
        return vertex;
    }
}
