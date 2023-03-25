package islandADT.TypeWrappers;

public class TileTypeWrapperCreator {
    public static void create_tile_types() {
        TileTypeWrapper.add_biome("Lake", new int[]{1, 129, 144}, true, true);
        TileTypeWrapper.add_biome("Ocean", new int[]{0, 86, 161}, false, true);
        TileTypeWrapper.add_biome("Sand", new int[]{194, 178, 128}, true, false);
        TileTypeWrapper.add_biome("Land", new int[]{39, 150, 33}, true, false);
        TileTypeWrapper.add_biome("RiverLake", new int[]{46, 162, 205}, true, true);
        TileTypeWrapper.add_biome("Aquifer", new int[]{255, 255, 255}, true, true);

        
        TileTypeWrapper.add_biome("Desert", new int[]{212, 177, 21}, true, false);
        TileTypeWrapper.add_biome("Savannah", new int[]{212, 126, 21}, true, false);
        TileTypeWrapper.add_biome("Grassland", new int[]{131, 148, 01}, true, false);
        TileTypeWrapper.add_biome("Forest", new int[]{5, 97, 29}, true, false);
        TileTypeWrapper.add_biome("Rainforest", new int[] {13, 224, 70}, true, false);
        TileTypeWrapper.add_biome("Taiga", new int[]{6, 140, 118}, true, false);
        TileTypeWrapper.add_biome("Tundra", new int[]{39, 5, 161}, true, false);
        TileTypeWrapper.add_biome("Mountain", new int[]{127, 126, 130}, true, false);
    }
}
