package islandADTTest.tests;

import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import islandADTTest.tags.Before;
import islandADTTest.tags.TestActual;

import java.util.ArrayList;
import java.util.List;

import static islandADTTest.tests.Assertions.assertNotNull;
import static islandADTTest.tests.Assertions.assertNotZero;

public class AquiferTest extends TestRenders{
    List<Integer> Lakes = new ArrayList<>();
    List<Integer> Aquifers = new ArrayList<>();
    @Before
    public void SetUpRivers(){
        setUpMesh(RenderEnum.normal);

        try {
            outputMesh = new MeshFactory().read("testOutput.mesh");
        } catch (Exception e) {}

        for (Structs.Polygon p :outputMesh.getPolygonsList()){
            String val = "1";
            String color = extractColor(p.getPropertiesList());
            if(color.equals("[1, 129, 144]")) {
                Lakes.add(extractID(p.getPropertiesList()));
            }
        }

        setUpMesh(RenderEnum.moisture);

        try {
            outputMesh = new MeshFactory().read("testOutput.mesh");
        } catch (Exception e) {}

        for (Structs.Polygon p :outputMesh.getPolygonsList()){
            String val = "1";
            String color = extractColor(p.getPropertiesList());
            if(color.equals("[14, 2, 58]")) {
                Aquifers.add(extractID(p.getPropertiesList()));
            }
        }
    }

    @TestActual
    public void testAquifers() {
        assertNotNull(Aquifers);
    }

    private static String extractColor(java.util.List<Structs.Property> properties) {
        String val = null;
        for(Structs.Property p: properties) {
            if (p.getKey().equals("rgb_color")) {
                val = p.getValue();
            }
        }
        if (val == null)
            return "";
        return val;
    }

    private static int extractID(List<Structs.Property> properties) {
        String val = "0";
        for(Structs.Property p: properties) {
            if (p.getKey().equals("id")) {
                val = p.getValue();
            }
        }
        return Integer.parseInt(val);
    }
}
