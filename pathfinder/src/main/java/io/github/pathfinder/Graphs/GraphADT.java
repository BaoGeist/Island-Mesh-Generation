package io.github.pathfinder.Graphs;

import io.github.pathfinder.Graphs.Parts.Edge;
import io.github.pathfinder.Graphs.Parts.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GraphADT {
    private List<Node> adjacency_list;
    private List<Edge> edges;

    public GraphADT() {
        this.adjacency_list = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    protected void add_node(Integer id, Map<Integer, Integer> neighbours) {
        Node newNode = new Node(id);
        newNode.set_neighbours(neighbours);
        adjacency_list.add(newNode);
    }

    protected void add_edge(int id, int weight) {
        Edge newEdge = new Edge(id, weight);
        edges.add(newEdge);
    }

    protected List<Node> get_adjacency_list() {
        return adjacency_list.isEmpty() ? null : adjacency_list;
    }

    protected List<Edge> getEdges() {
        return edges.isEmpty() ? null : edges;
    }

    @Override
    public String toString() {
        String returnString = new String();
        returnString += "Nodes: \n";
        for(Node node:adjacency_list) {
            returnString += node.toString() + "\n";
        }

        returnString += "Edges: \n";
        for(Edge edge: edges) {
            returnString += edge.toString() + "\n";
        }
        return returnString;
    }
}
