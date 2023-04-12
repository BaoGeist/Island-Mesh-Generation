package islandADT.Urbanism;

import io.github.pathfinder.Graphs.GraphADT;
import io.github.pathfinder.Paths.Path;
import io.github.pathfinder.Paths.PathFinder;
import io.github.pathfinder.Paths.PathFinderShortest;
import islandADT.Container.GeometryContainer;
import islandADT.GeometryWrappers.PolygonWrapper;
import islandADT.GeometryWrappers.SegmentWrapper;
import islandADT.GeometryWrappers.VertexWrapper;
import islandADT.TypeWrappers.Cities;
import islandADT.TypeWrappers.SegmentTypeWrapper;

import java.util.*;

import static islandADT.Container.GeometryContainerCalculator.getDryLandPolygons;
import static islandADT.Container.GeometryContainerCalculator.getPolygonFromCentroid;
import static islandADT.Utils.MathUtils.distance_between_points;

public class GraphAdapter {

    Set<TempEdge> edges = new HashSet<>();
    Set<Integer> nodes = new HashSet<>();
    GraphADT graph;
    Cities cities;

    public GraphAdapter(GeometryContainer geometryContainer) {
        this.graph = populate_graph(geometryContainer);
    }


    public void adapt_graph(GeometryContainer geometryContainer, int source, int[] sinks) {
        Map<Integer, SegmentWrapper> segments = geometryContainer.get_segments();

        int pathSegmentsIndexes = segments.size() * 2;

        for(int sink: sinks) {
            Path path = request_path_finder_shortest(source, sink);
            List<Integer> path_nodes = path.get_path_integer();

            for(int i = 0; i < path_nodes.size() - 1; i++) {
                SegmentWrapper segment = new SegmentWrapper(pathSegmentsIndexes, path_nodes.get(i), path_nodes.get(i+1));

                SegmentTypeWrapper segment_info = new SegmentTypeWrapper(SegmentTypeWrapper.SegmentType.NotWater);
                segment_info.setFlow(2);
                segment.setSegmentTypeWrapper(segment_info);

                segments.put(pathSegmentsIndexes, segment);
                geometryContainer.add_segment(segment);

                pathSegmentsIndexes++;
            }
        }
    }

    private Path request_path_finder_shortest(int source, int sink) {
        PathFinder pathFinder = new PathFinderShortest(graph);
        return pathFinder.path_find(source, sink);
    }

    private GraphADT populate_graph(GeometryContainer geometryContainer) {
        populate_nodes(geometryContainer);
        populate_edges(geometryContainer);

        GraphADT graph = new GraphADT();

        for (Integer node : nodes) {
            Map<Integer, Integer> nodeMap = new HashMap<>();
            for (TempEdge<Integer> edge : edges) {
                if (edge.getNode1() == node) {
                    nodeMap.put(edge.getNode2(), edge.getId());

                } else if (edge.getNode2() == node) {
                    nodeMap.put(edge.getNode1(), edge.getId());

                }
            }
            graph.add_node(node, nodeMap);
        }

        for (TempEdge<Integer> edge : edges) {
            graph.add_edge(edge.getId(), edge.getCost());
        }

        return graph;
    }

    private void populate_nodes(GeometryContainer geometryContainer) {
        List<PolygonWrapper> polygons = getDryLandPolygons(geometryContainer);

        for(PolygonWrapper polygon: polygons) {
            int centroid_id = polygon.getId_centroid();
            nodes.add(centroid_id);
        }
    }

    private void populate_edges(GeometryContainer geometryContainer) {
        Map<Integer, VertexWrapper> vertices = geometryContainer.get_vertices();
        Map<Integer, PolygonWrapper> polygons = geometryContainer.get_polygons();

        Set<Integer> outside_nodes = new HashSet<>();

        for(Integer centroid_id: nodes) {
            PolygonWrapper polygon = getPolygonFromCentroid(geometryContainer, centroid_id);
            VertexWrapper centroid = vertices.get(centroid_id);

            List<Integer> neighbour_polygons = polygon.get_neighbours();
            for(int neighbour_polygon: neighbour_polygons) {
                int neighbour_id = polygons.get(neighbour_polygon).getId_centroid();
                VertexWrapper neighbour = vertices.get(neighbour_id);

                int edge_id = edges.size();
                RoadEvaluator roadEvaluator = new RoadEvaluator(geometryContainer);
                int weight = roadEvaluator.calculate_road_weight(geometryContainer, polygon, polygons.get(neighbour_polygon), centroid, neighbour);


                edges.add(new TempEdge(edge_id, centroid_id, neighbour_id , weight));
                outside_nodes.add(neighbour_id);
            }
        }
        nodes.addAll(outside_nodes);
    }




}
