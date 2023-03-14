package islandADT.Exporter;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import islandADT.Wrappers.TileTypeWrapper;

import java.util.Arrays;
import java.util.List;

public class OurPolygonIsland{
    public Structs.Polygon create_geometry(List<Integer> segments, TileTypeWrapper tileType, int centroid, List<Integer> neighbours, List<float[]> coords, int height) {
        Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue(Arrays.toString(tileType.getColor())).build();
        Structs.Property x_coords = Structs.Property.newBuilder().setKey("x_coords").setValue(Arrays.toString(coords.get(0))).build();
        Structs.Property y_coords = Structs.Property.newBuilder().setKey("y_coords").setValue(Arrays.toString(coords.get(1))).build();
        Structs.Property height_p = Structs.Property.newBuilder().setKey("height").setValue(String.valueOf(height)).build();
        Structs.Polygon polygon = Structs.Polygon.newBuilder().addAllSegmentIdxs(segments).setCentroidIdx(centroid).addAllNeighborIdxs(neighbours).addProperties(color).addProperties(x_coords).addProperties(y_coords).addProperties(height_p).build();
        return polygon;
    }

}
