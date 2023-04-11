package io.github.pathfinder;

import io.github.pathfinder.Graphs.GraphADT;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GraphMaker {

    public class TempEdge<Integer> {
        private Integer id;
        private Integer node1;
        private Integer node2;
        private Integer cost;

        public TempEdge(Integer id, Integer node1, Integer node2, Integer cost) {
            this.id = id;
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }

        public Integer getId() {
            return id;
        }

        public Integer getNode1() {
            return node1;
        }

        public Integer getNode2() {
            return node2;
        }

        public Integer getCost() {
            return cost;
        }
    }

    Set<TempEdge> edges = new HashSet<>();
    Set<Integer> nodes = new HashSet<>();

    public void new_edge(int id, int node1, int node2, int weight) {
        edges.add(new TempEdge(id, node1, node2 , weight));
        nodes.add(node1);
        nodes.add(node2);
    }

    public void new_node(int id) {
        nodes.add(id);
    }

    public GraphADT populate_graph() {
        GraphADT graph = new GraphADT();

        for(Integer node: nodes) {
            Map<Integer, Integer> nodeMap = new HashMap<>();
            for(TempEdge<Integer> edge: edges) {
                if(edge.getNode1() == node) {
                    nodeMap.put(edge.getNode2(), edge.getId());
                } else if (edge.getNode2() == node) {
                    nodeMap.put(edge.getNode1(), edge.getId());
                }
            }
            graph.add_node(node, nodeMap);
        }

        for(TempEdge<Integer> edge: edges) {
            graph.add_edge(edge.getId(), edge.getCost());
        }

        return graph;
    }


    public GraphADT create_graph() {

        populate_edges();
        populate_nodes();


        return populate_graph();
    }

    public void populate_nodes() {
        int numberOfNodes = 10;
        for(int i = 0; i < numberOfNodes; i++) {
            new_node(i);
        }
    }

    public void populate_edges() {
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
