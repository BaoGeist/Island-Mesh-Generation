package ca.mcmaster.cas.se2aa4.a2.visualizer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import org.apache.batik.ext.awt.geom.Polygon2D;


import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Path2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ca.mcmaster.cas.se2aa4.a2.visualizer.PropertyUtils.*;

public class GraphicRenderer {

    private static final int THICKNESS = 3;
    public void render(Mesh aMesh, Graphics2D canvas) {
        canvas.setColor(Color.BLACK);
        Stroke stroke = new BasicStroke(0.5f);
        canvas.setStroke(stroke);
        // for polygons, STILL DOES NOT WORK, NOT SURE WHY
        for (Structs.Polygon p: aMesh.getPolygonsList()) {
            Color polygon_color = extractColor(p.getPropertiesList());
            float[] x_coords = extractCoords(p.getPropertiesList()).get(0);
            float[] y_coords = extractCoords(p.getPropertiesList()).get(1);

            Path2D.Float path = new Path2D.Float();
            path.moveTo(x_coords[0], y_coords[0]);

            for (int i = 1; i < x_coords.length; i++) {
                path.lineTo(x_coords[i], y_coords[i]);
            }
            path.lineTo(x_coords[0], y_coords[0]);
            path.closePath();

            canvas.setColor(polygon_color);
            canvas.fill(path);
        }

        for (Vertex v: aMesh.getVerticesList()) {
            double centre_x = v.getX() - (THICKNESS/2.0d);
            double centre_y = v.getY() - (THICKNESS/2.0d);
            Color old = canvas.getColor();
            canvas.setColor(extractColor(v.getPropertiesList()));
            Ellipse2D point = new Ellipse2D.Double(centre_x, centre_y, THICKNESS, THICKNESS);
            canvas.fill(point);
            canvas.setColor(old);
        }

        for (Structs.Segment s: aMesh.getSegmentsList()){
            float[] vertices = extractHeadTail(s.getPropertiesList());
            // baoze started here
            Color segment_color = extractColor(s.getPropertiesList());
            double x1 = vertices[0];
            double y1 = vertices[1];
            double x2 = vertices[2];
            double y2 = vertices[3];

            canvas.setColor(segment_color);
            canvas.drawLine((int) x1, (int) y1, (int) x2, (int) y2);

        }
    }

    public void debug(Mesh aMesh, Graphics2D canvas) {
        canvas.setColor(Color.WHITE);
        Stroke stroke = new BasicStroke(0.5f);
        canvas.setStroke(stroke);
        for (Structs.Polygon p: aMesh.getPolygonsList()) {
            float[] x_coords = extractCoords(p.getPropertiesList()).get(0);
            float[] y_coords = extractCoords(p.getPropertiesList()).get(1);

            Path2D.Float path = new Path2D.Float();
            path.moveTo(x_coords[0], y_coords[0]);

            for (int i = 1; i < x_coords.length; i++) {
                path.lineTo(x_coords[i], y_coords[i]);
            }
            path.closePath();

            canvas.setColor(Color.BLACK);
            canvas.fill(path);

            Color centroid = Color.RED;
            double[] centroid_coords = new double[]{2.0, 2.0};
            Ellipse2D point = new Ellipse2D.Double(centroid_coords[0], centroid_coords[1], THICKNESS, THICKNESS);
            canvas.fill(point);
            canvas.setColor(centroid);
        }
        for (Vertex v: aMesh.getVerticesList()) {
            double centre_x = v.getX() - (THICKNESS/2.0d);
            double centre_y = v.getY() - (THICKNESS/2.0d);
            Color old = Color.BLACK;
            canvas.setColor(extractColor(v.getPropertiesList()));
            Ellipse2D point = new Ellipse2D.Double(centre_x, centre_y, THICKNESS, THICKNESS);
            canvas.fill(point);
            canvas.setColor(old);
        }
        for (Structs.Segment s: aMesh.getSegmentsList()){
            float[] vertices = extractHeadTail(s.getPropertiesList());
            // baoze started here
            Color segment_color = Color.BLACK;
            double x1 = vertices[0];
            double y1 = vertices[1];
            double x2 = vertices[2];
            double y2 = vertices[3];

            canvas.setColor(segment_color);
            canvas.drawLine((int) x1, (int) y1, (int) x2, (int) y2);

        }

    }



}
