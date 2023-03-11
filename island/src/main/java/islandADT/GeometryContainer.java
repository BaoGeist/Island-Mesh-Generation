package islandADT;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import org.locationtech.jts.geom.Coordinate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static meshcore.Utils.PropertyUtils.*;

public class GeometryContainer {
    ArrayList<Vertex> vertices = new ArrayList<Vertex>();
    ArrayList<Segment> segments = new ArrayList<Segment>();
    ArrayList<Structs.Polygon> polygons = new ArrayList<>();
    final double uncertainty = 0.005;

    private boolean double_equals(float d1, float d2) { return (Math.abs(d1-d2) < uncertainty); }

    //Method to check if vertex is unique, if so, return it, if not, return the old vertex from that position
    public Vertex check_unique_vertex(Vertex new_vertex) {
        float float_x_new = (float) new_vertex.getX();
        float float_y_new = (float) new_vertex.getY();
        if(vertices.isEmpty()) {
            vertices.add(new_vertex);
            return (new_vertex);
        } else {
            for(Vertex old_vertex: vertices) {
                float float_x_old = (float) old_vertex.getX();
                float float_y_old = (float) old_vertex.getY();

                if(double_equals(float_x_new, float_x_old) && double_equals(float_y_new, float_y_old)) {
                    return old_vertex;
                }
            }
            vertices.add(new_vertex);
            return new_vertex;
        }

    }

    // Same as above but for segments
    public Segment check_unique_segment(Segment new_segment, ArrayList<Vertex> all_vertices) {
        Set<Vertex> new_segment_vertices = new HashSet<>();
        new_segment_vertices.add(all_vertices.get(new_segment.getV1Idx()));
        new_segment_vertices.add(all_vertices.get(new_segment.getV2Idx()));
        if(segments.isEmpty()) {
            segments.add(new_segment);
            return new_segment;
        }
        for(Segment s_old: segments) {
            Set<Vertex> old_segment_vertices = new HashSet<>();
            old_segment_vertices.add(all_vertices.get(s_old.getV1Idx()));
            old_segment_vertices.add(all_vertices.get(s_old.getV1Idx()));
            if(new_segment_vertices.equals(old_segment_vertices)) {
                return s_old;
            }
        }
        segments.add(new_segment);
        return new_segment;

    }
    public void add_polygons(Structs.Polygon p) {
        polygons.add(p);
    }

    //Returns the ID of a polygon from its centroid coordinates
    public int return_polygon_id_from_centroid_coordinate(Coordinate coordinate) {
        for(Structs.Polygon p: polygons) {
            double[] centroid = extractCentroidCoords(p.getPropertiesList());
            if(Math.abs(coordinate.getX() - centroid[0]) < 0.01 && Math.abs(coordinate.getY() - centroid[1]) < 0.01) {
                return extractID(p.getPropertiesList());
            }
        }
        return -1;
    }

}
