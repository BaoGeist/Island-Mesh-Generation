package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OurMesh {
    private int width;
    private int height;
    private int square_size;
    private Structs.Vertex[][] grid;
    private float alpha_entry;
    private int thickness;
    private ArrayList<Structs.Vertex> vertices = new ArrayList<>();
    private ArrayList<Structs.Segment> vertical_segments = new ArrayList<>();
    private ArrayList<Structs.Segment> horizontal_segments = new ArrayList<>();
    private ArrayList<Structs.Polygon> polygons = new ArrayList<>();

    private ArrayList<Structs.Vertex> centroids = new ArrayList<>();
    public OurMesh(int width, int height, int square_size, float alpha_entry, int thickness) {
        this.width = width;
        this.height = height;
        this.square_size = square_size;
        this.alpha_entry = alpha_entry;
        this.thickness = thickness;
        Structs.Vertex[][] new_grid = new Structs.Vertex[this.width][this.height];
        this.grid = new_grid;
    }
    private void generateVertices(){
        // Creates all vertices and stores them in an ArrayList
        for(int x = 0; x < width; x += square_size){
            for (int y = 0; y < height; y += square_size) {
                OurVertex vertex = new OurVertex();
                ArrayList<Object> send_array = new ArrayList<>();
                send_array.add((float) x);
                send_array.add((float) y);
                ArrayList<Object> returned_array = vertex.create_geometry(vertices.size(), send_array, 1.00f, 1, 1);
                Structs.Vertex newVertex = (Structs.Vertex) returned_array.get(0);
                vertices.add(newVertex);
            }
        }
    }

    private void generateSegments(){
        // Creates all horizontal segments and stores them in an ArrayList
        for (int x = 0; x+1 < width/square_size; x += 1) {
            for (int y = 0; y < height/square_size; y += 1) {
                OurSegment segment = new OurSegment();
                ArrayList inputVertices = new ArrayList();
                inputVertices.add(vertices.get(y + x*height/square_size));
                inputVertices.add(vertices.get((y+x*height/square_size)+height/square_size));
                ArrayList<Object> returned_array = segment.create_geometry(horizontal_segments.size(), inputVertices, alpha_entry, thickness, 1);
                Structs.Segment newSegment = (Structs.Segment) returned_array.get(0);
                horizontal_segments.add(newSegment);
            }
        }
        // Creates all vertical segments and stores them in an ArrayList
        for (int x = 0; x < width/square_size; x += 1) {
            for (int y = 0; y < height/square_size; y += 1) {
                if (y != (height/square_size - 1)) {
                    OurSegment segment = new OurSegment();
                    ArrayList inputVertices = new ArrayList();
                    inputVertices.add(vertices.get(y + x*height/square_size));
                    inputVertices.add(vertices.get((y+x*height/square_size)+1));
                    ArrayList<Object> returned_array = segment.create_geometry(vertical_segments.size(), inputVertices, alpha_entry, thickness, 1);
                    Structs.Segment newSegment = (Structs.Segment) returned_array.get(0);
                    vertical_segments.add(newSegment);
                }
            }
        }
    }

    private void generatePolygons(){
        // Creates all polygons
        int iteratorh = 0, iteratorv = 0;
        for (int x = 0; x+1 < width/square_size; x += 1) {
            for (int y = 0; y+1 < height/square_size; y += 1) {
                ArrayList<Structs.Segment> PolygonSegments = new ArrayList<>();

                PolygonSegments.add((Structs.Segment) horizontal_segments.get(iteratorh));
                PolygonSegments.add((Structs.Segment) vertical_segments.get(iteratorv+(height/square_size)-1));
                PolygonSegments.add((Structs.Segment) horizontal_segments.get(iteratorh+1));
                PolygonSegments.add((Structs.Segment) vertical_segments.get(iteratorv));

//                System.out.println("new");
//                System.out.println(extractID(horizontal_segments.get(iteratorh).getPropertiesList()));
//                System.out.println(extractID(horizontal_segments.get(iteratorh+1).getPropertiesList()));
//                System.out.println(extractID(vertical_segments.get(iteratorv).getPropertiesList()));
//                System.out.println(extractID(vertical_segments.get(iteratorv+19).getPropertiesList()));
//                System.out.println(Arrays.toString(extractSegmentMiddle(horizontal_segments.get(iteratorh).getPropertiesList())));
//                System.out.println(Arrays.toString(extractSegmentMiddle(horizontal_segments.get(iteratorh+1).getPropertiesList())));
//                System.out.println(Arrays.toString(extractSegmentMiddle(vertical_segments.get(iteratorv).getPropertiesList())));
//                System.out.println(Arrays.toString(extractSegmentMiddle(vertical_segments.get(iteratorv+19).getPropertiesList())));

                OurPolygon polygonFactory = new OurPolygon();

                ArrayList<Object> PolygonSegmentsObjects = PolygonSegments.stream()
                        .map(s -> (Object) s)
                        .collect(Collectors.toCollection(ArrayList::new));

                ArrayList<Object> return_array = polygonFactory.create_geometry(polygons.size(), PolygonSegmentsObjects, alpha_entry, thickness, vertices.size());
                Structs.Polygon polygon1 = (Structs.Polygon) return_array.get(0);
                polygonFactory.neighbours_id = setNeighbours();

                vertices.add((Structs.Vertex) return_array.get(1));
                polygons.add(polygon1);

                iteratorh++;
                iteratorv++;
            }
            iteratorh++;
        }
    }

    public Structs.Mesh generate() {
        generateVertices();
        generateSegments();
        generatePolygons();
        //System.out.println(polygons.size());
        vertical_segments.addAll(horizontal_segments);
        return Structs.Mesh.newBuilder().addAllVertices(vertices).addAllSegments(vertical_segments).addAllPolygons(polygons).build();
    }

    private ArrayList<Integer> setNeighbours(){
        ArrayList<Integer> PolygonNeighbours = new ArrayList<>();

        int CurrentID = polygons.size();
        int row = CurrentID % (width/square_size - 1);
        int column = CurrentID / (height/square_size - 1);

        if (column > 0){
            PolygonNeighbours.add(CurrentID - width/square_size - 1); // Add left neighbour
        }
        if (column < width/square_size - 2){
            PolygonNeighbours.add(CurrentID + width/square_size - 1); // Add right neighbour
        }
        if (row > 0){
            PolygonNeighbours.add(CurrentID - 1); // Add top neighbour
        }
        if (row < width/square_size - 1){
            PolygonNeighbours.add(CurrentID + 1); // Add bottom neighbour
        }

        PolygonNeighbours.removeIf(id -> id < 0 || id > width*height-1);

        return PolygonNeighbours;
    }


}