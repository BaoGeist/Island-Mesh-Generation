package islandADT.Elevation;

import islandADT.Generator.RandomSeed;
import islandADT.GeometryContainer;
import islandADT.GeometryContainerCalculator;
import islandADT.GeometryWrappers.PolygonWrapper;
import islandADT.GeometryWrappers.SegmentWrapper;
import islandADT.GeometryWrappers.VertexWrapper;
import islandADT.Utils.MathUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlainsElevationFixture implements ElevationFixture{
    private void vertex_height_relaxation(GeometryContainer geometryContainer) {
        Map<Integer, VertexWrapper> vertices = geometryContainer.get_vertices();
        for(Integer key: vertices.keySet()) {
            VertexWrapper v = vertices.get(key);
            if(v.isLandornah()) {
                Map<Integer, VertexWrapper> vertex_neighbours = GeometryContainerCalculator.getVertexNeighbors(geometryContainer, v);

                List<Integer> heightList = vertex_neighbours.values().stream()
                        .map(VertexWrapper::getHeight)
                        .collect(Collectors.toList());

                int new_height = MathUtils.average(heightList);
                v.setHeight(new_height);
            }
        }
    }

    public void set_elevation(GeometryContainer geometryContainer) {
        RandomSeed instanceRandom = RandomSeed.getInstance();

        Map<Integer, PolygonWrapper> polygons = geometryContainer.get_polygons();
        Map<Integer, SegmentWrapper> segments = geometryContainer.get_segments();
        Map<Integer, VertexWrapper> vertices = geometryContainer.get_vertices();

        int min_elevation = 100;
        int max_elevation = 300;

        CustomPrecisionModel precisionModel = new CustomPrecisionModel(1);

        for(Integer key: vertices.keySet()) {
            VertexWrapper v = vertices.get(key);
            if(v.isLandornah()) {
                int randomHeight = instanceRandom.randomInt(min_elevation, max_elevation);
                v.setHeight(randomHeight);
            } else {
                v.setHeight(0);
            }
        }


        int vertex_relaxation_value = 5;
        for(int i = 0; i < vertex_relaxation_value; i++) {
            vertex_height_relaxation(geometryContainer);
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
