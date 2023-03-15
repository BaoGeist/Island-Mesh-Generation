import ca.mcmaster.cas.se2aa4.a2.visualizer.Configurations.VisualizerConfiguration;
import ca.mcmaster.cas.se2aa4.a2.visualizer.RenderChooser.RenderChooser;
import ca.mcmaster.cas.se2aa4.a2.visualizer.Specifications.VisualizerSpecification;

public class Main {

    public static void main(String[] args){
        VisualizerConfiguration configurations = new VisualizerConfiguration(args);
        VisualizerSpecification visualizerSpecification = configurations.getVisualizerSpecifications();
        RenderChooser renderChooser = new RenderChooser(visualizerSpecification);
        renderChooser.render_choose();
    }
}
