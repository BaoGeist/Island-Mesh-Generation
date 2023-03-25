package islandADT.Specifications;

public class IslandSpecifications {
    private String input;
    private String output;
    private String shape;
    private String elevation;
    private String seed;
    private String lakes;
    private String aquifers;
    private String soil;
    private String rivers;
    private String biomes;
    private String mode;

    public IslandSpecifications(String input, String output, String shape, String elevation, String seed, String lakes, String rivers, String aquifers, String soil, String biomes, String mode) {
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
        this.soil = soil;
        this.biomes = biomes;
        this.mode = mode;
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

    public String getMode() {
        return mode;
    }

    public String getSeed() {return seed;}
    public String getLakes() {return lakes;}
    public String getSoil() {return soil;}
    public String getRivers() {return rivers;}
    public String getAquifers() {return aquifers;}
    public String getBiomes() {return biomes;}
}
