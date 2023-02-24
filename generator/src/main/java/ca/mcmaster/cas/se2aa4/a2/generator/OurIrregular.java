package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.triangulate.VoronoiDiagramBuilder;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

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

    private static Coordinate calculate_lloyd_relaxation_single(Geometry notcell) {
        Coordinate[] lloydcoord = notcell.getCoordinates();
        ArrayList<Coordinate> triangle_centroids = new ArrayList<>();
        ArrayList<Double> triangle_areas = new ArrayList<>();
        int first_iterator = 1, second_iterator = 2;
        while (second_iterator <= lloydcoord.length - 2) {
            double area = MathManz.calculate_area_of_triangle(lloydcoord[0], lloydcoord[first_iterator], lloydcoord[second_iterator]);
            Coordinate centroid = MathManz.calculate_centroid_of_triangle(lloydcoord[0], lloydcoord[first_iterator], lloydcoord[second_iterator]);
            triangle_areas.add(area);
            triangle_centroids.add(centroid);
            first_iterator++;
            second_iterator++;
        }
        Coordinate new_centroid = MathManz.calculate_new_centroid(triangle_areas, triangle_centroids);
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
        ArrayList<Coordinate> listCoordinates = generate_random_points(250);


        // voronoi diagram
        Geometry voronoiedPoints = generate_voronoi(listCoordinates);

        // lloyd relaxation
        int lloyd_number = 10;
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
                ArrayList<Object> send_array = new ArrayList<>();
                send_array.add((float) cell.getCoordinates()[j].x);
                send_array.add((float) cell.getCoordinates()[j].y);
                ArrayList<Object> returned_array = vertexFactory.create_geometry(vertices.size() + j, send_array, 1.00f, 1, 1);
                segment_vertices.add((Vertex) returned_array.get(0));
            }
            for(int j = 0; j < cell.getCoordinates().length - 2; j++) {
                OurSegment segmentFactory = new OurSegment();
                ArrayList<Vertex> inputVertex = new ArrayList<>();
                inputVertex.add(segment_vertices.get(j));
                inputVertex.add(segment_vertices.get(j+1));

                ArrayList<Object> inputVertex_objects = inputVertex.stream()
                        .map(s -> (Object) s)
                        .collect(Collectors.toCollection(ArrayList::new));

                ArrayList returned_array = segmentFactory.create_geometry(segment_vertices.size() + j, inputVertex_objects, 1.00f, 1, 1);
                polygon_segments.add((Segment) returned_array.get(0));
            }
            OurPolygon polygonFactory = new OurPolygon();
            // returns an ArrayList with a Polygon and Vertex (the centroid) object
            // TODO fix this centroid vertex calculation cuz it isn't accurate (Baoze)

            ArrayList<Object> polygon_segments_objects = polygon_segments.stream()
                    .map(s -> (Object) s)
                    .collect(Collectors.toCollection(ArrayList::new));

            ArrayList<Object> return_array = polygonFactory.create_geometry(polygons.size(), polygon_segments_objects,  1.00f, 1, vertices.size() + cell.getCoordinates().length);
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
