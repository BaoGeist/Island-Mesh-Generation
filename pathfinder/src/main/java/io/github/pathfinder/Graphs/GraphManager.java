package io.github.pathfinder.Graphs;

import io.github.pathfinder.Graphs.Parts.Edge;
import io.github.pathfinder.Graphs.Parts.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GraphManager {
    private GraphADT graph;

    public GraphManager(GraphADT graph) {
        this.graph = graph;
    }

    /**
     * @return size of the adjacency list in terms of nodes
     */
    public int get_adjacency_size() {
        return graph.get_adjacency_list().size();
    }

    /**
     * @param intNode
     * @return the node with the corresponding id to the one provided
     */
    public Node get_node_from_int(int intNode) {
        List<Node> adj = graph.get_adjacency_list();
        for(Node n: adj) {
            if(n.getId() == intNode) {
                return n;
            }
        }
        return null;
    }

    /**
     * @return a deep copy of the adjacency list
     */
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

    /**
     * @param intEdge
     * @return returns a Edge object provided its index
     */
    public Edge get_edge_from_int(int intEdge) {
        List<Edge> edges = graph.getEdges();
        for(Edge edge: edges) {
            if(edge.getId() == intEdge) {return edge;}
        }
        return null;
    }

    /**
     * @return the number of edges
     */
    public int get_edge_number() {
        return graph.getEdges().size();
    }

    /**
     * @return the number of nodes
     */
    public int get_node_number() {
        return graph.get_adjacency_list().size();
    }

    /**
     * @param node1
     * @param node2
     * @return boolean for if an edge is in the graph given its head and tail nodes
     */
    public boolean contains_edge(int node1, int node2) {
        Node node1Object = get_node_from_int(node1);

        Map<Integer, Integer> neighbours = node1Object.getNeighbours();

        return neighbours.containsKey(node2);
    }

    /**
     * @param node_id
     * @return boolean for if a node exists given its index
     */
    public boolean contains_node(int node_id) {
        List<Node> adj = graph.get_adjacency_list();

        List<Integer> adj_ids = adj.stream()
                .map(Node::getId)
                .collect(Collectors.toList());

        return adj_ids.contains(node_id);
    }

    /**
     * @param node
     * @return boolean for if a note exists given the node
     */
    public boolean contains_node(Node node) {
        List<Node> adj = graph.get_adjacency_list();

        for(Node node1 : adj) {
            if(node.equals(node1)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return if GraphADT is empty
     */
    public boolean isEmpty() {
        return graph.isEmpty();
    }

    /**
     * @return list of nodes represented by their ids
     */
    public List<Integer> getListNodes() {
        List<Node> adj = graph.get_adjacency_list();

        List<Integer> adj_ids = adj.stream()
                .map(Node::getId)
                .collect(Collectors.toList());

        return adj_ids;
    }


}
