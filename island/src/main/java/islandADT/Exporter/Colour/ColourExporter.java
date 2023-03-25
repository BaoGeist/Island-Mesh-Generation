package islandADT.Exporter.Colour;

import islandADT.Exporter.Exporter;
import islandADT.GeometryWrappers.PolygonWrapper;
import islandADT.GeometryWrappers.SegmentWrapper;

import java.awt.*;
import java.util.Map;

public abstract class ColourExporter implements Exporter<PolygonWrapper, int[]> {
    protected int increment;
    public abstract void set_increments(Map<Integer, PolygonWrapper> polygons);

    public abstract int[] export(PolygonWrapper polygonWrapper);

}
