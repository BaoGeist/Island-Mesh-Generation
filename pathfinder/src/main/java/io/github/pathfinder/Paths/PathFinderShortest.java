package io.github.pathfinder.Paths;

import io.github.pathfinder.Graphs.Parts.Edge;
import io.github.pathfinder.Graphs.GraphADT;
import io.github.pathfinder.Graphs.GraphManager;
import io.github.pathfinder.Graphs.Parts.Node;

import java.util.*;

public class PathFinderShortest implements PathFinder {
    private GraphADT graph;
    private GraphManager graphManager;

    public PathFinderShortest(GraphADT graph) {
        this.graph = graph;
        this.graphManager = new GraphManager(graph);

    }

    /**
     * @param source
     * @param sink
     * @return a Path object for the given source and sink node
     */
    public Path path_find(Integer source, Integer sink) {

        int adj_size = graphManager.get_adjacency_size();
        List<Integer> node_ids = graphManager.getListNodes();

        Map<Integer, Integer> path = new HashMap<>();
        Map<Integer, Integer> cost = new HashMap<>();
        for(int i = 0; i < adj_size; i++) {
            path.put(node_ids.get(i), -1);
            cost.put(node_ids.get(i), Integer.MAX_VALUE);
        }

        path.put(source, source);
        cost.put(source, 0);
        graphManager.get_node_from_int(source).setCost(0);

        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.add(graphManager.get_node_from_int(source));

        Set<Integer> visited = new HashSet<>();
        visited.add(source);

        while(!pq.isEmpty()) {
            Node m = pq.remove();
            visited.add(m.getId());
            Map<Integer, Integer> neighbours = m.getNeighbours();

            for(Integer n: neighbours.keySet()) {
                if(!visited.contains(n)) {
                    Edge edge = graphManager.get_edge_from_int(neighbours.get(n));
                    if(cost.get(m.getId()) + edge.getWeight() < cost.get(n)) {
                        path.put(n, m.getId());
                        cost.put(n, edge.getWeight() + cost.get(m.getId()));
                        graphManager.get_node_from_int(n).setCost(cost.get(n));
                        pq.add(graphManager.get_node_from_int(n));
                    }
                }
            }
        }
        return path_to_object(path, sink);
    }

    private Path path_to_object(Map<Integer, Integer> path, int sink) {
        Path pathObj;
        if(path.get(sink) == sink) {
            pathObj = new Path();
            pathObj.addPath(graphManager.get_node_from_int(sink));
            return pathObj;
        } else if(path.get(sink) == -1) {
            return new Path();
        }
        else {
            pathObj = path_to_object(path, path.get(sink));
            pathObj.addPath(graphManager.get_node_from_int(sink));
        }
        return pathObj;
    }
}
