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
        canvas.setColor(Color.WHITE);
        Stroke stroke = new BasicStroke(0.5f);
        canvas.setStroke(stroke);
<<<<<<< HEAD
        for (Structs.Polygon p: aMesh.getPolygonsList()) { //polygons are drawn first as the back layer
            float[] x_coords = extractCoords(p.getPropertiesList()).get(0);
            float[] y_coords = extractCoords(p.getPropertiesList()).get(1);
=======
        for (Structs.Polygon p: aMesh.getPolygonsList()) {
            float[] x_coords = extractCoordsforPolygons(p.getPropertiesList()).get(0);
            float[] y_coords = extractCoordsforPolygons(p.getPropertiesList()).get(1);
>>>>>>> 9485a7ac44e1a7b9b6839ab4bd58f89681d66f68

            Path2D.Float path = new Path2D.Float();
            path.moveTo(x_coords[0], y_coords[0]);

            for (int i = 1; i < x_coords.length; i++) {
                path.lineTo(x_coords[i], y_coords[i]);
            }
            path.closePath();

            canvas.setColor(Color.BLACK);
            canvas.fill(path);

            double[] centroid_coords = new double[]{2.0, 2.0};
            Ellipse2D point = new Ellipse2D.Double(centroid_coords[0], centroid_coords[1], THICKNESS, THICKNESS);
            canvas.setColor(Color.BLACK);
            canvas.fill(point);
        }
        for (Vertex v: aMesh.getVerticesList()) { //vertices and segments are drawn overtop of polygons
            double centre_x = v.getX() - (THICKNESS/2.0d);
            double centre_y = v.getY() - (THICKNESS/2.0d);
            Ellipse2D point = new Ellipse2D.Double(centre_x, centre_y, THICKNESS, THICKNESS);
            canvas.setColor(Color.BLACK);
            canvas.fill(point);
        }
        for (Structs.Segment s: aMesh.getSegmentsList()){
            Vertex v1 = aMesh.getVertices(s.getV1Idx());
            Vertex v2 = aMesh.getVertices(s.getV2Idx());

            Color segment_color = extractColor(s.getPropertiesList());

            canvas.setColor(segment_color);
            canvas.drawLine((int) v1.getX(), (int) v1.getY(), (int) v2.getX(), (int) v2.getY());

        }

    }



    private int[] extractSegmentIDs(List<Property> properties) { //applicable for polygons
        String val = null;
        for (Property p: properties) {
            if (p.getKey().equals("segment_ids")) {
                val = p.getValue();
            }
        }
        String[] segments = val.split(",");
        int[] lines = new int[segments.length];
        for(int i = 0; i < lines.length; i++) {
            lines[i] = Integer.parseInt(segments[i]);
        }
        return lines;
    }

    private int extractSegID(List<Property> properties) { //similar to above but only applicable for segments
        String val = null;
        for (Property p: properties) {
            if (p.getKey().equals("segment_id")) {
                val = p.getValue();
            }
        }
        int segment;
        segment = Integer.parseInt(val);
        return segment;
    }

}
