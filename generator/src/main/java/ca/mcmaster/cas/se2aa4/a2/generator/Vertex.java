package ca.mcmaster.cas.se2aa4.a2.generator;
import java.util.Random;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;


public class Vertex {
    private double[] coords = new double[2];
    private int[] colorCodes = new int[3];

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
    }

    public int[] get_color() {
        return colorCodes;
    }

    public Vertex makeVertex(double x, double y) {
        this.set_coords(x, y);
        this.set_color();
        return this;
    }
        
}
