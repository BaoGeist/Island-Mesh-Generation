package io.github.pathfinder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Edge implements Propertiable{
    private Map<String, Object> properties;

    public Edge() {
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
}
