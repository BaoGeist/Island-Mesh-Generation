package islandADT.Biomes;

import java.util.Map;

import islandADT.Container.GeometryContainer;
import islandADT.GeometryWrappers.PolygonWrapper;
import islandADT.TypeWrappers.TileTypeWrapper;
import islandADT.Specifications.IslandSpecifications;

public class ArcticBiomeSetter extends BiomeSetterAbstract {
    private IslandSpecifications islandSpecifications;

    public ArcticBiomeSetter(IslandSpecifications islandSpecifications) {
        this.islandSpecifications = islandSpecifications;
    }

    private void calculateArcticBiome(PolygonWrapper p) {
        double moisture = p.getMoisture();
        int height = p.getHeight();
        int[] coords = new int[2];

        if (height < 2*height_increment) {coords[0] = 1;}
        else if (height < 4*height_increment) {coords[0] = 2;}
        else if (height < 6*height_increment) {coords[0] = 3;}
        else if (height < 8*height_increment) {coords[0] = 4;}
        else {coords[0] = 5;}

        if (moisture < 2*moisture_increment) {coords[1] = 1;}
        else if (moisture < 4*moisture_increment) {coords[1] = 2;}
        else if (moisture < 6*moisture_increment) {coords[1] = 3;}
        else if (moisture < 8*moisture_increment) {coords[1] = 4;}
        else if (moisture <= 10*moisture_increment) {coords[1] = 5;}

        TileTypeWrapper Taiga = new TileTypeWrapper("Taiga");
        TileTypeWrapper Tundra = new TileTypeWrapper("Tundra");
        TileTypeWrapper Mountain = new TileTypeWrapper("Mountain");
        TileTypeWrapper Bamboo = new TileTypeWrapper("Bamboo Mountain");
        
        if (coords[0] <= 3) {
            if (coords[1] <= 2) {
                p.setTileType(Taiga);
            }
            else {
                p.setTileType(Tundra);
            }
        }
        else {
            if (coords[1] == 1) {
                p.setTileType(Bamboo);
            }
            else {
                p.setTileType(Mountain);
            }
        }

    }

    /**
     * Assigns biome TileTypeWrappers to all land tiles of a mesh
     * @param geometryContainer
     */
    public void generate (GeometryContainer geometryContainer) {
        set_increments(geometryContainer.get_polygons());
        Map<Integer, PolygonWrapper> polygons = geometryContainer.get_polygons();
        for (PolygonWrapper p: polygons.values()) {
            if (!p.isWaterOrNah() && p.isLandornah()) {
                calculateArcticBiome(p);
            }
        }
    }
}
