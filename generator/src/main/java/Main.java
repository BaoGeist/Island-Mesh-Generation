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

        int width = Integer.parseInt(args[2]);
        int height = Integer.parseInt(args[3]);

        if (args[1].equals("regular")){

            int square_size = Integer.parseInt(args[4]);
            float alpha_entry = Float.parseFloat(args[5]);
            int thickness = Integer.parseInt(args[6]);

            OurMesh generator = new OurMesh(width, height, square_size, alpha_entry, thickness);
            Mesh myMesh = generator.generate();

            factory.write(myMesh, args[0]);

        } else if (args[1].equals("irregular")){

            int num_polygons = Integer.parseInt(args[4]);

            OurIrregular generator2 = new OurIrregular(width, height, num_polygons);
            Mesh myMesh2 = generator2.generate();

            factory.write(myMesh2, args[0]);

        } else {
            System.out.println("Invalid command, please generate a 'regular' or 'irregular' mesh");
        }
    }

}