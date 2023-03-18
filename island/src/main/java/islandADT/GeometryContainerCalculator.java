package islandADT;

import islandADT.GeometryWrappers.PolygonWrapper;
import islandADT.GeometryWrappers.SegmentWrapper;
import islandADT.GeometryWrappers.VertexWrapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeometryContainerCalculator {
    public static boolean polygon_no_ocean_neighbours(GeometryContainer geometryContainer, int i) {
        Map<Integer, PolygonWrapper> polygons = geometryContainer.get_polygons();
        PolygonWrapper p = polygons.get(i);
        List neighbours = p.get_neighbours();
        for(Object n_id: neighbours) {
            int n_id_int = (Integer) n_id;
            if(! polygons.get(n_id_int).isLandornah()) {
                return false;
            }
        }
        return true;
    }

    public static Map<Integer, VertexWrapper> getVertexNeighbors(GeometryContainer geometryContainer, VertexWrapper v){
        Map<Integer, VertexWrapper> vertexNeighbors = new HashMap<>();
        Map<Integer, SegmentWrapper> segments = geometryContainer.get_segments();
        Map<Integer, VertexWrapper> vertices = geometryContainer.get_vertices();

        for (SegmentWrapper seg: segments.values()){

            int v1_id = seg.getV1id();
            int v2_id = seg.getV2id();

            if (v.get_id() == v1_id){
                vertexNeighbors.put(v2_id, vertices.get(v2_id));
            } else if (v.get_id() == v2_id){
                vertexNeighbors.put(v1_id, vertices.get(v1_id));
            }
        }
        return vertexNeighbors;
    }
}
