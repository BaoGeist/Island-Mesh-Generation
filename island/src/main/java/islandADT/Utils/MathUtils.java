package islandADT.Utils;


import islandADT.Elevation.CustomPrecisionModel;

import java.util.ArrayList;
import java.util.List;

public class MathUtils {
    public static int distance_between_centre(double[] coords, CustomPrecisionModel precisionModel) {
        int centre_x = 250, centre_y = 250;
        double intermezzo1 = Math.pow(centre_x - coords[0], 2);
        double intermezzo2 = Math.pow(centre_y - coords[1], 2);
        double return_double = Math.sqrt(intermezzo1 + intermezzo2);
        int return_int = (int) precisionModel.makePrecise(return_double);
        return return_int;
    }

    public static int average(List<Integer> integers) {
        int total = 0, count = 0;
        for(Integer integer: integers) {
            total += integer;
            count++;
        }
        return total/count;
    }

    public static double distance_between_points(double[] coords, double[] otherCoords){
        double intermezzo1 = Math.pow(otherCoords[0] - coords[0], 2);
        double intermezzo2 = Math.pow(otherCoords[1] - coords[1], 2);
        double return_double = Math.sqrt(intermezzo1 + intermezzo2);
        return return_double;
    }

    public static double[] cartesian_from_polar(double[] polar) {
        double x = 250 + polar[0] * Math.cos(polar[1]);
        double y = 250 + polar[0] * Math.sin(polar[1]);

        return new double[]{x, y};

    }

    public static boolean intersection_between_lines(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        double m1 = (y2 - y1) / (x2 - x1);
        double b1 = y1 - m1 * x1;
        double m2 = (y4 - y3) / (x4 - x3);
        double b2 = y3 - m2 * x3;

        if (m1 == m2 && b1 != b2) { return false; }

        double x = (y2 - y1 + b1 - b2) / (m1 - m2);

        if ((x <= x1 || x >= x2) && (x <= x2 || x >= x1)) {
            return false;
        }
        if ((x <= x3 || x >= x4) && (x <= x4 || x >= x3)) {
            return false;
        }

        return true;
    }
}
