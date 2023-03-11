package islandADT.Wrappers;

public class SegmentWrapper {
    private int id_segment;
    private int v1id;
    private int v2id;

    //TODO add structs.segment as an instance variable if needed
    public SegmentWrapper(int id_segment, int v1id, int v2id) {
        this.id_segment = id_segment;
        this.v1id = v1id;
        this.v2id = v2id;
    }

    public int get_id() {return this.id_segment;}
}
