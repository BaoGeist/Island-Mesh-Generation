package islandADT.Biomes;

import islandADT.Container.GeometryContainer;
import islandADT.Generator.GenerateFeatureInterface;

public interface BiomeInterface extends GenerateFeatureInterface{
    /**
     * generates biome feature
     * @param geometryContainer
     */
    public void generate(GeometryContainer geometryContainer);
}
