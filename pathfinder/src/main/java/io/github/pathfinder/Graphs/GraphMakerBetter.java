package io.github.pathfinder.Graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GraphMakerBetter implements GraphMakerInterface{

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

    @Override
    public GraphADT create_graph() {
        Set<Integer> nodes = new HashSet<>();
        Set<TempEdge> edges = new HashSet<>();

        nodes = populate_nodes();
        edges = populate_edges();

        return populate_graph(nodes, edges);
    }

    private Set<Integer> populate_nodes() {
        Set<Integer> nodes = new HashSet<>();
        int numberOfNodes = 11;
        for(int i = 0; i < numberOfNodes; i++) {
            nodes.add(i);
        }
        return nodes;
    }

    private Set<TempEdge> populate_edges() {
        Set<TempEdge> edges = new HashSet<>();
        edges.add(new TempEdge(0, 0, 1, 1));
        edges.add(new TempEdge(1, 0, 2, 1));
        edges.add(new TempEdge(2, 0, 3, 1));
        edges.add(new TempEdge(3, 1, 4, 1));
        edges.add(new TempEdge(4, 2, 3, 1));
        edges.add(new TempEdge(5, 3, 4, 1));
        edges.add(new TempEdge(6, 4, 5, 1));
        edges.add(new TempEdge(7, 3, 5, 1));
        edges.add(new TempEdge(8, 5, 7, 1));
        edges.add(new TempEdge(9, 6, 5, 1));
        edges.add(new TempEdge(10, 9, 6, 1));
        edges.add(new TempEdge(11, 9, 3, 1));
        edges.add(new TempEdge(12, 9, 8, 1));
        edges.add(new TempEdge(13, 8, 7, 1));
        return edges;
    }

    private GraphADT populate_graph(Set<Integer> nodes, Set<TempEdge> edges) {
        GraphADT graph = new GraphADT();

        for(Integer node: nodes) {
            Map<Integer, Integer> nodeMap = new HashMap<>();
            for(TempEdge<Integer> edge: edges) {
                if(edge.getNode1() == node) {
                    nodeMap.put(edge.getNode2(), edge.id);
                } else if (edge.getNode2() == node) {
                    nodeMap.put(edge.getNode1(), edge.id);
                }
            }
            graph.add_node(node, nodeMap);
        }

        for(TempEdge<Integer> edge: edges) {
            graph.add_edge(edge.getId(), edge.cost);
        }

        return graph;
    }


}
