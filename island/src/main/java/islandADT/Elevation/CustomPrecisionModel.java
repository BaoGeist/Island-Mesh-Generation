package islandADT.Elevation;

public class CustomPrecisionModel {
    private int scale;

    public CustomPrecisionModel(int scale) {
        this.scale = scale;
    }

    public int makePrecise(double value) {
        return (int) Math.round(value);
    }
}
