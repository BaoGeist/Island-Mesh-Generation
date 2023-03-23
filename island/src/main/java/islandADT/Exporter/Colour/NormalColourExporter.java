package islandADT.Exporter.Colour;

import islandADT.GeometryWrappers.PolygonWrapper;
import islandADT.TypeWrappers.TileTypeWrapper;

import java.awt.*;
import java.util.Map;

public class NormalColourExporter extends ColourExporter {

    @Override
    public void set_increments(Map<Integer, PolygonWrapper> polygons) {
        increment = 0;
    };

    @Override
    public int[] export(PolygonWrapper p) {
        TileTypeWrapper tileTypeWrapper = p.getTileType();
        int[] color = tileTypeWrapper.getColor();
        return color;
    }
}
