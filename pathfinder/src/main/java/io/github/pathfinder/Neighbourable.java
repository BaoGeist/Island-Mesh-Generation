package io.github.pathfinder;

import java.util.Map;

public interface Neighbourable<T, U> {
    public void set_neighbours(Map<T, U> neighbours);
}
