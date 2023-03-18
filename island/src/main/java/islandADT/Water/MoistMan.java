package islandADT.Water;

import islandADT.GeometryContainer;
import islandADT.GeometryContainerCalculator;
import islandADT.GeometryWrappers.PolygonWrapper;
import islandADT.GeometryWrappers.VertexWrapper;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import java.util.HashMap;
import java.util.Map;

public class MoistMan {
    private static Map<Point, Double> waterTileSources = new HashMap<>();

    public static void setWaterSources(GeometryContainer geometryContainer) {
        Map<Integer, PolygonWrapper> polygons = geometryContainer.get_polygons();
        Map<Integer, VertexWrapper> vertices = geometryContainer.get_vertices();

        int counter = 0;
        for(PolygonWrapper p: polygons.values()) {
            if(p.isWaterOrNah()) {
                VertexWrapper v = vertices.get(p.getId_centroid());
                Coordinate v_coord = new Coordinate(v.getCoords()[0], v.getCoords()[1]);
                GeometryFactory geometryFactory = new GeometryFactory();
                Point point = geometryFactory.createPoint(v_coord);

                waterTileSources.put(point, p.getMoisture());
            }
            counter++;
        }
        System.out.println(counter);
    }

    public static void calculateMoisture(GeometryContainer geometryContainer) {
        Map<Integer, VertexWrapper> vertices = geometryContainer.get_vertices();
        for(PolygonWrapper p: GeometryContainerCalculator.getDryLandPolygons(geometryContainer)) {
            double totalMoisture = 0.0;

            for(Map.Entry<Point, Double> entry : waterTileSources.entrySet()) {
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
