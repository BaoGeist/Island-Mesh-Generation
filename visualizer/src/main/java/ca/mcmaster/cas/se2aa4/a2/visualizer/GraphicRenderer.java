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

public class GraphicRenderer {

    private static final int THICKNESS = 3;
    public void render(Mesh aMesh, Graphics2D canvas) {
        canvas.setColor(Color.BLACK);
        Stroke stroke = new BasicStroke(0.5f);
        canvas.setStroke(stroke);
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
        // for polygons, STILL DOES NOT WORK, NOT SURE WHY
        for (Structs.Polygon p: aMesh.getPolygonsList()) {
            Color polygon_color = extractColor(p.getPropertiesList());
            float[] x_coords = extractCoords(p.getPropertiesList()).get(0);
            float[] y_coords = extractCoords(p.getPropertiesList()).get(1);
            System.out.println("heqfoieojwf");

            Path2D.Float path = new Path2D.Float();
            path.moveTo(x_coords[0], y_coords[0]);

            for (int i = 1; i < x_coords.length; i++) {
                path.lineTo(x_coords[i], y_coords[i]);
            }
            path.lineTo(x_coords[0], y_coords[0]);
            path.closePath();

            canvas.setColor(Color.green);
            canvas.fill(path);
        }
    }

    public void debug(Mesh aMesh, Graphics2D canvas) {
        canvas.setColor(Color.WHITE);
        Stroke stroke = new BasicStroke(0.5f);
        canvas.setStroke(stroke);
        for (Structs.Polygon p: aMesh.getPolygonsList()) { //polygons are drawn first as the back layer
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

            double[] centroid_coords = new double[]{2.0, 2.0};
            Ellipse2D point = new Ellipse2D.Double(centroid_coords[0], centroid_coords[1], THICKNESS, THICKNESS);
            canvas.setColor(Color.RED);
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
            float[] vertices = extractHeadTail(s.getPropertiesList());
            double x1 = vertices[0];
            double y1 = vertices[1];
            double x2 = vertices[2];
            double y2 = vertices[3];
            canvas.setColor(Color.BLACK);
            canvas.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
        }
        for (Structs.Polygon p: aMesh.getPolygonsList()) { //neighbourhoods need to be drawn last, but they require polygons, so we repeat
            for (Structs.Polygon q: aMesh.getPolygonsList()) {
                int p_neighbour = extractNeighbourID(p.getPropertiesList());
                int q_neighbour = extractNeighbourID(q.getPropertiesList());
                if (p_neighbour == q_neighbour) {

                }
            }
        }

    }

    private Color extractColor(List<Property> properties) {
        String val = null;
        for(Property p: properties) {
            if (p.getKey().equals("rgb_color")) {
//                System.out.println(p.getValue());
                val = p.getValue();
            }
        }
        if (val == null)
            return Color.BLACK;
        String[] raw = val.split(",");
        int red = Integer.parseInt(raw[0].replace("[","").replace(" ", ""));
        int green = Integer.parseInt(raw[1].replace(" ", ""));
        int blue = Integer.parseInt(raw[2].replace("]","").replace(" ", ""));
        return new Color(red, green, blue);
    }

    private ArrayList<float[]> extractCoords(List<Property> properties) {
        String x_coords = null, y_coords = null;
        for (Property p: properties) {
            System.out.println(p.getKey());
            if (p.getKey().equals("x_coords")) {
                x_coords = p.getValue();
            }
            if (p.getKey().equals("y_coords")) {
                y_coords = p.getValue();
            }
        }

        String[] raw_x = x_coords.split(",");
        System.out.println(Arrays.toString(raw_x));
        float[] pro_x = new float[raw_x.length];
        String[] raw_y = y_coords.split(",");
        float[] pro_y = new float[raw_y.length];

        for(int i = 0; i < raw_x.length; i++) {
            pro_x[i] = Float.parseFloat(raw_x[i].replace("[","").replace(" ", "").replace("]",""));
            pro_y[i] = Float.parseFloat(raw_y[i].replace("[","").replace(" ", "").replace("]",""));

        }
        ArrayList<float[]> return_array = new ArrayList<>();
        return_array.add(pro_x);
        return_array.add(pro_y);
        return return_array;
    }

    private float[] extractHeadTail(List<Property> properties) {
//        System.out.println(properties);
        String head = null, tail = null;
        for(Property p: properties) {
            if (p.getKey().equals("head")) {
//                System.out.println(p.getValue());
                head = p.getValue();
            }
            if (p.getKey().equals("tail")) {
//                System.out.println(p.getValue());
                tail = p.getValue();
            }
        }
        if (head == null || tail == null)
            return new float[]{};
        String[] raw_head = head.split(",");
        String[] raw_tail = tail.split(",");
        float[] head_tail = new float[raw_head.length*2];

        for(int i = 0; i < raw_head.length; i++) {
            head_tail[i] = Float.parseFloat(raw_head[i]);
            head_tail[i+raw_head.length] = Float.parseFloat(raw_tail[i]);
        }

        return head_tail;
    }

    private int extractNeighbourID(List<Property> properties) {
        String val = null;
        for(Property p: properties) {
            if (p.getKey().equals("neighbours")) {
//                System.out.println(p.getValue());
                val = p.getValue();
            }
        }
        return Integer.parseInt(val);
    }

}
