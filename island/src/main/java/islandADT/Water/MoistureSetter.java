package islandADT.Water;

import islandADT.GeometryContainer;
import islandADT.GeometryContainerCalculator;
import islandADT.GeometryWrappers.PolygonWrapper;
import islandADT.GeometryWrappers.SegmentWrapper;
import islandADT.GeometryWrappers.VertexWrapper;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import java.util.HashMap;
import java.util.Map;

public class MoistureSetter {
    private static Map<Point, Double> waterSources = new HashMap<>();

    public static void setWaterSources(GeometryContainer geometryContainer) {
        Map<Integer, PolygonWrapper> polygons = geometryContainer.get_polygons();
        Map<Integer, SegmentWrapper> segments = geometryContainer.get_segments();
        Map<Integer, VertexWrapper> vertices = geometryContainer.get_vertices();

        for(PolygonWrapper p: polygons.values()) {
            if(p.isWaterOrNah()) {
                VertexWrapper v = vertices.get(p.getId_centroid());
                Coordinate v_coord = new Coordinate(v.getCoords()[0], v.getCoords()[1]);
                GeometryFactory geometryFactory = new GeometryFactory();
                Point point = geometryFactory.createPoint(v_coord);

                waterSources.put(point, p.getMoisture());
            }
        }

        for (SegmentWrapper s: segments.values()){
            if(s.getSegmentTypeWrapper().getFlow() > 0) {
                VertexWrapper v = vertices.get(s.getV1id());
                VertexWrapper v2 = vertices.get(s.getV2id());

                Coordinate v_coord = new Coordinate((v.getCoords()[0]+v2.getCoords()[0])/2, (v.getCoords()[1]+v2.getCoords()[1])/2);
                GeometryFactory geometryFactory = new GeometryFactory();
                Point point = geometryFactory.createPoint(v_coord);

                waterSources.put(point, (double)s.getSegmentTypeWrapper().getFlow()*5);
            }
        }
    }

    public static void calculateMoisture(GeometryContainer geometryContainer) {
        Map<Integer, VertexWrapper> vertices = geometryContainer.get_vertices();
        for(PolygonWrapper p: GeometryContainerCalculator.getDryLandPolygons(geometryContainer)) {
            double totalMoisture = 0.0;

            for(Map.Entry<Point, Double> entry : waterSources.entrySet()) {
                Point waterSource = entry.getKey();
                double moisture = entry.getValue();

                VertexWrapper v = vertices.get(p.getId_centroid());
                Coordinate v_coord = new Coordinate(v.getCoords()[0], v.getCoords()[1]);
                GeometryFactory geometryFactory = new GeometryFactory();
                Point point = geometryFactory.createPoint(v_coord);
                double distance = point.distance(waterSource);
                double weight = 10/distance;

                totalMoisture += moisture * weight;
            }
            p.setMoisture(totalMoisture);
        }


    }
}
