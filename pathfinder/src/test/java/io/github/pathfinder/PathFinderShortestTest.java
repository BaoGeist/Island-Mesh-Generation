package io.github.pathfinder;

import io.github.pathfinder.Graphs.GraphMakerBetter;
import io.github.pathfinder.Graphs.GraphManager;
import org.junit.jupiter.api.Test;

import static io.github.pathfinder.Assertions.assertEquals;

public class PathFinderShortestTest {
    @Test
    public void cardinality_zero_paths_test() {
        GraphMakerBetter graphMaker = new GraphMakerBetter();
        graphMaker.populate_edges();
        GraphManager tempManager = new GraphManager(graphMaker.populate_graph());
        int pre_nodes = tempManager.get_node_number();

        graphMaker.new_node(14);
        tempManager = new GraphManager(graphMaker.populate_graph());
        int post_nodes = tempManager.get_node_number();

        assertEquals(pre_nodes + 1, post_nodes);
    }
}
