package ca.mcmaster.cas.se2aa4.a2.generator;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Random;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
//import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;

public class DotGen {

    private final int width = 500;
    private final int height = 500;
    private final int square_size = 20;

    private int[] parse_string(String parse) {
        String[] array_return = parse.split(",", -1);
        int[] array_return_int = new int[array_return.length];
        for(int i = 0; i < array_return_int.length; i++) {
            array_return_int[i] = Integer.parseInt(array_return[i]);
        }
        return array_return_int;
    }

    public Mesh generate() {
        Set<Vertex> vertices = new HashSet<>();
        Set<Segment> segments = new HashSet<>();
        Random bag = new Random();
        // Create all the vertices
        for(int x = 0; x < width; x += square_size) {
            for(int y = 0; y < height; y += square_size) {
                OurVertex v1 = new OurVertex();
                v1.makeVertex(x, y);

                OurVertex v2 = new OurVertex();
                v2.makeVertex(x + square_size, y);

                OurVertex v3 = new OurVertex();
                v3.makeVertex(x, y + square_size);

                OurVertex v4 = new OurVertex();
                v4.makeVertex(x + square_size, y + square_size);

                int[] first_colour = v1.get_color_array();
                int[] second_colour = v2.get_color_array();
                int[] third_colour = v3.get_color_array();
                int[] fourth_colour = v4.get_color_array();

                Property head1 = Property.newBuilder().setKey("head").setValue(String.format("%f,%f", (double)x, (double)y)).build();
                Property tail1 = Property.newBuilder().setKey("tail").setValue(String.format("%f,%f", (double)x+square_size, (double)y)).build();
                String new_colour1 = Arrays.toString(new int[]{(first_colour[0] + second_colour[0])/2, (first_colour[1] + second_colour[1])/2, (first_colour[2] + second_colour[2])/2});
                Property color1 = Property.newBuilder().setKey("rgb_color").setValue(new_colour1).build();
                Segment connected1 = Segment.newBuilder().addProperties(head1).addProperties(tail1).addProperties(color1).build();


                Property head2 = Property.newBuilder().setKey("head").setValue(String.format("%f,%f", (double)x+square_size, (double)y)).build();
                Property tail2 = Property.newBuilder().setKey("tail").setValue(String.format("%f,%f", (double)x+square_size, (double)y+square_size)).build();
                String new_colour2 = Arrays.toString(new int[]{(second_colour[0] + fourth_colour[0])/2, (second_colour[1] + fourth_colour[1])/2, (second_colour[2] + fourth_colour[2])/2});
                Property color2 = Property.newBuilder().setKey("rgb_color").setValue(new_colour2).build();
                Segment connected2 = Segment.newBuilder().addProperties(head2).addProperties(tail2).addProperties(color2).build();


                Property head3 = Property.newBuilder().setKey("head").setValue(String.format("%f,%f", (double)x, (double)y+square_size)).build();
                Property tail3 = Property.newBuilder().setKey("tail").setValue(String.format("%f,%f", (double)x+square_size, (double)y+square_size)).build();
                String new_colour3 = Arrays.toString(new int[]{(fourth_colour[0] + third_colour[0])/2, (fourth_colour[1] + third_colour[1])/2, (fourth_colour[2] + third_colour[2])/2});
                Property color3 = Property.newBuilder().setKey("rgb_color").setValue(new_colour3).build();
                Segment connected3 = Segment.newBuilder().addProperties(head3).addProperties(tail3).addProperties(color3).build();

                Property head4 = Property.newBuilder().setKey("head").setValue(String.format("%f,%f", (double)x, (double)y+square_size)).build();
                Property tail4 = Property.newBuilder().setKey("tail").setValue(String.format("%f,%f", (double)x, (double)y)).build();
                String new_colour4 = Arrays.toString(new int[]{(first_colour[0] + third_colour[0])/2, (first_colour[1] + third_colour[1])/2, (first_colour[2] + third_colour[2])/2});
                Property color4 = Property.newBuilder().setKey("rgb_color").setValue(new_colour4).build();
                Segment connected4 = Segment.newBuilder().addProperties(head4).addProperties(tail4).addProperties(color4).build();

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

        return Mesh.newBuilder().addAllVertices(vertices).addAllSegments(segments).build();
    }

}
