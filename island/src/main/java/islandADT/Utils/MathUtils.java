package islandADT.Utils;


import islandADT.Elevation.CustomPrecisionModel;

import java.util.ArrayList;
import java.util.List;

import static java.awt.geom.Line2D.linesIntersect;

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


}
