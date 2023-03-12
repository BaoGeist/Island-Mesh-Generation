package islandADT.Exporter;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import islandADT.Wrappers.TileTypeWrapper;

import java.util.Arrays;
import java.util.List;

public class OurPolygonIsland{
    public Structs.Polygon create_geometry(List<Integer> segments, TileTypeWrapper tileType, int centroid, List<Integer> neighbours) {
        Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue(Arrays.toString(tileType.getColor())).build();
        Structs.Polygon polygon = Structs.Polygon.newBuilder().addAllSegmentIdxs(segments).setCentroidIdx(centroid).addAllNeighborIdxs(neighbours).addProperties(color).build();
        return polygon;
    }

}
