package islandADT.Urbanism;

import islandADT.Container.GeometryContainer;
import islandADT.Elevation.CustomPrecisionModel;
import islandADT.Generator.RandomSeed;
import islandADT.TypeWrappers.Cities;
import islandADT.GeometryWrappers.PolygonWrapper;
import islandADT.GeometryWrappers.VertexWrapper;
import islandADT.Specifications.IslandSpecifications;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static islandADT.Container.GeometryContainerCalculator.getDryLandPolygons;
import static islandADT.Utils.MathUtils.distance_between_centre;

public class CityGenerator {
    private IslandSpecifications islandSpecifications;

    public CityGenerator(IslandSpecifications islandSpecifications) {
        this.islandSpecifications = islandSpecifications;
    }

    public Cities generate_cities(GeometryContainer geometryContainer) {
        RandomSeed instanceRandom = RandomSeed.getInstance();
        List<PolygonWrapper> polygons = getDryLandPolygons(geometryContainer);
        Map<Integer, VertexWrapper> vertices = geometryContainer.get_vertices();

        int number_of_cities = Integer.parseInt(islandSpecifications.getCity());
        int[] cities = new int[number_of_cities];

        int counter = 0;
        while(counter < number_of_cities) {
            PolygonWrapper potential_city_tile = polygons.get(instanceRandom.randomInt(polygons.size()));
            if(potential_city_tile.isLandornah()) {
                VertexWrapper potential_city = vertices.get(potential_city_tile.getId_centroid());
                if (potential_city.getPopulation() == 0) {
                    potential_city.setPopulation(potential_city_tile.getHeight() / 50);
                    cities[counter] = potential_city.get_id();
                    counter++;
                }
            }
        }

        int source = 0;
        int source_distance = Integer.MAX_VALUE;

        CustomPrecisionModel customPrecisionModel =  new CustomPrecisionModel(1);
        for(int city: cities) {
            if(distance_between_centre(vertices.get(city).getCoords(), customPrecisionModel) < source_distance) {
                source_distance = distance_between_centre(vertices.get(city).getCoords(), customPrecisionModel);
                source = city;
            }
        }

        vertices.get(source).setPopulation(7);


        return new Cities(source, cities);
    }
}
