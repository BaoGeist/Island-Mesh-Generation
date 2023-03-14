package islandADT.Exporter;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import islandADT.Wrappers.SegmentTypeWrapper;
import meshcore.ADT.OurSegment;

import java.util.ArrayList;
import java.util.Arrays;

public class OurSegmentIsland{
    public Structs.Segment create_geometry(int v1id, int v2id, SegmentTypeWrapper segmentTypeWrapper, int height) {
        int[] color = segmentTypeWrapper.getColor();
        int thickness = segmentTypeWrapper.getFlow();
        Structs.Property color_p = Structs.Property.newBuilder().setKey("rgb_color").setValue(Arrays.toString(color)).build();
        Structs.Property thickness_p = Structs.Property.newBuilder().setKey("thicc").setValue(String.valueOf(thickness)).build();
        Structs.Property height_p = Structs.Property.newBuilder().setKey("height").setValue(String.valueOf(height)).build();
        Structs.Segment segment = Structs.Segment.newBuilder().setV1Idx(v1id).setV2Idx(v2id).addProperties(color_p).addProperties(thickness_p).build();
        return segment;
    }
}
