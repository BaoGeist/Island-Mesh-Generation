package islandADT.Exporter;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import islandADT.TypeWrappers.SegmentTypeWrapper;
import islandADT.GeometryWrappers.SegmentWrapper;

public class OurSegmentExporter implements Exporter<SegmentWrapper, Structs.Segment> {
    public Structs.Segment export(SegmentWrapper s) {
        SegmentTypeWrapper segmentTypeWrapper = s.getSegmentTypeWrapper();
        int v1id = s.getV1id();
        int v2id = s.getV2id();
        int height = s.getHeight();
        OurSegmentIsland segmentIslandFactory = new OurSegmentIsland();
        return segmentIslandFactory.create_geometry(v1id, v2id, segmentTypeWrapper, height);
    }
}
