package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.ArrayList;
import java.util.Random;

public class Mesh {

    private int width;
    private int height;
    private int square_size;

    private ArrayList<ArrayList<Structs.Vertex>> grid;

    public Mesh(int width, int height, int square_size) {
        this.width = width;
        this.height = height;
        this.square_size = square_size;
        this.grid = new ArrayList<>();
    }

    public void generate() {
        Random bag = new Random();
        // Create all the vertices
        for (int x = 0; x < width; x += square_size) {
            for (int y = 0; y < height; y += square_size) {
                int red = bag.nextInt(255);
                int green = bag.nextInt(255);
                int blue = bag.nextInt(255);
                String colorCode = red + "," + green + "," + blue;
                Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
                Structs.Vertex v = Structs.Vertex.newBuilder().setX((double) x).setY((double) y).addProperties(color).build();

            }
        }
    }
}
