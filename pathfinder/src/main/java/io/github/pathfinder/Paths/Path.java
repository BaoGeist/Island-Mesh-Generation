package io.github.pathfinder.Paths;

import io.github.pathfinder.Graphs.Parts.Node;

import java.util.ArrayList;
import java.util.List;

public class Path {
    List<Node> path_list;

    public Path() {
        path_list = new ArrayList<>();
    }

    public void addPath(Node n) {
        path_list.add(n);
    }

    @Override
    public String toString() {
        if(path_list.isEmpty()) {
            return "No path.";
        } else {
            String returnString = new String();
            for(Node n: path_list) {
                returnString += n.getId() + " ";
            }
            return returnString;
        }
    }
}
