package islandADT.Generator;

import islandADT.Container.GeometryContainer;

public interface GenerateFeatureInterface {
    /**
     * generates features for a mesh with island shape and elevation profile established
     * @param geometryContainer
     */
    public void generate(GeometryContainer geometryContainer);
}
