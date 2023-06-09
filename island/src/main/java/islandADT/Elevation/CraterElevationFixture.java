package islandADT.Elevation;

import islandADT.Generator.RandomSeed;
import islandADT.Container.GeometryContainer;
import islandADT.GeometryWrappers.PolygonWrapper;
import islandADT.GeometryWrappers.SegmentWrapper;
import islandADT.GeometryWrappers.VertexWrapper;

import java.util.Map;

import static islandADT.Container.GeometryContainerCalculator.getFurthestLandVertex;
import static islandADT.Utils.MathUtils.distance_between_centre;

public class CraterElevationFixture implements ElevationFixture{
    CustomPrecisionModel precisionModel = new CustomPrecisionModel(1);

    private int height_from_center(VertexWrapper v, int min, int max, int furthest) {
        double[] coords = v.getCoords();
        int distance = distance_between_centre(coords, precisionModel);
        int height = distance * max / furthest;
        RandomSeed instanceRandom = RandomSeed.getInstance();
        int random_adjustment = instanceRandom.randomInt(-3, 3);
        return height + random_adjustment;
    }
    public void set_elevation(GeometryContainer geometryContainer) {
        RandomSeed instanceRandom = RandomSeed.getInstance();
        Map<Integer, PolygonWrapper> polygons = geometryContainer.get_polygons();
        Map<Integer, SegmentWrapper> segments = geometryContainer.get_segments();
        Map<Integer, VertexWrapper> vertices = geometryContainer.get_vertices();


        int min_elevation = instanceRandom.randomInt(1, 100);
        int max_elevation = instanceRandom.randomInt(500,600);
        int furthest_vertex = getFurthestLandVertex(geometryContainer, precisionModel);

        for(Integer key: vertices.keySet()) {
            VertexWrapper v = vertices.get(key);
            if(v.isLandornah()) {
                int height = height_from_center(v, min_elevation, max_elevation, furthest_vertex);
                v.setHeight(height);
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
