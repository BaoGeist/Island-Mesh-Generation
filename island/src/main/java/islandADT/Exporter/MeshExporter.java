package islandADT.Exporter;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import islandADT.GeometryContainer;
import islandADT.Wrappers.PolygonWrapper;
import islandADT.Wrappers.SegmentWrapper;
import islandADT.Wrappers.VertexWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MeshExporter implements Exporter<GeometryContainer, Structs.Mesh> {
    public Structs.Mesh export(GeometryContainer geometryContainer) {
        Map<Integer, PolygonWrapper> polygons = geometryContainer.get_polygons();
        Map<Integer, SegmentWrapper> segments = geometryContainer.get_segments();
        Map<Integer, VertexWrapper> vertices = geometryContainer.get_vertices();

        List<Structs.Polygon> polygons_output = new ArrayList<>();
        List<Structs.Segment> segments_output = new ArrayList<>();
        List<Structs.Vertex> vertices_output = new ArrayList<>();

        Exporter polygonExporter = new OurPolygonExporter();
        Exporter segmentExporter = new OurSegmentExporter();
        Exporter vertexExporter = new OurVertexExporter();

        for(Map.Entry<Integer, PolygonWrapper> entry: polygons.entrySet()) {
            Integer key = entry.getKey();
            PolygonWrapper p = entry.getValue();
            polygons_output.add((Structs.Polygon) polygonExporter.export(entry.getValue()));
        }

        for(Map.Entry<Integer, SegmentWrapper> entry: segments.entrySet()) {
            Integer key = entry.getKey();
            SegmentWrapper p = entry.getValue();
//            System.out.println(key + " = " + key);
//            System.out.println(p.get_id());
            segments_output.add((Structs.Segment) segmentExporter.export(entry.getValue()));
        }

        for(Map.Entry<Integer, VertexWrapper> entry: vertices.entrySet()) {
            Integer key = entry.getKey();
            VertexWrapper p = entry.getValue();
//            System.out.println(key + " = " + key);
//            System.out.println(p.get_id());
            vertices_output.add((Structs.Vertex) vertexExporter.export(entry.getValue()));
        }
        return Structs.Mesh.newBuilder().addAllPolygons(polygons_output).addAllSegments(segments_output).addAllVertices(vertices_output).build();
    }
}
