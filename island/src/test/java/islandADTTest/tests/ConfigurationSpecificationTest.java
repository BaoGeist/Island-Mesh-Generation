package islandADTTest.tests;

import islandADT.Configurations.IslandConfiguration;
import islandADT.Specifications.IslandSpecifications;
import islandADTTest.tags.Before;
import islandADTTest.tags.TestActual;

import java.util.ArrayList;
import java.util.List;

import static islandADTTest.Assertions.*;

public class ConfigurationSpecificationTest {

    List<String> inputs = new ArrayList<>();
    IslandSpecifications islandSpecifications = null;

    @Before
    public void setUpMinimalInputs(){
        inputs.add("-i");
        inputs.add("testInput.mesh");
        inputs.add("-o");
        inputs.add("testOutput.mesh");

        String[] arrayInputs = inputs.toArray(new String[0]);
        IslandConfiguration islandConfiguration = new IslandConfiguration(arrayInputs);
        this.islandSpecifications = islandConfiguration.getIslandSpecifications();
    }

    @TestActual
    public void testSpecificationNotNull() {
        assertNotNull(islandSpecifications);
    }

    @TestActual
    public void testElevationDefault() { assertEquals(islandSpecifications.getElevation(), "plain"); }
    @TestActual
    public void testSoilDefault() { assertEquals(islandSpecifications.getSoil(), "fertile"); }
    @TestActual
    public void testAquifersDefault() { assertEquals(islandSpecifications.getAquifers(), "3"); }
    @TestActual
    public void testShapeDefault() { assertEquals(islandSpecifications.getShape(), "circle");}
    @TestActual
    public void testModeDefault() { assertEquals(islandSpecifications.getMode(), "normal"); }
    @TestActual
    public void testRiversDefault() { assertEquals(islandSpecifications.getRivers(), "3"); }
    @TestActual
    public void testLakesDefault() { assertEquals(islandSpecifications.getLakes(), "3"); }
    @TestActual
    public void testSeedDefault() { assertEquals(islandSpecifications.getSeed(), ""); }


}
