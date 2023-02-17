package ca.mcmaster.cas.se2aa4.a2.generator;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;

import java.util.ArrayList;
import java.util.List;


public class OurPolygon {
    Vertex middle_vertex;
    ArrayList<Segment> segments_group = new ArrayList<>();
    ArrayList<Integer> segments_id = new ArrayList<Integer>();
    private int thickness = 1;
    private float alpha = 1;

    public Polygon create_polygon(Segment... segments) {
        for(Segment segment: segments) {
            segments_group.add(segment);
            segments_id.add(extractID(segment.getPropertiesList()));
        }
        middle_vertex = create_middle_vertex();
        return build_polygon();
    }

    private Vertex create_middle_vertex() {
        int totalx = 0, totaly = 0, count = 0;
        for(Segment segment: segments_group) {
            totalx += extractSegmentMiddle(segment.getPropertiesList())[0];
            totaly += extractSegmentMiddle(segment.getPropertiesList())[1];
            count++;
        }
        OurVertex v = new OurVertex();
        return v.makeCentroidVertex(totalx/count, totalx/count);
    }

    private Polygon build_polygon() {
        Property thicc = Property.newBuilder().setKey("thicc").setValue(Integer.toString(thickness)).build();
        Property a = Property.newBuilder().setKey("alpha").setValue(Float.toString(alpha)).build();
        Polygon p = Polygon.newBuilder().addAllSegmentIdxs(segments_id).setCentroidIdx(extractID(middle_vertex.getPropertiesList())).addProperties(thicc).addProperties(a).build();
        return p;
    }

    private int[] extractSegmentMiddle(List<Property> properties) {
        String val = null;
        for(Property p: properties) {
            if (p.getKey().equals("middle")) {
//                System.out.println(p.getValue());
                val = p.getValue();
            }
        }
        return parse_string_to_array_int(val);
    }

    private int extractID(List<Property> properties) {
        String val = null;
        for(Property p: properties) {
            if (p.getKey().equals("id")) {
//                System.out.println(p.getValue());
                val = p.getValue();
            }
        }
        return Integer.parseInt(val);
    }

    private int[] parse_string_to_array_int(String parse) {
        String[] array_return = parse.split(",", -1);
        int[] array_return_int = new int[array_return.length];
        for(int i = 0; i < array_return_int.length; i++) {
            array_return_int[i] = Integer.parseInt(array_return[i]);
        }
        return array_return_int;
    }


}
