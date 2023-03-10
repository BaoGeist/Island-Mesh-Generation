package ca.mcmaster.cas.se2aa4.a2.generator.Utils;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PropertyUtils {

    // Extracts IDs for all our geometries
    public static int extractID(List<Structs.Property> properties) {
        String val = "0";
        for(Structs.Property p: properties) {
            if (p.getKey().equals("id")) {
                val = p.getValue();
            }
        }
        return Integer.parseInt(val);
    }

    // Extracts heads vertex for a segment
    public static double[] extractHeadCoords(List<Structs.Property> properties) {
        String val = null;
        for(Structs.Property p: properties) {
            if (p.getKey().equals("head")) {
                val = p.getValue();
            }
        }
        String[] raw = val.split(",");
        Double x = Double.parseDouble(raw[0].replace("[","").replace(" ", ""));
        Double y = Double.parseDouble(raw[1].replace(" ", ""));
        return new double[]{x, y};
    }

    //Extracts tail coordinate for building a segment
    public static double[] extractTailCoords(List<Structs.Property> properties) {
        String val = null;
        for(Structs.Property p: properties) {
            if (p.getKey().equals("tail")) {
                val = p.getValue();
            }
        }
        String[] raw = val.split(",");
        Double x = Double.parseDouble(raw[0].replace("[","").replace(" ", ""));
        Double y = Double.parseDouble(raw[1].replace(" ", ""));
        return new double[]{x, y};
    }

    public static int[] extractColor(List<Structs.Property> properties) {
        String val = null;
        for(Structs.Property p: properties) {
            if (p.getKey().equals("rgb_color")) {
                val = p.getValue();
            }
        }
        String[] raw = val.split(",");
        int red = Integer.parseInt(raw[0].replace("[","").replace(" ", ""));
        int green = Integer.parseInt(raw[1].replace(" ", ""));
        int blue = Integer.parseInt(raw[2].replace("]","").replace(" ", ""));
        return new int[]{red, green, blue};
    }


    // Extracts an ArrayList of coordinates from Polygon
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
}
