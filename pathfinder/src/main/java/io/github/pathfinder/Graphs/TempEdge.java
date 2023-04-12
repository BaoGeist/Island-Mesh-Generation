package io.github.pathfinder.Graphs;

public class TempEdge{
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