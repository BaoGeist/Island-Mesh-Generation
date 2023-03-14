package islandADT.Elevation;

public class CustomPrecisionModel {
    private int scale;

    //TODO B easy test
    //TODO B make this not just int
    public CustomPrecisionModel(int scale) {
        this.scale = scale;
    }

    public int makePrecise(double value) {
        return (int) Math.round(value);
    }
}
