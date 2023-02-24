package ca.mcmaster.cas.se2aa4.a2.generator;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;

import java.util.ArrayList;
import java.util.Random;

import static ca.mcmaster.cas.se2aa4.a2.generator.PropertyUtils.*;


public class OurPolygon implements OurGeometryFactory{
    Vertex middle_vertex;
    ArrayList<Segment> segments_group = new ArrayList<>();
    ArrayList<Integer> segments_id = new ArrayList<>();
    ArrayList<Integer> neighbours_id = new ArrayList<>();
    ArrayList<Double> x_coords = new ArrayList<>();
    ArrayList<Double> y_coords = new ArrayList<>();
    private int thickness = 1;
    private String colorCode = "";
    private float alpha = 1;
    private int id;
    private double[] centroid_coords = new double[2];
    private Polygon actual_Polygon;

    @Override
    public ArrayList<Object> create_geometry(int id_self, ArrayList<Object> arrayArgs, float alpha, int thickness, int misc) {
        for(Object arg: arrayArgs) {
            Segment segmented = (Segment) arg;
            segments_group.add(segmented);
            segments_id.add(extractID(segmented.getPropertiesList()));
        }
        id = id_self;
        set_color();
        set_coords();
        int centroid_index = misc;
        create_middle_vertex(centroid_index);
        actual_Polygon = build_polygon();
        ArrayList<Object> return_array = new ArrayList<>();

        return_array.add(actual_Polygon);
        return_array.add(middle_vertex);
        return return_array;
    }

    private void set_color() {
        Random bag = new Random();
        int red = bag.nextInt(255);
        int green = bag.nextInt(255);
        int blue = bag.nextInt(255);
        colorCode = red + "," + green + "," + blue;
    }

    private void set_coords(){
        // adds the first segment
        x_coords.add(extractHeadCoords(segments_group.get(0).getPropertiesList())[0]);
        y_coords.add(extractHeadCoords(segments_group.get(0).getPropertiesList())[1]);
        x_coords.add(extractTailCoords(segments_group.get(0).getPropertiesList())[0]);
        y_coords.add(extractTailCoords(segments_group.get(0).getPropertiesList())[1]);
        for(int i = 1; i < segments_group.size(); i++) {
            if(x_coords.get(i) == extractHeadCoords(segments_group.get(i).getPropertiesList())[0] && y_coords.get(i) == extractHeadCoords(segments_group.get(i).getPropertiesList())[1]) {
                x_coords.add(extractTailCoords(segments_group.get(i).getPropertiesList())[0]);
                y_coords.add(extractTailCoords(segments_group.get(i).getPropertiesList())[1]);
            }
            // checks if tail of new segment is the same as the last point added, adds the head if so
            else if (x_coords.get(i) == extractTailCoords(segments_group.get(i).getPropertiesList())[0] && y_coords.get(i) == extractTailCoords(segments_group.get(i).getPropertiesList())[1]) {
                x_coords.add(extractHeadCoords(segments_group.get(i).getPropertiesList())[0]);
                y_coords.add(extractHeadCoords(segments_group.get(i).getPropertiesList())[1]);
            }
        }

    }

    private void create_middle_vertex(int id) {
        int totalx = 0, totaly = 0, count = 0;
        for(Segment segment: segments_group) {
            totalx += extractSegmentMiddle(segment.getPropertiesList())[0];
            totaly += extractSegmentMiddle(segment.getPropertiesList())[1];
            count++;
        }
        OurVertex v = new OurVertex();
        middle_vertex = v.create_geometry_centroid((double) totalx/count, (double) totaly/count, id);
    }

    public double[] get_middle_vertex() {
        int totalx = 0, totaly = 0, count = 0;
        for(Segment segment: segments_group) {
            totalx += extractSegmentMiddle(segment.getPropertiesList())[0];
            totaly += extractSegmentMiddle(segment.getPropertiesList())[1];
            count++;
        }
        this.centroid_coords[0] = (double) totalx/count;
        this.centroid_coords[1] = (double) totaly/count;
        return centroid_coords;
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
        Property x_coords = Property.newBuilder().setKey("x_coords").setValue(this.x_coords.toString()).build();
        Property y_coords = Property.newBuilder().setKey("y_coords").setValue(this.y_coords.toString()).build();
        Property centroid_coords = Property.newBuilder().setKey("centroid_coords").setValue(this.centroid_coords.toString()).build();
        Polygon p = Polygon.newBuilder().addAllSegmentIdxs(segments_id).addProperties(middle_id).addProperties(thicc).addProperties(a).addProperties(polygon_id).addProperties(neighbours_id).addProperties(color).addProperties(x_coords).addProperties(y_coords).build();
        return p;
    }


}
