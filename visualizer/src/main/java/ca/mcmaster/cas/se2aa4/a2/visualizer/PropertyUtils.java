package ca.mcmaster.cas.se2aa4.a2.visualizer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PropertyUtils {

    // Extracts color
    public static Color extractColor(java.util.List<Structs.Property> properties) {
        String val = null;
        for(Structs.Property p: properties) {
            if (p.getKey().equals("rgb_color")) {
                val = p.getValue();
            }
        }
        if (val == null)
            return Color.BLACK;
        String[] raw = val.split(",");
        int red = Integer.parseInt(raw[0].replace("[","").replace(" ", ""));
        int green = Integer.parseInt(raw[1].replace(" ", ""));
        int blue = Integer.parseInt(raw[2].replace("]","").replace(" ", ""));
        return new Color(red, green, blue);
    }

    //Extracts the coordinates for all polygons
    public static ArrayList<float[]> extractCoordsforPolygons(java.util.List<Structs.Property> properties) {
        String x_coords = null, y_coords = null;
        for(Structs.Property p: properties) {
            if (p.getKey().equals("x_coords")) {
                x_coords = p.getValue();
            }
            if (p.getKey().equals("y_coords")) {
                y_coords = p.getValue();
            }
        }

        String[] raw_x = x_coords.split(",");
        float[] pro_x = new float[raw_x.length];
        String[] raw_y = y_coords.split(",");
        float[] pro_y = new float[raw_y.length];

        for(int i = 0; i < raw_x.length; i++) {
            pro_x[i] = Float.parseFloat(raw_x[i].replace("[","").replace(" ", "").replace("]",""));
            pro_y[i] = Float.parseFloat(raw_y[i].replace("[","").replace(" ", "").replace("]",""));

        }
        ArrayList<float[]> return_array = new ArrayList<>();
        return_array.add(pro_x);
        return_array.add(pro_y);
        return return_array;
    }

    //Extracts the centroid
    public static boolean extractCentroid(List<Structs.Property> properties) {
        String val = null;
        for (Structs.Property p: properties) {
            if (p.getKey().equals("centroid_or_nah")) {
                val = p.getValue();
            }
        }
        return Boolean.parseBoolean(val);
    }

    public static double[] extractCentroidCoords(List<Structs.Property> properties) {
        String val = null;
        for(Structs.Property p: properties) {
            if (p.getKey().equals("centroid_coords")) {
                val = p.getValue();
            }
        }
        String[] raw = val.split(",");
        Double x = Double.parseDouble(raw[0].replace("[","").replace(" ", "").replace("]",""));
        Double y = Double.parseDouble(raw[1].replace("[","").replace(" ", "").replace("]",""));
        return new double[]{x, y};
    }

    public static int extractHeight(List<Structs.Property> properties) {
        String val = "0";
        for(Structs.Property p: properties) {
            if (p.getKey().equals("height")) {
                val = p.getValue();
            }
        }
        return Integer.parseInt(val);
    }

    public static int extractThickness(List<Structs.Property> properties){
        String val = "1";
        for (Structs.Property p: properties) {
            if (p.getKey().equals("thicc")) {
                val = p.getValue();
            }
        }
        return Integer.parseInt(val);
    }

    public static double[] extractMinMax(List<Structs.Polygon> polygons, String type) {
        String val = null;
        double min = Double.MAX_VALUE, max = Double.MIN_VALUE;
        double height = 0;
        for(Structs.Polygon p: polygons) {
            for(Structs.Property property: p.getPropertiesList()) {
                if(property.getKey().equals(type)) height = Double.parseDouble(property.getValue());
            }
            min = Math.min(height, min);
            max = Math.max(height, max);
        }
        return new double[]{min, max};
    }

    public static double extractMoisture(List<Structs.Property> properties) {
        String val = "0";
        for(Structs.Property p: properties) {
            if (p.getKey().equals("moisture")) {
                val = p.getValue();
            }
        }
        return Double.parseDouble(val);
    }

}
