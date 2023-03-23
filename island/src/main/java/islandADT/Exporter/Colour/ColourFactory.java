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
    }

    public static ColourExporter create(IslandSpecifications islandSpecifications) {
        String mode = islandSpecifications.getMode();
        return colorBindings.get(mode);
    }

}
