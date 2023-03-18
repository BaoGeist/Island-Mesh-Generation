package islandADT.GeometryWrappers;

import islandADT.TypeWrappers.SegmentTypeWrapper;

public class SegmentWrapper {
    private int id_segment;
    private int v1id;
    private int v2id;
    private boolean landornah;
    private SegmentTypeWrapper segmentTypeWrapper;
    private int height = 0;

    public SegmentWrapper(int id_segment, int v1id, int v2id) {
        this.id_segment = id_segment;
        this.v1id = v1id;
        this.v2id = v2id;
    }

    public SegmentTypeWrapper getSegmentTypeWrapper() {
        return segmentTypeWrapper;
    }

    public int getV1id() {
        return v1id;
    }

    public int getV2id() {
        return v2id;
    }

    public void setSegmentTypeWrapper(SegmentTypeWrapper segmentTypeWrapper) {
        this.segmentTypeWrapper = segmentTypeWrapper;
    }

    public int get_id() {return this.id_segment;}

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
