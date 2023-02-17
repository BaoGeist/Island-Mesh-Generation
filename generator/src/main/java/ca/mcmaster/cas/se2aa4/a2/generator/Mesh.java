package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;

public class Mesh {

    private int width;
    private int height;
    private int square_size;

    private Structs.Vertex[][] grid;

    public Mesh(int width, int height, int square_size) {
        this.width = width;
        this.height = height;
        this.square_size = square_size;
        Structs.Vertex[][] new_grid = new Structs.Vertex[this.width][this.height];
        this.grid = new_grid;
        for(int x = 0; x < width; x += 1){
            for (int y = 0; y < height; y += 1) {
                Structs.Vertex vertex = Structs.Vertex.newBuilder().setX((double) x).setY((double) y).build();
                this.grid[x][y] = vertex;
            }
        }
    }

    public void generate() {
        Set<OurVertex> vertices = new HashSet<>();
        Set<OurSegment> segments = new HashSet<>();
        Random bag = new Random();
        // Create all the vertices
        for (int x = 0; x < width; x += square_size) {
            for (int y = 0; y < height; y += square_size) {
                OurVertex v1 = new OurVertex();
                v1.makeVertex((double)x, (double)y); // TODO - make sure these are 2 decimal places
                OurVertex v2 = new OurVertex();
                v2.makeVertex((double)x + square_size, (double)y);
                OurVertex v3 = new OurVertex();
                v3.makeVertex((double)x, (double)y + square_size);
                OurVertex v4 = new OurVertex();
                v4.makeVertex((double)x + square_size, (double)y + square_size);

                Structs.Property head1 = Structs.Property.newBuilder().setKey("head").setValue(String.format("%f,%f", (double)x, (double)y)).build();
                Structs.Property tail1 = Structs.Property.newBuilder().setKey("tail").setValue(String.format("%f,%f", (double)x+square_size, (double)y)).build();
                String new_colour1 = Arrays.toString(new int[]{(first_colour[0] + second_colour[0])/2, (first_colour[1] + second_colour[1])/2, (first_colour[2] + second_colour[2])/2});
                Structs.Property color1 = Structs.Property.newBuilder().setKey("rgb_color").setValue(new_colour1).build();
                Structs.Segment connected1 = Structs.Segment.newBuilder().addProperties(head1).addProperties(tail1).addProperties(color1).build();

                Structs.Property head2 = Structs.Property.newBuilder().setKey("head").setValue(String.format("%f,%f", (double)x+square_size, (double)y)).build();
                Structs.Property tail2 = Structs.Property.newBuilder().setKey("tail").setValue(String.format("%f,%f", (double)x+square_size, (double)y+square_size)).build();
                String new_colour2 = Arrays.toString(new int[]{(second_colour[0] + fourth_colour[0])/2, (second_colour[1] + fourth_colour[1])/2, (second_colour[2] + fourth_colour[2])/2});
                Structs.Property color2 = Structs.Property.newBuilder().setKey("rgb_color").setValue(new_colour2).build();
                Structs.Segment connected2 = Structs.Segment.newBuilder().addProperties(head2).addProperties(tail2).addProperties(color2).build();


                Structs.Property head3 = Structs.Property.newBuilder().setKey("head").setValue(String.format("%f,%f", (double)x, (double)y+square_size)).build();
                Structs.Property tail3 = Structs.Property.newBuilder().setKey("tail").setValue(String.format("%f,%f", (double)x+square_size, (double)y+square_size)).build();
                String new_colour3 = Arrays.toString(new int[]{(fourth_colour[0] + third_colour[0])/2, (fourth_colour[1] + third_colour[1])/2, (fourth_colour[2] + third_colour[2])/2});
                Structs.Property color3 = Structs.Property.newBuilder().setKey("rgb_color").setValue(new_colour3).build();
                Structs.Segment connected3 = Structs.Segment.newBuilder().addProperties(head3).addProperties(tail3).addProperties(color3).build();

                Structs.Property head4 = Structs.Property.newBuilder().setKey("head").setValue(String.format("%f,%f", (double)x, (double)y+square_size)).build();
                Structs.Property tail4 = Structs.Property.newBuilder().setKey("tail").setValue(String.format("%f,%f", (double)x, (double)y)).build();
                String new_colour4 = Arrays.toString(new int[]{(first_colour[0] + third_colour[0])/2, (first_colour[1] + third_colour[1])/2, (first_colour[2] + third_colour[2])/2});
                Structs.Property color4 = Structs.Property.newBuilder().setKey("rgb_color").setValue(new_colour4).build();
                Structs.Segment connected4 = Structs.Segment.newBuilder().addProperties(head4).addProperties(tail4).addProperties(color4).build();

                segments.add(connected1);
                segments.add(connected2);
                segments.add(connected3);
                segments.add(connected4);

                vertices.add(v1);
                vertices.add(v2);
                vertices.add(v3);
                vertices.add(v4);
            }
        }
        return Structs.Mesh.newBuilder().addAllVertices(vertices).addAllSegments(segments).build();
    }
}
