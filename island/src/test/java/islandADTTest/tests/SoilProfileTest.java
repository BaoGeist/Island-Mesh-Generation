package islandADTTest.tests;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import islandADTTest.tags.Before;
import islandADTTest.tags.TestActual;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static islandADTTest.tests.Assertions.assertTrue;

public class SoilProfileTest extends TestRenders{

    List<Integer> testPolygons = new ArrayList<>();

    boolean pass = false;

    @Before
    public void setUpMoistures() {
        Structs.Mesh aridMesh = null, fertileMesh = null, swampMesh = null;

        aridMesh = setUpMeshMoisture("arid");
        fertileMesh = setUpMeshMoisture("fertile");
        swampMesh = setUpMeshMoisture("swamp");

        Random random = new Random();


        for(int i = 0; i < 100; i ++) {

            testPolygons.add(random.nextInt(aridMesh.getPolygonsList().size()));
        }

        for(Integer id_polygon: testPolygons) {
            String aridColor = extractColor(aridMesh.getPolygonsList().get(id_polygon).getPropertiesList());
            String fertileColor = extractColor(fertileMesh.getPolygonsList().get(id_polygon).getPropertiesList());
            String swampColor = extractColor(swampMesh.getPolygonsList().get(id_polygon).getPropertiesList());

            if(!swampColor.equals(fertileColor) || !fertileColor.equals(aridColor) || !aridColor.equals(swampColor)) {
                pass = true;
            }
        }


    }

    @TestActual
    public void testSoilProfiles() {assertTrue(pass); }

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
