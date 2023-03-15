package islandADT;

import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import islandADT.Elevation.ElevationFixture;
import islandADT.Elevation.CraterElevationFixture;
import islandADT.Elevation.PlainsElevationFixture;
import islandADT.Elevation.VolcanicElevationFixture;
import islandADT.Exporter.Exporter;
import islandADT.Exporter.MeshExporter;
import islandADT.Extracter.Extracter;
import islandADT.Extracter.MeshExtracter;

import java.io.IOException;

public class IslandGenerator {

    public void create_island(Structs.Mesh m) {
        // creates a new extracter
        Extracter extracter = new MeshExtracter();

        // gets a geometry container with hashsets of all geometries
        GeometryContainer geometryContainer = (GeometryContainer) extracter.extract(m);

        //TODO B make an encapsulation of shape and elevation setting into package called configuration
         // shape setting
        SetPolygonTypes setter = new SetPolygonTypes();
        setter.set_tile_type(geometryContainer);

        // elevation setting
        ElevationFixture elevationfixture = new PlainsElevationFixture();
        elevationfixture.set_elevation(geometryContainer);

        // exporting
        Exporter finalExporter = new MeshExporter();
        Structs.Mesh finalMesh = (Structs.Mesh) finalExporter.export(geometryContainer);

        // TODO B dynamic file output name
        String meshfile = "island.mesh";

        MeshFactory factory = new MeshFactory();
        try {
            factory.write(finalMesh, meshfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
