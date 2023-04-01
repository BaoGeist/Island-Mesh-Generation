package io.github.pathfinder;

import java.util.HashMap;
import java.util.Map;

public class Node implements Propertiable, Neighbourable<Integer, Integer> {
    private Map<String, Object> properties;
    private Map<Integer, Integer> neighbours;

    public Node() {
        this.properties = new HashMap<>();
    }

    public void add_property(String key, Object value) {
        this.properties.put(key, value);
    }

    public Map<String, Object> get_properties() {
        Map<String, Object> copiedProperties = new HashMap<>();
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            copiedProperties.put(entry.getKey(), entry.getValue());
        }
        return copiedProperties;
    }

    public void set_neighbours(Map<Integer, Integer> neighbours) {
        this.neighbours = neighbours;
    }
}
