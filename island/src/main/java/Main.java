import islandADT.Configurations.IslandConfiguration;
import islandADT.Specifications.IslandSpecifications;
import islandADT.Generator.IslandGenerator;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    // sample run "java -jar island.jar -i temp.mesh -o island.mesh"

    public static void main(String[] args) throws IOException {
        System.out.println(Arrays.toString(args));
        IslandConfiguration islandConfiguration = new IslandConfiguration(args);
        IslandSpecifications islandSpecifications = islandConfiguration.getIslandSpecifications();
        IslandGenerator islandGenerator = new IslandGenerator(islandSpecifications);
        islandGenerator.create_island();

    }

}
