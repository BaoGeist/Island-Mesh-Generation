package islandADT.Extracter;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import islandADT.GeometryWrappers.VertexWrapper;
import meshcore.Utils.PropertyUtils;

public class VertexExtracter implements Extracter<Structs.Vertex, VertexWrapper> {
    public VertexWrapper extract(Structs.Vertex oldVertex) {
        int id_vertex = PropertyUtils.extractID(oldVertex.getPropertiesList());

        boolean centroid_vertex = PropertyUtils.extractBoolean(oldVertex.getPropertiesList());
        double[] coords = {oldVertex.getX(), oldVertex.getY()};
        VertexWrapper newVertex = new VertexWrapper(id_vertex, centroid_vertex, coords);
        return newVertex;
    }
}
