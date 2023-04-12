package io.github.pathfinder.Graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GraphMaker {

    private Set<TempEdge> edges = new HashSet<>();
    private Set<Integer> nodes = new HashSet<>();

    /**
     * adds a new edge to the GraphMaker set of edges, creates nodes if they are not in the set of nodes
     * @param id
     * @param node1
     * @param node2
     * @param weight
     */
    public void new_edge(int id, int node1, int node2, int weight) {
        edges.add(new TempEdge(id, node1, node2 , weight));
        nodes.add(node1);
        nodes.add(node2);
    }

    /**
     * adds a node to the GraphMaker set of nodes
     * @param id
     */
    public void new_node(int id) {
        nodes.add(id);
    }

    /**
     * creates the GraphADT with nodes and edges already in the sets
     * @return
     */
    public GraphADT populate_graph() {
        GraphADT graph = new GraphADT();

        for(Integer node: nodes) {
            Map<Integer, Integer> nodeMap = new HashMap<>();
            for(TempEdge edge: edges) {
                if(edge.getNode1() == node) {
                    nodeMap.put(edge.getNode2(), edge.getId());
                } else if (edge.getNode2() == node) {
                    nodeMap.put(edge.getNode1(), edge.getId());
                }
            }
            graph.add_node(node, nodeMap);
        }

        for(TempEdge edge: edges) {
            graph.add_edge(edge.getId(), edge.getCost());
        }

        return graph;
    }

    /**
     * Sample run of the GraphMaker class
     * @return a viable GraphADT to work with
     */
    public GraphADT create_graph() {

        populate_edges_example();
        populate_nodes_example();


        return populate_graph();
    }

    public void populate_nodes_example() {
        int numberOfNodes = 10;
        for(int i = 0; i < numberOfNodes; i++) {
            new_node(i);
        }
    }

    public void populate_edges_example() {
        new_edge(0, 0, 1, 1);
        new_edge(1, 0, 2, 1);
        new_edge(2, 0, 3, 1);
        new_edge(3, 1, 4, 1);
        new_edge(4, 2, 3, 1);
        new_edge(5, 3, 4, 1);
        new_edge(6, 4, 5, 1);
        new_edge(7, 3, 5, 1);
        new_edge(8, 5, 7, 1);
        new_edge(9, 6, 5, 1);
        new_edge(10, 9, 6, 1);
        new_edge(11, 9, 3, 1);
        new_edge(12, 9, 8, 1);
        new_edge(13, 8, 7, 1);
    }


}
