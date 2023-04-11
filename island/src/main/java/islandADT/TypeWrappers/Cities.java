package islandADT.TypeWrappers;

public class Cities {

    int source;
    int[] sink;
    public Cities(int source, int[] sink) {
        this.source = source;
        this.sink = sink;
    }

    public int getCenter() {
        return source;
    }

    public int[] getOthers() {
        return sink;
    }
}
