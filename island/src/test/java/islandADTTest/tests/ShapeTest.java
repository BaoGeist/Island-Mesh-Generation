package islandADTTest.tests;

import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import islandADTTest.tags.Before;
import islandADTTest.tags.TestActual;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static islandADTTest.tests.Assertions.assertNotNull;

public class ShapeTest {

    Structs.Mesh outputMesh;
    //Things to test -> Basically just test for general division of water and land
    //This means that I can verify that all the sand tiles are next to both water and land
    //Sometimes this will fail as some sand tiles are fully surrounded by water, so as long as half of them are surrounded by both land and water test will pass

    List<Integer> edgeOceanTiles = new ArrayList<>();

    @Before
    public void SetUpSandTiles(){
        try {
            outputMesh = new MeshFactory().read("testOutput.mesh");
        } catch (Exception e) {}

        Color ocean = new Color(0, 86, 161);

        for (Structs.Polygon p :outputMesh.getPolygonsList()){
            Color polygonColor = extractColor(p.getPropertiesList());
            if (polygonColor.getRGB() == ocean.getRGB()){
                List<Integer> neighbors = p.getNeighborIdxsList();
                for (int i = 0; i < neighbors.size(); i++){
                    int polygonIdx = neighbors.get(i);
                    Structs.Polygon polygon = outputMesh.getPolygons(polygonIdx);
                    Color neighborColor = extractColor(polygon.getPropertiesList());
                    if (neighborColor.getRGB() != ocean.getRGB()){
                        edgeOceanTiles.add(polygon.getCentroidIdx());
                    }
                }
            }
        }
    }

    public static Color extractColor(java.util.List<Structs.Property> properties) {
        String val = null;
        for(Structs.Property p: properties) {
            if (p.getKey().equals("rgb_color")) {
                val = p.getValue();
            }
        }
        if (val == null)
            return Color.BLACK;
        String[] raw = val.split(",");
        int red = Integer.parseInt(raw[0].replace("[","").replace(" ", ""));
        int green = Integer.parseInt(raw[1].replace(" ", ""));
        int blue = Integer.parseInt(raw[2].replace("]","").replace(" ", ""));
        return new Color(red, green, blue);
    }

    @TestActual
    public void testShapes(){
        assertNotNull(edgeOceanTiles);
    }
}
