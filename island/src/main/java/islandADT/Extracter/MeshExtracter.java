package islandADT.Extracter;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import islandADT.GeometryContainer;
import islandADT.Wrappers.PolygonWrapper;
import islandADT.Wrappers.SegmentWrapper;
import islandADT.Wrappers.VertexWrapper;

public class MeshExtracter implements Extracter<Structs.Mesh, GeometryContainer>{

    GeometryContainer geometryContainer = new GeometryContainer();
    public GeometryContainer extract(Structs.Mesh oldMesh) {
        GeometryContainer geometryContainer = new GeometryContainer();
        for(Structs.Vertex v: oldMesh.getVerticesList() ) {
            VertexExtracter vertexExtracter = new VertexExtracter();
            VertexWrapper newv = vertexExtracter.extract(v);
            geometryContainer.add_vertex(newv);
        }
        for(Structs.Segment s: oldMesh.getSegmentsList() ) {
            SegmentExtracter segmentExtracter = new SegmentExtracter();
            SegmentWrapper news = segmentExtracter.extract(s);
            geometryContainer.add_segment(news);
        }
        for(Structs.Polygon p: oldMesh.getPolygonsList() ) {
            PolygonExtracter polygonExtracter = new PolygonExtracter();
            PolygonWrapper newp = polygonExtracter.extract(p);
            geometryContainer.add_polygon(newp);
        }
        return geometryContainer;
    }
}
