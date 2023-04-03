import io.github.pathfinder.Graphs.GraphADT;
import io.github.pathfinder.Graphs.GraphMakerBasic;
import io.github.pathfinder.Graphs.GraphMakerBetter;
import io.github.pathfinder.Graphs.GraphMakerInterface;
import io.github.pathfinder.Paths.PathFinder;
import io.github.pathfinder.Paths.PathFinderShortest;

public class Main {
    public static void main(String[] args) {
        GraphMakerInterface graphMaker = new GraphMakerBetter();
        GraphADT graph = graphMaker.create_graph();

        PathFinder graphTraveller = new PathFinderShortest(graph);

        for(int i = 0; i < 10; i++) {
            System.out.println("Path to " + i);
            System.out.println(graphTraveller.path_find(0, i));
        }

    }

}
