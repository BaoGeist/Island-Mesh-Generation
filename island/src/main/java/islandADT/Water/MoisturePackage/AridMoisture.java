package islandADT.Water.MoisturePackage;

import islandADT.Container.GeometryContainer;
import islandADT.Container.GeometryContainerCalculator;
import islandADT.GeometryWrappers.PointWrapper;
import islandADT.GeometryWrappers.PolygonWrapper;
import islandADT.GeometryWrappers.VertexWrapper;

import java.util.Map;

import static islandADT.Utils.MathUtils.distance_between_points;
import static islandADT.Water.MoistureSetter.waterSources;

public class AridMoisture implements MoistureInterface {
    @Override
    public void setMoisture(GeometryContainer geometryContainer) {
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
