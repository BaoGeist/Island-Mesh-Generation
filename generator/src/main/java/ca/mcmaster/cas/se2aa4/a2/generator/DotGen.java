package ca.mcmaster.cas.se2aa4.a2.generator;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.Random;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;

public class DotGen {

    private final int width = 500;
    private final int height = 500;
    private final int square_size = 20;

    public Mesh generate() {
        Set<Vertex> vertices = new HashSet<>();
        Set<Segment> segments = new HashSet<>();
        // Create all the vertices
        for(int x = 0; x < width; x += square_size) {
            for(int y = 0; y < height; y += square_size) {
                Vertex v1 = Vertex.newBuilder().setX((double) x).setY((double) y).build();
                Vertex v2 = Vertex.newBuilder().setX((double) x+square_size).setY((double) y).build();
                Vertex v3 = Vertex.newBuilder().setX((double) x).setY((double) y+square_size).build();
                Vertex v4 = Vertex.newBuilder().setX((double) x+square_size).setY((double) y+square_size).build();
                vertices.add(v1);
                vertices.add(v2);
                vertices.add(v3);
                vertices.add(v4);

                Segment s1 = Segment.newBuilder().setV1Idx(v1.hashCode()).setV2Idx(v2.hashCode()).build();
                Segment s2 = Segment.newBuilder().setV1Idx(v2.hashCode()).setV2Idx(v3.hashCode()).build();
                Segment s3 = Segment.newBuilder().setV1Idx(v3.hashCode()).setV2Idx(v4.hashCode()).build();
                Segment s4 = Segment.newBuilder().setV1Idx(v1.hashCode()).setV2Idx(v4.hashCode()).build();

                Property head1 = Property.newBuilder().setKey("head").setValue(String.format("%f,%f", (double)x, (double)y)).build();
                Property tail1 = Property.newBuilder().setKey("tail").setValue(String.format("%f,%f", (double)x+square_size, (double)y)).build();
                Segment connected1 = Segment.newBuilder(s1).addProperties(head1).addProperties(tail1).build();

                Property head2 = Property.newBuilder().setKey("head").setValue(String.format("%f,%f", (double)x+square_size, (double)y)).build();
                Property tail2 = Property.newBuilder().setKey("tail").setValue(String.format("%f,%f", (double)x, (double)y+square_size)).build();
                Segment connected2 = Segment.newBuilder(s2).addProperties(head2).addProperties(tail2).build();
                Property head3 = Property.newBuilder().setKey("head").setValue(String.format("%f,%f", (double)x, (double)y+square_size)).build();
                Property tail3 = Property.newBuilder().setKey("tail").setValue(String.format("%f,%f", (double)x+square_size, (double)y+square_size)).build();
                Segment connected3 = Segment.newBuilder(s3).addProperties(head3).addProperties(tail3).build();
                Property head4 = Property.newBuilder().setKey("head").setValue(String.format("%f,%f", (double)x+square_size, (double)y+square_size)).build();
                Property tail4 = Property.newBuilder().setKey("tail").setValue(String.format("%f,%f", (double)x, (double)y)).build();
                Segment connected4 = Segment.newBuilder(s4).addProperties(head4).addProperties(tail4).build();

                segments.add(connected1);
                segments.add(connected2);
                segments.add(connected3);
                segments.add(connected4);

                System.out.println(connected4.getPropertiesList());
            }
        }

        // Distribute colors randomly. Vertices are immutable, need to enrich them
        Set<Vertex> verticesWithColors = new HashSet<>();
        Random bag = new Random();
        for(Vertex v: vertices){
            int red = bag.nextInt(255);
            int green = bag.nextInt(255);
            int blue = bag.nextInt(255);

            String colorCode = red + "," + green + "," + blue;
            Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
            Vertex colored = Vertex.newBuilder(v).addProperties(color).build();
            verticesWithColors.add(colored);
        }


//        // Distribute colors randomly. Vertices are immutable, need to enrich them
//        Set<Segment> segmentsWithColors = new HashSet<>();
//        for(Vertex v: vertices){
//            int red = bag.nextInt(255);
//            int green = bag.nextInt(255);
//            int blue = bag.nextInt(255);
//            String colorCode = red + "," + green + "," + blue;
//            Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
//            Vertex colored = Vertex.newBuilder(v).addProperties(color).build();
//            verticesWithColors.add(colored);
//        }

        return Mesh.newBuilder().addAllVertices(verticesWithColors).addAllSegments(segments).build();
    }

}
