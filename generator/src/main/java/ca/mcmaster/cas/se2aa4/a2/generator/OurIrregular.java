package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.triangulate.VoronoiDiagramBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.HashMap;
public class OurIrregular {

    private int length = 500;
    private int width = 500;

    private static ArrayList<Coordinate> generate_random_points(int number) {
        PrecisionModel newModel = new PrecisionModel(PrecisionModel.FLOATING_SINGLE);

        Random random = new Random();
        ArrayList<Coordinate> listCoordinates = new ArrayList<>();

        for (int i = 0; i < number; i++) {
            double x = random.nextDouble() * 500;
            double y = random.nextDouble() * 500;
            Coordinate randomCoordinate = new Coordinate(x, y);
            newModel.makePrecise(randomCoordinate);
            listCoordinates.add(randomCoordinate);
        }
        return listCoordinates;
    }

    private Geometry generate_voronoi(ArrayList<Coordinate> listCoordinates) {
        VoronoiDiagramBuilder newVoronoi = new VoronoiDiagramBuilder();

        GeometryFactory newFactory = new GeometryFactory();

        newVoronoi.setSites(listCoordinates);
        Geometry diagram = newVoronoi.getDiagram(newFactory);

        // Create an envelope representing the 500x500 grid
        Envelope env = new Envelope(0, width, 0, length);

        // Crop the diagram to the envelope
        diagram = diagram.intersection(newFactory.toGeometry(env));

        return diagram;
    }

    private static double calculate_length_of_segment(Coordinate c1, Coordinate c2) {
        return Math.sqrt(Math.pow((c1.x - c2.x),2) + (Math.pow((c1.y - c2.y),2)));
    }

    private static double calculate_area_of_triangle(Coordinate c1, Coordinate c2, Coordinate c3) {
        double a = calculate_length_of_segment(c1, c2);
        double b = calculate_length_of_segment(c2, c3);
        double c = calculate_length_of_segment(c3, c1);
        double s = (a + b + c) / 2;
        return Math.sqrt(s*(s-a)*(s-b)*(s-c));
    }

    private static Coordinate calculate_centroid_of_triangle(Coordinate c1, Coordinate c2, Coordinate c3) {
        double x = (c1.x + c2.x + c3.x)/3;
        double y = (c1.y + c2.y + c3.y)/3;
        return new Coordinate(x, y);
    }

    private static Coordinate calculate_weighted_average(ArrayList<Double> areas, ArrayList<Coordinate> coordinates) {
        double x_total = 0, y_total = 0, area = 0;
        for(int i = 0; i < areas.size(); i++) {
            area += areas.get(i);
            x_total += coordinates.get(i).x * areas.get(i);
            y_total += coordinates.get(i).y * areas.get(i);
        }
        return new Coordinate(x_total/area, y_total/area);
    }

    private static Coordinate calculate_lloyd_relaxation_single(Geometry notcell) {
        Coordinate[] lloydcoord = notcell.getCoordinates();
        ArrayList<Coordinate> triangle_centroids = new ArrayList<>();
        ArrayList<Double> triangle_areas = new ArrayList<>();
        int first_iterator = 1, second_iterator = 2;
        while (second_iterator <= lloydcoord.length - 2) {
            double area = calculate_area_of_triangle(lloydcoord[0], lloydcoord[first_iterator], lloydcoord[second_iterator]);
            Coordinate centroid = calculate_centroid_of_triangle(lloydcoord[0], lloydcoord[first_iterator], lloydcoord[second_iterator]);
            triangle_areas.add(area);
            triangle_centroids.add(centroid);
            first_iterator++;
            second_iterator++;
        }
        Coordinate new_centroid = calculate_weighted_average(triangle_areas, triangle_centroids);
        return new_centroid;
    }

    private static ArrayList<Coordinate> calculate_lloyd_relaxation_multiple(Geometry oldPoints) {
        ArrayList<Coordinate> listVoronoied = new ArrayList<>();
        for(int i = 0; i < oldPoints.getNumGeometries(); i++) {
            Geometry notcell = oldPoints.getGeometryN(i);
            Coordinate new_centroid = calculate_lloyd_relaxation_single(notcell);
            listVoronoied.add(new_centroid);
        }
        return listVoronoied;
    }

    private static final int THICKNESS = 3;
    public Mesh generate() {
        ArrayList<Vertex> vertices = new ArrayList<>();
        ArrayList<Segment> segments = new ArrayList<>();
        ArrayList<Polygon> polygons = new ArrayList<>();

        // create random points everywhere on the plane
        ArrayList<Coordinate> listCoordinates = generate_random_points(25);


        // voronoi diagram
        Geometry voronoiedPoints = generate_voronoi(listCoordinates);

        // lloyd relaxation
        int lloyd_number = 20;
        for(int i = 0; i < lloyd_number; i++) {
            listCoordinates = calculate_lloyd_relaxation_multiple(voronoiedPoints);
            voronoiedPoints = generate_voronoi(listCoordinates);
        }
        listCoordinates = calculate_lloyd_relaxation_multiple(voronoiedPoints);


        /// creating mesh
        for(int i = 0; i < voronoiedPoints.getNumGeometries(); i++) {
            Geometry cell = voronoiedPoints.getGeometryN(i);
            ArrayList<Vertex> segment_vertices = new ArrayList<>();
            ArrayList<Segment> polygon_segments = new ArrayList<>();
            for (int j = 0; j < cell.getCoordinates().length - 1; j++) {
                OurVertex vertexFactory = new OurVertex();
                segment_vertices.add(vertexFactory.makeVertex(cell.getCoordinates()[j].x, cell.getCoordinates()[j].y, vertices.size() + j));
            }
            for(int j = 0; j < cell.getCoordinates().length - 2; j++) {
                OurSegment segmentFactory = new OurSegment();
                polygon_segments.add(segmentFactory.create_segment(segment_vertices.get(j), segment_vertices.get(j+1), 1.0f, 1, 1));
            }
            OurPolygon polygonFactory = new OurPolygon();
            // returns an ArrayList with a Polygon and Vertex (the centroid) object
            // TODO fix this centroid vertex calculation cuz it isn't accurate (Baoze)
            ArrayList<Object> return_array = polygonFactory.create_polygon(polygons.size(), vertices.size() + cell.getCoordinates().length, polygon_segments);
            polygons.add((Structs.Polygon) return_array.get(0));
            vertices.add((Structs.Vertex) return_array.get(1));
            // TODO compute neighbourhood relationships using Delaunay's triangulation
            vertices.addAll(segment_vertices);
            segments.addAll(polygon_segments);
        }
        return Structs.Mesh.newBuilder().addAllVertices(vertices).addAllSegments(segments).addAllPolygons(polygons).build();
        //polygons

    }
}
