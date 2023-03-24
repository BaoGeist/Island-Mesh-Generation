package islandADT.Water.Moisture;

import islandADT.GeometryContainer;
import islandADT.GeometryWrappers.PointWrapper;
import islandADT.GeometryWrappers.PolygonWrapper;
import islandADT.GeometryWrappers.SegmentWrapper;
import islandADT.GeometryWrappers.VertexWrapper;
import islandADT.Specifications.IslandSpecifications;

import java.util.HashMap;
import java.util.Map;

public abstract class MoistureProfile implements Moisture{
    protected static Map<PointWrapper, Double> waterSources = new HashMap<>();

    protected void setWaterSources(GeometryContainer geometryContainer) {
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

    @Override
    public abstract void calculateMoisture(GeometryContainer geometryContainer);
}
