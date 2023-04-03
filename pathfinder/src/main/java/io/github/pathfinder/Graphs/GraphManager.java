package io.github.pathfinder.Graphs;

import io.github.pathfinder.Graphs.Parts.Edge;
import io.github.pathfinder.Graphs.Parts.Node;

import java.util.List;

public class GraphManager {
    GraphADT graph;

    public GraphManager(GraphADT graph) {
        this.graph = graph;
    }

    public int get_adjacency_size() {
        return graph.get_adjacency_list().size();
    }

    public Node get_node_from_int(int intNode) {
        List<Node> adj = graph.get_adjacency_list();
        return adj.get(intNode);
    }

    public Edge get_edge_from_int(int intEdge) {
        List<Edge> edges = graph.getEdges();
        return edges.get(intEdge);
    }

    public int get_edge_number() {
        return graph.getEdges().size();
    }

    public int get_node_number() {
        return graph.get_adjacency_list().size();
    }
}
