package islandADT.Exporter;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import islandADT.Exporter.Colour.ColourExporter;
import islandADT.Exporter.Colour.ColourFactory;
import islandADT.Exporter.Colour.ColourGenerator;
import islandADT.GeometryContainer;
import islandADT.GeometryWrappers.PolygonWrapper;
import islandADT.GeometryWrappers.SegmentWrapper;
import islandADT.GeometryWrappers.VertexWrapper;
import islandADT.Specifications.IslandSpecifications;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MeshExporter implements Exporter<GeometryContainer, Structs.Mesh> {
    IslandSpecifications islandSpecifications;
    public MeshExporter(IslandSpecifications islandSpecifications) {
        this.islandSpecifications = islandSpecifications;
    }

    private double[] get_min_max_height_from_vertices(Map<Integer, VertexWrapper> vertices) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for(VertexWrapper v: vertices.values()) {
            if(v.getHeight() < min) min = v.getHeight();
            if(v.getHeight() > max) max = v.getHeight();
        }
        return new double[]{min, max};
    }

    private double[] get_min_max_moisture_from_polygons(Map<Integer, PolygonWrapper> polygons) {
        double min = Double.MAX_VALUE, max = Double.MIN_VALUE;
        for(PolygonWrapper p: polygons.values()) {
            if(p.getMoisture() < min) min = p.getMoisture();
            if(p.getMoisture() > max) max = p.getMoisture();
        }
        return new double[]{min, max};
    }



    public Structs.Mesh export(GeometryContainer geometryContainer) {
        Map<Integer, PolygonWrapper> polygons = geometryContainer.get_polygons();
        Map<Integer, SegmentWrapper> segments = geometryContainer.get_segments();
        Map<Integer, VertexWrapper> vertices = geometryContainer.get_vertices();

        List<Structs.Polygon> polygons_output = new ArrayList<>();
        List<Structs.Segment> segments_output = new ArrayList<>();
        List<Structs.Vertex> vertices_output = new ArrayList<>();

        ColourGenerator colourFactory = new ColourGenerator(islandSpecifications.getMode());
        colourFactory.set_increments(polygons);

        ColourFactory colourFactory1 = new ColourFactory();
        ColourExporter colour = colourFactory1.create(islandSpecifications);
        colour.set_increments(polygons);

        Exporter polygonExporter = new OurPolygonExporter(colour);
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
            segments_output.add((Structs.Segment) segmentExporter.export(entry.getValue()));
        }

        for(Map.Entry<Integer, VertexWrapper> entry: vertices.entrySet()) {
            Integer key = entry.getKey();
            VertexWrapper p = entry.getValue();
            vertices_output.add((Structs.Vertex) vertexExporter.export(entry.getValue()));
        }

        double[] min_max_height = get_min_max_height_from_vertices(vertices);
        Structs.Property min_max_h = Structs.Property.newBuilder().setKey("min_max_height").setValue(Arrays.toString(min_max_height)).build();
        double[] min_max_moisture = get_min_max_moisture_from_polygons(polygons);
        Structs.Property min_max_m = Structs.Property.newBuilder().setKey("min_max_moisture").setValue(Arrays.toString(min_max_moisture)).build();

        return Structs.Mesh.newBuilder().addProperties(min_max_h).addProperties(min_max_m).addAllPolygons(polygons_output).addAllSegments(segments_output).addAllVertices(vertices_output).build();
    }
}
