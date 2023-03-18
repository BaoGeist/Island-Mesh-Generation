package islandADT.TypeWrappers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TileTypeWrapper {

    //TODO B REDO
    private static Map<String, TileTuple> tuple_map = new HashMap<String, TileTuple>();

    private TileTuple<int[], Boolean> tileTuple;

    public static void add_biome(String type, int[] color, boolean landOrNah) {
        tuple_map.put(type, new TileTuple(color, landOrNah));
    }

    private int[] color = new int[3];


    public TileTypeWrapper(String tile) {
        tileTuple = tuple_map.get(tile);
        color_decide(tile);
    }

    private void color_decide(String tile) {
        this.color = (int[]) tuple_map.get(tile).getFirst();
    }

    public int[] getColor() {
        return color;
    }

    public boolean isLandOrNah() {
        return tileTuple.getSecond();
    }

    public boolean isEquals(Object o) {
        if(this == o) return true;
        if(!(o instanceof TileTypeWrapper)) return false;
        TileTypeWrapper compare = (TileTypeWrapper) o;
        return Arrays.equals(this.color, compare.getColor());
    }
}
