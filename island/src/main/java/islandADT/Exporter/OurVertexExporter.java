package islandADT.Exporter;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import islandADT.Wrappers.SegmentWrapper;
import islandADT.Wrappers.VertexWrapper;

public class OurVertexExporter implements Exporter<VertexWrapper, Structs.Vertex> {

    public Structs.Vertex export(VertexWrapper v) {
        boolean centroid_or_nah = v.isCentroid_vertex();
        double[] coords = v.getCoords();
        OurVertexIsland vertexIslandFactory = new OurVertexIsland();
        return vertexIslandFactory.create_geometry(centroid_or_nah, coords);
    }
}
