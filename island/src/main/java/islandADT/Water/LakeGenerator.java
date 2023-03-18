package islandADT.Water;

import islandADT.Generator.RandomSeed;
import islandADT.GeometryContainer;
import islandADT.GeometryContainerCalculator;
import islandADT.GeometryWrappers.PolygonWrapper;
import islandADT.GeometryWrappers.SegmentWrapper;
import islandADT.Specifications.IslandSpecifications;
import islandADT.TypeWrappers.TileTypeWrapper;
import islandADT.GeometryWrappers.VertexWrapper;

import java.util.*;

import static islandADT.GeometryContainerCalculator.*;

public class LakeGenerator extends WaterTile{
    int amount_of_lakes = 0;
    public LakeGenerator(IslandSpecifications islandSpecifications) {
        amount_of_lakes = Integer.parseInt(islandSpecifications.getLakes());
    }
    private void lake_erosion(GeometryContainer geometryContainer, List<PolygonWrapper> lakes) {
        int min_height = Integer.MAX_VALUE;
        for(PolygonWrapper lake: lakes) {
            List<VertexWrapper> vertices = vertices_of_a_polygon(geometryContainer, lake);
            for(VertexWrapper v: vertices) {
                min_height = Math.min(min_height, v.getHeight());
            }
        }

        for(PolygonWrapper lake: lakes) {
            lake.setHeight(min_height - 50);

            List<VertexWrapper> vertices = vertices_of_a_polygon(geometryContainer, lake);
            List<SegmentWrapper> segments = segments_of_a_polygon(geometryContainer, lake);

            for(SegmentWrapper s: segments) {
                s.setHeight(min_height - 50);
            }

            for(VertexWrapper v: vertices) {
                v.setHeight(min_height - 50);
            }
        }
    }

    public void generate(GeometryContainer geometryContainer) {
        Map<Integer, PolygonWrapper> polygons = geometryContainer.get_polygons();
        Map<Integer, VertexWrapper> vertices = geometryContainer.get_vertices();
        for(int i = 0; i < amount_of_lakes; i++) {
            List<PolygonWrapper> lakes = new ArrayList<>();
            TileTypeWrapper Lake = new TileTypeWrapper("Lake");

            int lake_id = random_start(geometryContainer);
            while(polygons.get(lake_id).getTileType().isEquals(Lake)) {
                lake_id = random_start(geometryContainer);
            }

            polygons.get(lake_id).setTileType(Lake);
            lakes.add(polygons.get(lake_id));

            for(PolygonWrapper neighbour: polygon_neighbours_objects(geometryContainer, lake_id)) {
                if(RandomSeed.randomBoolean() && polygon_no_ocean_neighbours(geometryContainer, neighbour.get_id())) {
                    neighbour.setTileType(Lake);
                    lakes.add(neighbour);
                }
            }

            lake_erosion(geometryContainer, lakes);
        }
    }

}
