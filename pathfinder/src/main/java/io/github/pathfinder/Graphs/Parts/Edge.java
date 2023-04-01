package io.github.pathfinder.Graphs.Parts;

import java.util.HashMap;
import java.util.Map;

public class Edge implements Propertiable {
    private Map<String, Object> properties;
    private int id;
    private int weight;

    public Edge(int id, int weight) {
        this.id = id;
        this.weight = weight;
        this.properties = new HashMap<>();
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

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", " + "Weight:" + weight;
    }
}
