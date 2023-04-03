package io.github.pathfinder.Graphs;

public class GraphMakerBetter extends GraphMakerAbstract{

    @Override
    public GraphADT create_graph() {

        populate_edges();
        populate_nodes();


        return populate_graph();
    }

    @Override
    public void populate_nodes() {
        int numberOfNodes = 10;
        for(int i = 0; i < numberOfNodes; i++) {
            new_node(i);
        }
    }

    @Override
    public void populate_edges() {
        new_edge(0, 0, 1, 1);
        new_edge(1,0,2,1);
        new_edge(0, 0, 1, 1);
        new_edge(1, 0, 2, 1);
        new_edge(2, 0, 3, 1);
        new_edge(3, 1, 4, 1);
        new_edge(4, 2, 3, 1);
        new_edge(5, 3, 4, 1);
        new_edge(6, 4, 5, 1);
        new_edge(7, 3, 5, 1);
        new_edge(8, 5, 7, 1);
        new_edge(9, 6, 5, 1);
        new_edge(10, 9, 6, 1);
        new_edge(11, 9, 3, 1);
        new_edge(12, 9, 8, 1);
        new_edge(13, 8, 7, 1);
    }


}
