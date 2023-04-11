package io.github.pathfinder;

import io.github.pathfinder.Graphs.*;
import io.github.pathfinder.Graphs.Parts.Node;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class GraphMakerBetterTest {
    GraphADT testGraph;
    GraphManager graphManager;
    private void set_up() {
        GraphMaker graphMaker = new GraphMaker();
        testGraph = graphMaker.create_graph();

        graphManager = new GraphManager(testGraph);
    }

    @Test
    public void crosscheck_test() {
        set_up();

        GraphMaker graphMaker = new GraphMaker();
        GraphADT graphCC = graphMaker.create_graph();

        GraphManager graphManagerCC = new GraphManager(graphCC);

        List<Node> adj = graphManager.get_adjacency_list();
        List<Node> adjCC = graphManagerCC.get_adjacency_list();

        for(int i = 0; i < adj.size(); i++) {
            assertEquals(adj.get(i), adjCC.get(i));
        }
    }

    @Test
    public void new_node_test() {
        GraphMaker graphMaker = new GraphMaker();
        graphMaker.populate_nodes();
        GraphManager tempManager = new GraphManager(graphMaker.populate_graph());
        int pre_nodes = tempManager.get_node_number();

        graphMaker.new_node(14);
        tempManager = new GraphManager(graphMaker.populate_graph());
        int post_nodes = tempManager.get_node_number();

        assertEquals(pre_nodes + 1, post_nodes);
    }

    @Test
    public void new_edge_test() {
        GraphMaker graphMaker = new GraphMaker();
        graphMaker.populate_edges();
        GraphManager tempManager = new GraphManager(graphMaker.populate_graph());
        int pre_edges = tempManager.get_edge_number();

        graphMaker.new_edge(14, 0, 13, 1);
        tempManager = new GraphManager(graphMaker.populate_graph());
        int post_edges = tempManager.get_edge_number();

        assertEquals(pre_edges + 1, post_edges);
    }

    @Test
    public void populate_nodes_test() {
        set_up();

        assertEquals(10, graphManager.get_node_number());
    }

    @Test
    public void populate_edges_test() {
        set_up();

        assertEquals(14, graphManager.get_edge_number());

    }

    @Test
    public void populate_graph_test() {
        set_up();

        assertFalse(graphManager.isEmpty());
    }
}
