package islandADT.Container;

import islandADT.Elevation.CustomPrecisionModel;
import islandADT.GeometryWrappers.PolygonWrapper;
import islandADT.GeometryWrappers.SegmentWrapper;
import islandADT.GeometryWrappers.VertexWrapper;

import static islandADT.Utils.MathUtils.distance_between_centre;

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

    public static List<PolygonWrapper> getPolygonsContainingVertex(GeometryContainer geometryContainer, VertexWrapper v){
        Map<Integer, PolygonWrapper> polygons = geometryContainer.get_polygons();
        List<PolygonWrapper> polygonsWithVertex = new ArrayList<>();
        for(PolygonWrapper polygon: polygons.values()){
            List<VertexWrapper> verticesOfPolygon = vertices_of_a_polygon(geometryContainer, polygon);
            if (verticesOfPolygon.contains(v)){
                polygonsWithVertex.add(polygon);
            }
        }
        return polygonsWithVertex;
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

    public static List<PolygonWrapper> getDryLandPolygons(GeometryContainer geometryContainer) {
        List<PolygonWrapper> return_array = new ArrayList<>();
        for (PolygonWrapper p: geometryContainer.get_polygons().values()){
            if(p.isLandornah() && !p.isWaterOrNah()) {
                return_array.add(p);
            }
        }
        return return_array;
    }

    public static int getFurthestLandVertex(GeometryContainer geometryContainer, CustomPrecisionModel precisionModel) {
        Map<Integer, VertexWrapper> vertices = geometryContainer.get_vertices();
        int furthest = 0;
        for(VertexWrapper v: vertices.values()) {
            if(v.isLandornah() && distance_between_centre(v.getCoords(), precisionModel) > furthest) {
                furthest = distance_between_centre(v.getCoords(), precisionModel);
            }
        }
        return furthest;
    }

    public static PolygonWrapper getPolygonFromCentroid(GeometryContainer geometryContainer, int id) {
        Map<Integer, PolygonWrapper> polygons = geometryContainer.get_polygons();
        for(PolygonWrapper polygon : polygons.values()) {
            if(polygon.getId_centroid() == id) {
                return polygon;
            }
        }
        return null;
    }

    public static double[] getMoistureRange(GeometryContainer geometryContainer) {
        List<PolygonWrapper> polygons = getDryLandPolygons(geometryContainer);
        double min = Double.MAX_VALUE, max = Double.MIN_VALUE;
        for(PolygonWrapper polygon: polygons) {
            double moisture = polygon.getMoisture();
            if(moisture != 20) {
                min = Math.min(moisture, min);
                max = Math.max(moisture, max);
            }
        }
        return new double[]{min, max};
    }
}
