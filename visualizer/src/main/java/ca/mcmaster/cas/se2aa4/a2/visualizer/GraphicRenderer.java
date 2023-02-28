package ca.mcmaster.cas.se2aa4.a2.visualizer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;


import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Path2D;
import java.awt.geom.Ellipse2D;

import static ca.mcmaster.cas.se2aa4.a2.visualizer.PropertyUtils.*;

public class GraphicRenderer {

    private static final int THICKNESS = 3;
    public void render(Mesh aMesh, Graphics2D canvas) {
        canvas.setColor(Color.BLACK);
        Stroke stroke = new BasicStroke(0.5f);
        canvas.setStroke(stroke);
        for (Structs.Polygon p: aMesh.getPolygonsList()) {
            Color polygon_color = extractColor(p.getPropertiesList());
            float[] x_coords = extractCoordsforPolygons(p.getPropertiesList()).get(0);
            float[] y_coords = extractCoordsforPolygons(p.getPropertiesList()).get(1);

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
        for (Structs.Segment s: aMesh.getSegmentsList()){
            Vertex v1 = aMesh.getVertices(s.getV1Idx());
            Vertex v2 = aMesh.getVertices(s.getV2Idx());

            Color segment_color = extractColor(s.getPropertiesList());

            canvas.setColor(segment_color);
//            Stroke strokeseg = new BasicStroke(10);
//            canvas.setStroke(strokeseg);
            canvas.drawLine((int) v1.getX(), (int) v1.getY(), (int) v2.getX(), (int) v2.getY());
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
    }

    public void debug(Mesh aMesh, Graphics2D canvas) {
        canvas.setColor(Color.BLACK);
        Stroke stroke = new BasicStroke(0.5f);
        canvas.setStroke(stroke);
        for (Structs.Polygon p: aMesh.getPolygonsList()) { //polygons are drawn first as the back layer
            float[] x_coords = extractCoordsforPolygons(p.getPropertiesList()).get(0);
            float[] y_coords = extractCoordsforPolygons(p.getPropertiesList()).get(1);

            Path2D.Float path = new Path2D.Float();
            path.moveTo(x_coords[0], y_coords[0]);

            for (int i = 1; i < x_coords.length; i++) {
                path.lineTo(x_coords[i], y_coords[i]);
            }
            path.closePath();

            canvas.setColor(Color.BLACK);
            canvas.fill(path);
        }
        for (Vertex v: aMesh.getVerticesList()) { //vertices and segments are drawn overtop of polygons
            double centre_x = v.getX() - (THICKNESS/2.0d);
            double centre_y = v.getY() - (THICKNESS/2.0d);
            Ellipse2D point = new Ellipse2D.Double(centre_x, centre_y, THICKNESS, THICKNESS);
            boolean centroid_or_nah = extractCentroid(v.getPropertiesList());
            if (centroid_or_nah == true) {
                canvas.setColor(Color.RED);
            }
            else {
                canvas.setColor(Color.BLACK);
            }
            canvas.fill(point);
        }
        for (Structs.Segment s: aMesh.getSegmentsList()){
            Vertex v1 = aMesh.getVertices(s.getV1Idx());
            Vertex v2 = aMesh.getVertices(s.getV2Idx());

            canvas.setColor(Color.BLACK);
            canvas.drawLine((int) v1.getX(), (int) v1.getY(), (int) v2.getX(), (int) v2.getY());

        }
        for (Structs.Polygon p: aMesh.getPolygonsList()) { //neighbourhoods need to be drawn last, but they require polygons, so we repeat
            canvas.setColor(Color.GRAY);
            for (Structs.Polygon q: aMesh.getPolygonsList()) {
                int p_neighbour = extractNeighbourID(p.getPropertiesList());
                int q_neighbour = extractNeighbourID(q.getPropertiesList());
                if (p_neighbour == q_neighbour) { //this confirms that the two polygons are neighbours
                    int[] lines_p = new int[50];
                    int[] lines_q = new int[50];
                    lines_p = extractSegmentIDs(p.getPropertiesList());
                    lines_q = extractSegmentIDs(q.getPropertiesList());
                    for (int i = 0; i < 50; i++) {
                        for (int j = 0; j < 50; j++) {
                            if (lines_p[i] == lines_q[j]) {
                                for (Structs.Segment s: aMesh.getSegmentsList()) {
                                    if (extractSegID(s.getPropertiesList()) == lines_p[i]) {
                                        float[] vertices = extractHeadTail(s.getPropertiesList());
                                        double x1 = vertices[0];
                                        double y1 = vertices[1];
                                        double x2 = vertices[2];
                                        double y2 = vertices[3];
                                        canvas.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}