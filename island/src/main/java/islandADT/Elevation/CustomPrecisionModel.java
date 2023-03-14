package islandADT.Elevation;

public class CustomPrecisionModel {
    private int scale;

    //TODO easy test
    public CustomPrecisionModel(int scale) {
        this.scale = scale;
    }

    public int makePrecise(double value) {
        return (int) Math.round(value);
    }
}
