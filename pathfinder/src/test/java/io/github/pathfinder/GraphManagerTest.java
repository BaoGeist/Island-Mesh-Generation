package io.github.pathfinder;

import io.github.pathfinder.Graphs.GraphADT;
import io.github.pathfinder.Graphs.GraphManager;
import io.github.pathfinder.Graphs.Parts.Node;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GraphManagerTest {
    GraphManager graphManager;
    GraphADT testGraph;
    private void set_up() {
        GraphMaker graphMaker = new GraphMaker();
        testGraph = graphMaker.create_graph();

        graphManager = new GraphManager(testGraph);
    }

    @Test
    public void adjacency_test() {
        set_up();

        assertEquals(10, graphManager.get_adjacency_size());

        List<Node> adj = graphManager.get_adjacency_list();

        Random random = new Random();

        graphManager.contains_node(adj.get(random.nextInt(adj.size())));
    }

    @Test void empty_test() {
        GraphADT testGraph = new GraphADT();
        GraphManager testManager = new GraphManager(testGraph);
        assertTrue(testManager.isEmpty());
    }


}
