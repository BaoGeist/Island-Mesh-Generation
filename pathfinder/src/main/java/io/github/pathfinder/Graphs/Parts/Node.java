package io.github.pathfinder.Graphs.Parts;

import java.util.HashMap;
import java.util.Map;

public class Node implements Propertiable, Neighbourable<Integer, Integer> {
    private Map<String, Object> properties;
    private Map<Integer, Integer> neighbours;
    private int id;
    private int cost;

    public Node(int id) {
        this.properties = new HashMap<>();
        this.id = id;
    }

    @Override
    public void add_property(String key, Object value) {
        this.properties.put(key, value);
    }

    @Override
    public Map<String, Object> get_properties() {
        Map<String, Object> copiedProperties = new HashMap<>();
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            copiedProperties.put(entry.getKey(), entry.getValue());
        }
        return copiedProperties;
    }

    @Override
    public void set_neighbours(Map<Integer, Integer> neighbours) {
        this.neighbours = neighbours;
    }

    public void set_neighbour(int node_id, int edge_id) {
        this.neighbours.put(node_id, edge_id);
    }

    @Override
    public Map<Integer, Integer> getNeighbours() {
        return neighbours;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString(){
        String returnString = id + ": ";
        for(Integer neighbour: neighbours.keySet()) {
            returnString += neighbour + " ";
        }
        return returnString;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node oObject = (Node) o;
        return(this.getId() == oObject.getId());
    }
}

