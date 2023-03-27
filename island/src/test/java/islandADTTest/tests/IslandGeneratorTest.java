package islandADTTest.tests;

import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import islandADT.Configurations.IslandConfiguration;
import islandADT.Generator.IslandGenerator;
import islandADT.Specifications.IslandSpecifications;
import islandADTTest.tags.Before;
import islandADTTest.tags.TestActual;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static islandADTTest.tests.Assertions.assertNotNull;

public class IslandGeneratorTest {
    Structs.Mesh inputMesh;
    Structs.Mesh outputMesh;
    IslandSpecifications islandSpecifications;

    private void createInputMush() {
        String inputMeshPath = "testInput.mesh";

        File file = new File(inputMeshPath);
        if(!file.exists()) {
            System.out.println("Generating testInput.mesh as one is not detected");
            String pathToGenerator = "../generator/generator.jar";
            String[] GeneratorArgs = {"-mf", "testInput.mesh", "-mv", "irregular", "-num", "1000", "-ln", "25"};
            ProcessBuilder pb = new ProcessBuilder("java", "-jar", pathToGenerator);
            pb.command().addAll(Arrays.asList(GeneratorArgs));
            try {
                pb.start();
            } catch (IOException ioe) {
                System.out.println("fuck");
            }
        }
        System.out.println("n'est pas existe");
    }

    @Before
    public void setUpInputMesh() {
        createInputMush();

        String[] args = new String[]{"-i", "testInput.mesh", "-o", "testOutput.mesh", "-altitude", "plains"};
        IslandConfiguration islandConfiguration = new IslandConfiguration(args);
        islandSpecifications = islandConfiguration.getIslandSpecifications();

        try {
            inputMesh = new MeshFactory().read("testInput.mesh");
        } catch (Exception e) {

        }

        IslandGenerator islandGenerator = new IslandGenerator(islandSpecifications);
        islandGenerator.create_island();

        try {
            outputMesh = new MeshFactory().read("testOutput.mesh");
        } catch (Exception e) {

        }
        System.out.println("run");
    }

    @TestActual
    public void testPreMesh() {
        assertNotNull(inputMesh);
    }


    @TestActual
    public void testPostMesh() {
        assertNotNull(outputMesh);
    }

}
