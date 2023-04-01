package io.github.pathfinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GraphADT {
    private List<Node> adjacency_list;

    public GraphADT() {
        this.adjacency_list = new ArrayList<>();
    }

    public void add_node(Map<Integer, Integer> neighbours) {
        Node new_node = new Node();
        new_node.set_neighbours(neighbours);
        adjacency_list.add(new_node);
    }

    public List<Node> get_adjacency_list() {
        return adjacency_list;
    }
}
