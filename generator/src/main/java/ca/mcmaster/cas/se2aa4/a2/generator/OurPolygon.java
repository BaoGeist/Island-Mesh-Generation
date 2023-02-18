package ca.mcmaster.cas.se2aa4.a2.generator;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class OurPolygon {
    Vertex middle_vertex;
    ArrayList<Segment> segments_group = new ArrayList<>();
    ArrayList<Integer> segments_id = new ArrayList<>();
    ArrayList<Integer> neighbours_id = new ArrayList<>();
    ArrayList<Double> x_coords = new ArrayList<>();
    ArrayList<Double> y_coords = new ArrayList<>();
    private int thickness = 1;
    private int[] colorCodes = new int[3];
    private String colorCode;
    private float alpha = 1;
    private int id;

    public Polygon create_polygon(int id_self, ArrayList<Segment> segments) {
        for(Segment segment: segments) {
            segments_group.add(segment);
            segments_id.add(extractID(segment.getPropertiesList()));
        }
        id = id_self;
        set_color();
<<<<<<< HEAD
<<<<<<< HEAD
        set_coords();
=======
>>>>>>> c20d31a (Added color to OurPolygon and used it in GraphicRenderer)
=======
        set_coords();
>>>>>>> 8170089 (Added coordinates to OurPolygon and graphs them in GraphicRenderer and fills them in)
        middle_vertex = create_middle_vertex();
        return build_polygon();
    }

    private void set_color() {
        Random bag = new Random();
        int red = bag.nextInt(255);
        int green = bag.nextInt(255);
        int blue = bag.nextInt(255);
        colorCodes[0] = red;
        colorCodes[1] = green;
        colorCodes[2] = blue;
        colorCode = red + "," + green + "," + blue;
    }

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 8170089 (Added coordinates to OurPolygon and graphs them in GraphicRenderer and fills them in)
    private void set_coords(){
        for(Segment segment: segments_group) {
            x_coords.add(extractHeadCoords(segment.getPropertiesList())[0]);
            y_coords.add(extractHeadCoords(segment.getPropertiesList())[1]);
        }
    }

<<<<<<< HEAD
=======
>>>>>>> c20d31a (Added color to OurPolygon and used it in GraphicRenderer)
=======
>>>>>>> 8170089 (Added coordinates to OurPolygon and graphs them in GraphicRenderer and fills them in)
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

    private String get_neighbours_id() {
        String outputString = "";
        for(Integer id: neighbours_id) {
            outputString += String.valueOf(id);
        }
        return outputString;
    }

    private Polygon build_polygon() {
        Property thicc = Property.newBuilder().setKey("thicc").setValue(Integer.toString(thickness)).build();
        Property a = Property.newBuilder().setKey("alpha").setValue(Float.toString(alpha)).build();
        Property polygon_id = Property.newBuilder().setKey("id").setValue(String.valueOf(id)).build();
        Property neighbours_id = Property.newBuilder().setKey("neighbours").setValue(get_neighbours_id()).build();
        Property middle_id = Property.newBuilder().setKey("middle_id").setValue(Integer.toString(extractID(middle_vertex.getPropertiesList()))).build();
        Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
<<<<<<< HEAD
<<<<<<< HEAD
        Property x_coords = Property.newBuilder().setKey("x_coords").setValue(this.x_coords.toString()).build();
        Property y_coords = Property.newBuilder().setKey("y_coords").setValue(this.y_coords.toString()).build();
        Polygon p = Polygon.newBuilder().addAllSegmentIdxs(segments_id).setCentroidIdx(extractID(middle_vertex.getPropertiesList())).addProperties(thicc).addProperties(a).addProperties(polygon_id).addProperties(neighbours_id).addProperties(middle_id).addProperties(color).addProperties(x_coords).addProperties(y_coords).build();
=======
        Polygon p = Polygon.newBuilder().addAllSegmentIdxs(segments_id).setCentroidIdx(extractID(middle_vertex.getPropertiesList())).addProperties(thicc).addProperties(a).addProperties(polygon_id).addProperties(neighbours_id).addProperties(middle_id).addProperties(color).build();
>>>>>>> c20d31a (Added color to OurPolygon and used it in GraphicRenderer)
=======
        Property x_coords = Property.newBuilder().setKey("x_coords").setValue(this.x_coords.toString()).build();
        Property y_coords = Property.newBuilder().setKey("y_coords").setValue(this.y_coords.toString()).build();
        Polygon p = Polygon.newBuilder().addAllSegmentIdxs(segments_id).setCentroidIdx(extractID(middle_vertex.getPropertiesList())).addProperties(thicc).addProperties(a).addProperties(polygon_id).addProperties(neighbours_id).addProperties(middle_id).addProperties(color).addProperties(x_coords).addProperties(y_coords).build();
>>>>>>> 8170089 (Added coordinates to OurPolygon and graphs them in GraphicRenderer and fills them in)
        return p;
    }


<<<<<<< HEAD
<<<<<<< HEAD
    private double[] extractSegmentMiddle(List<Property> properties) {
=======
    private int[] extractSegmentMiddle(List<Property> properties) {
>>>>>>> 8170089 (Added coordinates to OurPolygon and graphs them in GraphicRenderer and fills them in)
=======
    private double[] extractSegmentMiddle(List<Property> properties) {
>>>>>>> 86b4dda (new commit)
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
        String val = "0";
        for(Property p: properties) {
            if (p.getKey().equals("id")) {
//                System.out.println(p.getValue());
                val = p.getValue();
            }
        }
        return Integer.parseInt(val);
    }

    private double[] extractHeadCoords(List<Property> properties) {
        String val = null;
        for(Property p: properties) {
            if (p.getKey().equals("head")) {
//                System.out.println(p.getValue());
                val = p.getValue();
            }
        }
        String[] raw = val.split(",");
        Double x = Double.parseDouble(raw[0].replace("[","").replace(" ", ""));
        Double y = Double.parseDouble(raw[1].replace(" ", ""));
<<<<<<< HEAD
<<<<<<< HEAD
        return new double[]{x, y};
    }

    private double[] parse_string_to_array_int(String parse) {
=======
        int blue = Integer.parseInt(raw[2].replace("]","").replace(" ", ""));
        return new double[]{x, y};
    }

    private int[] parse_string_to_array_int(String parse) {
>>>>>>> 8170089 (Added coordinates to OurPolygon and graphs them in GraphicRenderer and fills them in)
=======
        return new double[]{x, y};
    }

    private double[] parse_string_to_array_int(String parse) {
>>>>>>> 86b4dda (new commit)
        String[] array_return = parse.split(",", -1);
        double[] array_return_int = new double[array_return.length];
        for(int i = 0; i < array_return_int.length; i++) {
            array_return_int[i] = Double.parseDouble(array_return[i]);
        }
        return array_return_int;
    }


}
