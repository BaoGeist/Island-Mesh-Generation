package ca.mcmaster.cas.se2aa4.a2.visualizer.Configurations;

import ca.mcmaster.cas.se2aa4.a2.visualizer.Specifications.VisualizerSpecification;
import org.apache.commons.cli.*;

public class VisualizerConfiguration {
    public static final String INPUT = "i";
    public static final String OUTPUT = "o";
    public static final String HELP = "help";
    private static final String MODE = "mode";
    private CommandLine cli;
    public VisualizerConfiguration(String[] args) {
        try {
            this.cli = new DefaultParser().parse(options(), args);
            if (cli.hasOption((HELP))) {
                help();
            }
            if(!inputValidation()) {
                System.exit(0);
            }

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

    public VisualizerSpecification getVisualizerSpecifications() {
        String input = cli.getOptionValue(INPUT);
        String output = cli.getOptionValue(OUTPUT);
        String mode;
        if(cli.hasOption(MODE)) {
            mode = cli.getOptionValue(MODE);
        } else {
            mode = "graphic";
        }


        return new VisualizerSpecification(input, output, mode);
    }

    private Options options() {
        Options options = new Options();
        options.addOption(new Option(INPUT, true, "Input mesh file"));
        options.addOption(new Option(OUTPUT, true, "Output mesh file"));
        options.addOption(new Option(HELP, false, "Print help message"));
        options.addOption(new Option(MODE, true, "Renderer mode"));
        return options;
    }

    private void help() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("java -jar visualizer.jar", options());
        System.exit(0);
    }

}
