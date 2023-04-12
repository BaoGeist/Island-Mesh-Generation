package islandADT.Urbanism;

import islandADT.Container.GeometryContainer;
import islandADT.GeometryWrappers.PointWrapper;
import islandADT.GeometryWrappers.PolygonWrapper;
import islandADT.GeometryWrappers.SegmentWrapper;
import islandADT.GeometryWrappers.VertexWrapper;
import islandADT.TypeWrappers.SegmentTypeWrapper;
import meshcore.Utils.MathUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static islandADT.Utils.MathUtils.distance_between_points;
import static meshcore.Utils.MathUtils.intersection_between_lines;

public class RoadEvaluator {
    List<SegmentWrapper> rivers = new ArrayList<>();
    public RoadEvaluator(GeometryContainer geometryContainer) {
        populate_rivers(geometryContainer);
    }
    public int calculate_road_weight(GeometryContainer geometryContainer, PolygonWrapper polygon, PolygonWrapper neighbour_polygon, VertexWrapper centroid, VertexWrapper neighbour) {
        int height_distance = Math.abs(polygon.getHeight() - neighbour_polygon.getHeight());
        double distance = distance_between_points(centroid.getCoords(), neighbour.getCoords());
        int riverCost = needs_bridge(centroid, neighbour, geometryContainer)? 10000 : 0;
        int weight = Math.max(1, (int) (5*height_distance) + riverCost);
        return weight;
    }

    private boolean needs_bridge(VertexWrapper centroid, VertexWrapper neighbour, GeometryContainer geometryContainer) {
        Map<Integer, VertexWrapper> vertices = geometryContainer.get_vertices();
        double x1 = centroid.getCoords()[0], y1 = centroid.getCoords()[1];
        double x2 = neighbour.getCoords()[0], y2 = neighbour.getCoords()[1];

        for(SegmentWrapper river: rivers) {
            VertexWrapper head = vertices.get(river.getV1id()), tail = vertices.get(river.getV2id());
            double x3 = head.getCoords()[0], y3 = head.getCoords()[1];
            double x4 = tail.getCoords()[0], y4 = tail.getCoords()[1];

            if(intersection_between_lines(x1, y1, x2, y2, x3, y3, x4, y4)) {
                return true;
            }
        }
        return false;
    }

    private void populate_rivers(GeometryContainer geometryContainer) {
        Map<Integer, SegmentWrapper> segments = geometryContainer.get_segments();

        for (SegmentWrapper s: segments.values()){
            if(s.getSegmentTypeWrapper().getFlow() > 0 && s.isWaterornah()) {
                rivers.add(s);
            }
        }
    }
}
