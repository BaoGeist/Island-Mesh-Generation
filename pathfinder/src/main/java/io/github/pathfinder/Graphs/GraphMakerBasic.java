package io.github.pathfinder.Graphs;

import java.util.HashMap;
import java.util.Map;

public class GraphMakerBasic implements GraphMakerInterface{
    @Override
    public GraphADT create_graph(){
        GraphADT graph = new GraphADT();

        graph.add_edge(0, 1);
        graph.add_edge(1, 1);
        graph.add_edge(2, 1);
        graph.add_edge(3, 1);
        graph.add_edge(4, 1);
        graph.add_edge(5, 1);
        graph.add_edge(6, 1);
        graph.add_edge(7, 1);
        graph.add_edge(8, 1);
        graph.add_edge(9, 1);
        graph.add_edge(10, 1);
        graph.add_edge(11, 1);
        graph.add_edge(12, 5);
        graph.add_edge(13, 1);

        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 0);
        map.put(2, 1);
        map.put(3, 2);
        graph.add_node(0, map);

        Map<Integer, Integer> map1 = new HashMap<>();
        map1.put(0, 0);
        map1.put(4, 3);
        graph.add_node(1, map1);

        Map<Integer, Integer> map2 = new HashMap<>();
        map2.put(0, 1);
        map2.put(3, 4);
        graph.add_node(2, map2);

        Map<Integer, Integer> map3 = new HashMap<>();
        map3.put(0, 2);
        map3.put(2, 4);
        map3.put(4, 5);
        map3.put(9, 11);
        map3.put(5, 7);
        graph.add_node(3, map3);

        Map<Integer, Integer> map4 = new HashMap<>();
        map4.put(1, 3);
        map4.put(3, 5);
        map4.put(4, 6);
        graph.add_node(4, map4);

        Map<Integer, Integer> map5 = new HashMap<>();
        map5.put(3, 7);
        map5.put(4, 6);
        map5.put(7, 8);
        map5.put(6, 9);
        graph.add_node(5, map5);

        Map<Integer, Integer> map6 = new HashMap<>();
        map6.put(9, 10);
        map6.put(5, 9);
        graph.add_node(6, map6);

        Map<Integer, Integer> map7 = new HashMap<>();
        map7.put(8, 13);
        map7.put(5, 8);
        graph.add_node(7, map7);

        Map<Integer, Integer> map8 = new HashMap<>();
        map8.put(9, 12);
        map8.put(7, 13);
        graph.add_node(8, map8);

        Map<Integer, Integer> map9 = new HashMap<>();
        map9.put(3, 11);
        map9.put(6, 10);
        map9.put(8, 12);
        graph.add_node(9, map9);

        System.out.println(graph.toString());
        return graph;
    }
}
