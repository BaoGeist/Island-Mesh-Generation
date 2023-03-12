package islandADT.Wrappers;

public class TileTypeWrapper {

    public enum TileType {
        Lagoon, Ocean, Sand, Land
    }

    private TileType tileType;
    private int[] color = new int[3];

    public TileTypeWrapper(TileType tileType) {
        this.tileType = tileType;
        color_decide();
    }

    //TODO find better ways to implement this
    private void color_decide() {
        switch (tileType) {
            case Lagoon:
                color[0] = 1;
                color[1] = 120;
                color[2] = 144;
                break;
            case Ocean:
                color[0] = 0;
                color[1] = 86;
                color[2] = 161;
                break;
            case Sand:
                color[0] = 194;
                color[1] = 178;
                color[2] = 128;
                break;
            case Land:
                color[0] = 124;
                color[1] = 252;
                color[2] = 0;
                break;
            default:
                color[0] = 0;
                color[1] = 0;
                color[2] = 0;
        }
    }

    public TileType getTileType() {
        return tileType;
    }

    public int[] getColor() {
        return color;
    }
}
