package islandADTTest.tests;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import islandADTTest.tags.Before;
import islandADTTest.tags.TestActual;

import java.lang.reflect.Array;
import java.util.*;

import static islandADTTest.Assertions.*;


public class MoistureRenderTest extends TestRenders {
    @Before
    public void setUpMoistureMesh() {
        setUpMesh(RenderEnum.moisture);
    }

    @TestActual
    public void testMoistureMesh() {
        List<Structs.Polygon> polygons = outputMesh.getPolygonsList();

        Set<String> unique_colors = new HashSet<>();
        List<String> known_colors = populate_viable_colors();

        for(Structs.Polygon p: polygons) {
            unique_colors.add(extractColor(p.getPropertiesList()));
        }

        System.out.println(unique_colors);

        for(String color: unique_colors) {
            if(!known_colors.contains(color)) {
                System.out.println(color);
            }
            asssertContainsString(known_colors, color);
        }

        int unique_colors_threshold = 3;
        assertTrue(unique_colors.size() >= unique_colors_threshold);

    }

    private static List<String> populate_viable_colors(){
        List<String> list_known = new ArrayList<>();
        list_known.add("[0, 86, 161]");
        list_known.add("[0, 0, 0]");
        list_known.add("[145, 71, 234]");
        list_known.add("[200, 177, 238]");
        list_known.add("[255, 255, 255]");
        list_known.add("[188, 227, 255]");
        list_known.add("[128, 163, 255]");
        list_known.add("[63, 106, 255]");
        list_known.add("[8, 78, 255]");
        list_known.add("[51, 31, 182]");
        list_known.add("[34, 9, 138]");
        list_known.add("[24, 5, 103]");
        list_known.add("[14, 2, 58]");
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
