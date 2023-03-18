package islandADT.Water;

import islandADT.GeometryContainer;
import islandADT.GeometryContainerCalculator;
import islandADT.GeometryWrappers.PolygonWrapper;
import islandADT.TypeWrappers.SegmentTypeWrapper;
import islandADT.GeometryWrappers.SegmentWrapper;
import islandADT.GeometryWrappers.VertexWrapper;

import java.util.ArrayList;
import java.util.Map;

import static islandADT.TypeWrappers.SegmentTypeWrapper.SegmentType.NotWater;
import static islandADT.TypeWrappers.SegmentTypeWrapper.SegmentType.Water;

public class RiverGenerator implements WaterBody{
    private void generateSpring(GeometryContainer geometryContainer){

        Map<Integer, VertexWrapper> vertices = geometryContainer.get_vertices();

        int highest_point = 0;
        int springVertex_id = 0;
        for (VertexWrapper v : vertices.values()){
            highest_point = Math.max(v.getHeight(), highest_point);
        }
        for (VertexWrapper v: vertices.values()){
            if (v.getHeight() == highest_point && v.isSpringVertex() == false){
                springVertex_id = v.get_id();
                break;
            }
        }
        VertexWrapper springVertex = vertices.get(springVertex_id);
        springVertex.setSpringVertex(true);
    }

    public void generate(GeometryContainer geometryContainer){
        generateSpring(geometryContainer);

        Map<Integer, VertexWrapper> vertices = geometryContainer.get_vertices();

        for (VertexWrapper v: vertices.values()){
            ArrayList<VertexWrapper> river = new ArrayList<>();
            if (v.isSpringVertex()){
                river.add(v);
                System.out.println("First point height = "+ v.getHeight());
                int riverFlowVertexID = generate_flow(v, geometryContainer);
                VertexWrapper riverFlowVertex = vertices.get(riverFlowVertexID);
                System.out.println("Second point height = " + riverFlowVertex.getHeight());
                river.add(riverFlowVertex);

                boolean inNotOcean = true;

                boolean isEnd = isEndOfRiver(riverFlowVertex, geometryContainer);
                while (isEnd){
                    riverFlowVertexID = generate_flow(riverFlowVertex, geometryContainer);
                    riverFlowVertex = vertices.get(riverFlowVertexID);

                    inNotOcean = checkIfInOcean(geometryContainer, riverFlowVertex);

                    if (!inNotOcean){
                        isEnd = false;
                    } else {
                        river.add(riverFlowVertex);
                        isEnd = isEndOfRiver(riverFlowVertex, geometryContainer);
                        System.out.println("Next point height = " + riverFlowVertex.getHeight());
                    }
                }
                setRiverSegmentType(river, geometryContainer);
                System.out.println("river size = " + river.size());
                if (inNotOcean){
                    createLake(river, geometryContainer);
                }
            }
        }
    }

    private boolean checkIfInOcean(GeometryContainer geometryContainer, VertexWrapper riverFlowVertex){
        Map<Integer, VertexWrapper> neighboringVertices = GeometryContainerCalculator.getVertexNeighbors(geometryContainer, riverFlowVertex);
        boolean somebool = true;
        for (VertexWrapper vertex : neighboringVertices.values()){
            if (!vertex.isLandornah()){
                somebool = false;
                break;
            }
        }
        return somebool;
    }
    private void createLake(ArrayList<VertexWrapper> river, GeometryContainer geometryContainer) {
        Map<Integer, PolygonWrapper> polygons = geometryContainer.get_polygons();
        VertexWrapper endOfRiverVertex = river.get(river.size()-1);

        ArrayList<PolygonWrapper> neighboringPolygons = new ArrayList<>();
        for (PolygonWrapper p : polygons.values()){

        }

    }

    private int generate_flow(VertexWrapper v, GeometryContainer geometryContainer) {
        Map<Integer, VertexWrapper> neighboringVertices = GeometryContainerCalculator.getVertexNeighbors(geometryContainer, v);

        int lowestVertex = 1000;

        for (VertexWrapper vertex : neighboringVertices.values()) {
            lowestVertex = Math.min(vertex.getHeight(), lowestVertex);
        }

        for (VertexWrapper vertex : neighboringVertices.values()) {
            System.out.println("Neighbor height: " + vertex.getHeight());
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
        System.out.println("Next line and no lower elevation");
        return false;
    }

    private void setRiverSegmentType(ArrayList<VertexWrapper> river, GeometryContainer geometryContainer){
        Map<Integer, SegmentWrapper> segments = geometryContainer.get_segments();
        SegmentTypeWrapper water = new SegmentTypeWrapper(Water);
        SegmentTypeWrapper notWater = new SegmentTypeWrapper(NotWater);
        int firstRiverVertexID;
        int secondRiverVertexID;

        for (SegmentWrapper seg: segments.values()){
            seg.setSegmentTypeWrapper(notWater);
        }

        for(int key = 0; key < river.size()-1; key++) {
            firstRiverVertexID = river.get(key).get_id();
            secondRiverVertexID = river.get(key+1).get_id();

            for(SegmentWrapper seg: segments.values()) {
                if (seg.getV1id() == firstRiverVertexID) {
                    if (seg.getV2id() == secondRiverVertexID) {
                        seg.setSegmentTypeWrapper(water);
                        System.out.println("segment colored");
                    }
                } else if (seg.getV1id() == secondRiverVertexID) {
                    if (seg.getV2id() == firstRiverVertexID) {
                        seg.setSegmentTypeWrapper(water);
                        System.out.println("segment colored v2");
                    }
                }
            }
        }
    }
}
