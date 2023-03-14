package ca.mcmaster.cas.se2aa4.a2.visualizer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;

import static ca.mcmaster.cas.se2aa4.a2.visualizer.PropertyUtils.extractHeight;

public class HeatmapRenderer {
    private static final int THICKNESS = 3;

    //TODO B put this method responsibility elsewhere
    private Color color_from_height(int height) {
        return new Color(0, 0, height*50);
    }

    //TODO B make height have a min and max input for heatmaps
    private Color color_from_height_vol(int height) {
        return new Color(0, 0, height/2);
    }

    public void render(Structs.Mesh aMesh, Graphics2D canvas) {
        canvas.setColor(Color.WHITE);
        Stroke stroke = new BasicStroke(0.5f);
        canvas.setStroke(stroke);
        //TODO B add coordinates to polygon
//         Draws all polygons
        for (Structs.Polygon p: aMesh.getPolygonsList()) {

//            Color polygon_color = PropertyUtils.extractColor(p.getPropertiesList());
            Color height_color = color_from_height_vol(extractHeight(p.getPropertiesList()));
            System.out.println(extractHeight(p.getPropertiesList()));
            float[] x_coords = PropertyUtils.extractCoordsforPolygons(p.getPropertiesList()).get(0);
            float[] y_coords = PropertyUtils.extractCoordsforPolygons(p.getPropertiesList()).get(1);

            Path2D.Float path = new Path2D.Float();
            path.moveTo(x_coords[0], y_coords[0]);

            for (int i = 1; i < x_coords.length; i++) {
                path.lineTo(x_coords[i], y_coords[i]);
            }
            path.lineTo(x_coords[0], y_coords[0]);
            path.closePath();

            canvas.setColor(height_color);
            canvas.fill(path);
        }
        // Draws all segments
        for (Structs.Segment s: aMesh.getSegmentsList()){
            Structs.Vertex v1 = aMesh.getVertices(s.getV1Idx());
            Structs.Vertex v2 = aMesh.getVertices(s.getV2Idx());

            Color segment_color = PropertyUtils.extractColor(s.getPropertiesList());

            canvas.setColor(Color.GRAY);
            canvas.drawLine((int) v1.getX(), (int) v1.getY(), (int) v2.getX(), (int) v2.getY());
        }
        //Draws all vertices
//        for (Structs.Vertex v: aMesh.getVerticesList()) {
//            double centre_x = v.getX() - (THICKNESS/2.0d);
//            double centre_y = v.getY() - (THICKNESS/2.0d);
//            Color old = canvas.getColor();
//            canvas.setColor(PropertyUtils.extractColor(v.getPropertiesList()));
//            Ellipse2D point = new Ellipse2D.Double(centre_x, centre_y, THICKNESS, THICKNESS);
//            canvas.fill(point);
//            canvas.setColor(Color.BLACK);
//        }
    }

    public void debug(Structs.Mesh aMesh, Graphics2D canvas) {
        System.out.println("nothing");
    }
}
