package ca.mcmaster.cas.se2aa4.a2.visualizer.Configurations;

import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.visualizer.RenderOptions.HeatmapRenderer;
import ca.mcmaster.cas.se2aa4.a2.visualizer.MeshDump;
import ca.mcmaster.cas.se2aa4.a2.visualizer.SVGCanvas;
import ca.mcmaster.cas.se2aa4.a2.visualizer.Specifications.VisualizerSpecification;
import org.apache.commons.cli.*;

import java.awt.*;
import java.io.IOException;

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
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public VisualizerSpecification getVisualizerSpecifications() {
        String input = cli.getOptionValue(INPUT);
        String output = cli.getOptionValue(OUTPUT);
        String mode = cli.getOptionValue(MODE);

        return new VisualizerSpecification(input, output, mode);
    }

    private Options options() {
        Options options = new Options();
        options.addOption(new Option(INPUT, true, "Input mesh file"));
        options.addOption(new Option(OUTPUT, true, "Output mesh file"));
        options.addOption(new Option(HELP, false, "Print help message"));
        options.addOption(new Option(MODE, true, "Island shape"));
        return options;
    }

    private void help() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("java -jar visualizer.jar", options());
        System.exit(0);
    }

    public void runConfig(String[] args) {



        Option input = Option.builder("mf")
                .argName("mesh_file")
                .hasArg()
                .required(true)
                .desc("file for writing mesh to")
                .build();

        Option output = Option.builder("of")
                .argName("output_file")
                .hasArg()
                .required(true)
                .desc("Mesh output file SVG")
                .build();

        Option debug = Option.builder("X")
                .argName("Debug mode")
                .hasArg(false)
                .desc("Activate Debug mode")
                .build();

        Option help = Option.builder("h")
                .argName("help")
                .hasArg(false)
                .desc("help")
                .build();

        Options options = new Options();

        options.addOption(input);
        options.addOption(output);
        options.addOption(debug);
        options.addOption(help);

        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine line = parser.parse(options, args);

            if(line.hasOption("h")){
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("ant", options);
            }

            if (line.hasOption("mf")){
                if (line.hasOption("of")){
                    // Getting width and height for the canvas
                    Structs.Mesh aMesh = new MeshFactory().read(line.getOptionValue("mf"));
                    double max_x = Double.MIN_VALUE;
                    double max_y = Double.MIN_VALUE;
                    for (Structs.Vertex v: aMesh.getVerticesList()) {
                        max_x = (Double.compare(max_x, v.getX()) < 0? v.getX(): max_x);
                        max_y = (Double.compare(max_y, v.getY()) < 0? v.getY(): max_y);
                    }
                    //Creating canvas to draw the mesh
                    Graphics2D canvas = SVGCanvas.build((int) Math.ceil(max_x), (int) Math.ceil(max_y));
                    HeatmapRenderer renderer = new HeatmapRenderer();

                    if (line.hasOption("X")){
                        //Turn on debug mode
                        renderer.debug(aMesh, canvas);
                    } else {
                        renderer.render(aMesh, canvas);
                    }
                    // Storing the result in an SVG file
                    SVGCanvas.write(canvas, line.getOptionValue("of"));
                    // Dump the mesh to stdout
                    MeshDump dumper = new MeshDump();
                    dumper.dump(aMesh);
                }
            }
        } catch (ParseException | IOException exp){
            System.err.println("Parsing failed. Reason: " + exp.getMessage());
        }
    }

}
