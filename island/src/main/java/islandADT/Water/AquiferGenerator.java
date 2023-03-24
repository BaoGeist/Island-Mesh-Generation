package islandADT.Water;

import islandADT.Generator.RandomSeed;
import islandADT.GeometryContainer;
import islandADT.GeometryWrappers.PolygonWrapper;
import islandADT.GeometryWrappers.SegmentWrapper;
import islandADT.GeometryWrappers.VertexWrapper;
import islandADT.Specifications.IslandSpecifications;
import islandADT.TypeWrappers.TileTypeWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static islandADT.GeometryContainerCalculator.*;
import static islandADT.GeometryContainerCalculator.polygon_no_ocean_neighbours;

public class AquiferGenerator extends WaterTile{
    int amount_of_aquifers = 0;

    public AquiferGenerator(IslandSpecifications islandSpecifications) {
        amount_of_aquifers = Integer.parseInt(islandSpecifications.getAquifers());
    }

    public void generate(GeometryContainer geometryContainer) {
        RandomSeed instanceRandom = RandomSeed.getInstance();

        Map<Integer, PolygonWrapper> polygons = geometryContainer.get_polygons();
        Map<Integer, VertexWrapper> vertices = geometryContainer.get_vertices();
        for(int i = 0; i < amount_of_aquifers; i++) {
            List<PolygonWrapper> aquifers = new ArrayList<>();
            TileTypeWrapper Aquifer = new TileTypeWrapper("Aquifer");
            TileTypeWrapper Lake = new TileTypeWrapper("Lake");

            int aquifer_id = random_start(geometryContainer);
            while(polygons.get(aquifer_id).getTileType().isEquals(Aquifer) || polygons.get(aquifer_id).getTileType().isEquals(Lake)) {
                aquifer_id = random_start(geometryContainer);
            }

            polygons.get(aquifer_id).setTileType(Aquifer);
            aquifers.add(polygons.get(aquifer_id));
            polygons.get(aquifer_id).setMoisture(20);

            for(PolygonWrapper neighbour: polygon_neighbours_objects(geometryContainer, aquifer_id)) {
                if(instanceRandom.randomBoolean() && polygon_no_ocean_neighbours(geometryContainer, neighbour.get_id())) {
                    neighbour.setTileType(Aquifer);
                    aquifers.add(neighbour);
                    neighbour.setMoisture(20);
                }
            }
        }
    }
}
