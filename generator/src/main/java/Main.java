import ca.mcmaster.cas.se2aa4.a2.generator.*;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import org.apache.commons.cli.*;
import java.io.IOException;

public class Main {

    // generate irregular and regular mesh at the same time
    // standard command line call - java -jar generator.jar -mf sample.mesh -mv regular -s 500 -ss 25 -t 1
    // standard CLI call for irregular - java -jar generator.jar -mf sample.mesh -mv irregular -w 500 -h 500 -num 200
    public static void main(String[] args) throws IOException, ParseException {

        MeshFactory factory = new MeshFactory();

        Option mesh = Option.builder("mf")
                .argName("mesh_file")
                .hasArg()
                .required(true)
                .desc("file for writing mesh to")
                .build();

        Option meshversion = Option.builder("mv")
                .argName("mesh_version")
                .hasArg()
                .required(true)
                .desc("which mesh is built - regular or irregular")
                .build();

        Option width  = Option.builder("s")
                .argName("side")
                .hasArg()
                .desc("size of side of mesh")
                .build();

        Option square_size = Option.builder("ss")
                .argName("square_size")
                .hasArg()
                .desc("size of the lengths of the squares being built")
                .build();

        Option num_of_polygons = Option.builder("num")
                .argName("num_of_polygons")
                .hasArg()
                .desc("number of polygons generated")
                .build();

        Option lloyd_num = Option.builder("ln")
                .argName("Lloyd Number")
                .hasArg()
                .desc("Relaxation level")
                .build();

        Option help = Option.builder("h")
                .argName("help")
                .hasArg(false)
                .desc("help")
                .build();

        Options options = new Options();

        options.addOption(mesh);
        options.addOption(meshversion);
        options.addOption(width);
        options.addOption(square_size);
        options.addOption(num_of_polygons);
        options.addOption(lloyd_num);
        options.addOption(help);

        CommandLineParser parser = new DefaultParser();

        try {
            // parse the command line arguments
            CommandLine line = parser.parse(options, args);
            String meshfile = line.getOptionValue("mf");

            if(line.hasOption("h")){
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("ant", options);
            }

            if(line.hasOption("mf")) {
                int sideInt;

                if(line.hasOption("s")){
                    sideInt = Integer.parseInt(line.getOptionValue("s"));
                } else {
                    sideInt = 500;
                }

                if(line.hasOption("mv")){

                    String version = line.getOptionValue("mv");

                    if(version.equals("regular")){

                        int square_sizeInt;
                        if(line.hasOption("ss")){
                            square_sizeInt = Integer.parseInt(line.getOptionValue("ss"));
                            if (sideInt%square_sizeInt != 0){
                                System.out.println("ur a bonobo. That's not a square.");
                                sideInt -= sideInt%square_sizeInt;
                                System.out.println("But its ok :). We fixed it for you. You are welcome.");
                            }
                        } else {
                            square_sizeInt = 25;
                        }

                        OurMesh generator = new OurMesh(sideInt, sideInt, square_sizeInt,1, 1);
                        Mesh myMesh = generator.generate();
                        factory.write(myMesh, meshfile);

                    } else if (version.equals("irregular")){

                        int num_polygons;
                        if (line.hasOption("num")){
                            num_polygons = Integer.parseInt(line.getOptionValue("num"));
                        } else {
                            num_polygons = 200;
                        }

                        int lloyd_number;
                        if (line.hasOption("ln")){
                            lloyd_number = Integer.parseInt(line.getOptionValue("ln"));
                        } else {
                            lloyd_number = 5;
                        }

                        OurIrregular generator2 = new OurIrregular(sideInt, sideInt, num_polygons, lloyd_number);
                        Mesh myMesh2 = generator2.generate();
                        factory.write(myMesh2, meshfile);

                    } else {
                        System.err.println("Parsing failed. Reason: No meshversion passed");
                    }
                }
            }
        }
        catch (ParseException exp) {
            System.err.println("Parsing failed.  Reason: " + exp.getMessage());
        }
    }

}