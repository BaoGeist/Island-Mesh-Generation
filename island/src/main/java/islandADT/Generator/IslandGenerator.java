package islandADT.Generator;

import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import islandADT.Elevation.CraterElevationFixture;
import islandADT.Elevation.VolcanicElevationFixture;
import islandADT.Water.AquiferGenerator;
import islandADT.Water.RiverGenerator;
import islandADT.GeometryContainer;
import islandADT.SetPolygonTypes;
import islandADT.Specifications.IslandSpecifications;
import islandADT.Elevation.ElevationFixture;
import islandADT.Elevation.PlainsElevationFixture;
import islandADT.Exporter.Exporter;
import islandADT.Exporter.MeshExporter;
import islandADT.Extracter.Extracter;
import islandADT.Extracter.MeshExtracter;
import islandADT.Water.LakeGenerator;
import islandADT.Water.WaterBody;
import islandADT.TypeWrappers.TileTypeWrapperCreator;

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

        // creates a random seed
        RandomSeed.set_randomseed(islandSpecifications.getSeed());

        //TODO B make an encapsulation of shape and elevation setting into package called configuration
        //TODO B move these elsewhere
         // shape setting

        TileTypeWrapperCreator.create_tile_types();

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

        RiverGenerator river = new RiverGenerator();
        river.generate(geometryContainer);

        WaterBody lakeGenerator = new LakeGenerator(islandSpecifications);
        lakeGenerator.generate(geometryContainer);

        WaterBody aquiferGenerator = new AquiferGenerator(islandSpecifications);
        aquiferGenerator.generate(geometryContainer);

        // exporting
        Exporter finalExporter = new MeshExporter();
        Structs.Mesh finalMesh = (Structs.Mesh) finalExporter.export(geometryContainer);



        // TODO B dynamic file output name
        String meshfile = islandSpecifications.getOutput();

        MeshFactory factory = new MeshFactory();
        try {
            factory.write(finalMesh, meshfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
