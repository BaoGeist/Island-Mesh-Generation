package ca.mcmaster.cas.se2aa4.a2.generator;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import java.util.Arrays;
import java.util.List;

public class OurSegment {
    private int[] colour_code = new int[3];
    private double[] head_coord = new double[2];
    private double[] tail_coord = new double[2];
    private Vertex head_vertex;
    private Vertex tail_vertex;

    private Segment actual_segment;

    public Structs.Segment create_segment(int ID1, int ID2, Vertex vertex1, Vertex vertex2) {
        set_segment_colour(vertex1, vertex2);
        head_vertex = vertex1;
        tail_vertex = vertex2;
        head_coord = get_coords(vertex1);
        tail_coord = get_coords(vertex2);
        return build_segment();
    }

    private int[] parse_string(String parse) {
        String[] array_return = parse.split(",", -1);
        int[] array_return_int = new int[array_return.length];
        for(int i = 0; i < array_return_int.length; i++) {
            array_return_int[i] = Integer.parseInt(array_return[i]);
        }
        return array_return_int;
    }

    private double[] get_coords(Vertex v) {
        double[] return_string = {v.getX(), v.getY()};
        return return_string;
    }


    private void set_segment_colour(Vertex vertex1, Vertex vertex2) {
        int[] first_colour = extractColor(vertex1.getPropertiesList());
        int[] second_colour = extractColor(vertex2.getPropertiesList());
        colour_code[0] = (first_colour[0] + second_colour[0]) / 2;
        colour_code[1] = (first_colour[1] + second_colour[1]) / 2;
        colour_code[2] = (first_colour[2] + second_colour[2]) / 2;
    }

    private Structs.Segment build_segment() {
        Structs.Property segment_head_coords = Structs.Property.newBuilder().setKey("head").setValue(String.format("%f,%f", head_coord[0], head_coord[1])).build();
        Structs.Property segment_tail_coords = Structs.Property.newBuilder().setKey("tail").setValue(String.format("%f,%f", tail_coord[0], tail_coord[1])).build();
        String new_colour1 = Arrays.toString(colour_code);
        Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue(new_colour1).build();
        Segment connected1 = Segment.newBuilder().addProperties(segment_tail_coords).addProperties(segment_head_coords).addProperties(color).build();
        return connected1;
    }

    private int[] extractColor(List<Property> properties) {
        String val = null;
        for(Property p: properties) {
            if (p.getKey().equals("rgb_color")) {
//                System.out.println(p.getValue());
                val = p.getValue();
            }
        }
        String[] raw = val.split(",");
        int red = Integer.parseInt(raw[0].replace("[","").replace(" ", ""));
        int green = Integer.parseInt(raw[1].replace(" ", ""));
        int blue = Integer.parseInt(raw[2].replace("]","").replace(" ", ""));
        return new int[]{red, green, blue};
    }
}