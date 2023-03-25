package islandADTTest.tests;

import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import islandADTTest.tags.Before;
import islandADTTest.tags.TestActual;

import java.util.ArrayList;
import java.util.List;

import static islandADTTest.Assertions.assertNotNull;

public class RiverTest {
    Structs.Mesh outputMesh;
    List<Integer> Rivers = new ArrayList<>();

    @Before
    public void SetUpRivers(){
        try {
            outputMesh = new MeshFactory().read("testOutput.mesh");
        } catch (Exception e) {}

        for (Structs.Segment s :outputMesh.getSegmentsList()){
            String val = "1";
            for (Structs.Property p: s.getPropertiesList()) {
                if (p.getKey().equals("thicc")) {
                    val = p.getValue();
                }
            }
            if (Integer.parseInt(val)>1){
                Rivers.add(s.getV1Idx());
            }
        }
    }


    @TestActual
    public void testRivers() {
        assertNotNull(Rivers);
    }
}
