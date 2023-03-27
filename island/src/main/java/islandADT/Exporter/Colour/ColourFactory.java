package islandADT.Exporter.Colour;

import islandADT.Specifications.IslandSpecifications;

import java.util.HashMap;
import java.util.Map;

public class ColourFactory {
    private static final Map<String, ColourExporter> colorBindings = new HashMap<>();

    static {
        colorBindings.put("normal", new NormalColourExporter());
        colorBindings.put("height", new HeightColourExporter());
        colorBindings.put("moisture", new MoistureColourExporter());
        colorBindings.put("resources", new ResourceColourExporter());
    }

    public static ColourExporter create(IslandSpecifications islandSpecifications) {
        String mode = islandSpecifications.getMode();
        try {
            return colorBindings.get(mode);
        } catch (NullPointerException ne) {
            return colorBindings.get("normal");
        }

    }

}
