package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.generator.ADT.OurMesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OurMeshTest {

    @Test
    public void meshIsNotNull() {
        OurMesh generator = new OurMesh(500, 500, 25,1.00f, 1);
        Structs.Mesh aMesh = generator.generate();
        assertNotNull(aMesh);
    }

}
