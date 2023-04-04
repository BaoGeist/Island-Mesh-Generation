package io.github.pathfinder;

import io.github.pathfinder.Graphs.GraphADT;
import io.github.pathfinder.Paths.Path;
import io.github.pathfinder.Paths.PathFinderShortest;
import io.github.pathfinder.Paths.PathFinder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PathFinderShortestTest {

    @Test
    public void cardinality_zero_paths_test() {
        GraphMaker graphMaker = new GraphMaker();
        graphMaker.populate_edges();

        graphMaker.new_node(10);

        graphMaker.populate_graph();

        GraphADT graph = graphMaker.create_graph();

        PathFinder pathFinder = new PathFinderShortest(graph);
        Path no_path = pathFinder.path_find(0, 10);

        assertEquals(0, no_path.length());
    }

    @Test
    public void cardinality_one_path_test() {
        GraphMaker graphMaker = new GraphMaker();
        graphMaker.populate_edges();
        graphMaker.populate_graph();

        GraphADT graph = graphMaker.create_graph();

        PathFinder pathFinder = new PathFinderShortest(graph);
        Path no_path = pathFinder.path_find(0, 1);

        System.out.println(no_path.length());

        assertEquals(1, no_path.length());
    }

    @Test
    public void cardinality_multiple_path_test() {
        GraphMaker graphMaker = new GraphMaker();
        graphMaker.populate_edges();
        graphMaker.populate_graph();

        GraphADT graph = graphMaker.create_graph();

        PathFinder pathFinder = new PathFinderShortest(graph);
        Path no_path = pathFinder.path_find(0, 9);

        assertTrue(no_path.length() > 1);
    }
}
