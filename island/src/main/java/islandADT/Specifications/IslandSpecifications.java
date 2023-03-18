package islandADT.Specifications;

public class IslandSpecifications {
    private String input;
    private String output;
    private String shape;
    private String elevation;
    private String seed;
    private String lakes;
    private String aquifers;

    private String rivers;

    public IslandSpecifications(String input, String output, String shape, String elevation, String seed, String lakes, String rivers, String aquifers) {
        this.input = input;
        this.output = output;
        this.shape = shape;
        this.elevation = elevation;
        if(seed == null) {
            this.seed = "";
        } else this.seed = seed;
        this.lakes = lakes;
        this.rivers = rivers;
        this.aquifers = aquifers;
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

    public String getSeed() {return seed;}

    public String getLakes() {return lakes;}


    public String getRivers() {return rivers;}

    public String getAquifers() {return aquifers;}
}
