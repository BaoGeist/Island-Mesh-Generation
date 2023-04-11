package islandADTTest.tests;

import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import islandADT.Configurations.IslandConfiguration;
import islandADT.Generator.IslandGenerator;
import islandADT.Specifications.IslandSpecifications;
import islandADTTest.tags.Before;
import islandADTTest.tags.TestActual;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static islandADTTest.tests.Assertions.assertTrue;
import static islandADTTest.tests.Assertions.assertEquals;

public class GraphAdapterTest {
    List<String> inputs = new ArrayList<>();
    IslandSpecifications islandSpecifications = null;
    Structs.Mesh outputMesh;
    List<Integer> Path = new ArrayList<>();

    @Before
    public void setUpPath() {
        inputs.add("-i");
        inputs.add("testInput.mesh");
        inputs.add("-o");
        inputs.add("testOutput.mesh");
        inputs.add("-cities");
        inputs.add("2");

        String[] arrayInputs = inputs.toArray(new String[0]);
        IslandConfiguration islandConfiguration = new IslandConfiguration(arrayInputs);
        this.islandSpecifications = islandConfiguration.getIslandSpecifications();

        IslandGenerator islandGenerator = new IslandGenerator(islandSpecifications);
        islandGenerator.create_island();

        try {
            outputMesh = new MeshFactory().read("testOutput.mesh");
        } catch (Exception e) {

        }

        for (Structs.Segment s :outputMesh.getSegmentsList()){
            String val = "1", val2 = "";
            for (Structs.Property p: s.getPropertiesList()) {
                if (p.getKey().equals("thicc")) {
                    val = p.getValue();
                }
                if (p.getKey().equals("rgb_color")) {
                    val2 = p.getValue();
                }
            }
            if (Integer.parseInt(val)>=1 && val2.equals("[255, 255, 255]")){
                System.out.println("debug debug");
                Path.add(s.getV1Idx());
                Path.add(s.getV2Idx());
            }
        }
    }


    @TestActual
    public void testPath() {
        Map<Integer, Integer> path_node_counter = new HashMap<>();
        for(Integer node: Path) {
            if(! path_node_counter.containsKey(node)) {
                path_node_counter.put(node, 1);
            } else {
                path_node_counter.put(node, 2);
            }
        }

        int one_node_counter = 0;
        for(int occurances: path_node_counter.values()) {
            assertTrue(occurances == 1 || occurances == 2);
            if(occurances == 1) {one_node_counter ++;}
        }
        assertEquals(one_node_counter, 2);

    }


}
