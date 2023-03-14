package islandADT.Wrappers;


//TODO B add a library for all this and TileTypeWrapper so it's extendable
public class SegmentTypeWrapper {
    public enum SegmentType {
        Water, NotWater
    }

    private SegmentType segmentType;
    private int[] color = new int[3];
    private int flow = 1;

    public SegmentTypeWrapper(SegmentType segmentType) {
        this.segmentType = segmentType;
        color_decide();
    }

    private void color_decide() {
        switch (segmentType) {
            case NotWater:
                color[0] = 0;
                color[1] = 0;
                color[2] = 0;
                break;
            case Water:
                color[0] = 0;
                color[1] = 105;
                color[2] = 148;
                break;
            default:
                color[0] = 0;
                color[1] = 0;
                color[2] = 0;
        }
    }

    public SegmentType getSegmentType() {
        return segmentType;
    }

    public void setSegmentType(SegmentType segmentType) {
        this.segmentType = segmentType;
    }

    public int[] getColor() {
        return color;
    }

    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }
}
