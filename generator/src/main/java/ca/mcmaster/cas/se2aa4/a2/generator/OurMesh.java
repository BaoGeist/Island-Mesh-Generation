package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OurMesh {
    private int width;
    private int height;
    private int square_size;
    private Structs.Vertex[][] grid;
    private float alpha_entry;
    private int thickness;
    private ArrayList<Structs.Vertex> vertices;
    private ArrayList<Structs.Segment> vertical_segments;
    private ArrayList<Structs.Segment> horizontal_segments;
    private ArrayList<Structs.Polygon> polygons;

    private ArrayList<Structs.Vertex> centroids = new ArrayList<>();
    public OurMesh(int width, int height, int square_size, float alpha_entry, int thickness, ArrayList<Structs.Vertex> vertices, ArrayList<Structs.Segment> horizontal_segments, ArrayList<Structs.Segment> vertical_segments ,ArrayList<Structs.Polygon> polygons) {
        this.width = width;
        this.height = height;
        this.square_size = square_size;
        this.alpha_entry = alpha_entry;
        this.thickness = thickness;
        this.vertices = vertices;
        this.horizontal_segments = horizontal_segments;
        this.vertical_segments = vertical_segments;
        this.polygons = polygons;
        Structs.Vertex[][] new_grid = new Structs.Vertex[this.width][this.height];
        this.grid = new_grid;
    }
    private void generateVertices(){
        // Creates all vertices and stores them in an ArrayList
        for(int x = 0; x < width; x += square_size){
            for (int y = 0; y < height; y += square_size) {
                OurVertex vertex = new OurVertex();
                Structs.Vertex newVertex = vertex.makeVertex((double)x, (double)y, vertices.size());
                vertices.add(newVertex);
            }
        }
    }

    private void generateSegments(){
        // Creates all horizontal segments and stores them in an ArrayList
        for (int x = 0; x+1 < width/square_size; x += 1) {
            for (int y = 0; y < height/square_size; y += 1) {
                OurSegment segment = new OurSegment();
                Structs.Segment newSegment = segment.create_segment(vertices.get(y + x*20), vertices.get((y+x*20)+20), alpha_entry, thickness, horizontal_segments.size());
                horizontal_segments.add(newSegment);
            }
        }
        // Creates all vertical segments and stores them in an ArrayList
        for (int x = 0; x < width/square_size; x += 1) {
            for (int y = 0; y < height/square_size; y += 1) {
                if (y != 19) {
                    OurSegment segment = new OurSegment();
                    Structs.Segment newSegment = segment.create_segment(vertices.get(y + x*20), vertices.get((y+x*20)+1), alpha_entry, thickness, vertical_segments.size());
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

                PolygonSegments.add(horizontal_segments.get(iteratorh));
                PolygonSegments.add(vertical_segments.get(iteratorv+19));
                PolygonSegments.add(horizontal_segments.get(iteratorh+1));
                PolygonSegments.add(vertical_segments.get(iteratorv));

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

                ArrayList<Object> return_array = polygonFactory.create_polygon(polygons.size(), vertices.size(), PolygonSegments);
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

    private ArrayList<Integer> setNeighbours(){ // Todo - Check if this works
        ArrayList<Integer> PolygonNeighbours = new ArrayList<>();

        int CurrentID = polygons.size();
        int row = CurrentID % 19;
        int column = CurrentID / 19;

        if (column > 0){
            PolygonNeighbours.add(CurrentID - 19); // Add left neighbour
        }
        if (column < 18){
            PolygonNeighbours.add(CurrentID + 19); // Add right neighbour
        }
        if (row > 0){
            PolygonNeighbours.add(CurrentID - 1); // Add top neighbour
        }
        if (row < 18){
            PolygonNeighbours.add(CurrentID + 1); // Add bottom neighbour
        }

        PolygonNeighbours.removeIf(id -> id < 0 || id > 359);

        return PolygonNeighbours;
    }

    private int extractID(List<Structs.Property> properties) {
        String val = "0";
        for(Structs.Property p: properties) {
            if (p.getKey().equals("id")) {
//                System.out.println(p.getValue());
                val = p.getValue();
            }
        }
        return Integer.parseInt(val);
    }

    public double[] extractSegmentMiddle(List<Structs.Property> properties) {
        String val = null;
        for(Structs.Property p: properties) {
            if (p.getKey().equals("middle")) {
//                System.out.println(p.getValue());
                val = p.getValue();
            }
        }
        return parse_string_to_array_int(val);
    }

    private double[] parse_string_to_array_int(String parse) {

        String[] array_return = parse.split(",", -1);
        double[] array_return_int = new double[array_return.length];
        for(int i = 0; i < array_return_int.length; i++) {
            array_return_int[i] = Double.parseDouble(array_return[i]);
        }
        return array_return_int;
    }


}