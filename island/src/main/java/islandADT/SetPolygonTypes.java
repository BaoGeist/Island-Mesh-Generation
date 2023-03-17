package islandADT;

import islandADT.Shapes.*;
import islandADT.Wrappers.PolygonWrapper;
import islandADT.Wrappers.SegmentWrapper;
import islandADT.Wrappers.TileTypeWrapper;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import java.util.List;
import java.util.Map;

public class SetPolygonTypes {

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

    public void set_tile_type(GeometryContainer geometryContainer, String islandShape) {


        if (islandShape == null){
            set_circle_tiles(geometryContainer);
        } else {
            switch (islandShape) {
                case "circle":
                    set_circle_tiles(geometryContainer);
                    break;
                case "oval":
                    set_oval_tiles(geometryContainer);
                    break;
                case "star":
                    set_star_tiles(geometryContainer);
                    break;
                case "country":
                    set_new_tiles(geometryContainer);
                    break;

            }
        }
    }

    private void set_circle_tiles(GeometryContainer geometryContainer) {

        Shape circle = new CircleShape();
        Geometry circularIsland = circle.generateIsland(150);
        set_tiles_inside_shape(geometryContainer, circularIsland);
    }

    private void set_oval_tiles(GeometryContainer geometryContainer){

        Shape oval = new OvalShape();
        Geometry ovalIsland = oval.generateIsland(150);
        set_tiles_inside_shape(geometryContainer, ovalIsland);

    }

    private void set_star_tiles(GeometryContainer geometryContainer){

        Shape star = new StarShape();
        Geometry starIsland = star.generateIsland(200);
        set_tiles_inside_shape(geometryContainer, starIsland);

    }

    private void set_new_tiles(GeometryContainer geometryContainer){

        Shape star = new UkraineShape();
        Geometry starIsland = star.generateIsland(200);
        set_tiles_inside_shape(geometryContainer, starIsland);

    }

    private void set_tiles_inside_shape(GeometryContainer geometryContainer, Geometry IslandShape){
        Map<Integer, PolygonWrapper> PolygonList = geometryContainer.get_polygons();

        for (PolygonWrapper p: PolygonList.values()){

            double[] centroid_coords = geometryContainer.get_vertices().get(p.getId_centroid()).getCoords();
            Coordinate newCentroidCoords = new Coordinate(centroid_coords[0], centroid_coords[1]);
            Point PolygonCenter = new GeometryFactory().createPoint(newCentroidCoords);

            if (IslandShape.contains(PolygonCenter)){
                p.setTileType(land);
                p.setLandornah(true);
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
                        p.setLandornah(true);
                        set_segment_vertex_land(p, geometryContainer);
                    };
                }
            }
        }
    }

}
