package io.github.pathfinder.Graphs;

import io.github.pathfinder.Graphs.Parts.Edge;
import io.github.pathfinder.Graphs.Parts.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        for(Node n: adj) {
            if(n.getId() == intNode) {
                return n;
            }
        }
        return null;
    }

    public List<Node> get_adjacency_list() {
        List<Node> adj = graph.get_adjacency_list();
        List<Node> copyadj = new ArrayList<>();
        for(Node node: adj) {
            Node newNode = new Node(node.getId());
            newNode.set_neighbours(node.getNeighbours());
            newNode.setCost(node.getCost());
            copyadj.add(newNode);
        }
        return copyadj;
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

    public boolean contains_edge(int node1, int node2) {
        Node node1Object = get_node_from_int(node1);

        Map<Integer, Integer> neighbours = node1Object.getNeighbours();

        return neighbours.containsKey(node2);
    }

    public boolean contains_node(int node_id) {
        List<Node> adj = graph.get_adjacency_list();

        List<Integer> adj_ids = adj.stream()
                .map(Node::getId)
                .collect(Collectors.toList());

        return adj_ids.contains(node_id);
    }

    public boolean contains_node(Node node) {
        List<Node> adj = graph.get_adjacency_list();

        for(Node node1 : adj) {
            if(node.equals(node1)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        if(graph.getEdges() == null && graph.get_adjacency_list() == null) { return true; }
        return false;
    }

    public List<Integer> getListNodes() {
        List<Node> adj = graph.get_adjacency_list();

        List<Integer> adj_ids = adj.stream()
                .map(Node::getId)
                .collect(Collectors.toList());

        return adj_ids;
    }


}
