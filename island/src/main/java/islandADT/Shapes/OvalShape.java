package islandADT.Shapes;

import islandADT.Generator.RandomSeed;
import islandADT.GeometryContainer;
import islandADT.GeometryWrappers.PolygonWrapper;
import islandADT.GeometryWrappers.SegmentWrapper;
import islandADT.TypeWrappers.TileTypeWrapper;
import org.locationtech.jts.geom.*;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class OvalShape implements Shape{

    private Polygon Oval;

    public Geometry generateIsland(int radius, GeometryContainer geometryContainer) {
        RandomSeed instanceRandom = RandomSeed.getInstance();

        GeometryFactory factory = new GeometryFactory();
        Coordinate center = new Coordinate(250, 250);
        int numPoints = 1000;
        double radiusX = instanceRandom.randomDouble(radius-50, radius+50);
        double radiusY = (radiusX > radius ? radiusX-50 : radiusX+50);
        Coordinate[] vertices = new Coordinate[numPoints];
        for (int i = 0; i < numPoints; i++) {
            double angle = ((double)i / numPoints) * Math.PI * 2.0;
            double x = center.x + Math.cos(angle) * radiusX;
            double y = center.y + Math.sin(angle) * radiusY;
            vertices[i] = new Coordinate(x, y);
        }
        vertices[numPoints-1] = vertices[0]; // close the ring by adding the first vertex again
        LinearRing shell = factory.createLinearRing(vertices);
        Oval = factory.createPolygon(shell, null);
        set_tiles_inside_shape(geometryContainer, Oval);
        return Oval;
    }

    private TileTypeWrapper ocean = new TileTypeWrapper("Ocean");
    private TileTypeWrapper sand = new TileTypeWrapper("Sand");
    private TileTypeWrapper land = new TileTypeWrapper("Land");

    private void set_segment_vertex_land(PolygonWrapper p, GeometryContainer geometryContainer) {
        List<Integer> p_segments = p.getSegments_group();
        for(Integer segment_id: p_segments) {
            SegmentWrapper s = geometryContainer.get_segments().get(segment_id);
            //TODO B make a better method name/implementation
            s.setLandornah(true);
            geometryContainer.get_vertices().get(s.getV1id()).setLandornah(true);
            geometryContainer.get_vertices().get(s.getV2id()).setLandornah(true);
        }
    }

    private void set_tiles_inside_shape(GeometryContainer geometryContainer, Geometry IslandShape){
        Map<Integer, PolygonWrapper> PolygonList = geometryContainer.get_polygons();

        for (PolygonWrapper p: PolygonList.values()){

            double[] centroid_coords = geometryContainer.get_vertices().get(p.getId_centroid()).getCoords();
            Coordinate newCentroidCoords = new Coordinate(centroid_coords[0], centroid_coords[1]);
            Point PolygonCenter = new GeometryFactory().createPoint(newCentroidCoords);

            if (IslandShape.contains(PolygonCenter)){
                p.setTileType(land);
                set_segment_vertex_land(p, geometryContainer);
            } else {
                p.setTileType(ocean);
            }
        }

        for (PolygonWrapper p: PolygonList.values()){
            if (p.getTileType() == land){
                for (int i = 0; i < p.get_neighbours().size(); i++){
                    PolygonWrapper neighbor = PolygonList.get(p.get_neighbours().get(i));
                    if (neighbor.getTileType() == ocean){
                        p.setTileType(sand);
                        set_segment_vertex_land(p, geometryContainer);
                    };
                }
            }
        }
    }


}
