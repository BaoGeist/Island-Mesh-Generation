package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.List;

public class PropertyUtils {
    public static double[] extractSegmentMiddle(List<Structs.Property> properties) {
        String val = null;
        for(Structs.Property p: properties) {
            if (p.getKey().equals("middle")) {
//                System.out.println(p.getValue());
                val = p.getValue();
            }
        }
        return parse_string_to_array_int(val);
    }

    public static double[] parse_string_to_array_int(String parse) {

        String[] array_return = parse.split(",", -1);
        double[] array_return_int = new double[array_return.length];
        for(int i = 0; i < array_return_int.length; i++) {
            array_return_int[i] = Double.parseDouble(array_return[i]);
        }
        return array_return_int;
    }

    public static int extractID(List<Structs.Property> properties) {
        String val = "0";
        for(Structs.Property p: properties) {
            if (p.getKey().equals("id")) {
//                System.out.println(p.getValue());
                val = p.getValue();
            }
        }
        return Integer.parseInt(val);
    }
    public static double[] extractHeadCoords(List<Structs.Property> properties) {
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

    public static double[] extractTailCoords(List<Structs.Property> properties) {
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

    public static int[] extractColor(List<Structs.Property> properties) {
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
}
