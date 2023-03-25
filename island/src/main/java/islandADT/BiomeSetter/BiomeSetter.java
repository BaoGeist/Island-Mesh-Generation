package islandADT.BiomeSetter;

import java.util.Map;

import islandADT.GeometryContainer;
import islandADT.GeometryWrappers.PolygonWrapper;
import islandADT.TypeWrappers.TileTypeWrapper;
import islandADT.Specifications.IslandSpecifications;

public class BiomeSetter {
    private String biomeType;
    private IslandSpecifications islandSpecifications;

    public BiomeSetter(IslandSpecifications islandSpecifications) {
        this.islandSpecifications = islandSpecifications;
    }

    public String calculateBiome(PolygonWrapper p) {
        double moisture = p.getMoisture();
        int height = p.getHeight();
        int score = 0;

        if (moisture < 4) {score += 1;}
        else if (moisture < 8) {score += 2;}
        else if (moisture < 12) {score += 3;}
        else if (moisture < 16) {score += 4;}
        else if (moisture < 20) {score += 5;}
        else {
            biomeType = "water";
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
                biomeType = "desert";
                TileTypeWrapper Desert = new TileTypeWrapper("Desert");
                p.setTileType(Desert);
                break;
            }
            case 4:
            case 9: {
                biomeType = "taiga";
                TileTypeWrapper Taiga = new TileTypeWrapper("Taiga");
                p.setTileType(Taiga);
                break;
            }
            case 5:
            case 10: {
                biomeType = "tundra";
                TileTypeWrapper Tundra = new TileTypeWrapper("Tundra");
                p.setTileType(Tundra);
                break;
            }
            case 6:
            case 11: {
                biomeType = "savannah";
                TileTypeWrapper Savannah = new TileTypeWrapper("Savannah");
                p.setTileType(Savannah);
                break;
            }
            case 7:
            case 8: {
                biomeType = "grassland";
                TileTypeWrapper Grassland = new TileTypeWrapper("Grassland");
                p.setTileType(Grassland);
                break;
            }
            case 12:
            case 13:
            case 14:
            case 18:
            case 19:
            case 23:
            case 24: {
                biomeType = "forest";
                TileTypeWrapper Forest = new TileTypeWrapper("Forest");
                p.setTileType(Forest);
                break;
            }
            case 15:
            case 20:
            case 25: {
                biomeType = "mountain";
                TileTypeWrapper Mountain = new TileTypeWrapper("Mountain");
                p.setTileType(Mountain);
                break;
            }
            case 16:
            case 17:
            case 21:
            case 22: {
                biomeType = "rainforest";
                TileTypeWrapper Rainforest = new TileTypeWrapper("Rainforest");
                p.setTileType(Rainforest);
                break;
            }
            default: {biomeType = "forest";}
        }
        
        return biomeType;
    }

    public void setMultipleBiomes(GeometryContainer geometryContainer) {
        Map<Integer, PolygonWrapper> polygons = geometryContainer.get_polygons();
        for (PolygonWrapper p: polygons.values()) {
            if (!p.isWaterOrNah() && p.isLandornah()) {
                calculateBiome(p);
            }
        }
    }

}
