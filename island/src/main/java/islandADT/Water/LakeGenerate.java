package islandADT.Water;

import islandADT.GeometryContainer;
import islandADT.Wrappers.PolygonWrapper;
import islandADT.Wrappers.TileTypeWrapper;
import islandADT.Wrappers.VertexWrapper;

import java.util.Arrays;
import java.util.Map;

public class LakeGenerate extends WaterTile{
    public void generate(GeometryContainer geometryContainer) {
        int lake_id = random_start(geometryContainer);
        Map<Integer, PolygonWrapper> polygons = geometryContainer.get_polygons();
        Map<Integer, VertexWrapper> vertices = geometryContainer.get_vertices();
        TileTypeWrapper Lake = new TileTypeWrapper("Lake");
        polygons.get(lake_id).setTileType(Lake);
        System.out.println(Arrays.toString(vertices.get(polygons.get(lake_id).getId_centroid()).getCoords()));
    }

}
