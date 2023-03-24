package islandADT.Water;

import islandADT.Generator.RandomSeed;
import islandADT.GeometryContainer;
import islandADT.GeometryContainerCalculator;
import islandADT.GeometryWrappers.PolygonWrapper;
import islandADT.Specifications.IslandSpecifications;
import islandADT.TypeWrappers.SegmentTypeWrapper;
import islandADT.GeometryWrappers.SegmentWrapper;
import islandADT.GeometryWrappers.VertexWrapper;
import islandADT.TypeWrappers.TileTypeWrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static islandADT.GeometryContainerCalculator.*;
import static islandADT.TypeWrappers.SegmentTypeWrapper.SegmentType.NotWater;
import static islandADT.TypeWrappers.SegmentTypeWrapper.SegmentType.Water;
import static islandADT.Utils.MathUtils.distance_between_points;

public class RiverGenerator implements WaterBody{

    int amount_of_rivers = 0;
    public RiverGenerator(IslandSpecifications islandSpecifications) {
        amount_of_rivers = Integer.parseInt(islandSpecifications.getRivers());
    }

    private List<Integer> initSprings(GeometryContainer geometryContainer){
        Map<Integer, VertexWrapper> vertices = geometryContainer.get_vertices();

        List<Integer> highest_points = new ArrayList<>();
        List<Integer> all_points = new ArrayList<>();

        for (VertexWrapper v : vertices.values()){
            all_points.add(v.getHeight());
        }

        for (int i = 0; i < amount_of_rivers+100; i ++){
            int maxIndex = 0;
            for (int j = 1; j < all_points.size(); j++) {
                if (all_points.get(j) > all_points.get(maxIndex)) {
                    maxIndex = j;
                }
            }
            highest_points.add(all_points.remove(maxIndex));
        }
        Collections.shuffle(highest_points);
        return highest_points;
    }


    private void generateSpring(GeometryContainer geometryContainer, List<Integer> randomPoints){
        Map<Integer, VertexWrapper> vertices = geometryContainer.get_vertices();

        int springVertex_id;
        for (VertexWrapper v: vertices.values()){
            if (randomPoints.contains(v.getHeight()) && !v.isSpringVertex() && !v.isRiverVertex()){
                springVertex_id = v.get_id();
                VertexWrapper springVertex = vertices.get(springVertex_id);
                springVertex.setSpringVertex(true);
                springVertex.setRiverVertex(true);
                break;
            }
        }
    }

    public void generate(GeometryContainer geometryContainer) {
        RandomSeed instanceRandom = RandomSeed.getInstance();

        Map<Integer, VertexWrapper> vertices = geometryContainer.get_vertices();

        setRiverInitialSegmentType(geometryContainer);

        List<Integer> randomPoints = initSprings(geometryContainer);

        generateSpring(geometryContainer, randomPoints);

        int counter = 0;

        for (VertexWrapper v : vertices.values()) {
            ArrayList<VertexWrapper> river = new ArrayList<>();
            boolean inNotWater = true;
            if (v.isSpringVertex() && counter < amount_of_rivers) {
                int dischargeLevel = instanceRandom.randomInt(1,3);

                river.add(v);
                v.setFlow(dischargeLevel);

                int riverFlowVertexID = generate_flow(v, geometryContainer);
                VertexWrapper riverFlowVertex = vertices.get(riverFlowVertexID);

                river.add(riverFlowVertex);
                checkForRiverMerge(riverFlowVertex, dischargeLevel);

                boolean isEnd = isEndOfRiver(riverFlowVertex, geometryContainer);

                while (isEnd) {
                    riverFlowVertexID = generate_flow(riverFlowVertex, geometryContainer);
                    riverFlowVertex = vertices.get(riverFlowVertexID);
                    inNotWater = checkIfInWater(geometryContainer, riverFlowVertex);
                    river.add(riverFlowVertex);

                    checkForRiverMerge(riverFlowVertex, dischargeLevel);

                    if (inNotWater) {
                        isEnd = isEndOfRiver(riverFlowVertex, geometryContainer);
                    } else {
                        break;
                    }
                }
                setRiverSegmentType(river, geometryContainer);

                for (VertexWrapper vert: river){
                    System.out.println("River flow = " + vert.getFlow());
                }

                System.out.println("river size = " + river.size());
                if (inNotWater) {
                    createLake(river, geometryContainer);
                    System.out.println("End of river is not in ocean or lake");
                }
                counter++;
                generateSpring(geometryContainer, randomPoints);
            }
        }
    }

