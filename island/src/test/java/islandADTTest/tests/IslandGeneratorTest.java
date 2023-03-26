package islandADTTest.tests;

import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import islandADT.Configurations.IslandConfiguration;
import islandADT.Generator.IslandGenerator;
import islandADT.Specifications.IslandSpecifications;
import islandADTTest.tags.Before;
import islandADTTest.tags.TestActual;

import java.io.IOException;
import java.util.Arrays;

import static islandADTTest.Assertions.assertNotNull;

public class IslandGeneratorTest {
    Structs.Mesh inputMesh;
    Structs.Mesh outputMesh;
    IslandSpecifications islandSpecifications;

    @Before
    public void setUpInputMesh() {
        System.out.println("generating");
        String pathToGenerator = "../generator/generator.jar";
        String[] GeneratorArgs = {"-mf", "testInput.mesh", "-mv", "irregular", "-num", "1000", "-ln", "25"};
        ProcessBuilder pb = new ProcessBuilder("java", "-jar", pathToGenerator);
        pb.command().addAll(Arrays.asList(GeneratorArgs));
        try {
            pb.start();
        } catch (IOException ioe) {
            System.out.println("fuck");
        }


        try {
            inputMesh = new MeshFactory().read("testInput.mesh");
        } catch (Exception e) {

        }
        System.out.println("run");
    }

    @TestActual
    public void testPreMesh() {
        assertNotNull(inputMesh);
    }


    @Before
    public void setUpMeshConfigAndSpecs() {
        String[] args = new String[]{"-i", "testInput.mesh", "-o", "testOutput.mesh", "-altitude", "plains"};
        IslandConfiguration islandConfiguration = new IslandConfiguration(args);
        islandSpecifications = islandConfiguration.getIslandSpecifications();
    }

    @Before
    public void setUpOutputMesh() {
        IslandGenerator islandGenerator = new IslandGenerator(islandSpecifications);
        islandGenerator.create_island();

        try {
            outputMesh = new MeshFactory().read("testOutput.mesh");
        } catch (Exception e) {

        }
        System.out.println("run");
    }


    @TestActual
    public void testPostMesh() {
        assertNotNull(outputMesh);
    }

}
