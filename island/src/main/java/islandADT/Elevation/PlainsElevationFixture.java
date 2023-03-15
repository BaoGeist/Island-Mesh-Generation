package islandADT.Elevation;

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

        Random random = new Random();

        CustomPrecisionModel precisionModel = new CustomPrecisionModel(1);

        //NOTE sets vertices and segments to land if polygons are land
        //TODO B should be moved to when land tiles are set
        for(Integer key: polygons.keySet()) {
            PolygonWrapper p = polygons.get(key);
            if(p.isLandornah()) {
                List<Integer> p_segments = p.getSegments_group();
                System.out.println(p_segments);
                for(Integer segment_id: p_segments) {
                    SegmentWrapper s = segments.get(segment_id);
                    //TODO B make a better method name/implementation
                    s.setLandornah(true);
                    System.out.println("true");
                    vertices.get(s.getV1id()).setLandornah(true);
                    vertices.get(s.getV2id()).setLandornah(true);
                }
            }
        }

        for(Integer key: vertices.keySet()) {
            VertexWrapper v = vertices.get(key);
            if(v.isLandornah()) {
                int randomHeight = random.nextInt(min_elevation, max_elevation);
                v.setHeight(randomHeight);
            } else {
                //FIXME B default height is 0
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
                //FIXME B default height is 0
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
                //FIXME B ditto
                p.setHeight(0);
            }
        }
    }
}
