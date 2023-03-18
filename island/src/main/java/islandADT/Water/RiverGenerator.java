package islandADT.Water;

import islandADT.GeometryContainer;
import islandADT.GeometryContainerCalculator;
import islandADT.GeometryWrappers.PolygonWrapper;
import islandADT.Specifications.IslandSpecifications;
import islandADT.TypeWrappers.SegmentTypeWrapper;
import islandADT.GeometryWrappers.SegmentWrapper;
import islandADT.GeometryWrappers.VertexWrapper;
import islandADT.TypeWrappers.TileTypeWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static islandADT.GeometryContainerCalculator.*;
import static islandADT.TypeWrappers.SegmentTypeWrapper.SegmentType.NotWater;
import static islandADT.TypeWrappers.SegmentTypeWrapper.SegmentType.Water;

public class RiverGenerator implements WaterBody{

    int amount_of_rivers = 0;
    public RiverGenerator(IslandSpecifications islandSpecifications) {
        amount_of_rivers = Integer.parseInt(islandSpecifications.getRivers());
    }

    private void generateSprings(GeometryContainer geometryContainer){

        Map<Integer, VertexWrapper> vertices = geometryContainer.get_vertices();

        int springVertex_id;
        int counter = 0;
        List<Integer> highest_points = new ArrayList<>();
        List<Integer> all_points = new ArrayList<>();

        for (VertexWrapper v : vertices.values()){
            all_points.add(v.getHeight());
        }

        for (int i = 0; i < amount_of_rivers; i ++){
            int maxIndex = 0;
            for (int j = 1; j < all_points.size(); j++) {
                if (all_points.get(j) > all_points.get(maxIndex)) {
                    maxIndex = j;
                }
            }
            highest_points.add(all_points.remove(maxIndex));
        }

        for (VertexWrapper v: vertices.values()){
            if (highest_points.contains(v.getHeight()) && v.isSpringVertex() == false && counter < amount_of_rivers){
                springVertex_id = v.get_id();
                VertexWrapper springVertex = vertices.get(springVertex_id);
                springVertex.setSpringVertex(true);
                counter++;
            }
        }
    }

    public void generate(GeometryContainer geometryContainer) {
        generateSprings(geometryContainer);

        Map<Integer, VertexWrapper> vertices = geometryContainer.get_vertices();
        setRiverInitialSegmentType(geometryContainer);

        for (VertexWrapper v : vertices.values()) {
            ArrayList<VertexWrapper> river = new ArrayList<>();
            boolean inNotWater = true;
            if (v.isSpringVertex()) {
                river.add(v);

                System.out.println("First point height = " + v.getHeight());
                int riverFlowVertexID = generate_flow(v, geometryContainer);
                VertexWrapper riverFlowVertex = vertices.get(riverFlowVertexID);
                System.out.println("Second point height = " + riverFlowVertex.getHeight());
                river.add(riverFlowVertex);

                boolean isEnd = isEndOfRiver(riverFlowVertex, geometryContainer);
                while (isEnd) {
                    riverFlowVertexID = generate_flow(riverFlowVertex, geometryContainer);
                    riverFlowVertex = vertices.get(riverFlowVertexID);
                    inNotWater = checkIfInWater(geometryContainer, riverFlowVertex);
                    river.add(riverFlowVertex);
                    if (inNotWater) {
                        isEnd = isEndOfRiver(riverFlowVertex, geometryContainer);
                        System.out.println("Next point height = " + riverFlowVertex.getHeight());
                    } else {
                        break;
                    }
                }
                setRiverSegmentType(river, geometryContainer);
                System.out.println("river size = " + river.size());
                if (inNotWater) {
                    createLake(river, geometryContainer);
                    System.out.println("not in ocean or lake");
                }
            }
        }
    }

    private boolean checkIfInWater(GeometryContainer geometryContainer, VertexWrapper riverFlowVertex){
        //if this returns true, it means that the vertex is not next to an ocean or lake
        boolean bool = true;
        List<PolygonWrapper> polygonListWithVertex = getPolygonsContainingVertex(geometryContainer, riverFlowVertex);
        for (PolygonWrapper p: polygonListWithVertex){
            if (p.isWaterOrNah()){
                bool = false;
            }
        }
        return bool;
    }
    private void createLake(ArrayList<VertexWrapper> river, GeometryContainer geometryContainer) {
        VertexWrapper endOfRiverVertex = river.get(river.size()-1);
        List<PolygonWrapper> polygonListWithVertex = getPolygonsContainingVertex(geometryContainer, endOfRiverVertex);
        int lowest_elevation = 10000;
        for (PolygonWrapper p: polygonListWithVertex){
            lowest_elevation = Math.min(p.getHeight(), lowest_elevation);
        }

        TileTypeWrapper RiverLake = new TileTypeWrapper("RiverLake");

        for (PolygonWrapper p: polygonListWithVertex){
            if (p.getHeight() == lowest_elevation){
                p.setTileType(RiverLake);
                break;
            }
        }
    }

    private int generate_flow(VertexWrapper v, GeometryContainer geometryContainer) {
        Map<Integer, VertexWrapper> neighboringVertices = GeometryContainerCalculator.getVertexNeighbors(geometryContainer, v);

        int lowestVertex = 1000;

        for (VertexWrapper vertex : neighboringVertices.values()) {
            lowestVertex = Math.min(vertex.getHeight(), lowestVertex);
        }

        for (VertexWrapper vertex : neighboringVertices.values()) {
            if (vertex.getHeight() == lowestVertex) {
                return vertex.get_id();
            }
        }
        System.out.println("If you see this, error in line 81 of River.java");
        return 0;
    }

    private boolean isEndOfRiver(VertexWrapper riverFlowVertex, GeometryContainer geometryContainer){
        int currentElevation = riverFlowVertex.getHeight();
        Map<Integer, VertexWrapper> neighboringVertices = GeometryContainerCalculator.getVertexNeighbors(geometryContainer, riverFlowVertex);

        for (VertexWrapper vertex : neighboringVertices.values()) {
            System.out.print(vertex.getHeight() + " ");
            if (vertex.getHeight() < currentElevation){
                return true;
            }
        }
        return false;
    }

    private void setRiverInitialSegmentType(GeometryContainer geometryContainer){
        Map<Integer, SegmentWrapper> segments = geometryContainer.get_segments();
        SegmentTypeWrapper notWater = new SegmentTypeWrapper(NotWater);
        for (SegmentWrapper seg: segments.values()){
            seg.setSegmentTypeWrapper(notWater);
        }
    }
    private void setRiverSegmentType(ArrayList<VertexWrapper> river, GeometryContainer geometryContainer){
        Map<Integer, SegmentWrapper> segments = geometryContainer.get_segments();
        SegmentTypeWrapper water = new SegmentTypeWrapper(Water);
        int firstRiverVertexID;
        int secondRiverVertexID;

        for(int key = 0; key < river.size()-1; key++) {
            firstRiverVertexID = river.get(key).get_id();
            secondRiverVertexID = river.get(key+1).get_id();

            for(SegmentWrapper seg: segments.values()) {
                if (seg.getV1id() == firstRiverVertexID) {
                    if (seg.getV2id() == secondRiverVertexID) {
                        seg.setSegmentTypeWrapper(water);
                    }
                } else if (seg.getV1id() == secondRiverVertexID) {
                    if (seg.getV2id() == firstRiverVertexID) {
                        seg.setSegmentTypeWrapper(water);
                    }
                }
            }
        }
    }
}
