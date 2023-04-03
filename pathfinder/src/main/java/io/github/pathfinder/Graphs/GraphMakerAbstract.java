package io.github.pathfinder.Graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class GraphMakerAbstract implements GraphMakerInterface{

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

    Set<Integer> nodes = new HashSet<>();
    Set<TempEdge> edges = new HashSet<>();

    /***
     * Adds a new edge to the graph provided an integer id, node1 id, node2 id and weight. Will also add any nodes used into the graph
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

    /***
     * Adds a new node to the graph
     * @param id
     */
    public void new_node(int id) {
        nodes.add(id);
    }

    public abstract void populate_edges();

    public abstract void populate_nodes();

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

}
