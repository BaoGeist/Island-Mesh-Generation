package io.github.pathfinder.Graphs.Parts;

import java.util.Map;

public interface Neighbourable<T, U> {
    public void set_neighbours(Map<T, U> neighbours);

    public Map<Integer, Integer> getNeighbours();
}
