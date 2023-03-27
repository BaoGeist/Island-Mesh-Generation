package islandADT.Exporter.Colour;

import islandADT.GeometryWrappers.PolygonWrapper;

import java.util.Map;

public class ResourceColourExporter extends ColourExporter {
    @Override
    public void set_increments(Map<Integer, PolygonWrapper> polygons) {
        int increment = 1;
    }

    @Override
    public int[] export(PolygonWrapper p) {
        int value = p.getScore();
        if(value <= 2 || p.isWaterOrNah()) return new int[]{0, 0, 0};
        else if(value <= 3) return new int[]{188, 227, 255};
        else if(value <= 4) return new int[]{128, 163, 255};
        else if(value <= 5) return new int[]{63, 106, 255};
        else if(value <= 6) return new int[]{8, 78, 255};
        else if(value <= 7) return new int[]{51, 31, 182};
        else if(value <= 8) return new int[]{34, 9, 138};
        else if(value <= 9) return new int[]{24, 5, 103};
        else if (value > 9) return new int[]{14, 2, 58};
        else if (value == 0) return new int[]{255, 255, 255};
        else {
            return new int[]{57, 157, 73};
        }
    }
}
