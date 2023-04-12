package islandADT.Biomes;

import islandADT.Container.GeometryContainer;
import islandADT.Generator.GenerateFeatureInterface;

public interface BiomeInterface extends GenerateFeatureInterface{
    public void generate(GeometryContainer geometryContainer);
}
