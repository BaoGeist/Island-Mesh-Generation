package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import org.locationtech.jts.algorithm.ConvexHull;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.triangulate.VoronoiDiagramBuilder;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;
public class OurIrregular {

    PrecisionModel precisionModel = new PrecisionModel(PrecisionModel.FIXED);
    private int height;
    private int width;

    private int num_polygons;

    public OurIrregular(int width, int height, int num_polygons){
        this.width = width;
        this.height = height;
        this.num_polygons = num_polygons;
    };

    private ArrayList<Coordinate> generate_random_points(int number) {
        Random random = new Random(3);
        ArrayList<Coordinate> listCoordinates = new ArrayList<>();

        for (int i = 0; i < number; i++) {
            double x = random.nextDouble() * height;
            double y = random.nextDouble() * width;
            Coordinate randomCoordinate = new Coordinate(x, y);
            precisionModel.makePrecise(randomCoordinate);
            listCoordinates.add(randomCoordinate);
        }
        return listCoordinates;
    }

    private Geometry generate_voronoi(ArrayList<Coordinate> listCoordinates) {
        VoronoiDiagramBuilder newVoronoi = new VoronoiDiagramBuilder();
        GeometryFactory newFactory = new GeometryFactory();

        newVoronoi.setSites(listCoordinates);
        Geometry diagram = newVoronoi.getDiagram(newFactory);


        for(Coordinate c : diagram.getCoordinates()) {
            precisionModel.makePrecise(c);
        }

        // Create an envelope representing the 500x500 grid
        Envelope env = new Envelope(0, width, 0, height);

        // Crop the diagram to the envelope
        diagram = diagram.intersection(newFactory.toGeometry(env));

        return diagram;
    }

    private Coordinate calculate_lloyd_relaxation_single(Geometry notcell) {
        Coordinate[] lloydcoord = notcell.getCoordinates();
        ArrayList<Coordinate> triangle_centroids = new ArrayList<>();
        ArrayList<Double> triangle_areas = new ArrayList<>();
        int first_iterator = 1, second_iterator = 2;
        while (second_iterator <= lloydcoord.length - 2) {
            double area = MathUtils.calculate_area_of_triangle(lloydcoord[0], lloydcoord[first_iterator], lloydcoord[second_iterator]);
            Coordinate centroid = MathUtils.calculate_centroid_of_triangle(lloydcoord[0], lloydcoord[first_iterator], lloydcoord[second_iterator]);
            triangle_areas.add(area);
            triangle_centroids.add(centroid);
            first_iterator++;
            second_iterator++;
        }
        Coordinate new_centroid = MathUtils.calculate_new_centroid(triangle_areas, triangle_centroids);
        precisionModel.makePrecise(new_centroid);
        return new_centroid;
    }

    private ArrayList<Coordinate> calculate_lloyd_relaxation_multiple(Geometry oldPoints) {
        ArrayList<Coordinate> listVoronoied = new ArrayList<>();
        for(int i = 0; i < oldPoints.getNumGeometries(); i++) {
            Geometry notcell = oldPoints.getGeometryN(i);
            Coordinate new_centroid = calculate_lloyd_relaxation_single(notcell);
            precisionModel.makePrecise(new_centroid);
            listVoronoied.add(new_centroid);
        }
        return listVoronoied;
    }

    private static final int THICKNESS = 3;


