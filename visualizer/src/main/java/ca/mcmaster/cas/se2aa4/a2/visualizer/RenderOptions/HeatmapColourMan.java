package ca.mcmaster.cas.se2aa4.a2.visualizer.RenderOptions;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.awt.*;

import static ca.mcmaster.cas.se2aa4.a2.visualizer.PropertyUtils.extractHeight;
import static ca.mcmaster.cas.se2aa4.a2.visualizer.PropertyUtils.extractMinMaxHeights;

// takes in any min and max and divides it into 5 levels
public class HeatmapColourMan {
    private int increment, min, max;

    public HeatmapColourMan(Structs.Mesh aMesh) {
        double[] min_max = extractMinMaxHeights(aMesh.getPropertiesList());
        min = (int) min_max[0];
        max = (int) min_max[1];
        increment = (int) ((min_max[1] - min_max[0])/5);
    }

    public Color color_from_integer(int integer) {
        if(integer > 0 && integer <= increment) return new Color(192,214,255);
        else if (integer > increment && integer <= 2*increment) return new Color(106, 155, 235);
        else if(integer > 2*increment && integer <= 3*increment) return new Color(18, 78, 176);
        else if(integer > 3*increment && integer <= 4*increment) return new Color(49,18,176);
        else if (integer > 4*increment) return new Color(20,4,82);
        else if (integer == 0) return new Color(255,255,255);
        else {
            return new Color(0,0,250);
        }

    }
}
