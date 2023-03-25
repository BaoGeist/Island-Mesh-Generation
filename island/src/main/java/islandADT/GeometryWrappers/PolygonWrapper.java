package islandADT.GeometryWrappers;

import islandADT.TypeWrappers.TileTypeWrapper;
import islandADT.Water.Moisture;
import islandADT.Biomes.*;

import java.util.ArrayList;
import java.util.List;

public class PolygonWrapper {
    private int id_polygon;
    private ArrayList<float[]> x_y_coords;
    private List<Integer> neighbours;
    private int id_centroid;
    private TileTypeWrapper tileType;
    private List<Integer> segments_group;
    private Moisture moisture;
    // set this boolean somewhere
    private int height;
    private Biome biome_name;

    public PolygonWrapper(int id_polygon, ArrayList<float[]> x_y_coords, List<Integer> neighbours, int id_centroid, List<Integer> segments_group, TileTypeWrapper tileType, Biome biome) {
        this.id_polygon = id_polygon;
        this.x_y_coords = x_y_coords;
        this.neighbours = neighbours;
        this.id_centroid = id_centroid;
        this.segments_group = segments_group;
        this.tileType = tileType;
        this.moisture = new Moisture(0);
        this.biome = new Biome();
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

    public boolean isLandornah() {return tileType.isLandOrNah(); }

    public boolean isWaterOrNah() {return tileType.isWaterOrNah(); }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getMoisture() {
        return moisture.getValue();
    }

    public void setMoisture(double moisture) {
        this.moisture.setValue(moisture);
    }

    public void newMoisture(Moisture moisture) {this.moisture = moisture;}

    public String getBiome(double m, int h) {
        return biome_name.setBiome(m, h);
    }
}