    public Mesh generate() {
        ArrayList<Vertex> vertices = new ArrayList<>();
        ArrayList<Segment> segments = new ArrayList<>();
        ArrayList<Polygon> polygons = new ArrayList<>();
        ArrayList<Vertex> unique_vertices_object = new ArrayList<>();
        ArrayList<Segment> unique_segments_object = new ArrayList<>();
        ArrayList<Vertex> centroids = new ArrayList<>();

        int unique_vertices_counter = 0;
        int unique_segments_counter = 0;

        GeometryContainer meshContainer = new GeometryContainer();

        // create random points everywhere on the plane
        ArrayList<Coordinate> listCoordinates = generate_random_points(num_polygons);


        // voronoi diagram
        Geometry voronoiedPoints = generate_voronoi(listCoordinates);

        // lloyd relaxation
        int lloyd_number = 5;
        for(int i = 0; i < lloyd_number; i++) {
            listCoordinates = calculate_lloyd_relaxation_multiple(voronoiedPoints);
            voronoiedPoints = generate_voronoi(listCoordinates);
        }


        /// creating mesh
        // this loop runs for each voroinoi polygon
        for(int i = 0; i < voronoiedPoints.getNumGeometries(); i++) {
            Geometry cell = voronoiedPoints.getGeometryN(i);
            Geometry reorderedPolygon = new ConvexHull(cell).getConvexHull();
            ArrayList<Vertex> segment_vertices = new ArrayList<>();
            ArrayList<Segment> polygon_segments = new ArrayList<>();
            System.out.println("New polygon");
            // vertices
            for (int j = 0; j < reorderedPolygon.getCoordinates().length - 1; j++) {
                OurVertex vertexFactory = new OurVertex();
                ArrayList<Object> send_array = new ArrayList<>();
                send_array.add((float) reorderedPolygon.getCoordinates()[j].x);
                send_array.add((float) reorderedPolygon.getCoordinates()[j].y);
                System.out.println(send_array.toString());

                ArrayList<Object> returned_array = vertexFactory.create_geometry(unique_vertices_counter, send_array, 1.00f, 1, 1);

                // returns the created vertex if it is unique, returns an older vertex if it is not (unique in terms of coordinates)
                Vertex verified_vertex = meshContainer.check_unique_vertex( (Vertex) returned_array.get(0));

                // if the created vertex and verified vertex are different, then unique_vertices increments
                if(verified_vertex == returned_array.get(0)) {
                    unique_vertices_counter++;
                    unique_vertices_object.add(verified_vertex);

                }
                // all vertices are added to the vertices of the current segment
                segment_vertices.add(verified_vertex);
            }

            // segments
            for(int j = 0; j < reorderedPolygon.getCoordinates().length - 2; j++) {
                OurSegment segmentFactory = new OurSegment();
                ArrayList<Vertex> inputVertex = new ArrayList<>();
                inputVertex.add(segment_vertices.get(j));
                inputVertex.add(segment_vertices.get(j+1));

                ArrayList<Object> inputVertex_objects = inputVertex.stream()
                        .map(s -> (Object) s)
                        .collect(Collectors.toCollection(ArrayList::new));

                unique_segments_counter++;
                ArrayList returned_array = segmentFactory.create_geometry(unique_segments_counter, inputVertex_objects, 1.00f, 1, 1);
                Segment verified_segment = meshContainer.check_unique_segment((Segment) returned_array.get(0), unique_vertices_object);

                if(verified_segment == returned_array.get(0)) {
                    unique_segments_counter++;
                    unique_segments_object.add(verified_segment);
                }
                polygon_segments.add(verified_segment);

            }

            // polygon
            OurPolygon polygonFactory = new OurPolygon();
            // returns an ArrayList with a Polygon and Vertex (the centroid) object
            ArrayList<Object> polygon_segments_objects = polygon_segments.stream()
                    .map(s -> (Object) s)
                    .collect(Collectors.toCollection(ArrayList::new));

            ArrayList<Object> return_array = polygonFactory.create_geometry(polygons.size(), polygon_segments_objects,  1.00f, 1, unique_vertices_counter);
            polygons.add((Polygon) return_array.get(0));
            centroids.add((Vertex) return_array.get(1));

            // TODO compute neighbourhood relationships using Delaunay's triangulation

            vertices.addAll(segment_vertices);
            segments.addAll(polygon_segments);
            unique_vertices_object.add((Vertex) return_array.get(1));
            unique_vertices_counter++;
        }
        return Mesh.newBuilder().addAllVertices(unique_vertices_object).addAllSegments(segments).addAllPolygons(polygons).build();
        //polygons

    }
}
