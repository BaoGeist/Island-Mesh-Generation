package islandADT.Extracter;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import islandADT.GeometryWrappers.PolygonWrapper;
import islandADT.TypeWrappers.TileTypeWrapper;
import islandADT.Biomes.*;
import meshcore.Utils.PropertyUtils;

import java.util.ArrayList;
import java.util.List;

public class PolygonExtracter implements Extracter<Structs.Polygon, PolygonWrapper> {
    public PolygonWrapper extract(Structs.Polygon oldPolygon) {
        int id_polygon = PropertyUtils.extractID(oldPolygon.getPropertiesList());
        ArrayList<float[]> x_y_coords = PropertyUtils.extractCoordsforPolygons(oldPolygon.getPropertiesList());
        List<Integer> neighbours = oldPolygon.getNeighborIdxsList();
        int centroid_id = oldPolygon.getCentroidIdx();
        List<Integer> segments = oldPolygon.getSegmentIdxsList();
        TileTypeWrapper tileTypeWrapper = new TileTypeWrapper("Ocean");
        PolygonWrapper newPolygon = new PolygonWrapper(id_polygon, x_y_coords, neighbours, centroid_id, segments, tileTypeWrapper);
        return newPolygon;
    }
}
