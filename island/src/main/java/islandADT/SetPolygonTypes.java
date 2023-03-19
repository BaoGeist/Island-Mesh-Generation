package islandADT;

import islandADT.Configurations.IslandConfiguration;
import islandADT.Elevation.CraterElevationFixture;
import islandADT.Elevation.ElevationFixture;
import islandADT.Elevation.PlainsElevationFixture;
import islandADT.Elevation.VolcanicElevationFixture;
import islandADT.Shapes.*;
import islandADT.GeometryWrappers.PolygonWrapper;
import islandADT.GeometryWrappers.SegmentWrapper;
import islandADT.Specifications.IslandSpecifications;
import islandADT.TypeWrappers.TileTypeWrapper;
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

    public void set_island_shape(GeometryContainer geometryContainer, IslandSpecifications islandSpecifications) {
        String islandShape = islandSpecifications.getShape();
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

    public void set_island_elevation(GeometryContainer geometryContainer, IslandSpecifications islandSpecifications) {
        ElevationFixture elevationFixture;
        switch(islandSpecifications.getElevation()) {
            case "plains":
                elevationFixture = new PlainsElevationFixture();
                break;
            case "volcanic":
                elevationFixture = new VolcanicElevationFixture();
                break;
            case "crater":
                elevationFixture = new CraterElevationFixture();
                break;
            default:
                elevationFixture = new PlainsElevationFixture();
                break;
        }
        elevationFixture.set_elevation(geometryContainer);
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
