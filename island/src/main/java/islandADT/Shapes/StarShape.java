package islandADT.Shapes;

import islandADT.Generator.RandomSeed;
import islandADT.Container.GeometryContainer;
import islandADT.GeometryWrappers.PolygonWrapper;
import islandADT.GeometryWrappers.SegmentWrapper;
import islandADT.TypeWrappers.TileTypeWrapper;
import org.locationtech.jts.geom.*;

import java.util.List;
import java.util.Map;

public class StarShape implements Shape{

    private Polygon Star;

    public Geometry generateIsland(int radius, GeometryContainer geometryContainer) {
        RandomSeed instanceRandom = RandomSeed.getInstance();
        GeometryFactory factory = new GeometryFactory();
        Coordinate center = new Coordinate(250, 250);
        int numPoints = instanceRandom.randomInt(5, 12);  // number of points in the star
        double innerRadius = radius/2;  // radius of the inner points
        Coordinate[] vertices = new Coordinate[numPoints * 2+1];
        for (int i = 0; i <= numPoints * 2; i++) {
            double angle = ((double)i / numPoints) * Math.PI;
            double r = i % 2 == 0 ? radius : innerRadius;
            double x = center.x + Math.cos(angle + Math.PI/numPoints) * r;
            double y = center.y + Math.sin(angle + Math.PI/numPoints) * r;
            vertices[i] = new Coordinate(x, y);
        }
        vertices[numPoints * 2] = vertices[0]; // close the ring by adding the first vertex again
        LinearRing shell = factory.createLinearRing(vertices);
        Star = factory.createPolygon(shell, null);
        set_tiles_inside_shape(geometryContainer, Star);
        return Star;
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
