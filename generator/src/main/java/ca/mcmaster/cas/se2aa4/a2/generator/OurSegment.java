package ca.mcmaster.cas.se2aa4.a2.generator;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;

import java.util.Arrays;

public class OurSegment {
    private int[] colour_code = new int[3];
    private double[] head_coord = new double[2];
    private double[] tail_coord = new double[2];
<<<<<<< Updated upstream
    private OurVertex head_vertex;
    private OurVertex tail_vertex;
=======
    private double[] middle_coord = new double[2];
    private Vertex head_vertex;
    private Vertex tail_vertex;
>>>>>>> Stashed changes

    private Segment actual_segment;

    public Structs.Segment create_segment(int ID1, int ID2, OurVertex vertex1, OurVertex vertex2) {
        set_segment_colour(vertex1, vertex2);
        head_vertex = vertex1;
        tail_vertex = vertex2;
        head_coord = get_coords(vertex1);
        tail_coord = get_coords(vertex2);
        return build_segment();
    }

    private double[] get_coords(OurVertex v) {
        double[] return_string = {v.getX(), v.getY()};
        return return_string;
    }


    private void set_segment_colour(OurVertex vertex1, OurVertex vertex2) {
        colour_code[0] = (vertex1.get_colour()[0] + vertex2.get_colour()[0]) / 2;
        colour_code[1] = (vertex1.get_colour()[1] + vertex2.get_colour()[1]) / 2;
        colour_code[2] = (vertex1.get_colour()[2] + vertex2.get_colour()[2]) / 2;
    }

    // TODO round values inside class and not at build_segment()
    private void set_segment_middle() {
        middle_coord[0] = (head_coord[0] + tail_coord[0])/2;
        middle_coord[1] = (head_coord[1] + tail_coord[1])/2;
    }

    private Structs.Segment build_segment() {
        Property segment_head_coords = Structs.Property.newBuilder().setKey("head").setValue(String.format("%.2f,%.2f", head_coord[0], head_coord[1])).build();
        Property segment_tail_coords = Structs.Property.newBuilder().setKey("tail").setValue(String.format("%.2f,%.2f", tail_coord[0], tail_coord[1])).build();
        Property segment_middle_coords = Structs.Property.newBuilder().setKey("middle").setValue(String.format("%.2f,%.2f", middle_coord[0], middle_coord[1])).build();
        String new_colour1 = Arrays.toString(colour_code);
<<<<<<< Updated upstream
        Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue(new_colour1).build();
        Segment connected1 = Segment.newBuilder().addProperties(segment_tail_coords).addProperties(segment_head_coords).addProperties(color).build();
=======
        Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue(new_colour1).build();
        Segment connected1 = Segment.newBuilder().addProperties(segment_tail_coords).addProperties(segment_head_coords).addProperties(segment_middle_coords).addProperties(color).build();
        return connected1;
>>>>>>> Stashed changes
    }

}