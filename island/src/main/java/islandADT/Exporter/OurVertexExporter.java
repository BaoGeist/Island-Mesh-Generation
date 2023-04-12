package islandADT.Exporter;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import islandADT.Exporter.Colour.ColourExporter;
import islandADT.GeometryWrappers.VertexWrapper;
import islandADT.Specifications.IslandSpecifications;

public class OurVertexExporter implements Exporter<VertexWrapper, Structs.Vertex> {

    public Structs.Vertex export(VertexWrapper v) {
        boolean centroid_or_nah = v.isCentroid_vertex();
        double[] coords = v.getCoords();
        int population = v.getPopulation();
        OurVertexIsland vertexIslandFactory = new OurVertexIsland();
        int[] color;
        if(v.getPopulation() == 10) {
            color = new int[]{255,160,122};
        } else {
            color = new int[]{220,220,220};
        }


        return vertexIslandFactory.create_geometry(centroid_or_nah, coords, population, color);
    }
}
