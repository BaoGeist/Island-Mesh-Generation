package islandADT;

import islandADT.GeometryWrappers.PolygonWrapper;
import islandADT.GeometryWrappers.SegmentWrapper;
import islandADT.GeometryWrappers.VertexWrapper;

import java.util.*;

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

    public static List<PolygonWrapper> polygon_neighbours_objects(GeometryContainer geometryContainer, int i) {
        Map<Integer, PolygonWrapper> polygons = geometryContainer.get_polygons();
        List<PolygonWrapper> neighbours_objects = new ArrayList<PolygonWrapper>();
        PolygonWrapper p = polygons.get(i);
        List<Integer> neighbours = p.get_neighbours();
        for(Object n_id: neighbours) {
            neighbours_objects.add(polygons.get(n_id));

        }
        return neighbours_objects;
    }

    public static List<VertexWrapper> vertices_of_a_polygon(GeometryContainer geometryContainer, PolygonWrapper p) {
        Map<Integer, VertexWrapper> vertices = geometryContainer.get_vertices();
        Map<Integer, SegmentWrapper> segments = geometryContainer.get_segments();

        Set<VertexWrapper> set = new LinkedHashSet<>();
        List<VertexWrapper> polygon_vertices = new ArrayList<>(set);
        for(int i: p.getSegments_group()) {
            SegmentWrapper s = segments.get(i);
            polygon_vertices.add(vertices.get(s.getV1id()));
            polygon_vertices.add(vertices.get(s.getV2id()));
        }
        return polygon_vertices;
    }

    public static List<SegmentWrapper> segments_of_a_polygon(GeometryContainer geometryContainer, PolygonWrapper p) {
        Map<Integer, SegmentWrapper> segments = geometryContainer.get_segments();

        List<SegmentWrapper> polygon_segments = new ArrayList<>();
        for(int i: p.getSegments_group()) {
            polygon_segments.add(segments.get(i));
        }
        return polygon_segments;
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
