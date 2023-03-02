package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import java.util.ArrayList;
import java.util.List;

public class PropertyUtils {
    protected static double[] extractSegmentMiddle(List<Structs.Property> properties) {
        String val = null;
        for(Structs.Property p: properties) {
            if (p.getKey().equals("middle")) {
//                System.out.println(p.getValue());
                val = p.getValue();
            }
        }
        return parse_string_to_array_int(val);
    }

    protected static double[] parse_string_to_array_int(String parse) {

        String[] array_return = parse.split(",", -1);
        double[] array_return_int = new double[array_return.length];
        for(int i = 0; i < array_return_int.length; i++) {
            array_return_int[i] = Double.parseDouble(array_return[i]);
        }
        return array_return_int;
    }

    protected static int extractID(List<Structs.Property> properties) {
        String val = "0";
        for(Structs.Property p: properties) {
            if (p.getKey().equals("id")) {
//                System.out.println(p.getValue());
                val = p.getValue();
            }
        }
        return Integer.parseInt(val);
    }
    protected static double[] extractHeadCoords(List<Structs.Property> properties) {
        String val = null;
        for(Structs.Property p: properties) {
            if (p.getKey().equals("head")) {
//                System.out.println(p.getValue());
                val = p.getValue();
            }
        }
        String[] raw = val.split(",");
        Double x = Double.parseDouble(raw[0].replace("[","").replace(" ", ""));
        Double y = Double.parseDouble(raw[1].replace(" ", ""));
        return new double[]{x, y};
    }

    protected static int extractCentroidID(List<Structs.Property> properties) {
        String val = "0";
        for(Structs.Property p: properties) {
            if (p.getKey().equals("middle_id")) {
//                System.out.println(p.getValue());
                val = p.getValue();
            }
        }
        return Integer.parseInt(val);
    }

    protected static double[] extractTailCoords(List<Structs.Property> properties) {
        String val = null;
        for(Structs.Property p: properties) {
            if (p.getKey().equals("tail")) {
//                System.out.println(p.getValue());
                val = p.getValue();
            }
        }
        String[] raw = val.split(",");
        Double x = Double.parseDouble(raw[0].replace("[","").replace(" ", ""));
        Double y = Double.parseDouble(raw[1].replace(" ", ""));
        return new double[]{x, y};
    }

    protected static int[] extractColor(List<Structs.Property> properties) {
        String val = null;
        for(Structs.Property p: properties) {
            if (p.getKey().equals("rgb_color")) {
//                System.out.println(p.getValue());
                val = p.getValue();
            }
        }
        String[] raw = val.split(",");
        int red = Integer.parseInt(raw[0].replace("[","").replace(" ", ""));
        int green = Integer.parseInt(raw[1].replace(" ", ""));
        int blue = Integer.parseInt(raw[2].replace("]","").replace(" ", ""));
        return new int[]{red, green, blue};
    }


    protected static ArrayList<float[]> extractCoordsforPolygons(java.util.List<Structs.Property> properties) {
        String x_coords = null, y_coords = null;
        for(Structs.Property p: properties) {
            if (p.getKey().equals("x_coords")) {
//                System.out.println(p.getValue());
                x_coords = p.getValue();
            }
            if (p.getKey().equals("y_coords")) {
//                System.out.println(p.getValue());
                y_coords = p.getValue();
            }
        }

        String[] raw_x = x_coords.split(",");
//        System.out.println(Arrays.toString(raw_x));
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
//                System.out.println(p.getValue());
                val = p.getValue();
            }
        }
        String[] raw = val.split(",");
        Double x = Double.parseDouble(raw[0].replace("[","").replace(" ", "").replace("]",""));
        Double y = Double.parseDouble(raw[1].replace("[","").replace(" ", "").replace("]",""));
        return new double[]{x, y};
    }
}
