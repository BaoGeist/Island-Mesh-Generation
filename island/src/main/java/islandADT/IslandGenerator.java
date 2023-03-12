package islandADT;

import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import islandADT.Exporter.Exporter;
import islandADT.Exporter.MeshExporter;
import islandADT.Extracter.Extracter;
import islandADT.Extracter.MeshExtracter;
import islandADT.Wrappers.PolygonWrapper;

import java.io.IOException;
import java.util.Map;

public class IslandGenerator {

    public void create_island(Structs.Mesh m) {
        System.out.println("test");
        // creates a new extracter
        Extracter extracter = new MeshExtracter();

        // gets a geometry container with hashsets of all geometries
        GeometryContainer geometryContainer = (GeometryContainer) extracter.extract(m);

        Map<Integer, PolygonWrapper> test_map = geometryContainer.get_polygons();

        for(Map.Entry<Integer, PolygonWrapper> entry: test_map.entrySet()) {
            Integer key = entry.getKey();
            PolygonWrapper p = entry.getValue();
            System.out.println(key + " = " + key);
            System.out.println(p.get_id());
        }

        Exporter finalExporter = new MeshExporter();
        Structs.Mesh finalMesh = (Structs.Mesh) finalExporter.export(geometryContainer);

        //TODO dynamic file output name
        String meshfile = "island.mesh";

        MeshFactory factory = new MeshFactory();
        try {
            factory.write(finalMesh, meshfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
