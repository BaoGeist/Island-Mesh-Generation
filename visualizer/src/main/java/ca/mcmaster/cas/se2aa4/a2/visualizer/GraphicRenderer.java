package ca.mcmaster.cas.se2aa4.a2.visualizer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;

import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
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
        // for polygons, also baoze struggling here
        for (Polygon p: aMesh.getPolygonsList()) {
            int centroid_id = extractMiddleID(p.getPropertiesList());
            // add code to get centroid from the list and then graph it
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

    private int extractMiddleID(List<Property> properties) {
        String val = null;
        for(Property p: properties) {
            if (p.getKey().equals("middle_id")) {
//                System.out.println(p.getValue());
                val = p.getValue();
            }
        }
        return Integer.parseInt(val);
    }

}
