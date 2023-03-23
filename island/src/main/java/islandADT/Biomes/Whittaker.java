package islandADT.Biomes;

import islandADT.Water.Moisture;
import islandADT.GeometryWrappers.PolygonWrapper;

public class Whittaker {
    String biome = null;

    private String setBiome(double moisture, int height) {
        int score = 0;

        if (moisture < 4) {score += 1;}
        else if (moisture < 8) {score += 2;}
        else if (moisture < 12) {score += 3;}
        else if (moisture < 16) {score += 4;}
        else if (moisture < 20) {score += 5;}
        else {
            biome = "water";
        }

        if (height < 200 && height >= 100) {
            score += 5;
        }
        else if (height < 300) {
            score += 10;
        }
        else if (height < 400) {
            score += 15;
        }
        else {
            score += 20;
        }

        switch (score) {
            case 1: 
            case 2:
            case 3: {
                biome = "desert";
                break;
            }
            case 4:
            case 9: {
                biome = "taiga";
                break;
            }
            case 5:
            case 10: {
                biome = "tundra";
                break;
            }
            case 6:
            case 11: {
                biome = "savannah";
                break;
            }
            case 7:
            case 8: {
                biome = "grassland";
                break;
            }
            case 12:
            case 13:
            case 14:
            case 18:
            case 19:
            case 23:
            case 24: {
                biome = "forest";
                break;
            }
            case 15:
            case 20:
            case 25: {
                biome = "mountain";
                break;
            }
            case 16:
            case 17:
            case 21:
            case 22: {
                biome = "rainforest";
                break;
            }
            default: {biome = "forest";}
        }
        
        return biome;
    }

    public String getBiome(PolygonWrapper p) {
        if (biome == null) {
            double moisture = p.getMoisture();
            int height = p.getHeight();
            setBiome(moisture, height);
        }
        return biome;
    }
}
