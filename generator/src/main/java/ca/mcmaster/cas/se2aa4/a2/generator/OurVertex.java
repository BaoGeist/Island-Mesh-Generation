package ca.mcmaster.cas.se2aa4.a2.generator;
import java.util.Random;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;


public class OurVertex {
    
    private double[] coords = new double[2];
    private int[] colorCodes = new int[3];
    private String colorCode;
    private int id;
    private double[] centroid_coords = new double[2];
    boolean centroid_bool = false;

    private void set_coords(double x_coord, double y_coord) {
        coords[0] = x_coord;
        coords[1] = y_coord;
    }

    public double[] get_coords() {
        return coords;
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

    public String get_color_string() {
        return colorCode;
    }

    public int[] get_color_array() {
        return colorCodes;
    }

    public Vertex makeVertex(double x, double y, int id) {
        this.set_coords(x, y);
        this.set_color();
        return build_vertex();
    }

    public Vertex makeCentroidVertex(double x, double y, int id) {
        this.set_coords(x, y);
//        System.out.println("x" + x + "y" + y);
        colorCode = "0,0,0";
        centroid_bool = true;
        return build_vertex();
    }

    private Vertex build_vertex() {
        Property centroid_or_nah = Property.newBuilder().setKey("centroid_or_nah").setValue(String.valueOf(centroid_bool)).build();
        Property color = Property.newBuilder().setKey("rgb_color").setValue(this.get_color_string()).build();
        Vertex v = Vertex.newBuilder().setX(coords[0]).setY(coords[1]).addProperties(color).addProperties(centroid_or_nah).build();
        return v;
    }
        
}
