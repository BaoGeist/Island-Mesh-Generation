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
            canvas.drawLine((int) v1.getX(), (int) v1.getY(), (int) v2.getX(), (int) v2.getY());
        }
        for (Structs.Vertex v : aMesh.getVerticesList()) {
            //vertices and segments are drawn overtop of polygons
            double centre_x = v.getX();
            double centre_y = v.getY();
            int size = extractSize(v.getPropertiesList());

            Ellipse2D point = new Ellipse2D.Double(centre_x - (float) size/2, (float) centre_y - size/2, size, size);
            boolean centroid_or_nah = extractCentroid(v.getPropertiesList());
            if (centroid_or_nah == true) {
                canvas.setColor(Color.RED);
            } else {
                canvas.setColor(Color.BLUE);
            }
            canvas.fill(point);
        }
    }

}