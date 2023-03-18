package islandADT.TypeWrappers;

public class TileTypeWrapperCreator {
    public static void create_tile_types() {
        TileTypeWrapper.add_biome("Lake", new int[]{1, 129, 144});
        TileTypeWrapper.add_biome("Ocean", new int[]{0, 86, 161});
        TileTypeWrapper.add_biome("Sand", new int[]{194, 178, 128});
        TileTypeWrapper.add_biome("Land", new int[]{39, 150, 33});
        //TODO B change aquifer to something else
        TileTypeWrapper.add_biome("Aquifer", new int[]{255, 255, 255});
    }
}
