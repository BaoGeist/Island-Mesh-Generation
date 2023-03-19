package ca.mcmaster.cas.se2aa4.a2.visualizer.RenderOptions;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.awt.*;

import static ca.mcmaster.cas.se2aa4.a2.visualizer.PropertyUtils.*;

// takes in any min and max and divides it into 5 levels
public class HeatmapPainter {
    private int increment, min, max;

    public HeatmapPainter(Structs.Mesh aMesh, String type) {
        double[] min_max = extractMinMax(aMesh.getPolygonsList(), type);
        min = (int) min_max[0];
        max = (int) min_max[1];
        increment = (int) ((min_max[1] - min_max[0])/10);
    }

    public Color color_from_integer(double value) {
        if(value > 0 && value <= increment) return new Color(145, 71, 234);
        else if (value > increment && value <= 2*increment) return new Color(200, 177, 238);
        else if(value > 2*increment && value <= 3*increment) return new Color(255, 255, 255);
        else if(value > 3*increment && value <= 4*increment) return new Color(188, 227, 255);
        else if(value > 4*increment && value <= 5*increment) return new Color(128, 163, 255);
        else if(value > 5*increment && value <= 6*increment) return new Color(63, 106, 255);
        else if(value > 6*increment && value <= 7*increment) return new Color(8, 78, 255);
        else if(value > 7*increment && value <= 8*increment) return new Color(51, 31, 182);
        else if(value > 8*increment && value <= 9*increment) return new Color(34, 9, 138);
        else if(value > 9*increment && value <= 10*increment) return new Color(24, 5, 103);
        else if (value > 10*increment) return new Color(14, 2, 58);
        else if (value == 0) return new Color(0, 0, 0);
        else {
            return new Color(57, 157, 73);
        }

    }
}
