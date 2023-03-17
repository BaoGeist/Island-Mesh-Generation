package islandADT.Elevation;

import islandADT.Generator.RandomSeed;
import islandADT.GeometryContainer;
import islandADT.Wrappers.PolygonWrapper;
import islandADT.Wrappers.SegmentWrapper;
import islandADT.Wrappers.VertexWrapper;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class PlainsElevationFixture implements ElevationFixture{
    public void set_elevation(GeometryContainer geometryContainer) {
        Map<Integer, PolygonWrapper> polygons = geometryContainer.get_polygons();
        Map<Integer, SegmentWrapper> segments = geometryContainer.get_segments();
        Map<Integer, VertexWrapper> vertices = geometryContainer.get_vertices();

        int min_elevation = 1;
        int max_elevation = 10;

        CustomPrecisionModel precisionModel = new CustomPrecisionModel(1);

        for(Integer key: vertices.keySet()) {
            VertexWrapper v = vertices.get(key);
            if(v.isLandornah()) {
                int randomHeight = RandomSeed.randomInt(min_elevation, max_elevation);
                v.setHeight(randomHeight);
            } else {
                v.setHeight(0);
            }
        }

        for(Integer key: segments.keySet()) {
            SegmentWrapper s = segments.get(key);
            if(s.isLandornah()) {
                VertexWrapper head = vertices.get(s.getV1id());
                VertexWrapper tail = vertices.get(s.getV1id());
                Integer average_height = precisionModel.makePrecise((head.getHeight() + tail.getHeight())/2);
                s.setHeight(average_height);
            } else {
                s.setHeight(0);
            }
        }

        for(Integer key: polygons.keySet()) {
            PolygonWrapper p = polygons.get(key);
            if(p.isLandornah()) {
                double total = 0;
                int count = 0;
                for(Integer segment_id: p.getSegments_group()) {
                    SegmentWrapper s = segments.get(segment_id);
                    int segment_height = s.getHeight();
                    total += segment_height;
                    count++;
                }
                total /= count;
                p.setHeight(precisionModel.makePrecise(total));
            } else {
                p.setHeight(0);
            }
        }
    }
}
