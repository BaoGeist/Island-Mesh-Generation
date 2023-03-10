package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.generator.ADT.OurIrregular;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OurIrregularTest {

    @Test
    public void irregularMeshIsNotNull() {
        OurIrregular generator = new OurIrregular(400, 400, 200, 5);
        Structs.Mesh aMesh = generator.generate();
        assertNotNull(aMesh);
    }
}
