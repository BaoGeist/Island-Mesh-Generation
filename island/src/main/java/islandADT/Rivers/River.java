package islandADT.Rivers;

import islandADT.GeometryContainer;
import islandADT.Wrappers.SegmentTypeWrapper;
import islandADT.Wrappers.SegmentWrapper;
import islandADT.Wrappers.VertexWrapper;

import java.util.ArrayList;
import java.util.Map;

import static islandADT.Wrappers.SegmentTypeWrapper.SegmentType.NotWater;
import static islandADT.Wrappers.SegmentTypeWrapper.SegmentType.Water;

public class River {
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

    // Todo D: make this be able to generate more than 1 spring, (check if highest vertex is already spring, if yes, move on)

    public void generateRiver(GeometryContainer geometryContainer){
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

                while (isEndOfRiver(riverFlowVertex, geometryContainer)){
                    riverFlowVertexID = generate_flow(riverFlowVertex, geometryContainer);
                    riverFlowVertex = vertices.get(riverFlowVertexID);
                    river.add(riverFlowVertex);
                    System.out.println("Next point height = " + riverFlowVertex.getHeight());
                }
                setRiverSegmentType(river, geometryContainer);
                System.out.println("river size = " + river.size());
            }
        }
    }

    private int generate_flow(VertexWrapper v, GeometryContainer geometryContainer) {
        geometryContainer.set_vertexNeighbors(v);

        Map<Integer, VertexWrapper> neighboringVertices = geometryContainer.getVertexNeighbors(v);

        int lowestVertex = 1000;

        for (VertexWrapper vertex : neighboringVertices.values()) {
            lowestVertex = Math.min(vertex.getHeight(), lowestVertex);
        }

        int riverFlowVertexID = 0;
        for (VertexWrapper vertex : neighboringVertices.values()) {
            System.out.println("Neighbor height: " + vertex.getHeight());
            if (vertex.getHeight() == lowestVertex) {
                riverFlowVertexID = vertex.get_id();
                break;
            }
        }
        return riverFlowVertexID;
    }

    private boolean isEndOfRiver(VertexWrapper riverFlowVertex, GeometryContainer geometryContainer){
        int currentElevation = riverFlowVertex.getHeight();
        Map<Integer, VertexWrapper> neighboringVertices = geometryContainer.getVertexNeighbors(riverFlowVertex);

        for (VertexWrapper vertex : neighboringVertices.values()) {
            if (vertex.getHeight() < currentElevation){
                return true;
            }
        }
        return false;
    }

    private void setRiverSegmentType(ArrayList<VertexWrapper> river, GeometryContainer geometryContainer){
        Map<Integer, SegmentWrapper> segments = geometryContainer.get_segments();
        int key = 0;
        SegmentTypeWrapper water = new SegmentTypeWrapper(Water);
        SegmentTypeWrapper notWater = new SegmentTypeWrapper(NotWater);
        int firstRiverVertexID;
        int secondRiverVertexID;

        for (SegmentWrapper seg: segments.values()){
            seg.setSegmentTypeWrapper(notWater);
        }

        for (SegmentWrapper seg: segments.values()){
            if (key >= river.size()-1){
                break;
            }

            firstRiverVertexID = river.get(key).get_id();
            secondRiverVertexID = river.get(key+1).get_id();

            if (seg.getV1id() == firstRiverVertexID){
                if (seg.getV2id() == secondRiverVertexID){
                    seg.setSegmentTypeWrapper(water);
                    key++;
                    System.out.println("segment colored");
                    System.out.println(seg.getSegmentTypeWrapper());
                }
            } else if (seg.getV1id() == secondRiverVertexID){
                if (seg.getV2id() == firstRiverVertexID){
                    seg.setSegmentTypeWrapper(water);
                    key++;
                    System.out.println("segment colored v2");
                    System.out.println(seg.getSegmentTypeWrapper());
                }
            }
        }
    }
}
