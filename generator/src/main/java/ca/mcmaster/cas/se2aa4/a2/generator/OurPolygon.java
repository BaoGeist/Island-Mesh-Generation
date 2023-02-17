package ca.mcmaster.cas.se2aa4.a2.generator;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;

import java.util.ArrayList;
import java.util.List;


public class OurPolygon {
    Vertex middle_vertex;
    ArrayList<Segment> segments = new ArrayList<>();

    public Polygon create_polygon(Segment... segments) {
        for(Segment segment: segments) {
            this.segments.add(segment);
        }
        middle_vertex = create_middle_vertex();
        return build_segment();
    }

    private Vertex create_middle_vertex() {
        int totalx = 0, totaly = 0, count = 0;
        for(Segment segment: this.segments) {
            totalx += extractSegmentMiddle(segment.getPropertiesList())[0];
            totaly += extractSegmentMiddle(segment.getPropertiesList())[1];
            count++;
        }
        OurVertex v = new OurVertex();
        return v.makeCentroidVertex(totalx/count, totalx/count);
    }

    private Polygon build_segment() {

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

    private int[] parse_string_to_array_int(String parse) {
        String[] array_return = parse.split(",", -1);
        int[] array_return_int = new int[array_return.length];
        for(int i = 0; i < array_return_int.length; i++) {
            array_return_int[i] = Integer.parseInt(array_return[i]);
        }
        return array_return_int;
    }


}
