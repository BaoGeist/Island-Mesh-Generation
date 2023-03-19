package ca.mcmaster.cas.se2aa4.a2.visualizer.RenderOptions;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.visualizer.PropertyUtils;

import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Path2D;
import java.awt.geom.Ellipse2D;

import static ca.mcmaster.cas.se2aa4.a2.visualizer.PropertyUtils.*;

public class GraphicRenderer implements Renderer{

    private static final int THICKNESS = 3;

    // renders our mesh
    public void render(Mesh aMesh, Graphics2D canvas) {
        canvas.setColor(Color.WHITE);
        Stroke stroke = new BasicStroke(1f);
        canvas.setStroke(stroke);
        //Draws all polygons
        for (Structs.Polygon p: aMesh.getPolygonsList()) {

            Color polygon_color = PropertyUtils.extractColor(p.getPropertiesList());
            float[] x_coords = PropertyUtils.extractCoordsforPolygons(p.getPropertiesList()).get(0);
            float[] y_coords = PropertyUtils.extractCoordsforPolygons(p.getPropertiesList()).get(1);

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
        // Draws all segments
        for (Structs.Segment s: aMesh.getSegmentsList()){
            Vertex v1 = aMesh.getVertices(s.getV1Idx());
            Vertex v2 = aMesh.getVertices(s.getV2Idx());

            Color segment_color = PropertyUtils.extractColor(s.getPropertiesList());
            Integer thickness = PropertyUtils.extractThickness(s.getPropertiesList());

            canvas.setColor(segment_color);
            Stroke newStroke = new BasicStroke(thickness);
            canvas.setStroke(newStroke);
//            System.out.println(segment_color);
            canvas.drawLine((int) v1.getX(), (int) v1.getY(), (int) v2.getX(), (int) v2.getY());
        }

//        //Draws all vertices
//        for (Vertex v: aMesh.getVerticesList()) {
//            double centre_x = v.getX() - (THICKNESS/2.0d);
//            double centre_y = v.getY() - (THICKNESS/2.0d);
//            Color old = canvas.getColor();
//            canvas.setColor(PropertyUtils.extractColor(v.getPropertiesList()));
//            Ellipse2D point = new Ellipse2D.Double(centre_x, centre_y, THICKNESS, THICKNESS);
//            canvas.fill(point);
//            canvas.setColor(Color.BLACK);
//        }
    }

}