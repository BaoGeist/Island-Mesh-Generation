package islandADT.Exporter.Colour;

import islandADT.GeometryWrappers.PolygonWrapper;

import java.awt.*;
import java.util.Map;

public class HeightColourExporter extends ColourExporter {
    private int increment;
    private int min;
    private int max;
    @Override
    public void set_increments(Map<Integer, PolygonWrapper> polygons) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for(PolygonWrapper p: polygons.values()) {
            int height = p.getHeight();
            min = Math.min(height, min);
            max = Math.max(height, max);
        }

        increment = (int) ((max - min)/10);
        this.min = min;
        this.max = max;
    }

    @Override
    public int[] export(PolygonWrapper p) {
        Double value = (double) p.getHeight();
        if(value == 0)  return new int[]{0, 0, 0};
        else if(value > min && value <= increment) return new int[]{145, 71, 234};
        else if (value > increment && value <= 2*increment) return new int[]{200, 177, 238};
        else if(value > 2*increment && value <= 3*increment) return new int[]{255, 255, 255};
        else if(value > 3*increment && value <= 4*increment) return new int[]{188, 227, 255};
        else if(value > 4*increment && value <= 5*increment) return new int[]{128, 163, 255};
        else if(value > 5*increment && value <= 6*increment) return new int[]{63, 106, 255};
        else if(value > 6*increment && value <= 7*increment) return new int[]{8, 78, 255};
        else if(value > 7*increment && value <= 8*increment) return new int[]{51, 31, 182};
        else if(value > 8*increment && value <= 9*increment) return new int[]{34, 9, 138};
        else if(value > 9*increment && value <= 10*increment) return new int[]{24, 5, 103};
        else if (value > 10*increment) return new int[]{14, 2, 58};
        else {
            return new int[]{57, 157, 73};
        }
    }

}
