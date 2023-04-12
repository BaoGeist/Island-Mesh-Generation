package islandADT.Urbanism;

import islandADT.Container.GeometryContainer;
import islandADT.Elevation.CustomPrecisionModel;
import islandADT.Generator.RandomSeed;
import islandADT.GeometryWrappers.PointWrapper;
import islandADT.GeometryWrappers.SegmentWrapper;
import islandADT.TypeWrappers.Cities;
import islandADT.GeometryWrappers.PolygonWrapper;
import islandADT.GeometryWrappers.VertexWrapper;
import islandADT.Specifications.IslandSpecifications;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static islandADT.Container.GeometryContainerCalculator.getDryLandPolygons;
import static islandADT.Container.GeometryContainerCalculator.getPolygonFromCentroid;
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
                CityEvaluator cityEvaluator = new CityEvaluator();
                if (potential_city.getPopulation() == 0 && cityEvaluator.suitable_city_placement(potential_city_tile.getId_centroid(), geometryContainer)) {
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

        vertices.get(source).setPopulation(10);

        return new Cities(source, cities);
    }




}