package islandADT.BiomeSetter;

import islandADT.Specifications.IslandSpecifications;

public class BiomeFactory {
    public static BiomeInterface selectBiomeProfile(IslandSpecifications islandSpecifications) {
        String biomeProfile = islandSpecifcations.getWhittaker();
        switch (biomeProfile) {
            case "arctic" :
                return new ArcticBiomeSetter(islandSpecifications);
        }
    }
}
