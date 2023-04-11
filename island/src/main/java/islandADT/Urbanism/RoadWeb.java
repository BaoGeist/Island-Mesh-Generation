package islandADT.Urbanism;

import islandADT.Container.GeometryContainer;
import islandADT.TypeWrappers.Cities;
import islandADT.Specifications.IslandSpecifications;

public class RoadWeb {
    IslandSpecifications islandSpecifications;
    public RoadWeb(IslandSpecifications islandSpecifications) {
        this.islandSpecifications = islandSpecifications;
    }
    public void create_roads(GeometryContainer geometryContainer) {
        CityGenerator cityGenerator = new CityGenerator(islandSpecifications);
        Cities cities = cityGenerator.generate_cities(geometryContainer);

        GraphAdapter graphAdapter = new GraphAdapter(geometryContainer);
        graphAdapter.AdaptedPath(geometryContainer, cities.getCenter(), cities.getOthers());
    }
}
