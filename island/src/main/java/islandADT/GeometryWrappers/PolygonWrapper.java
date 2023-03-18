package islandADT.GeometryWrappers;

import islandADT.TypeWrappers.TileTypeWrapper;

import java.util.ArrayList;
import java.util.List;

public class PolygonWrapper {
    private int id_polygon;
    private ArrayList<float[]> x_y_coords;
    private List<Integer> neighbours;
    private int id_centroid;
    private TileTypeWrapper tileType;
    private List<Integer> segments_group;
    // set this boolean somewhere
    private boolean landornah;
    private int height;

    public PolygonWrapper(int id_polygon, ArrayList<float[]> x_y_coords, List<Integer> neighbours, int id_centroid, List<Integer> segments_group) {
        this.id_polygon = id_polygon;
        this.x_y_coords = x_y_coords;
        this.neighbours = neighbours;
        this.id_centroid = id_centroid;
        this.segments_group = segments_group;
    }

    public List<Integer> getSegments_group() {
        return segments_group;
    }

    public TileTypeWrapper getTileType() {
        return tileType;
    }

    public void setTileType(TileTypeWrapper tileType) {
        this.tileType = tileType;
    }

    public int get_id() {return this.id_polygon;}

    public ArrayList<float[]> getX_y_coords() {
        return x_y_coords;
    }

    public List<Integer> getNeighbours() {
        return neighbours;
    }

    public int getId_centroid() {
        return id_centroid;
    }

    public List<Integer> get_neighbours() {return this.neighbours;}

    public boolean isLandornah() {
        return landornah;
    }

    public void setLandornah(boolean landornah) {
        this.landornah = landornah;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}