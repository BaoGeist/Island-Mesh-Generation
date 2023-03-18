package ca.mcmaster.cas.se2aa4.a2.visualizer.RenderChooser;

import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.visualizer.MeshDump;
import ca.mcmaster.cas.se2aa4.a2.visualizer.RenderOptions.*;
import ca.mcmaster.cas.se2aa4.a2.visualizer.SVGCanvas;
import ca.mcmaster.cas.se2aa4.a2.visualizer.Specifications.VisualizerSpecification;

import java.awt.*;

public class RenderChooser {
    private VisualizerSpecification visualizerSpecification;
    public RenderChooser(VisualizerSpecification visualizerSpecification) {
        this.visualizerSpecification = visualizerSpecification;
    }

    public void render_choose() {
        Structs.Mesh aMesh = null;
        try {
            aMesh = new MeshFactory().read(visualizerSpecification.getInput());
        } catch (Exception e) {
            System.out.println("nah");
        }
        double max_x = Double.MIN_VALUE;
        double max_y = Double.MIN_VALUE;
        for (Structs.Vertex v: aMesh.getVerticesList()) {
            max_x = (Double.compare(max_x, v.getX()) < 0? v.getX(): max_x);
            max_y = (Double.compare(max_y, v.getY()) < 0? v.getY(): max_y);
        }
        //Creating canvas to draw the mesh
        Graphics2D canvas = SVGCanvas.build((int) Math.ceil(max_x), (int) Math.ceil(max_y));

        Renderer renderer;
        switch(visualizerSpecification.getMode()) {
            case "debug":
                renderer = new DebugRenderer();
                break;
            case "heatmap_altitude":
                renderer = new Moistttttttttttttttttt();
                break;
            case "graphic":
                renderer = new GraphicRenderer();
                break;
            default:
                renderer = new GraphicRenderer();
                break;
        }

        renderer.render(aMesh, canvas);

        // Storing the result in an SVG file
        try {
            SVGCanvas.write(canvas, visualizerSpecification.getOutput());
        } catch (Exception e) {
            System.out.println("wee");
        }

        // Dump the mesh to stdout
        MeshDump dumper = new MeshDump();
        dumper.dump(aMesh);
    }
}
