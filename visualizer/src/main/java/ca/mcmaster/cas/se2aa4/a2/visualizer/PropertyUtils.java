package ca.mcmaster.cas.se2aa4.a2.visualizer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PropertyUtils {
    protected static Color extractColor(java.util.List<Structs.Property> properties) {
        String val = null;
        for(Structs.Property p: properties) {
            if (p.getKey().equals("rgb_color")) {
//                System.out.println(p.getValue());
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

    public static float[] extractHeadTail(java.util.List<Structs.Property> properties) {
//        System.out.println(properties);
        String head = null, tail = null;
        for(Structs.Property p: properties) {
            if (p.getKey().equals("head")) {
//                System.out.println(p.getValue());
                head = p.getValue();
            }
            if (p.getKey().equals("tail")) {
//                System.out.println(p.getValue());
                tail = p.getValue();
            }
        }
        if (head == null || tail == null)
            return new float[]{};
        String[] raw_head = head.split(",");
        String[] raw_tail = tail.split(",");
        float[] head_tail = new float[raw_head.length*2];

        for(int i = 0; i < raw_head.length; i++) {
            head_tail[i] = Float.parseFloat(raw_head[i]);
            head_tail[i+raw_head.length] = Float.parseFloat(raw_tail[i]);
        }

        return head_tail;
    }

    public static boolean extractCentroid(List<Structs.Property> properties) {
        String val = null;
        for (Structs.Property p: properties) {
            if (p.getKey().equals("centroid_or_nah")) {
                val = p.getValue();
            }
        }
        return Boolean.parseBoolean(val);
    }

    public static ArrayList<Double> extractNeighbourID(List<Structs.Property> properties) {
        String val = null;
        for(Structs.Property p: properties) {
            if (p.getKey().equals("neighbours")) {
                val = p.getValue();
                //System.out.println(val);
            }
        }
        String[] neighbours = val.split(",");
        ArrayList<Double> nums = new ArrayList<>();
        for(int i = 0; i < neighbours.length; i++) {
            String temp = neighbours[i];
            String temp2 = "";
            for (int j = 0; j < neighbours[i].length(); j++) {
                if ((int) temp.charAt(j) >= 48 && (int) temp.charAt(j) <= 57) {
                    temp2 += temp.charAt(j);
                    //System.out.println(temp2);
                }
                }
            nums.add(Double.parseDouble(temp2));
        }
        return nums;
    }


    public static ArrayList<Integer> extractSegmentIDs(List<Structs.Property> properties) { //applicable for polygons
        String val = null;
        for (Structs.Property p: properties) {
            if (p.getKey().equals("segments_id")) {
                val = p.getValue();
                //System.out.println(val);
            }
        }
        String[] segments = val.split(",");
        ArrayList<Integer> lines = new ArrayList<>();
        for(int i = 0; i < segments.length; i++) {
            String temp = segments[i];
            String temp2 = "";
            for (int j = 0; j < segments[i].length(); j++) {
                if ((int) temp.charAt(j) >= 48 && (int) temp.charAt(j) <= 57) {
                    temp2 += temp.charAt(j);
                    //System.out.println(temp2);
                }
                }
            lines.add(Integer.parseInt(temp2));
        }
        return lines;
    }

    public static int extractSegID(List<Structs.Property> properties) { //similar to above but only applicable for segments
        String val = null;
        for (Structs.Property p: properties) {
            if (p.getKey().equals("id")) {
                val = p.getValue();
            }
        }
        int segment;
        segment = Integer.parseInt(val);
        return segment;
    }
}
