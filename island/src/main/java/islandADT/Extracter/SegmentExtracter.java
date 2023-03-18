package islandADT.Extracter;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import islandADT.TypeWrappers.SegmentTypeWrapper;
import islandADT.GeometryWrappers.SegmentWrapper;
import meshcore.Utils.PropertyUtils;

public class SegmentExtracter implements Extracter<Structs.Segment, SegmentWrapper> {
    public SegmentWrapper extract(Structs.Segment oldSegment) {
        int id_segment = PropertyUtils.extractID(oldSegment.getPropertiesList());
        int v1id = oldSegment.getV1Idx();
        int v2id = oldSegment.getV2Idx();
        SegmentWrapper newSegment = new SegmentWrapper(id_segment, v1id, v2id);

        SegmentTypeWrapper segmentTypeWrapper = new SegmentTypeWrapper(SegmentTypeWrapper.SegmentType.Water);
        newSegment.setSegmentTypeWrapper(segmentTypeWrapper);
        return newSegment;
    }
}
