package io.github.pathfinder;

import io.github.pathfinder.Graphs.GraphADT;
import io.github.pathfinder.Graphs.GraphMakerBasic;
import io.github.pathfinder.Graphs.GraphMakerInterface;
import io.github.pathfinder.Graphs.GraphManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class GraphMakerBasicTest {
    GraphADT testGraph;
    GraphManager graphManager;
    private void set_up() {
        GraphMakerInterface graphMaker = new GraphMakerBasic();
        testGraph = graphMaker.create_graph();

        graphManager = new GraphManager(testGraph);
    }

    @Test
    public void edges_test() {
        set_up();

        assertEquals(14, graphManager.get_edge_number());

        int[] head_nodes = {0,0,0,1,1,2,2,3,3,3,3,3,4,4,4,5,5,5,5,6,6,7,7,8,8,9,9,9};
        int[] tail_nodes = {1,2,3,0,4,0,3,0,2,4,9,5,1,3,4,3,4,7,6,9,5,8,5,9,7,3,6,8};

        for(int i = 0; i < head_nodes.length; i++) {
            assertTrue(graphManager.contains_edge(head_nodes[i], tail_nodes[i]));
        }

    }

    @Test
    public void nodes_test() {
        set_up();

        assertEquals(10, graphManager.get_node_number());

        int[] node_ids = {0,1,2,3,4,5,6,7,8,9};

        for(int i = 0; i < node_ids.length; i++) {
            System.out.println(graphManager.contains_node(node_ids[i]));
        }
    }

}
