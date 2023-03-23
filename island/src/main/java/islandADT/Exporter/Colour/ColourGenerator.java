package islandADT.Exporter.Colour;

import islandADT.GeometryWrappers.PolygonWrapper;
import islandADT.GeometryWrappers.SegmentWrapper;
import islandADT.TypeWrappers.TileTypeWrapper;

import java.util.Map;

public class ColourGenerator {
    private String mode;
    private int increment;

    public ColourGenerator(String mode) {
        this.mode = mode;
    }

    public void set_increments(Map<Integer, PolygonWrapper> polygons) {
        switch(mode) {
            case "height":
                set_increments_height(polygons);
                break;
            case "moisture":
                set_increments_moisture(polygons);
                break;
            default:
                break;
        }
    }

    private void set_increments_moisture(Map<Integer, PolygonWrapper> polygons) {
        double min = Double.MAX_VALUE, max = Double.MIN_VALUE;
        for(PolygonWrapper p: polygons.values()) {
            double moisture = p.getMoisture();
            min = Math.min(moisture, min);
            max = Math.max(moisture, max);
        }

        increment = (int) ((max - min)/10);
    }

    private void set_increments_height(Map<Integer, PolygonWrapper> polygons) {
        double min = Double.MAX_VALUE, max = Double.MIN_VALUE;
        for(PolygonWrapper p: polygons.values()) {
            int height = p.getHeight();
            min = Math.min(height, min);
            max = Math.max(height, max);
        }

        increment = (int) ((max - min)/10);
    }

    public int[] color_polygon_generate(Object o, Double d, int d2) {
        switch(mode) {
            case "normal":
                return export_normal(o);
            case "height":
                return export_heatmap((double) d2);
            case "moisture":
                return export_heatmap(d);
            default:
                return export_normal(o);
        }
    }

//    public int[] color_segment_generate() {
//        switch(mode) {
//            case "normal":
//        }
//    }

    public int[] export_heatmap(Double value) {
        if(value > 0 && value <= increment) return new int[]{145, 71, 234};
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
        else if (value == 0)  {
            System.out.println("here");
            return new int[]{0, 0, 0};
        }
        else {
            return new int[]{57, 157, 73};
        }
    }

    public int[] export_normal(Object o) {
        TileTypeWrapper tileTypeWrapper = (TileTypeWrapper) o;
        int[] color = tileTypeWrapper.getColor();
        return color;
    }
}
