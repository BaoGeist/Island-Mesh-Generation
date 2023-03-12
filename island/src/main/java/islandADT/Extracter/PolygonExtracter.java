package islandADT.Extracter;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import islandADT.Wrappers.PolygonWrapper;
import meshcore.Utils.PropertyUtils;

import java.util.ArrayList;
import java.util.List;

public class PolygonExtracter implements Extracter<Structs.Polygon, PolygonWrapper> {
    public PolygonWrapper extract(Structs.Polygon oldPolygon) {
        int id_polygon = PropertyUtils.extractID(oldPolygon.getPropertiesList());
        ArrayList<float[]> x_y_coords = PropertyUtils.extractCoordsforPolygons(oldPolygon.getPropertiesList());
        List<Integer> neighbours = oldPolygon.getNeighborIdxsList();
        PolygonWrapper newPolygon = new PolygonWrapper(id_polygon, x_y_coords, neighbours);
        return newPolygon;
    }
}
