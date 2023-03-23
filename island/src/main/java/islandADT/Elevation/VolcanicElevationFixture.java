package islandADT.Elevation;

import islandADT.Generator.RandomSeed;
import islandADT.GeometryContainer;
import islandADT.GeometryWrappers.PolygonWrapper;
import islandADT.GeometryWrappers.SegmentWrapper;
import islandADT.GeometryWrappers.VertexWrapper;

import java.util.Map;

import static islandADT.GeometryContainerCalculator.getFurthestLandVertex;
import static islandADT.Utils.MathUtils.*;

public class VolcanicElevationFixture implements ElevationFixture{
    CustomPrecisionModel precisionModel = new CustomPrecisionModel(1);

    private int height_from_center(VertexWrapper v, int min, int max, int furthest, double[] peak) {
        double[] coords = v.getCoords();
        double distance = distance_between_points(coords, peak);
        int height = (int) (max - distance * max/furthest);
        int random_adjustment = RandomSeed.randomInt(-3, 3);
        return Math.max(height + random_adjustment, min + random_adjustment);
    }

    private double[] highest_vertex(int furthest) {
        double peak_distance = RandomSeed.randomDouble(furthest*0.8);
        double angle = RandomSeed.randomDouble(0, 2*Math.PI);
        double[] peak_coord = cartesian_from_polar(new double[]{peak_distance, angle});
        return peak_coord;
    }


    public void set_elevation(GeometryContainer geometryContainer) {
        Map<Integer, PolygonWrapper> polygons = geometryContainer.get_polygons();
        Map<Integer, SegmentWrapper> segments = geometryContainer.get_segments();
        Map<Integer, VertexWrapper> vertices = geometryContainer.get_vertices();

        int min_elevation = RandomSeed.randomInt(1, 100);
        int max_elevation = RandomSeed.randomInt(500,600);
        int furthest_vertex = getFurthestLandVertex(geometryContainer, precisionModel);
        double[] peak = highest_vertex(furthest_vertex);

        for (Integer key : vertices.keySet()) {
            VertexWrapper v = vertices.get(key);
            if (v.isLandornah()) {
                int height = height_from_center(v, min_elevation, max_elevation, furthest_vertex, peak);
                v.setHeight(height);
            } else {
                v.setHeight(0);
            }
        }

        for (Integer key : segments.keySet()) {
            SegmentWrapper s = segments.get(key);
            if (s.isLandornah()) {
                VertexWrapper head = vertices.get(s.getV1id());
                VertexWrapper tail = vertices.get(s.getV1id());
                Integer average_height = precisionModel.makePrecise((head.getHeight() + tail.getHeight()) / 2);
                s.setHeight(average_height);
            } else {
                s.setHeight(0);
            }
        }

        for (Integer key : polygons.keySet()) {
            PolygonWrapper p = polygons.get(key);
            if (p.isLandornah()) {
                double total = 0;
                int count = 0;
                for (Integer segment_id : p.getSegments_group()) {
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
