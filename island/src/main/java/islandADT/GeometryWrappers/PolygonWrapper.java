package islandADT.GeometryWrappers;

import islandADT.TypeWrappers.TileTypeWrapper;

import java.util.ArrayList;
import java.util.List;

public class PolygonWrapper {
    private int id_polygon;
    private ArrayList<float[]> x_y_coords;
    private List<Integer> neighbours;
    private int id_centroid;
    private TileTypeWrapper[] tileType_group = new TileTypeWrapper[2];
    private List<Integer> segments_group;
    private double moisture;
    // set this boolean somewhere
    private int height;

    public PolygonWrapper(int id_polygon, ArrayList<float[]> x_y_coords, List<Integer> neighbours, int id_centroid, List<Integer> segments_group, TileTypeWrapper tileType) {
        this.id_polygon = id_polygon;
        this.x_y_coords = x_y_coords;
        this.neighbours = neighbours;
        this.id_centroid = id_centroid;
        this.segments_group = segments_group;
        this.tileType_group[0] = tileType;
        this.moisture = 0;
    }

    public List<Integer> getSegments_group() {
        return segments_group;
    }

    public TileTypeWrapper getTileType() {
        if(tileType_group[1] == null) {
            return tileType_group[0];
        } else {
            return tileType_group[1];
        }
    }

    public void setTileType(TileTypeWrapper tileType) {
        TileTypeWrapper Lake = new TileTypeWrapper("Lake");
        TileTypeWrapper Aquifer = new TileTypeWrapper("Aquifer");
        TileTypeWrapper Ocean = new TileTypeWrapper("Ocean");
        TileTypeWrapper Land = new TileTypeWrapper("Land");
        if(tileType.isEqualsMultiple(Lake, Aquifer, Ocean, Land)) {
            tileType_group[0] = tileType;
        }
        else {
            tileType_group[1] = tileType;
        }
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

    public boolean isLandornah() {return tileType_group[0].isLandOrNah(); }

    public boolean isWaterOrNah() {return tileType_group[0].isWaterOrNah(); }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getMoisture() {
        return this.moisture;    }

    public void setMoisture(double moisture) {
        this.moisture = moisture;
    }

}