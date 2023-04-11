package io.github.pathfinder.Paths;

import io.github.pathfinder.Graphs.Parts.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Path {
    List<Node> path_list;

    public Path() {
        path_list = new ArrayList<>();
    }

    public void addPath(Node n) {
        path_list.add(n);
    }

    public int length() {
        return path_list.size() == 0 ? 0 : path_list.size()-1;
    }

    public List<Integer> get_path_integer() {
        List<Integer> return_array = path_list.stream()
                .map(Node::getId)
                .toList();
        return return_array;
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
