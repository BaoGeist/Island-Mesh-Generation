package islandADT.Wrappers;

public class VertexWrapper {
    private int id_vertex;
    private boolean centroid_vertex;
    private double[] coords;
    private boolean landornah = false;
    private int height = 0;

    //TODO B add structs.vertex as an instance variable if needed
    public VertexWrapper(int id_vertex, boolean centroid_vertex, double[] coords) {
        this.id_vertex = id_vertex;
        this.centroid_vertex = centroid_vertex;
        this.coords = coords;
    }

    //TODO B make this an interface if needed
    public int get_id() {return this.id_vertex;};

    public boolean isCentroid_vertex() {
        return centroid_vertex;
    }

    public double[] getCoords() {
        return coords;
    }

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