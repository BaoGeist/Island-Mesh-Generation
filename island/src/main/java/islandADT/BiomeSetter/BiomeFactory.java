package islandADT.BiomeSetter;

import islandADT.Specifications.IslandSpecifications;

public class BiomeFactory {
    public static BiomeInterface selectBiomeProfile(IslandSpecifications islandSpecifications) {
        String biomeProfile = islandSpecifications.getWhittaker();
        System.out.println("ASDLFHEWIOAEHFWIOAHFIWEOHFIWOAEFHWIAFHALKDSFHASDKFHAKSDLFHKAEFHWIAHFEAW");
        switch (biomeProfile) {
            case "arctic" :
                return new ArcticBiomeSetter(islandSpecifications);
            case "forest" :
                return new ForestBiomeSetter(islandSpecifications);
            case "desert" :
                return new DesertBiomeSetter(islandSpecifications);
            default:   
                return new BiomeSetter(islandSpecifications);
        }
    }
}
