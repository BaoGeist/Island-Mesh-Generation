package ca.mcmaster.cas.se2aa4.a2.generator;

import org.locationtech.jts.geom.PrecisionModel;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.MultiPoint;
import org.locationtech.jts.triangulate.VoronoiDiagramBuilder;

import org.locationtech.jts.geom.*;
import org.locationtech.jts.triangulate.VoronoiDiagramBuilder;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
public class OurIrregular {
    public static void main(String[] args) {
        PrecisionModel newModel = new PrecisionModel(PrecisionModel.FLOATING_SINGLE);
        VoronoiDiagramBuilder newVoronoi = new VoronoiDiagramBuilder();
        System.out.println(newModel.getType());

        GeometryFactory newFactory = new GeometryFactory();
        Random random = new Random();
        ArrayList<Coordinate> listCoordinates = new ArrayList<>();

        for (int i = 0; i < 1; i++) {
            double x = random.nextDouble();
            double y = random.nextDouble();
            Coordinate randomCoordinate = new Coordinate(x, y);
            newModel.makePrecise(randomCoordinate);
            listCoordinates.add(randomCoordinate);
        }

        newVoronoi.setSites(listCoordinates);
        Geometry voronoiedPoints = newVoronoi.getDiagram(newFactory);


        // Set up the image to draw the diagram on
        BufferedImage image = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.setBackground(Color.BLACK);

        // Iterate over the Voronoi diagram cells and draw each one
        for (int i = 0; i < voronoiedPoints.getNumGeometries(); i++) {
            Geometry cell = voronoiedPoints.getGeometryN(i);
            for (Coordinate c : cell.getCoordinates()) {
                g2d.fillRect((int) c.x, (int) c.y, 1, 1);
            }
        }

        try {// retrieve image
            File outputfile = new File("saved.png");
            ImageIO.write(image, "png", outputfile);
        } catch (IOException e) {
            System.out.println("fuck");
        }
    }
}
