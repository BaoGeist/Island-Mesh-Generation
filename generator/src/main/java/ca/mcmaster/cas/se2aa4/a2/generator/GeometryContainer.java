package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.ArrayList;
import java.util.stream.Collectors;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;

import static ca.mcmaster.cas.se2aa4.a2.generator.PropertyUtils.*;

public class GeometryContainer {
    ArrayList<Vertex> vertices = new ArrayList<Vertex>();
    ArrayList<ArrayList<Vertex>> segments = new ArrayList<ArrayList<Vertex>>();
    final double uncertainty = 0.005;

    private boolean double_equals(float d1, float d2) { return (Math.abs(d1-d2) < uncertainty); }

    public Vertex check_unique_vertex(Vertex new_vertex) {
        OurVertex vertexFactory = new OurVertex();
        if(vertices.isEmpty()) {
            vertices.add(new_vertex);
            return (new_vertex);
        } else {
            for(Vertex old_vertex: vertices) {
                float float_x_old = (float) old_vertex.getX();
                float float_y_old = (float) old_vertex.getY();
                float float_x_new = (float) new_vertex.getX();
                float float_y_new = (float) new_vertex.getY();
                if(double_equals(float_x_new, float_x_old) && double_equals(float_y_new, float_y_old)) {
                    System.out.println("this triggers here");
                    return old_vertex;
                }
            }
            vertices.add(new_vertex);
            return new_vertex;
        }

    }

//    public boolean check_unique_segment(ArrayList<Vertex> head_tail_vertices) {
//        for(ArrayList<Vertex> s_old: segments) {
//            int id_head_new = extractID(head_tail_vertices.get(0).getPropertiesList());
//            int id_tail_new = extractID(head_tail_vertices.get(1).getPropertiesList());
//            int id_head_old = extractID(s_old.get(0).getPropertiesList());
//            int id_tail_old = extractID(s_old.get(1).getPropertiesList());
//        }
//        return false;
//
//    }

}
