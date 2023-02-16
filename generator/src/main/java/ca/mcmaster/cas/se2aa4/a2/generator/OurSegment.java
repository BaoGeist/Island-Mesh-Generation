package ca.mcmaster.cas.se2aa4.a2.generator;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;

import java.util.Arrays;

public class OurSegment {
    private int[] colour_code = new int[3];
    private double[] head_coord = new double[2];
    private double[] tail_coord = new double[2];
    private OurVertex head_vertex;
    private OurVertex tail_vertex;

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

    private Structs.Segment build_segment() {
        Structs.Property segment_head_coords = Structs.Property.newBuilder().setKey("head").setValue(String.format("%f,%f", head_coord[0], head_coord[1])).build();
        Structs.Property segment_tail_coords = Structs.Property.newBuilder().setKey("tail").setValue(String.format("%f,%f", tail_coord[0], tail_coord[1])).build();
        String new_colour1 = Arrays.toString(colour_code);
        Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue(new_colour1).build();
        Segment connected1 = Segment.newBuilder().addProperties(segment_tail_coords).addProperties(segment_head_coords).addProperties(color).build();
    }

}