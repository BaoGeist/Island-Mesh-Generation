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
        int max_elevation = 5;

        Random random = new Random();

        //NOTE sets vertices and segments to land if polygons are land
        //TODO B should be moved to when land tiles are set
        for(Integer key: polygons.keySet()) {
            PolygonWrapper p = polygons.get(key);
            if(p.isLandornah()) {
                List<Integer> p_segments = p.getSegments_group();
                for(Integer segment_id: p_segments) {
                    SegmentWrapper s = segments.get(segment_id);
                    //TODO B make a better method name/implementation
                    s.setLandornah(true);
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
                //NOTE
                v.setHeight(0);
            }

        }
    }
}
