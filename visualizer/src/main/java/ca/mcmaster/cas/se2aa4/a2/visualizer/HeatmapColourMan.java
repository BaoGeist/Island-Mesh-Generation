package ca.mcmaster.cas.se2aa4.a2.visualizer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.awt.*;

import static ca.mcmaster.cas.se2aa4.a2.visualizer.PropertyUtils.extractHeight;
import static ca.mcmaster.cas.se2aa4.a2.visualizer.PropertyUtils.extractMinMaxHeights;

// takes in any min and max and divides it into 5 levels
public class HeatmapColourMan {
    private int increment, min, max;

    //TODO B not make this all extractheight
    public HeatmapColourMan(Structs.Mesh aMesh) {
        double[] min_max = extractMinMaxHeights(aMesh.getPropertiesList());
        min = (int) min_max[0];
        max = (int) min_max[1];
        increment = (int) ((min_max[1] - min_max[0])/5);
    }

    public Color color_from_integer(int integer) {
        if(integer > 0 && integer <= increment) return new Color(0,0,250);
        else if (integer > increment && integer <= 2*increment) return new Color(0, 0, 200);
        else if(integer > 2*increment && integer <= 3*increment) return new Color(0, 0, 150);
        else if(integer > 3*increment && integer <= 4*increment) return new Color(0,0,100);
        else if (integer > 4*increment && integer <= max) return new Color(0,0,50);
        else if (integer == 0) return new Color(255,255,255);
        else return Color.green;

    }
}
