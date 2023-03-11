import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import org.apache.commons.cli.*;

import java.awt.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
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

                }
            }
        } catch (ParseException exp){
            System.err.println("Parsing failed. Reason: " + exp.getMessage());
        }
    }

}
