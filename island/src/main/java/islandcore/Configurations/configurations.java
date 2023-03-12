package islandcore.Configurations;

import org.apache.commons.cli.*;

public class configurations {
    public static void runConfig(String[] args) {
        System.out.println("Running islandcore.Configurations");
        Option mesh = Option.builder("i")
                .argName("input_file")
                .hasArg()
                .required(true)
                .desc("file for writing mesh to")
                .build();

        Option meshversion = Option.builder("o")
                .argName("output_file")
                .hasArg()
                .required(true)
                .desc("file being generated")
                .build();

        Option mode = Option.builder("mode")
                .argName("generate_mode")
                .hasArg()
                .desc("generation mode")
                .build();

        Option help = Option.builder("h")
                .argName("help")
                .hasArg(false)
                .desc("help")
                .build();

        Options options = new Options();

        options.addOption(mesh);
        options.addOption(meshversion);
        options.addOption(mode);
        options.addOption(help);

        CommandLineParser parser = new DefaultParser();

        try {
            // parse the command line arguments
            CommandLine line = parser.parse(options, args);
            String meshfile = line.getOptionValue("mf");

            if (line.hasOption("h")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("ant", options);
            }

            if (line.hasOption("i")) {

                if (line.hasOption("mode")) {

                    String Mesh_mode = line.getOptionValue("mv");

                    if (Mesh_mode.equals("...")) {
                        // edit configs after lagoon/other modes are created
//                        OurMesh generator = new OurMesh();
//                        Structs.Mesh myMesh = generator.generate();
//                        factory.write(myMesh, meshfile);

                    } else if (Mesh_mode.equals("...")) {
                        //more MODES
//                        OurIrregular generator2 = new OurIrregular(sideInt, sideInt, num_polygons, lloyd_number);
//                        Structs.Mesh myMesh2 = generator2.generate();
//                        factory.write(myMesh2, meshfile);

                    } else {
                        System.err.println("Parsing failed. Reason: No mode passed");
                    }
                }
            }
        } catch (ParseException exp) {
            System.err.println("Parsing failed.  Reason: " + exp.getMessage());
        }
    }
}
