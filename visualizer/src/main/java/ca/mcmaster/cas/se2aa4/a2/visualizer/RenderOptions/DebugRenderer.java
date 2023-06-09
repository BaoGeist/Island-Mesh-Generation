package ca.mcmaster.cas.se2aa4.a2.visualizer.RenderOptions;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.visualizer.PropertyUtils;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;

import static ca.mcmaster.cas.se2aa4.a2.visualizer.PropertyUtils.extractCentroid;

public class DebugRenderer implements  Renderer{
    private static final int THICKNESS = 3;
    public void render(Structs.Mesh aMesh, Graphics2D canvas) {
        canvas.setColor(Color.BLACK);
        Stroke stroke = new BasicStroke(0.5f);
        canvas.setStroke(stroke);
        for (Structs.Polygon p : aMesh.getPolygonsList()) {
            //polygons are drawn first as the back layer
            float[] x_coords = PropertyUtils.extractCoordsforPolygons(p.getPropertiesList()).get(0);
            float[] y_coords = PropertyUtils.extractCoordsforPolygons(p.getPropertiesList()).get(1);

            Path2D.Float path = new Path2D.Float();
            path.moveTo(x_coords[0], y_coords[0]);

            for (int i = 1; i < x_coords.length; i++) {
                path.lineTo(x_coords[i], y_coords[i]);
            }
            path.closePath();

            canvas.setColor(Color.BLACK);
            canvas.fill(path);
        }

        for (Structs.Polygon p : aMesh.getPolygonsList()) {
            Structs.Vertex v1 = aMesh.getVerticesList().get(p.getCentroidIdx());
            for (Integer index : p.getNeighborIdxsList()) {
                Structs.Polygon neighbour = aMesh.getPolygonsList().get(index);
                Structs.Vertex v2 = aMesh.getVerticesList().get(neighbour.getCentroidIdx());
                System.out.println(neighbour.getCentroidIdx());

                canvas.setColor(Color.gray);
                canvas.drawLine((int) v1.getX(), (int) v1.getY(), (int) v2.getX(), (int) v2.getY());
            }
        }

        for (Structs.Vertex v : aMesh.getVerticesList()) {
            //vertices and segments are drawn overtop of polygons
            double centre_x = v.getX() - (THICKNESS / 2.0d);
            double centre_y = v.getY() - (THICKNESS / 2.0d);
            Ellipse2D point = new Ellipse2D.Double(centre_x, centre_y, THICKNESS, THICKNESS);
            boolean centroid_or_nah = extractCentroid(v.getPropertiesList());
            if (centroid_or_nah == true) {
                canvas.setColor(Color.RED);
            } else {
                canvas.setColor(Color.BLUE);
            }
            canvas.fill(point);
        }
        for (Structs.Segment s : aMesh.getSegmentsList()) {
            Structs.Vertex v1 = aMesh.getVertices(s.getV1Idx());
            Structs.Vertex v2 = aMesh.getVertices(s.getV2Idx());

            canvas.setColor(Color.GREEN);
            canvas.drawLine((int) v1.getX(), (int) v1.getY(), (int) v2.getX(), (int) v2.getY());

        }
    }
}
