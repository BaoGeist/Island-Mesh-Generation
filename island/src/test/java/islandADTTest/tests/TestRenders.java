package islandADTTest.tests;

import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import islandADT.Configurations.IslandConfiguration;
import islandADT.Generator.IslandGenerator;
import islandADT.Specifications.IslandSpecifications;
import islandADTTest.tags.Before;

import java.util.ArrayList;
import java.util.List;

public abstract class TestRenders {
    List<String> inputs = new ArrayList<>();
    IslandSpecifications islandSpecifications = null;
    Structs.Mesh outputMesh;

    public void setUpMesh(RenderEnum renderEnum) {
        inputs.add("-i");
        inputs.add("testInput.mesh");
        inputs.add("-o");
        inputs.add("testOutput.mesh");
        inputs.add("-mode");
        inputs.add(renderEnum.name());

        String[] arrayInputs = inputs.toArray(new String[0]);
        IslandConfiguration islandConfiguration = new IslandConfiguration(arrayInputs);
        this.islandSpecifications = islandConfiguration.getIslandSpecifications();

        IslandGenerator islandGenerator = new IslandGenerator(islandSpecifications);
        islandGenerator.create_island();

        try {
            outputMesh = new MeshFactory().read("testOutput.mesh");
        } catch (Exception e) {

        }
    }
}
