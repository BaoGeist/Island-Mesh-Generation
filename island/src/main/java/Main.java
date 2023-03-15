import islandADT.Configurations.IslandConfiguration;
import islandADT.Specifications.IslandSpecifications;
import islandADT.IslandGenerator;

import java.io.IOException;

public class Main {
    // sample run "java -jar island.jar -i new.mesh -o island.mesh"

    public static void main(String[] args) throws IOException {
        IslandConfiguration islandConfiguration = new IslandConfiguration(args);
        IslandSpecifications islandSpecifications = islandConfiguration.getIslandSpecifications();
        IslandGenerator islandGenerator = new IslandGenerator(islandSpecifications);
        islandGenerator.create_island();

    }

}
