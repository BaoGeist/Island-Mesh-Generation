package islandADT;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import islandADT.Wrappers.PolygonWrapper;
import islandADT.Wrappers.SegmentWrapper;
import islandADT.Wrappers.VertexWrapper;
import org.locationtech.jts.geom.Coordinate;

import java.util.*;

import static meshcore.Utils.PropertyUtils.*;

//TODO rename this to MeshWrapper
public class GeometryContainer {
    private Map<Integer, VertexWrapper> vertices = new HashMap<>();
    private Map<Integer, SegmentWrapper> segments = new HashMap<>();
    private Map<Integer, PolygonWrapper> polygons = new HashMap<>();
    private Map<Integer, VertexWrapper> vertexNeighbors = new HashMap<>();

    //TODO B maybe make all the adds the same call, but it sorts it into what it should be

    // adds a new VertexWrapper to GeometryContainer
    public void add_vertex(VertexWrapper v) {vertices.put(v.get_id(), v);}

    // adds a new SegmentWrapper to GeometryContainer
    public void add_segment(SegmentWrapper s) {segments.put(s.get_id(), s);}

    // adds a new PolygonWrapper to GeometryContainer
    public void add_polygon(PolygonWrapper p) {polygons.put(p.get_id(), p);}

    public void set_vertexNeighbors(VertexWrapper v){

        for (SegmentWrapper seg: segments.values()){

            int v1_id = seg.getV1id();
            int v2_id = seg.getV2id();

            if (v.get_id() == v1_id){
                vertexNeighbors.put(v2_id, vertices.get(v2_id));
            } else if (v.get_id() == v2_id){
                vertexNeighbors.put(v1_id, vertices.get(v1_id));
            }
        }
    }

    public void clear_vertexNeighbors(){
        vertexNeighbors.clear();
    }

    //TODO B make this abstraction leak minimal

    // self-expla
    public Map<Integer, VertexWrapper> get_vertices() {
        return vertices;
    }

    // self-expla
    public Map<Integer, SegmentWrapper> get_segments() {
        return segments;
    }

    // self-expla
    public Map<Integer, PolygonWrapper> get_polygons() {
        return polygons;
    }

    // self-expla
    public Map<Integer, VertexWrapper> getVertexNeighbors(VertexWrapper v){return vertexNeighbors;}

}
