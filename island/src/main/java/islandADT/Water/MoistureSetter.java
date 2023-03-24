package islandADT.Water;

import islandADT.GeometryContainer;
import islandADT.GeometryWrappers.PointWrapper;
import islandADT.GeometryWrappers.PolygonWrapper;
import islandADT.GeometryWrappers.SegmentWrapper;
import islandADT.GeometryWrappers.VertexWrapper;
import islandADT.Specifications.IslandSpecifications;
import islandADT.Water.MoisturePackage.AridMoisture;
import islandADT.Water.MoisturePackage.FertileMoisture;
import islandADT.Water.MoisturePackage.MoistureInterface;
import islandADT.Water.MoisturePackage.SwampMoisture;

import java.util.HashMap;
import java.util.Map;

public class MoistureSetter {
    public static Map<PointWrapper, Double> waterSources = new HashMap<>();
    static String soil_type;
    public MoistureSetter(IslandSpecifications islandSpecifications) {
        soil_type = islandSpecifications.getSoil();
    }

    private static void setWaterSources(GeometryContainer geometryContainer) {
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

        setWaterSources(geometryContainer);

        switch(soil_type){
            case "arid" :
                MoistureInterface aridMoisture = new AridMoisture();
                aridMoisture.setMoisture(geometryContainer);
                System.out.println("arid was good");
                break;
            case "fertile" :
                MoistureInterface fertileMoisture = new FertileMoisture();
                fertileMoisture.setMoisture(geometryContainer);
                System.out.println("fertile was good");
                break;
            case "swamp" :
                MoistureInterface swampMoisture = new SwampMoisture();
                swampMoisture.setMoisture(geometryContainer);
                System.out.println("Swamp was good");
                break;
        }
    }
}
