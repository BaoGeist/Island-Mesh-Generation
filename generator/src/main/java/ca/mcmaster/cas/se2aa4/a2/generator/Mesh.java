package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.ArrayList;
import java.util.Random;

public class Mesh {
    private int width;
    private int height;
    private int square_size;
    private Structs.Vertex[][] grid;

    private float alpha_entry;
    private int thickness;
    private ArrayList<Structs.Vertex> vertices;

    private ArrayList<Structs.Segment> segments;
    public Mesh(int width, int height, int square_size, float alpha_entry, int thickness, ArrayList<Structs.Vertex> vertices, ArrayList<Structs.Segment> segments) {
        this.width = width;
        this.height = height;
        this.square_size = square_size;
        this.alpha_entry = alpha_entry;
        this.thickness = thickness;
        this.vertices = vertices;
        this.segments = segments;
        Structs.Vertex[][] new_grid = new Structs.Vertex[this.width][this.height];
        this.grid = new_grid;
        for(int x = 0; x < width; x += 1){
            for (int y = 0; y < height; y += 1) {
                Structs.Vertex vertex = Structs.Vertex.newBuilder().setX((double) x).setY((double) y).build();
                this.grid[x][y] = vertex;
            }
        }
    }

    public Structs.Mesh generate() {
        Random bag = new Random();
        // Create all the vertices
        for (int x = 0; x < width; x += square_size) {
            for (int y = 0; y < height; y += square_size) {
                OurVertex v1 = new OurVertex();
                Structs.Vertex vertex1 = v1.makeVertex((double)x, (double)y); // TODO - make sure these are 2 decimal places
                OurVertex v2 = new OurVertex();
                Structs.Vertex vertex2 = v2.makeVertex((double)x + square_size, (double)y);
                OurVertex v3 = new OurVertex();
                Structs.Vertex vertex3 = v3.makeVertex((double)x, (double)y + square_size);
                OurVertex v4 = new OurVertex();
                Structs.Vertex vertex4 = v4.makeVertex((double)x + square_size, (double)y + square_size);

                OurSegment s1 = new OurSegment();
                Structs.Segment segment1 = s1.create_segment(vertices.size(), vertices.size()+1, vertex1, vertex2, alpha_entry, thickness, segments.size());
                OurSegment s2 = new OurSegment();
                Structs.Segment segment2 = s2.create_segment(vertices.size()+1, vertices.size()+3, vertex2, vertex4, alpha_entry, thickness, segments.size()+1);
                OurSegment s3 = new OurSegment();
                Structs.Segment segment3 = s3.create_segment(vertices.size()+3, vertices.size()+2, vertex4, vertex3, alpha_entry, thickness, segments.size()+2);
                OurSegment s4 = new OurSegment();
                Structs.Segment segment4 = s4.create_segment(vertices.size()+2, vertices.size(), vertex3, vertex1, alpha_entry, thickness, segments.size()+3);

                segments.add(segment1);
                segments.add(segment2);
                segments.add(segment3);
                segments.add(segment4);

                vertices.add(vertex1);
                vertices.add(vertex2);
                vertices.add(vertex3);
                vertices.add(vertex4);
            }
        }
        return Structs.Mesh.newBuilder().addAllVertices(vertices).addAllSegments(segments).build();
    }
}
