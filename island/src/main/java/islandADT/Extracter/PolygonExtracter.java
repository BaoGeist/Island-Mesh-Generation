package islandADT.Extracter;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import islandADT.Wrappers.PolygonWrapper;
import islandADT.Wrappers.TileTypeWrapper;
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
        System.out.println(segments);
        PolygonWrapper newPolygon = new PolygonWrapper(id_polygon, x_y_coords, neighbours, centroid_id, segments);
        //TODO B generate this elsewhere
        TileTypeWrapper tileTypeWrapper = new TileTypeWrapper(TileTypeWrapper.TileType.Ocean);
        newPolygon.setTileType(tileTypeWrapper);
        return newPolygon;
    }
}
