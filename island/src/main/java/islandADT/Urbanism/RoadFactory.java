package islandADT.Urbanism;

import islandADT.Container.GeometryContainer;
import islandADT.Generator.GenerateFeatureInterface;
import islandADT.TypeWrappers.Cities;
import islandADT.Specifications.IslandSpecifications;

public class RoadFactory implements GenerateFeatureInterface {
    IslandSpecifications islandSpecifications;
    public RoadFactory(IslandSpecifications islandSpecifications) {
        this.islandSpecifications = islandSpecifications;
    }
    public void generate(GeometryContainer geometryContainer) {
        CityGenerator cityGenerator = new CityGenerator(islandSpecifications);
        Cities cities = cityGenerator.generate_cities(geometryContainer);

        GraphAdapter graphAdapter = new GraphAdapter(geometryContainer);
        graphAdapter.adapt_graph(geometryContainer, cities.getCenter(), cities.getOthers());
    }
}
