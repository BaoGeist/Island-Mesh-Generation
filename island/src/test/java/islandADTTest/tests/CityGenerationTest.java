package islandADTTest.tests;

import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import islandADT.Configurations.IslandConfiguration;
import islandADT.Generator.IslandGenerator;
import islandADT.Specifications.IslandSpecifications;
import islandADTTest.tags.Before;
import islandADTTest.tags.TestActual;

import java.util.*;

import static islandADTTest.tests.Assertions.assertEquals;
import static islandADTTest.tests.Assertions.assertTrue;

public class CityGenerationTest {
    List<String> inputs = new ArrayList<>();
    IslandSpecifications islandSpecifications = null;
    Structs.Mesh outputMesh;
    Set<Structs.Vertex> cities = new HashSet<>();
    Set<Structs.Vertex> capitals = new HashSet<>();
    Map<Integer, Integer> city_size = new HashMap<>();

    @Before
    public void setUpPath() {
        inputs.add("-i");
        inputs.add("testInput.mesh");
        inputs.add("-o");
        inputs.add("testOutput.mesh");
        inputs.add("-cities");
        inputs.add("10");

        String[] arrayInputs = inputs.toArray(new String[0]);
        IslandConfiguration islandConfiguration = new IslandConfiguration(arrayInputs);
        this.islandSpecifications = islandConfiguration.getIslandSpecifications();

        IslandGenerator islandGenerator = new IslandGenerator(islandSpecifications);
        islandGenerator.create_island();

        try {
            outputMesh = new MeshFactory().read("testOutput.mesh");
        } catch (Exception e) {

        }

        for (Structs.Vertex v :outputMesh.getVerticesList()){
            String val = "1";
            for (Structs.Property p: v.getPropertiesList()) {
                if (p.getKey().equals("size")) {
                    val = p.getValue();
                }
            }
            int val_int = Integer.parseInt(val);
            if (val_int >= 1){
                if(city_size.containsKey(val_int)) {
                    city_size.put(val_int, city_size.get(val_int) + 1);
                } else {
                    city_size.put(val_int, 1);
                }

                if(Integer.parseInt(val) == 12) {
                    capitals.add(v);
                } else {
                    cities.add(v);
                }

            }
        }
    }


    @TestActual
    public void testPath() {
        assertEquals(capitals.size(), 1);
        assertTrue(cities.size() > 1 );
        assertTrue(city_size.size() > 1);


    }
}
