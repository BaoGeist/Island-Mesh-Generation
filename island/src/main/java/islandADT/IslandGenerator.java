package islandADT;

import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import islandADT.Elevation.CraterElevationFixture;
import islandADT.Elevation.VolcanicElevationFixture;
import islandADT.Specifications.IslandSpecifications;
import islandADT.Elevation.ElevationFixture;
import islandADT.Elevation.PlainsElevationFixture;
import islandADT.Exporter.Exporter;
import islandADT.Exporter.MeshExporter;
import islandADT.Extracter.Extracter;
import islandADT.Extracter.MeshExtracter;

import java.io.IOException;

public class IslandGenerator {

    private IslandSpecifications islandSpecifications;
    private Structs.Mesh m;

    public IslandGenerator(IslandSpecifications islandSpecifications) {
        this.islandSpecifications = islandSpecifications;
        try {
            this.m = new MeshFactory().read(islandSpecifications.getInput());
        }
        catch (IOException e) {
            System.out.println("heh");
        }
    }

    public void create_island() {
        // creates a new extracter
        Extracter extracter = new MeshExtracter();

        // gets a geometry container with hashsets of all geometries
        GeometryContainer geometryContainer = (GeometryContainer) extracter.extract(m);

        //TODO B make an encapsulation of shape and elevation setting into package called configuration
        //TODO B move these elsewhere
         // shape setting

        // circle island shape

        SetPolygonTypes setter = new SetPolygonTypes();
        setter.set_tile_type(geometryContainer, islandSpecifications.getShape());


        // elevation setting
        ElevationFixture elevationFixture;
        switch(islandSpecifications.getElevation()) {
            case "plains":
                elevationFixture = new PlainsElevationFixture();
                break;
            case "volcanic":
                elevationFixture = new VolcanicElevationFixture();
                break;
            case "crater":
                elevationFixture = new CraterElevationFixture();
                break;
            default:
                elevationFixture = new PlainsElevationFixture();
                break;
        }
        elevationFixture.set_elevation(geometryContainer);

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