package islandADT.Generator;

import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import islandADT.Urbanism.RoadWeb;
import islandADT.Water.*;
import islandADT.Container.GeometryContainer;
import islandADT.SetPolygonTypes;
import islandADT.Biomes.*;
import islandADT.Specifications.IslandSpecifications;
import islandADT.Exporter.Exporter;
import islandADT.Exporter.MeshExporter;
import islandADT.Extracter.Extracter;
import islandADT.Extracter.MeshExtracter;
import islandADT.TypeWrappers.TileTypeWrapperCreator;
import islandADT.Resources.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IslandGenerator {

    private IslandSpecifications islandSpecifications;
    private Structs.Mesh m;
    private List<GenerateFeatureInterface> bindings = new ArrayList<>();

    public IslandGenerator(IslandSpecifications islandSpecifications) {
        this.islandSpecifications = islandSpecifications;
        try {
            this.m = new MeshFactory().read(islandSpecifications.getInput());
        }
        catch (IOException e) {
            System.out.println("heh");
        }
    }

    private void create_bindings() {
        bindings.add(new LakeFactory(islandSpecifications));
        bindings.add(new RiverFactory(islandSpecifications));
        bindings.add(new AquiferFactory(islandSpecifications));
        bindings.add(new MoistureSetter(islandSpecifications));
        bindings.add(BiomeFactory.selectBiomeProfile(islandSpecifications));
        bindings.add(new Resources(islandSpecifications));
        bindings.add(new RoadWeb(islandSpecifications));
    }

    private GeometryContainer set_up() {
        // creates a new extracter
        Extracter extracter = new MeshExtracter();

        TileTypeWrapperCreator.create_tile_types();

        // gets a geometry container with hashsets of all geometries
        GeometryContainer geometryContainer = (GeometryContainer) extracter.extract(m);

        // creates a random seed
        RandomSeed.set_randomseed(islandSpecifications);
        System.out.println("Random Seed: " + RandomSeed.getInstance().get_seed());


        SetPolygonTypes islandGeographySetter = new SetPolygonTypes();
        islandGeographySetter.set_island_shape(geometryContainer, islandSpecifications);
        islandGeographySetter.set_island_elevation(geometryContainer, islandSpecifications);

        create_bindings();

        return geometryContainer;
    }

    public void create_island() {
        GeometryContainer geometryContainer = set_up();


        for(GenerateFeatureInterface featureGenerator: bindings) {
            featureGenerator.generate(geometryContainer);
        }


        export_island(geometryContainer);
    }

    private void export_island(GeometryContainer geometryContainer) {
        Exporter finalExporter = new MeshExporter(islandSpecifications);
        Structs.Mesh finalMesh = (Structs.Mesh) finalExporter.export(geometryContainer);

        String meshfile = islandSpecifications.getOutput();

        MeshFactory factory = new MeshFactory();
        try {
            factory.write(finalMesh, meshfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
