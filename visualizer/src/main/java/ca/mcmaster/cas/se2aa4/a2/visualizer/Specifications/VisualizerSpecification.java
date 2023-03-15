package ca.mcmaster.cas.se2aa4.a2.visualizer.Specifications;

public class VisualizerSpecification {
    private String input;
    private String output;
    private String mode;

    public VisualizerSpecification(String input, String output, String mode) {
        this.input = input;
        this.output = output;
        this.mode = mode;
    }

    public String getInput() {
        return input;
    }

    public String getOutput() {
        return output;
    }

    public String getMode() {
        return mode;
    }
}
