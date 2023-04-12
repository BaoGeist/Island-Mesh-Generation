package meshcore.Utils;

import org.locationtech.jts.geom.Coordinate;

import java.util.ArrayList;

import static java.awt.geom.Line2D.linesIntersect;

public class MathUtils {
    public static double calculate_length_of_segment(Coordinate c1, Coordinate c2) {
        return Math.sqrt(Math.pow((c1.x - c2.x),2) + (Math.pow((c1.y - c2.y),2)));
    }
    public static double calculate_area_of_triangle(Coordinate c1, Coordinate c2, Coordinate c3) {
        double a = calculate_length_of_segment(c1, c2);
        double b = calculate_length_of_segment(c2, c3);
        double c = calculate_length_of_segment(c3, c1);
        double s = (a + b + c) / 2;
        return Math.sqrt(s*(s-a)*(s-b)*(s-c));
    }

    public static Coordinate calculate_centroid_of_triangle(Coordinate c1, Coordinate c2, Coordinate c3) {
        double x = (c1.x + c2.x + c3.x)/3;
        double y = (c1.y + c2.y + c3.y)/3;
        return new Coordinate(x, y);
    }

    public static Coordinate calculate_new_centroid(ArrayList<Double> areas, ArrayList<Coordinate> coordinates) {
        double x_total = 0, y_total = 0, area = 0;
        for(int i = 0; i < areas.size(); i++) {
            area += areas.get(i);
            x_total += coordinates.get(i).x * areas.get(i);
            y_total += coordinates.get(i).y * areas.get(i);
        }
        return new Coordinate(x_total/area, y_total/area);
    }

    public static boolean intersection_between_lines(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        return linesIntersect(x1,y1,x2,y2,x3,y3,x4,y4);
    }
}
