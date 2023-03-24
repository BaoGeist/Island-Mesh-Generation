package islandADT.Water.MoisturePackage;

import islandADT.Specifications.IslandSpecifications;

public class MoistureFactory {
    public static MoistureInterface selectMoistureProfile(IslandSpecifications islandSpecifications) {
        String moistureProfile = islandSpecifications.getSoil();
        switch(moistureProfile) {
            case "arid" :
                return new AridMoisture();
            case "fertile" :
                return new FertileMoisture();
            case "swamp" :
                return new SwampMoisture();
            default:
                return new FertileMoisture();
        }
    }
}
