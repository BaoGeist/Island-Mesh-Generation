package io.github.pathfinder;

import io.github.pathfinder.Graphs.*;
import io.github.pathfinder.Graphs.Parts.Node;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.github.pathfinder.Assertions.assertEquals;

public class GraphMakerBetterTest {
    GraphADT testGraph;
    GraphManager graphManager;
    private void set_up() {
        GraphMakerInterface graphMaker = new GraphMakerBetter();
        testGraph = graphMaker.create_graph();

        graphManager = new GraphManager(testGraph);
    }

    @Test
    public void crosscheck_test() {
        set_up();

        GraphMakerInterface graphMaker = new GraphMakerBasic();
        GraphADT graphCC = graphMaker.create_graph();

        GraphManager graphManagerCC = new GraphManager(graphCC);

        List<Node> adj = graphManager.get_adjacency_list();
        List<Node> adjCC = graphManagerCC.get_adjacency_list();

        for(int i = 0; i < adj.size(); i++) {
            assertEquals(adj.get(i), adjCC.get(i));
        }
    }
}
