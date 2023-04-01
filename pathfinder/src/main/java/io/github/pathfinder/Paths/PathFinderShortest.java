package io.github.pathfinder.Paths;

import io.github.pathfinder.Graphs.Parts.Edge;
import io.github.pathfinder.Graphs.GraphADT;
import io.github.pathfinder.Graphs.GraphManager;
import io.github.pathfinder.Graphs.Parts.Node;
import io.github.pathfinder.Graphs.Parts.NodeComparator;

import java.util.*;

public class PathFinderShortest implements PathFinder {
    GraphADT graph;
    GraphManager graphManager;

    public PathFinderShortest(GraphADT graph) {
        this.graph = graph;
        this.graphManager = new GraphManager(graph);

    }

    public Path path_find(Integer source, Integer sink) {

        int adj_size = graphManager.get_adjacency_size();

        int[] path = new int[adj_size];
        int[] cost = new int[adj_size];
        for(int i = 0; i < adj_size; i++) {
            cost[i] = Integer.MAX_VALUE;
        }
        path[source] = source;
        cost[source] = 0;
        graphManager.get_node_from_int(source).setCost(0);

        PriorityQueue<Node> pq = new PriorityQueue<Node>(new NodeComparator());
        pq.add(graphManager.get_node_from_int(source));

        while(!pq.isEmpty()) {
            Node m = pq.remove();
            Map<Integer, Integer> neighbours = m.getNeighbours();
            for(Integer n: neighbours.keySet()) {
                Edge edge = graphManager.get_edge_from_int(neighbours.get(n));
                if(cost[m.getId()] + edge.getWeight() < cost[n]) {
                    path[n] = m.getId();
                    cost[n] = edge.getWeight() + cost[m.getId()];
                    graphManager.get_node_from_int(n).setCost(cost[n]);
                    pq.add(graphManager.get_node_from_int(n));
                }
            }
            System.out.println(pq);
        }
        return path_to_string(path, sink);
    }

    private Path path_to_string(int[] path, int sink) {
        Path pathObj;
        if(path[sink] == sink) {
            pathObj = new Path();
            pathObj.addPath(graphManager.get_node_from_int(sink));
            return pathObj;
        }
        else {
            pathObj = path_to_string(path, path[sink]);
            pathObj.addPath(graphManager.get_node_from_int(sink));
        }
        return pathObj;
    }
}
