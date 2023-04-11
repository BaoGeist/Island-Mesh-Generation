package islandADT.Exporter;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import meshcore.ADT.OurVertex;

import java.util.ArrayList;

public class OurVertexIsland{

    public Structs.Vertex create_geometry(boolean centroid_or_nah, double[] coords, int height, int population) {
        Structs.Property centroid_or_nah_p = Structs.Property.newBuilder().setKey("centroid_or_nah").setValue(String.valueOf(centroid_or_nah)).build();
        Structs.Property population_p = Structs.Property.newBuilder().setKey("size").setValue(String.valueOf(population)).build();
        Structs.Property height_p = Structs.Property.newBuilder().setKey("height").setValue(String.valueOf(height)).build();
        Structs.Vertex vertex = Structs.Vertex.newBuilder().setX(coords[0]).setY(coords[1]).addProperties(centroid_or_nah_p).addProperties(population_p).build();
        return vertex;
    }
}
