package ca.mcmaster.cas.se2aa4.a2.visualizer;

import org.locationtech.jts.geom.PrecisionModel;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.MultiPoint;
import org.locationtech.jts.triangulate.VoronoiDiagramBuilder;

import org.locationtech.jts.geom.*;
import org.locationtech.jts.triangulate.VoronoiDiagramBuilder;

import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
public class Test {
    public static void main(String[] args) {


        double max_x = Double.MIN_VALUE;
        double max_y = Double.MIN_VALUE;
        Graphics2D canvas = SVGCanvas.build((int) 500, 500);
        Stroke stroke = new BasicStroke(0.5f);
        canvas.setStroke(stroke);

        PrecisionModel newModel = new PrecisionModel(PrecisionModel.FLOATING_SINGLE);
        VoronoiDiagramBuilder newVoronoi = new VoronoiDiagramBuilder();
        System.out.println(newModel.getType());

        GeometryFactory newFactory = new GeometryFactory();
        Random random = new Random();
        ArrayList<Coordinate> listCoordinates = new ArrayList<>();

        for (int i = 0; i < 250; i++) {
            double x = random.nextDouble() * 500;
            double y = random.nextDouble() * 500;
            Coordinate randomCoordinate = new Coordinate(x, y);
            newModel.makePrecise(randomCoordinate);
            listCoordinates.add(randomCoordinate);
            System.out.println(randomCoordinate.toString());
        }

        newVoronoi.setSites(listCoordinates);
        Geometry voronoiedPoints = newVoronoi.getDiagram(newFactory);


        // Set up the image to draw the diagram on
        canvas.setColor(Color.BLACK);

        // Iterate over the Voronoi diagram cells and draw each one
        System.out.println("searaptre");
        for (int i = 0; i < voronoiedPoints.getNumGeometries(); i++) {
            Geometry cell = voronoiedPoints.getGeometryN(i);
            Path2D.Float path = new Path2D.Float();
            path.moveTo(voronoiedPoints.getGeometryN(i).getCoordinates()[0].x, voronoiedPoints.getGeometryN(i).getCoordinates()[0].y);
            for (Coordinate c : cell.getCoordinates()) {
                System.out.println(c.toString());
                double[] coordArray = new double[]{c.x, c.y};
                path.lineTo(coordArray[0], coordArray[1]);
            }
            path.closePath();
            canvas.setColor(Color.GREEN);
            canvas.draw(path);
        }
        String output = "test.svg";
        try {
            SVGCanvas.write(canvas, output);
        } catch (IOException e) {
            System.out.println("fuck");
        }

    }
}
