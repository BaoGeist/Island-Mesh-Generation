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
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
public class Test {

    private static ArrayList<Coordinate> generate_random_points() {
        PrecisionModel newModel = new PrecisionModel(PrecisionModel.FLOATING_SINGLE);

        Random random = new Random();
        ArrayList<Coordinate> listCoordinates = new ArrayList<>();

        for (int i = 0; i < 250; i++) {
            double x = random.nextDouble() * 500;
            double y = random.nextDouble() * 500;
            Coordinate randomCoordinate = new Coordinate(x, y);
            newModel.makePrecise(randomCoordinate);
            listCoordinates.add(randomCoordinate);
        }
        return listCoordinates;
    }

    private static Geometry generate_voronoi(ArrayList<Coordinate> listCoordinates) {
        VoronoiDiagramBuilder newVoronoi = new VoronoiDiagramBuilder();

        GeometryFactory newFactory = new GeometryFactory();

        newVoronoi.setSites(listCoordinates);
        return newVoronoi.getDiagram(newFactory);
    }

    private static double calculate_length_of_segment(Coordinate c1, Coordinate c2) {
        return Math.sqrt(Math.pow((c1.x - c2.x),2) + (Math.pow((c1.y - c2.y),2)));
    }

    private static double calculate_area_of_triangle(Coordinate c1, Coordinate c2, Coordinate c3) {
        double a = calculate_length_of_segment(c1, c2);
        double b = calculate_length_of_segment(c2, c3);
        double c = calculate_length_of_segment(c3, c1);
        double s = (a + b + c) / 2;
        return Math.sqrt(s*(s-a)*(s-b)*(s-c));
    }

    private static Coordinate calculate_centroid_of_triangle(Coordinate c1, Coordinate c2, Coordinate c3) {
        double x = (c1.x + c2.x + c3.x)/3;
        double y = (c1.y + c2.y + c3.y)/3;
        return new Coordinate(x, y);
    }

    private static Coordinate calculate_weighted_average(ArrayList<Double> areas, ArrayList<Coordinate> coordinates) {
        double x_total = 0, y_total = 0, area = 0;
        for(int i = 0; i < areas.size(); i++) {
            area += areas.get(i);
            x_total += coordinates.get(i).x * areas.get(i);
            y_total += coordinates.get(i).y * areas.get(i);
        }
        return new Coordinate(x_total/area, y_total/area);
    }

    private static Coordinate calculate_lloyd_relaxation_single(Geometry notcell) {
        Coordinate[] lloydcoord = notcell.getCoordinates();
        ArrayList<Coordinate> triangle_centroids = new ArrayList<>();
        ArrayList<Double> triangle_areas = new ArrayList<>();
        int first_iterator = 1, second_iterator = 2;
        while (second_iterator <= lloydcoord.length - 2) {
            double area = calculate_area_of_triangle(lloydcoord[0], lloydcoord[first_iterator], lloydcoord[second_iterator]);
            Coordinate centroid = calculate_centroid_of_triangle(lloydcoord[0], lloydcoord[first_iterator], lloydcoord[second_iterator]);
            triangle_areas.add(area);
            triangle_centroids.add(centroid);
            first_iterator++;
            second_iterator++;
        }
        Coordinate new_centroid = calculate_weighted_average(triangle_areas, triangle_centroids);
        return new_centroid;
    }

    private static ArrayList<Coordinate> calculate_lloyd_relaxation_multiple(Geometry oldPoints) {
        ArrayList<Coordinate> listVoronoied = new ArrayList<>();
        for(int i = 0; i < oldPoints.getNumGeometries(); i++) {
            Geometry notcell = oldPoints.getGeometryN(i);
            Coordinate new_centroid = calculate_lloyd_relaxation_single(notcell);
            listVoronoied.add(new_centroid);
        }
        return listVoronoied;
    }

    private static final int THICKNESS = 3;
    public static void main(String[] args) {

        ArrayList<Coordinate> listCoordinates = generate_random_points();

        Geometry voronoiedPoints = generate_voronoi(listCoordinates);

        for(int i = 0; i < 10; i++) {
            listCoordinates = calculate_lloyd_relaxation_multiple(voronoiedPoints);
            voronoiedPoints = generate_voronoi(listCoordinates);
        }

        //drawing
        Graphics2D canvas = SVGCanvas.build((int) 500, 500);
        Stroke stroke = new BasicStroke(0.5f);
        canvas.setStroke(stroke);
        canvas.setColor(Color.BLACK);

        for (int i = 0; i < voronoiedPoints.getNumGeometries(); i++) {
            Geometry cell = voronoiedPoints.getGeometryN(i);
            Path2D.Float path = new Path2D.Float();
            path.moveTo(voronoiedPoints.getGeometryN(i).getCoordinates()[0].x, voronoiedPoints.getGeometryN(i).getCoordinates()[0].y);
            for (Coordinate c : cell.getCoordinates()) {
                double[] coordArray = new double[]{c.x, c.y};
                path.lineTo(coordArray[0], coordArray[1]);
            }
            path.closePath();
            canvas.setColor(Color.GREEN);
            canvas.draw(path);
        }

//        // lloyd relaxation
//        Geometry notcell = voronoiedPoints.getGeometryN(25);
//        Coordinate new_centroid = calculate_lloyd_relaxation_single(notcell);
//        System.out.println(new_centroid.toString());
//        Ellipse2D point2 = new Ellipse2D.Double(new_centroid.x, new_centroid.y, THICKNESS, THICKNESS);
//        canvas.setColor(Color.CYAN);
//        canvas.fill(point2);
//
//        //
//        Path2D.Float pathpath = new Path2D.Float();
//        pathpath.moveTo(notcell.getCoordinates()[0].x, voronoiedPoints.getGeometryN(0).getCoordinates()[0].y);
//        int intcounter = 0;
//        for (Coordinate c : notcell.getCoordinates()) {
//            double[] coordArray = new double[]{c.x, c.y};
//            System.out.println(Arrays.toString(coordArray));
//            pathpath.lineTo(coordArray[0], coordArray[1]);
//
//            if(intcounter != 1 && intcounter != 0 && intcounter != voronoiedPoints.getGeometryN(25).getCoordinates().length-1  && intcounter != voronoiedPoints.getGeometryN(25).getCoordinates().length-2) {
//                Ellipse2D point = new Ellipse2D.Double(c.x, c.y, THICKNESS, THICKNESS);
//                canvas.setColor(Color.CYAN);
//                canvas.fill(point);
//            }
//            intcounter++;
//
//        }
//        pathpath.closePath();
//        canvas.setColor(Color.RED);
//        canvas.draw(pathpath);


        for (Coordinate c : listCoordinates) {
            Ellipse2D point = new Ellipse2D.Double(c.x, c.y, THICKNESS, THICKNESS);
            canvas.setColor(Color.white);
            canvas.fill(point);
        }

        String output = "test.svg";
        try {
            SVGCanvas.write(canvas, output);
        } catch (IOException e) {
            System.out.println("fuck");
        }

    }
}
