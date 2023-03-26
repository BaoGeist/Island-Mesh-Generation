package islandADTTest.tests;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import islandADTTest.tags.Before;
import islandADTTest.tags.TestActual;

import java.util.*;

import static islandADTTest.tests.Assertions.*;


public class NormalRenderTest extends TestRenders {
    @Before
    public void setUpNormalMesh() {
        setUpMesh(RenderEnum.normal);
    }

    @TestActual
    public void testNormalMesh() {
        List<Structs.Polygon> polygons = outputMesh.getPolygonsList();

        Set<String> unique_colors = new HashSet<>();
        List<String> known_colors = populate_viable_colors();

        for(Structs.Polygon p: polygons) {
            unique_colors.add(extractColor(p.getPropertiesList()));
        }

        for(String color: unique_colors) {
            asssertContainsString(known_colors, color);
        }

        int unique_colors_threshold = 3;
        assertTrue(unique_colors.size() >= unique_colors_threshold);

    }

    private static List<String> populate_viable_colors(){
        List<String> list_known = new ArrayList<>();
        list_known.add("[1, 129, 144]");
        list_known.add("[0, 86, 161]");
        list_known.add("[46, 162, 205]");
        list_known.add("[194, 178, 128]");
        list_known.add("[39, 150, 33]");
        list_known.add("[46, 162, 205]");
        list_known.add("[212, 177, 21]");
        list_known.add("[212, 126, 21]");
        list_known.add("[131, 148, 1]");
        list_known.add("[5, 97, 29]");
        list_known.add("[13, 224, 70]");
        list_known.add("[69, 186, 81]");
        list_known.add("[39, 5, 161]");
        list_known.add("[127, 126, 130]");
        list_known.add("[75, 92, 79]");
        list_known.add("[212, 240, 211]");
        list_known.add("[235, 38, 38]");

        return list_known;
    }


    // Extracts color
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
