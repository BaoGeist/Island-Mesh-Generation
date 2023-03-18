package islandADT;

import islandADT.GeometryWrappers.PolygonWrapper;

import java.util.List;
import java.util.Map;

public class GeometryContainerLibrarian {
    public boolean polygon_no_ocean_neighbours(GeometryContainer geometryContainer, int i) {
        Map<Integer, PolygonWrapper> polygons = geometryContainer.get_polygons();
        PolygonWrapper p = polygons.get(i);
        List neighbours = p.get_neighbours();
        for(Object n_id: neighbours) {
            int n_id_int = (Integer) n_id;
            if(! polygons.get(n_id_int).isLandornah()) {
                return false;
            }
        }
        return true;
    }
}
