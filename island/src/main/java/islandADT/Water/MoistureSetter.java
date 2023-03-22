package islandADT.Water;

import islandADT.GeometryContainer;
import islandADT.GeometryContainerCalculator;
import islandADT.GeometryWrappers.PointWrapper;
import islandADT.GeometryWrappers.PolygonWrapper;
import islandADT.GeometryWrappers.SegmentWrapper;
import islandADT.GeometryWrappers.VertexWrapper;
import islandADT.Specifications.IslandSpecifications;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import java.util.HashMap;
import java.util.Map;

import static islandADT.Utils.MathUtils.distance_between_points;

public class MoistureSetter {
    private static Map<PointWrapper, Double> waterSources = new HashMap<>();
    static String soil_type;
    public MoistureSetter(IslandSpecifications islandSpecifications) {
        soil_type = islandSpecifications.getSoil();
    }

    public static void setWaterSources(GeometryContainer geometryContainer) {
        Map<Integer, PolygonWrapper> polygons = geometryContainer.get_polygons();
        Map<Integer, SegmentWrapper> segments = geometryContainer.get_segments();
        Map<Integer, VertexWrapper> vertices = geometryContainer.get_vertices();

        for(PolygonWrapper p: polygons.values()) {
            if(p.isWaterOrNah()) {
                VertexWrapper v = vertices.get(p.getId_centroid());
                PointWrapper point = new PointWrapper(v.getCoords());

                waterSources.put(point, p.getMoisture());
            }
        }

        for (SegmentWrapper s: segments.values()){
            if(s.getSegmentTypeWrapper().getFlow() > 0) {
                VertexWrapper v = vertices.get(s.getV1id());
                VertexWrapper v2 = vertices.get(s.getV2id());
                double[] v_coord = new double[2];
                v_coord[0] = ((v.getCoords()[0]+v2.getCoords()[0])/2);
                v_coord[1] = ((v.getCoords()[1]+v2.getCoords()[1])/2);

                PointWrapper point = new PointWrapper(v_coord);
                waterSources.put(point, (double)s.getSegmentTypeWrapper().getFlow()*5);
            }
        }
    }

    public static void calculateMoisture(GeometryContainer geometryContainer) {
        switch(soil_type){
            case "arid" :
                calculateAridMoisture(geometryContainer);
                System.out.println("Arid was good");
                break;
            case "fertile" :
                calculateFertileMoisture(geometryContainer);
                System.out.println("fertile was good");
                break;
            case "swamp" :
                calculateSwampMoisture(geometryContainer);
                System.out.println("swamp was good");
                break;
        }
    }

    private static void calculateSwampMoisture(GeometryContainer geometryContainer) {
        Map<Integer, VertexWrapper> vertices = geometryContainer.get_vertices();
        for(PolygonWrapper p: GeometryContainerCalculator.getDryLandPolygons(geometryContainer)) {
            double totalMoisture = 0.0;

            for(Map.Entry<PointWrapper, Double> entry : waterSources.entrySet()) {
                PointWrapper waterSource = entry.getKey();
                double moisture = entry.getValue();

                VertexWrapper v = vertices.get(p.getId_centroid());
                double[] v_coords = v.getCoords();
                double[] source_coords = waterSource.getCoords();

                double distance = distance_between_points(v_coords, source_coords);
                double weight = 1/(Math.pow(distance,0.8));

                totalMoisture += moisture * weight;
            }
            p.setMoisture(totalMoisture);
        }
    }

    private static void calculateFertileMoisture(GeometryContainer geometryContainer) {
        Map<Integer, VertexWrapper> vertices = geometryContainer.get_vertices();
        for(PolygonWrapper p: GeometryContainerCalculator.getDryLandPolygons(geometryContainer)) {
            double totalMoisture = 0.0;

            for(Map.Entry<PointWrapper, Double> entry : waterSources.entrySet()) {
                PointWrapper waterSource = entry.getKey();
                double moisture = entry.getValue();

                VertexWrapper v = vertices.get(p.getId_centroid());
                double[] v_coords = v.getCoords();
                double[] source_coords = waterSource.getCoords();

                double distance = distance_between_points(v_coords, source_coords);
                double weight = 1/(distance);

                totalMoisture += moisture * weight;
            }
            p.setMoisture(totalMoisture);
        }
    }

    private static void calculateAridMoisture(GeometryContainer geometryContainer) {

        Map<Integer, VertexWrapper> vertices = geometryContainer.get_vertices();
        for(PolygonWrapper p: GeometryContainerCalculator.getDryLandPolygons(geometryContainer)) {
            double totalMoisture = 0.0;

            for(Map.Entry<PointWrapper, Double> entry : waterSources.entrySet()) {
                PointWrapper waterSource = entry.getKey();
                double moisture = entry.getValue();

                VertexWrapper v = vertices.get(p.getId_centroid());
                double[] v_coords = v.getCoords();
                double[] source_coords = waterSource.getCoords();

                double distance = distance_between_points(v_coords, source_coords);
                double weight = 1/(Math.pow(distance, 1.2));

                totalMoisture += moisture * weight;
            }
            p.setMoisture(totalMoisture);
        }
    }
}
