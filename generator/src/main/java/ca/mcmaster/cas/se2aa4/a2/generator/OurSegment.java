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
    private double[] middle_coord = new double[2];
    private Vertex head_vertex;
    private Vertex tail_vertex;
    private Segment actual_segment;
    private int thickness;
    private float alpha;
    private int id;

    public Structs.Segment create_segment(int ID1, int ID2, Vertex vertex1, Vertex vertex2, float alpha_entry, int thickness_entry, int IDself) {
        set_segment_colour(vertex1, vertex2);
        head_vertex = vertex1;
        tail_vertex = vertex2;
        head_coord = get_coords(vertex1);
        tail_coord = get_coords(vertex2);
        set_segment_middle();
        id = IDself;
        alpha = alpha_entry;
        thickness = thickness_entry;
        return build_segment();
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
        Segment connected1 = Segment.newBuilder().addProperties(segment_tail_coords).addProperties(segment_head_coords).addProperties(segment_middle_coords).addProperties(color).addProperties(thicc).addProperties(a).addProperties(segment_id).build();
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        OurSegment comparesegment = (OurSegment) obj;
        return (head_vertex == comparesegment.get_head_vertex() && tail_vertex == comparesegment.get_tail_vertex()) || (tail_vertex == comparesegment.get_head_vertex() && head_vertex == comparesegment.get_tail_vertex());
    }
}