package io.github.pathfinder;

import io.github.pathfinder.Graphs.GraphADT;
import io.github.pathfinder.Graphs.GraphMaker;
import io.github.pathfinder.Paths.Path;
import io.github.pathfinder.Paths.PathFinderShortest;
import io.github.pathfinder.Paths.PathFinder;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PathFinderShortestTest {

    @Test
    public void cardinality_zero_paths_test() {
        GraphMaker graphMaker = new GraphMaker();
        graphMaker.populate_edges_example();

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
        graphMaker.populate_edges_example();
        graphMaker.populate_graph();

        GraphADT graph = graphMaker.create_graph();

        PathFinder pathFinder = new PathFinderShortest(graph);
        Path no_path = pathFinder.path_find(0, 1);

        assertEquals(1, no_path.length());
    }

    @Test
    public void cardinality_multiple_path_test() {
        GraphMaker graphMaker = new GraphMaker();
        graphMaker.populate_edges_example();
        graphMaker.populate_graph();

        GraphADT graph = graphMaker.create_graph();

        PathFinder pathFinder = new PathFinderShortest(graph);
        Path no_path = pathFinder.path_find(0, 9);

        assertTrue(no_path.length() > 1);
    }

    @Test
    public void verified_test() {
        GraphMaker graphMaker = new GraphMaker();

        graphMaker.new_node(0);
        graphMaker.new_node(1);
        graphMaker.new_node(2);
        graphMaker.new_node(3);
        graphMaker.new_node(4);
        graphMaker.new_node(5);
        graphMaker.new_node(6);
        graphMaker.new_node(7);
        graphMaker.new_node(8);
        graphMaker.new_node(9);

        graphMaker.new_edge(0, 0, 3, 9);
        graphMaker.new_edge(1, 0, 7, 12);
        graphMaker.new_edge(2, 0, 4, 13);
        graphMaker.new_edge(3, 0, 9, 11);
        graphMaker.new_edge(4, 7, 2, 7);
        graphMaker.new_edge(5, 2, 3, 5);
        graphMaker.new_edge(6, 3, 8, 1);
        graphMaker.new_edge(7, 8, 1, 4);
        graphMaker.new_edge(8, 8, 5, 7);
        graphMaker.new_edge(9, 1, 5, 2);
        graphMaker.new_edge(10, 5, 9, 8);
        graphMaker.new_edge(11, 5, 6, 3);
        graphMaker.new_edge(12, 6, 9, 4);

        GraphADT graph = graphMaker.populate_graph();

        PathFinder pathFinder = new PathFinderShortest(graph);

        List<Integer> verified_path = new ArrayList<>();
        verified_path.add(3);
        verified_path.add(8);
        verified_path.add(3);
        verified_path.add(3);
        verified_path.add(0);
        verified_path.add(1);
        verified_path.add(5);
        verified_path.add(2);
        verified_path.add(3);
        verified_path.add(6);

        int source = 3;
        for(int i = 0; i < 10; i++) {
            Path path = pathFinder.path_find(source, i);
            String[] path_string = path.toString().strip().split(" ");
            if(i != source) {
                assertEquals(verified_path.get(i), Integer.parseInt(path_string[path_string.length-2]));
            } else {
                assertEquals(Integer.parseInt(path_string[path_string.length-1]), source);
            }
        }


    }
}
