package ca.mcmaster.cas.se2aa4.a2.visualizer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;

import static ca.mcmaster.cas.se2aa4.a2.visualizer.PropertyUtils.extractHeight;
import static ca.mcmaster.cas.se2aa4.a2.visualizer.PropertyUtils.extractMinMaxHeights;

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

    //TODO B make this into a separate class to call


    public void render(Structs.Mesh aMesh, Graphics2D canvas) {
        canvas.setColor(Color.WHITE);
        Stroke stroke = new BasicStroke(0.5f);
        canvas.setStroke(stroke);
        //TODO B add coordinates to polygon
//         Draws all polygons

        HeatmapColourMan Van_Gogh = new HeatmapColourMan(aMesh);

        for (Structs.Polygon p: aMesh.getPolygonsList()) {

//            Color polygon_color = PropertyUtils.extractColor(p.getPropertiesList());
//            Color height_color = color_from_height_vol(extractHeight(p.getPropertiesList()));
            Color heat_color = Van_Gogh.color_from_integer(extractHeight(p.getPropertiesList()));
            float[] x_coords = PropertyUtils.extractCoordsforPolygons(p.getPropertiesList()).get(0);
            float[] y_coords = PropertyUtils.extractCoordsforPolygons(p.getPropertiesList()).get(1);

            Path2D.Float path = new Path2D.Float();
            path.moveTo(x_coords[0], y_coords[0]);

            for (int i = 1; i < x_coords.length; i++) {
                path.lineTo(x_coords[i], y_coords[i]);
            }
            path.lineTo(x_coords[0], y_coords[0]);
            path.closePath();

            canvas.setColor(heat_color);
            canvas.fill(path);
        }
        // Draws all segments
        for (Structs.Segment s: aMesh.getSegmentsList()){
            Structs.Vertex v1 = aMesh.getVertices(s.getV1Idx());
            Structs.Vertex v2 = aMesh.getVertices(s.getV2Idx());

            Color segment_color = PropertyUtils.extractColor(s.getPropertiesList());

            canvas.setColor(Color.WHITE);
            canvas.drawLine((int) v1.getX(), (int) v1.getY(), (int) v2.getX(), (int) v2.getY());
        }
    }

    public void debug(Structs.Mesh aMesh, Graphics2D canvas) {
        System.out.println("nothing");
    }
}
