import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.visualizer.GraphicIrregular;
import ca.mcmaster.cas.se2aa4.a2.visualizer.GraphicRenderer;
import ca.mcmaster.cas.se2aa4.a2.visualizer.MeshDump;
import ca.mcmaster.cas.se2aa4.a2.visualizer.SVGCanvas;

import java.awt.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        // if statement determines whether irregular or regular mesh-optimized renderer are created
        // true is regular, false is irregular

        if(true) {
            // Extracting command line parameters
            String input = args[0];
            String output = args[1];
            boolean debug = false;
            for (String arg: args) {
                if (arg.equals("-X")) debug = true;
            }
            // Getting width and height for the canvas
            Structs.Mesh aMesh = new MeshFactory().read(input);
            double max_x = Double.MIN_VALUE;
            double max_y = Double.MIN_VALUE;
            for (Structs.Vertex v: aMesh.getVerticesList()) {
                max_x = (Double.compare(max_x, v.getX()) < 0? v.getX(): max_x);
                max_y = (Double.compare(max_y, v.getY()) < 0? v.getY(): max_y);
            }
            // Creating the Canvas to draw the mesh
            Graphics2D canvas = SVGCanvas.build((int) Math.ceil(max_x), (int) Math.ceil(max_y));
            GraphicRenderer renderer = new GraphicRenderer();
            // Painting the mesh on the canvas
            if (debug == false) {
                renderer.render(aMesh, canvas); }
            else {
                renderer.debug(aMesh, canvas);
                if (debug == true) System.out.println("SLAY");
            }
            // Storing the result in an SVG file
            SVGCanvas.write(canvas, output);
            // Dump the mesh to stdout
            MeshDump dumper = new MeshDump();
            dumper.dump(aMesh);
        } else {
            // Extracting command line parameters
            String input = args[0];
            String output = args[1];
            Structs.Mesh aMesh = new MeshFactory().read(input);
            Graphics2D canvas = SVGCanvas.build((int) 500, 500);
            GraphicIrregular renderer = new GraphicIrregular();
            renderer.render(aMesh, canvas);
            SVGCanvas.write(canvas, output);
            MeshDump dumper = new MeshDump();
            dumper.dump(aMesh);
        }

    }
}
