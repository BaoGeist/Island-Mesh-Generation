package islandADT.Configurations;

import islandADT.Specifications.IslandSpecifications;
import org.apache.commons.cli.*;

public class IslandConfiguration {
    private static final String INPUT = "i";
    private static final String OUTPUT = "o";
    private static final String SHAPE = "shape";
    private static final String ELEVATION = "altitude";
    private static final String HELP = "help";
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
        String shape = cli.getOptionValue(SHAPE);
        String elevation = cli.getOptionValue(ELEVATION);

        return new IslandSpecifications(input, output, shape, elevation);
    }
}
