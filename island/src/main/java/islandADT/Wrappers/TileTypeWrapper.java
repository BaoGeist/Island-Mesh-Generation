package islandADT.Wrappers;

public class TileTypeWrapper {

    public enum TileType {
        Lagoon, Ocean
    }


    private TileType tileType;
    private int[] color = new int[3];

    public TileTypeWrapper(TileType tileType) {
        this.tileType = tileType;
        color_decide();
    }

    //TODO find better ways to implement this
    //TODO set the rigth colours
    private void color_decide() {
        switch (tileType) {
            case Lagoon:
                color[0] = 76;
                color[1] = 183;
                color[2] = 165;
                break;
            case Ocean:
                color[0] = 0;
                color[1] = 105;
                color[2] = 148;
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