    private void checkForRiverMerge(VertexWrapper riverFlowVertex, int dischargeLevel){
        if (riverFlowVertex.isRiverVertex()||riverFlowVertex.isSpringVertex()){
            int previousFlowLevel = riverFlowVertex.getFlow();
            if (previousFlowLevel == 0){
                riverFlowVertex.setFlow(dischargeLevel);
            } else {
                int newFlowLevel = previousFlowLevel + dischargeLevel;
                riverFlowVertex.setFlow(newFlowLevel);
                System.out.println("THERE WAS A MERGE, previous:  " + previousFlowLevel + ", old: " + newFlowLevel);
            }
        } else {
            riverFlowVertex.setRiverVertex(true);
            riverFlowVertex.setFlow(dischargeLevel);
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
        Map<Integer, VertexWrapper> vertices = geometryContainer.get_vertices();

        VertexWrapper endOfRiverVertex = river.get(river.size()-1);
        VertexWrapper secondLastRiverVertex = river.get(river.size()-2);
        List<PolygonWrapper> polygonListWithVertex = getPolygonsContainingVertex(geometryContainer, endOfRiverVertex);

        double[] riverCoords = secondLastRiverVertex.getCoords();
        double furthestDistance = 0;

        for (PolygonWrapper p: polygonListWithVertex){
            double[] polygonCoords = vertices.get(p.getId_centroid()).getCoords();
            double distance = distance_between_points(riverCoords, polygonCoords);
            furthestDistance = Math.max(distance, furthestDistance);
        }

        TileTypeWrapper RiverLake = new TileTypeWrapper("RiverLake");

        for (PolygonWrapper p: polygonListWithVertex){
            double[] polygonCoords = vertices.get(p.getId_centroid()).getCoords();
            double distance = distance_between_points(riverCoords, polygonCoords);
            if (distance == furthestDistance){
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
        Map<Integer, VertexWrapper> vertices = geometryContainer.get_vertices();

        int firstRiverVertexID;
        int secondRiverVertexID;

        for(int key = 0; key < river.size()-1; key++) {

            firstRiverVertexID = river.get(key).get_id();
            secondRiverVertexID = river.get(key+1).get_id();

            for(SegmentWrapper seg: segments.values()) {
                if (seg.getV1id() == firstRiverVertexID) {
                    if (seg.getV2id() == secondRiverVertexID) {
                        SegmentTypeWrapper water = new SegmentTypeWrapper(Water);
                        seg.setSegmentTypeWrapper(water);
                        int v1Flow = vertices.get(seg.getV1id()).getFlow();
                        int v2Flow = vertices.get(seg.getV2id()).getFlow();

                        int segFlow = Math.min(v1Flow, v2Flow);
                        System.out.println("Segment flow = " + segFlow);

                        seg.getSegmentTypeWrapper().setFlow(segFlow);
                        break;
                    }
                } else if (seg.getV1id() == secondRiverVertexID) {
                    if (seg.getV2id() == firstRiverVertexID) {
                        SegmentTypeWrapper water = new SegmentTypeWrapper(Water);
                        seg.setSegmentTypeWrapper(water);
                        int v1Flow = vertices.get(seg.getV1id()).getFlow();
                        int v2Flow = vertices.get(seg.getV2id()).getFlow();

                        int segFlow = Math.min(v1Flow, v2Flow);
                        System.out.println("Segment flow = " + segFlow);

                        seg.getSegmentTypeWrapper().setFlow(segFlow);

                        break;
                    }
                }
            }
        }
    }
}
