package io.github.pathfinder;

import java.util.Map;

public interface Propertiable {
    public void add_property(String key, Object value);

    public Map<String, Object> get_properties();
}
