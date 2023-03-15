package islandADT.Specifications;

public class IslandSpecifications {
    private String input;
    private String output;
    private String shape;
    private String elevation;

    public IslandSpecifications(String input, String output, String shape, String elevation) {
        this.input = input;
        this.output = output;
        this.shape = shape;
        this.elevation = elevation;
    }

    public String getInput() {
        return input;
    }

    public String getOutput() {
        return output;
    }

    public String getShape() {
        return shape;
    }

    public String getElevation() {
        return elevation;
    }
}
