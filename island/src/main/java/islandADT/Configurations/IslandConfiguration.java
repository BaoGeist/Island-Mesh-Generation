package islandADT.Configurations;

import islandADT.Specifications.IslandSpecifications;
import org.apache.commons.cli.*;

import java.util.HashMap;
import java.util.Map;

public class IslandConfiguration {
    private static final String INPUT = "i";
    private static final String OUTPUT = "o";
    private static final String SHAPE = "shape";
    private static final String ELEVATION = "altitude";
    private static final String HELP = "help";
    private static final String SEED = "seed";
    private static final String LAKES = "lake";
    private static final String RIVERS = "river";
    private static final String AQUIFERS = "aquifer";
    private static final String SOIL = "soil";
    private static final String BIOMES = "biomes";
    private static final String MODE = "mode";
    private static final String CITY = "cities";
    private CommandLine cli;
    public IslandConfiguration(String[] args) {
        try {
            this.cli = new DefaultParser().parse(options(), args);
            if (cli.hasOption((HELP))) {
                help();
            }
            if(! inputValidation()) System.exit(0);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }

    }

    private boolean inputValidation() {
        if(! cli.hasOption((INPUT))) {
            System.out.println("Ensure an input file provided. Use -help as need");
            return false;
        }
        if(! cli.hasOption((OUTPUT))) {
            System.out.println("Ensure an output file provided");
            return false;
        }
        return true;
    }

    private Options options() {
        Options options = new Options();
        options.addOption(new Option(INPUT, true, "Input mesh file"));
        options.addOption(new Option(OUTPUT, true, "Output mesh file"));
        options.addOption(new Option(HELP, false, "Print help message"));
        options.addOption(new Option(SHAPE, true, "Island shape"));
        options.addOption(new Option(ELEVATION, true, "Elevation profile"));
        options.addOption(new Option(SEED, true, "Seed"));
        options.addOption(new Option(LAKES, true, "Maximum number of lakes"));
        options.addOption(new Option(RIVERS, true, "Total number of rivers"));
        options.addOption(new Option(AQUIFERS, true, "Maximum number of aquifers"));
        options.addOption(new Option(SOIL, true, "Soil type and moisture absorption levels"));
        options.addOption(new Option(MODE, true, "Type of mesh for rendering"));
        options.addOption(new Option(BIOMES, true, "Whittaker biome type"));
        options.addOption(new Option(CITY, true, "Urbanism expansion - number of cities generated"));
        return options;
    }

    private void help() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("java -jar island.jar", options());
        System.exit(0);
    }

    public IslandSpecifications getIslandSpecifications() {
        Map<String, String> defaults = new HashMap<>();
        defaults.put(SHAPE, "circle");
        defaults.put(ELEVATION, "plain");
        defaults.put(LAKES, "3");
        defaults.put(RIVERS, "3");
        defaults.put(AQUIFERS, "3");
        defaults.put(SOIL, "fertile");
        defaults.put(BIOMES, "default");
        defaults.put(MODE, "normal");
        defaults.put(CITY, "10");

        String input = cli.getOptionValue(INPUT);
        String output = cli.getOptionValue(OUTPUT);
        String shape = cli.hasOption(SHAPE) ? cli.getOptionValue(SHAPE): defaults.get(SHAPE);
        String elevation = cli.hasOption(ELEVATION) ? cli.getOptionValue(ELEVATION): defaults.get(ELEVATION);
        String seed = cli.getOptionValue(SEED);
        String lakes = cli.hasOption(LAKES) ? cli.getOptionValue(LAKES) : defaults.get(LAKES);
        String rivers = cli.hasOption(RIVERS) ? cli.getOptionValue(RIVERS) : defaults.get(RIVERS);
        String aquifers = cli.hasOption(AQUIFERS) ? cli.getOptionValue(AQUIFERS) : defaults.get(AQUIFERS);
        String soil = cli.hasOption(SOIL) ? cli.getOptionValue(SOIL) : defaults.get(SOIL);
        String biomes = cli.hasOption(BIOMES) ? cli.getOptionValue(BIOMES) : defaults.get(BIOMES);
        String mode = cli.hasOption(MODE) ? cli.getOptionValue(MODE) : defaults.get(MODE);
        String city = cli.hasOption(CITY) ? cli.getOptionValue(CITY) : defaults.get(CITY);

        return new IslandSpecifications(input, output, shape, elevation, seed, lakes, rivers, aquifers, soil, biomes, mode, city);
    }
}
