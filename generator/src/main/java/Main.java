import ca.mcmaster.cas.se2aa4.a2.generator.*;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import java.io.IOException;

public class Main {

    // generate irregular and regular mesh at the same time
    // standard command line call - java -jar generator.jar sample.mesh regular 500 500 25 1.00f 1
    // standard CLI call for irregular - java -jar generator.jar sample.mesh irregular 500 500 200
    public static void main(String[] args) throws IOException {

        MeshFactory factory = new MeshFactory();

        int width = (args.length > 2) ? Integer.parseInt(args[2]): 500;
        int height = (args.length > 3) ? Integer.parseInt(args[3]): 500;

        switch(MeshGeneratorEnum.valueOf(args[1])) {
            case regular:
                int square_size = (args.length > 4) ? Integer.parseInt(args[4]) : 25;
                float alpha_entry = (args.length > 5) ? Float.parseFloat(args[5]) : 1.00f;
                int thickness = (args.length > 6) ? Integer.parseInt(args[6]) : 1;

                OurMesh generator = new OurMesh(width, height, square_size, alpha_entry, thickness);
                Mesh myMesh = generator.generate();

                factory.write(myMesh, args[0]);
                break;
            case irregular:
                int num_polygons = (args.length > 4) ? Integer.parseInt(args[4]) : 200;

                OurIrregular generator2 = new OurIrregular(width, height, num_polygons);
                Mesh myMesh2 = generator2.generate();

                factory.write(myMesh2, args[0]);
                break;
            default:
                System.out.println("Invalid command, please generate a 'regular' or 'irregular' mesh");
        }
    }

}