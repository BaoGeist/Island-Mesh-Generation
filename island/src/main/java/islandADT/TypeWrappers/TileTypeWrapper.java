package islandADT.TypeWrappers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TileTypeWrapper {

    //TODO B REDO
    private static Map<String, TileThruple> tuple_map = new HashMap<String, TileThruple>();

    private TileThruple<int[], Boolean, Boolean> tileThruple;
    private String key;

    public static void add_biome(String type, int[] color, boolean landOrNah, boolean waterOrNah) {
        tuple_map.put(type, new TileThruple(color, landOrNah, waterOrNah));
    }

    private int[] color = new int[3];


    public TileTypeWrapper(String tile) {
        tileThruple = tuple_map.get(tile);
        key = tile;
        color_decide(tile);
    }

    private void color_decide(String tile) {
        this.color = (int[]) tuple_map.get(tile).getFirst();
    }

    public int[] getColor() {
        return color;
    }

    private String getKey() {return key; }

    public boolean isLandOrNah() {
        return tileThruple.getSecond();
    }

    public boolean isWaterOrNah() {
        return tileThruple.getThird();
    }

    public boolean isEquals(Object o) {
        if(this == o) return true;
        if(!(o instanceof TileTypeWrapper)) return false;
        TileTypeWrapper compare = (TileTypeWrapper) o;
        return this.key.equals(compare.getKey());
    }

    public boolean isEqualsMultiple(Object... objects) {
        for(Object object: objects) {
            if(isEquals(object)) {
                return true;
            };
        }
        return false;
    }
}
