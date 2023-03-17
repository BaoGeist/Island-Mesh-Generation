package islandADT.Wrappers;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class TileTypeWrapper {

    //TODO B REDO

    private static Map<String, int[]> color_map = new HashMap<String, int[]>();
    public static void add_biome(String type, int[] color) {
        color_map.put(type, color);
    }

    private int[] color = new int[3];


    public TileTypeWrapper(String tile) {
        color_decide(tile);
    }

    private void color_decide(String tile) {
        this.color = color_map.get(tile);
    }

    public int[] getColor() {
        return color;
    }
}
