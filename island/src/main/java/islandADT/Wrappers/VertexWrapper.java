package islandADT.Wrappers;

public class VertexWrapper {
    private int id_vertex;
    private boolean centroid_vertex;
    private double[] coords;

    //TODO add structs.vertex as an instance variable if needed
    public VertexWrapper(int id_vertex, boolean centroid_vertex, double[] coords) {
        this.id_vertex = id_vertex;
        this.centroid_vertex = centroid_vertex;
        this.coords = coords;
    }

    //TODO make this an interface if needed
    public int get_id() {return this.id_vertex;};
}
