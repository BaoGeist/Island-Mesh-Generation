package islandADT.Water;

import islandADT.Container.GeometryContainer;
import islandADT.Generator.GenerateFeatureInterface;

public interface WaterBody extends GenerateFeatureInterface {
    public void generate(GeometryContainer geometryContainer);
}
