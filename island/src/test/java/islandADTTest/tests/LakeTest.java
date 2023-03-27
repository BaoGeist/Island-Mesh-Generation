package islandADTTest.tests;

import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import islandADTTest.tags.Before;
import islandADTTest.tags.TestActual;

import java.util.ArrayList;
import java.util.List;

import static islandADTTest.tests.Assertions.assertNotNull;
import static islandADTTest.tests.Assertions.assertNotZero;

public class LakeTest extends TestRenders{
    Structs.Mesh outputMesh;
    List<Integer> Lakes = new ArrayList<>();
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
                Lakes.add(p.getPropertiesCount());
            }
        }
    }

    @TestActual
    public void testLakes() {
        assertNotZero(Lakes.size());
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
}
