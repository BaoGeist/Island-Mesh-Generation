package ca.mcmaster.cas.se2aa4.a2.generator;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static ca.mcmaster.cas.se2aa4.a2.generator.PropertyUtils.*;

public class OurSegment implements OurGeometryFactory{
    private int[] colour_code = new int[3];
    private double[] head_coord = new double[2];
    private double[] tail_coord = new double[2];
    private double[] middle_coord = new double[2];
    private Vertex head_vertex;
    private Vertex tail_vertex;
    private Segment actual_segment;
    private int thickness;
    private float alpha;
    private int id;

    private int v1id, v2id;

    @Override
    public ArrayList<Object> create_geometry( int idSelf, ArrayList<Object> arrayArgs, float alpha, int thickness, int misc) {
        ArrayList<Vertex> vertices = arrayArgs.stream()
                .map(s -> (Vertex) s)
                .collect(Collectors.toCollection(ArrayList::new));

        set_segment_colour(vertices.get(0), vertices.get(1));
        head_vertex = vertices.get(0);
        tail_vertex = vertices.get(1);
        head_coord = get_coords(vertices.get(0));
        tail_coord = get_coords(vertices.get(1));
        set_segment_middle();
        id = idSelf;
        this.alpha = alpha;
        this.thickness = thickness;
        v1id = extractID(vertices.get(0).getPropertiesList());
        v2id = extractID(vertices.get(1).getPropertiesList());
        ArrayList<Object> return_array= new ArrayList<>();
        return_array.add(build_segment());
        return return_array;
    }

    private double[] get_coords(Vertex v) {
        double[] return_string = {v.getX(), v.getY()};
        return return_string;
    }

    private Vertex get_head_vertex() {
        return head_vertex;
    }

    private Vertex get_tail_vertex() {
        return tail_vertex;
    }


    private void set_segment_colour(Vertex vertex1, Vertex vertex2) {
        int[] first_colour = extractColor(vertex1.getPropertiesList());
        int[] second_colour = extractColor(vertex2.getPropertiesList());
        colour_code[0] = (first_colour[0] + second_colour[0]) / 2;
        colour_code[1] = (first_colour[1] + second_colour[1]) / 2;
        colour_code[2] = (first_colour[2] + second_colour[2]) / 2;
    }

    private void set_segment_middle() {
        int i;
        for (i = 0; i < 2; i++) {
            double middle_coord_placeholder = (head_coord[i] + tail_coord[i])/2;
            String middle_coord_string = String.format("%.2f", middle_coord_placeholder);
            middle_coord_placeholder = Double.parseDouble(middle_coord_string);
            middle_coord[i] = middle_coord_placeholder;
        }
    }

    private Structs.Segment build_segment() {
        Property segment_head_coords = Property.newBuilder().setKey("head").setValue(String.format("%.2f,%.2f", head_coord[0], head_coord[1])).build();
        Property segment_tail_coords = Property.newBuilder().setKey("tail").setValue(String.format("%.2f,%.2f", tail_coord[0], tail_coord[1])).build();
        Property segment_middle_coords = Property.newBuilder().setKey("middle").setValue(String.format("%.2f,%.2f", middle_coord[0], middle_coord[1])).build();
        Property segment_id = Property.newBuilder().setKey("id").setValue(Integer.toString(id)).build();
        Property thicc = Property.newBuilder().setKey("thicc").setValue(Integer.toString(thickness)).build();
        Property a = Property.newBuilder().setKey("alpha").setValue(Float.toString(alpha)).build();
        String new_colour1 = Arrays.toString(colour_code);
        Property color = Property.newBuilder().setKey("rgb_color").setValue(new_colour1).build();
        Segment connected1 = Segment.newBuilder().setV1Idx(v1id).setV2Idx(v2id).addProperties(segment_tail_coords).addProperties(segment_head_coords).addProperties(segment_middle_coords).addProperties(color).addProperties(thicc).addProperties(a).addProperties(segment_id).build();
        return connected1;
    }
}