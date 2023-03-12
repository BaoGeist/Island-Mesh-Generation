package islandADT.Wrappers;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.ArrayList;
import java.util.List;

public class PolygonWrapper {
    private int id_polygon;
    private ArrayList<float[]> x_y_coords;
    private List<Integer> neighbours;

    //TODO add structs.polygon as an instance variable if needed
    public PolygonWrapper(int id_polygon, ArrayList<float[]> x_y_coords, List<Integer> neighbours) {
        this.id_polygon = id_polygon;
        this.x_y_coords = x_y_coords;
        this.neighbours = neighbours;
    }

    public int get_id() {return this.id_polygon;}

    public List<Integer> get_neighbours() {return this.neighbours;}

}