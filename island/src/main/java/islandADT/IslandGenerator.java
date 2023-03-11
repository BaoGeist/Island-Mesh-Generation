package islandADT;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.HashSet;

public class IslandGenerator {

    public Structs.Mesh create_island(Structs.Mesh m) {
        HashSet<Structs.Vertex> vertices = new HashSet<>();
        HashSet<Structs.Segment> segments = new HashSet<>();
        HashSet<Structs.Polygon> polygons = new HashSet<>();
        return Structs.Mesh.newBuilder().addAllVertices(vertices).addAllSegments(segments).addAllPolygons(polygons).build();
    }
}
