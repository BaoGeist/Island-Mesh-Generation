package islandADT;

import islandADT.Wrappers.PolygonWrapper;
import islandADT.Wrappers.TileTypeWrapper;

import java.util.Map;

import static islandADT.Wrappers.TileTypeWrapper.TileType.*;

public class SetPolygonTypes {
    private double center_x = 250;
    private double center_y = 250;

    public void set_tile_type(GeometryContainer geometryContainer) {
        try {
            Map<Integer, PolygonWrapper> PolygonList = geometryContainer.get_polygons();

            for (PolygonWrapper p: PolygonList.values()){
                TileTypeWrapper ocean = new TileTypeWrapper(Ocean);
                TileTypeWrapper sand = new TileTypeWrapper(Sand);
                TileTypeWrapper land = new TileTypeWrapper(Land);
                TileTypeWrapper lagoon = new TileTypeWrapper(Lagoon);

                double[] centroid_coords = geometryContainer.get_vertices().get(p.getId_centroid()).getCoords();
                double distance = Math.sqrt(Math.pow(center_x - centroid_coords[0], 2) + Math.pow(center_y - centroid_coords[1], 2));

                if (distance <= 150){
                    if (distance <= 50){
                        p.setTileType(lagoon);
                    } else {
                        p.setTileType(land);
                    }
                } else {
                    p.setTileType(ocean);
                }

                for (int i = 0; i < p.get_neighbours().size(); i++){
                    PolygonWrapper neighbor = PolygonList.get(p.get_neighbours().get(i));
                    if (neighbor.getTileType().equals(ocean) || neighbor.getTileType().equals(lagoon)){
                        p.setTileType(sand);
                    };
                }
            }
        } catch (Exception e) {
            System.out.println("cool");
        }

    }

}
