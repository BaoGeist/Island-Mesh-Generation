package islandADT.Exporter;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import meshcore.ADT.OurVertex;

import java.util.ArrayList;
import java.util.Arrays;

public class OurVertexIsland{

    public Structs.Vertex create_geometry(boolean centroid_or_nah, double[] coords, int height, int population, int[] color) {
        Structs.Property centroid_or_nah_p = Structs.Property.newBuilder().setKey("centroid_or_nah").setValue(String.valueOf(centroid_or_nah)).build();
        Structs.Property population_p = Structs.Property.newBuilder().setKey("size").setValue(String.valueOf(population)).build();
        Structs.Property color_p = Structs.Property.newBuilder().setKey("rgb_color").setValue(Arrays.toString(color)).build();
        Structs.Vertex vertex = Structs.Vertex.newBuilder().setX(coords[0]).setY(coords[1]).addProperties(centroid_or_nah_p).addProperties(population_p).addProperties(color_p).build();
        return vertex;
    }
}
