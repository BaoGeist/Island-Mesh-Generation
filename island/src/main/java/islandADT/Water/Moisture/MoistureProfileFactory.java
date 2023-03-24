package islandADT.Water.Moisture;

import islandADT.GeometryContainer;
import islandADT.Specifications.IslandSpecifications;

public class MoistureProfileFactory {
    public static Moisture createMoistureProfile(IslandSpecifications islandSpecifications) {
        String moistureProfile = islandSpecifications.getSoil();
        switch(moistureProfile) {
            case "arid" :
                return new AridProfile();
            case "fertile" :
                return new FertileProfile();
            case "swamp" :
                return new SwampProfile();
            default:
                return new FertileProfile();
        }
    }
}
