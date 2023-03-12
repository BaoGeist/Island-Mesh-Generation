package islandADT;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import islandADT.Extracter.PolygonExtracter;
import islandADT.Wrappers.PolygonWrapper;

import java.util.List;
import java.util.Map;

import static islandADT.IslandTileEnum.*;
import static meshcore.Utils.PropertyUtils.extractCentroidCoords;

public class SetPolygonTypes {
    private double center_x = 250;
    private double center_y = 250;

    private void set_tile_type(PolygonWrapper polygon, GeometryContainer geometryContainer) {
        Map<Integer, PolygonWrapper> PolygonList = geometryContainer.get_polygons();

        double[] centroid_coords = extractCentroidCoords(PolygonExtracter.extractProperties(polygon));
        double distance = Math.sqrt(Math.pow(center_x - centroid_coords[0], 2) + Math.pow(center_y - centroid_coords[1], 2));
        Structs.Property tile_type;

        if (distance <= 150){
            if (distance <= 50){
                tile_type = Structs.Property.newBuilder().setKey("tile_type").setValue(String.valueOf(LAGOON)).build();
            } else {
                tile_type = Structs.Property.newBuilder().setKey("tile_type").setValue(String.valueOf(LAND)).build();
            }
        } else {
            tile_type = Structs.Property.newBuilder().setKey("tile_type").setValue(String.valueOf(OCEAN)).build();
        }

        for (int i = 0; i < polygon.get_neighbours().size(); i++){
            PolygonWrapper neighbor = PolygonList.get(polygon.get_neighbours().get(i));
            // get tile type of neighbor
            // if tile type of neighbor == OCEAN or LAGOON
            // tile_type = Structs.Property.newBuilder().setKey("tile_type").setValue(String.valueOf(SAND)).build();
        }
        Structs.Polygon.newBuilder(polygon).addProperties(tile_type);
    }

}
