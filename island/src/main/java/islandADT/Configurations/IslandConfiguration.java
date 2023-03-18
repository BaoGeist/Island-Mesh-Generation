package islandADT.Configurations;

import islandADT.Specifications.IslandSpecifications;
import org.apache.commons.cli.*;

public class IslandConfiguration {
    private static final String INPUT = "i";
    private static final String OUTPUT = "o";
    private static final String SHAPE = "shape";
    private static final String ELEVATION = "altitude";
    private static final String HELP = "help";
    private static final String SEED = "seed";
    private static final String LAKES = "lake";
    private static final String AQUIFERS = "aquifer";
    private CommandLine cli;
    public IslandConfiguration(String[] args) {
        try {
            this.cli = new DefaultParser().parse(options(), args);
            if (cli.hasOption((HELP))) {
                help();
            }
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }

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
        options.addOption(new Option(AQUIFERS, true, "Maximum number of aquifers"));
        return options;
    }

    private void help() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("java -jar island.jar", options());
        System.exit(0);
    }

    public IslandSpecifications getIslandSpecifications() {
        String input = cli.getOptionValue(INPUT);
        String output = cli.getOptionValue(OUTPUT);
        System.out.println(output);
        System.out.println();
        String shape = cli.getOptionValue(SHAPE);
        String elevation = cli.getOptionValue(ELEVATION);
        String seed = cli.getOptionValue(SEED);
        String lakes = cli.getOptionValue(LAKES);
        String aquifers = cli.getOptionValue(AQUIFERS);

        return new IslandSpecifications(input, output, shape, elevation, seed, lakes, aquifers);
    }
}
