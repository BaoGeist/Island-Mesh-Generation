package islandADT.Exporter;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import islandADT.Exporter.Colour.ColourExporter;
import islandADT.Exporter.Colour.ColourGenerator;
import islandADT.GeometryWrappers.PolygonWrapper;
import islandADT.TypeWrappers.TileTypeWrapper;

import java.util.List;


//TODO inquire if all this is better as an overloaded method in one class
public class OurPolygonExporter implements Exporter<PolygonWrapper, Structs.Polygon> {
    ColourExporter colourExporter;
    public OurPolygonExporter(ColourExporter colourExporter) {
        this.colourExporter = colourExporter;
    }
    public Structs.Polygon export(PolygonWrapper p) {
        int centroid = p.getId_centroid();
        List<Integer> segments = p.getSegments_group();
        List<Integer> neighbours = p.getNeighbours();
        OurPolygonIsland polygonIslandFactory = new OurPolygonIsland();
        List<float[]> coords = p.getX_y_coords();
        TileTypeWrapper tileType = p.getTileType();

        int[] color = colourExporter.export(p);

        int height = p.getHeight();
        double moisture = p.getMoisture();
        // refactor ourpolygon to not need alpha or thickness
        return polygonIslandFactory.create_geometry(segments, color, centroid, neighbours, coords, height, moisture);
    }
}
