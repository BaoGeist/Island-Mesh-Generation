package islandADT.Urbanism;

import io.github.pathfinder.Paths.Path;

public interface GraphAdapterInterface {
    public Path request_path_finder_shortest(int source, int sink);
}
