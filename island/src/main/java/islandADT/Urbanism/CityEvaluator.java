package islandADT.Urbanism;

import islandADT.Container.GeometryContainer;
import islandADT.GeometryWrappers.PointWrapper;
import islandADT.GeometryWrappers.PolygonWrapper;
import islandADT.GeometryWrappers.SegmentWrapper;
import islandADT.GeometryWrappers.VertexWrapper;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static islandADT.Container.GeometryContainerCalculator.getPolygonFromCentroid;
import static islandADT.Container.GeometryContainerCalculator.polygon_neighbours_objects;

public class CityEvaluator {

    public static Map<PointWrapper, Double> waterSources = new HashMap<>();

    public boolean suitable_city_placement(int city, GeometryContainer geometryContainer) {
        return suitable_city_moisture(city, geometryContainer) && suitable_city_flatland(city, geometryContainer) && suitable_city_overpopulation(city, geometryContainer);
    }

    private boolean suitable_city_moisture(int city, GeometryContainer geometryContainer) {
        Map<Integer, VertexWrapper> vertices = geometryContainer.get_vertices();
        VertexWrapper cityVertex = vertices.get(city);
        PolygonWrapper cityLocation = getPolygonFromCentroid(geometryContainer, city);
        return cityLocation.getMoisture() > 3;
    }

    private boolean suitable_city_flatland(int city, GeometryContainer geometryContainer) {
        Map<Integer, VertexWrapper> vertices = geometryContainer.get_vertices();
        VertexWrapper cityVertex = vertices.get(city);
        PolygonWrapper cityLocation = getPolygonFromCentroid(geometryContainer, city);

        List<PolygonWrapper> neighbours = polygon_neighbours_objects(geometryContainer, cityLocation.get_id());
        for (PolygonWrapper neighbour: neighbours) {
            if(Math.abs(cityLocation.getHeight() - neighbour.getHeight()) > 30) {
                return false;
            }
        }
        return true;
    }

    private boolean suitable_city_overpopulation(int city, GeometryContainer geometryContainer) {
        Map<Integer, VertexWrapper> vertices = geometryContainer.get_vertices();
        VertexWrapper cityVertex = vertices.get(city);
        PolygonWrapper cityLocation = getPolygonFromCentroid(geometryContainer, city);

        List<PolygonWrapper> neighbours = polygon_neighbours_objects(geometryContainer, cityLocation.get_id());
        for (PolygonWrapper neighbour: neighbours) {
            VertexWrapper neighbour_centroid = vertices.get(neighbour.getId_centroid());
            if(neighbour_centroid.getPopulation() != 0) {
                return false;
            }
        }
        return true;
    }
}
