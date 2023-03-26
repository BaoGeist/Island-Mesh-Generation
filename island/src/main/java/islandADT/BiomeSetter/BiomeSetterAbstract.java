package islandADT.BiomeSetter;

import islandADT.GeometryWrappers.PolygonWrapper;

import java.util.Map;

public abstract class BiomeSetterAbstract implements BiomeInterface{
    int height_increment, moisture_increment;
    protected void set_increments_height(Map<Integer, PolygonWrapper> polygons) {
        double min = Double.MAX_VALUE, max = Double.MIN_VALUE;
        for(PolygonWrapper p: polygons.values()) {
            double moisture = p.getHeight();
            min = Math.min(moisture, min);
            max = Math.max(moisture, max);
        }

        height_increment = (int) ((max - min)/10);
    }

    protected void set_increments_moisture(Map<Integer, PolygonWrapper> polygons) {
        double min = Double.MAX_VALUE, max = Double.MIN_VALUE;
        for(PolygonWrapper p: polygons.values()) {
            double moisture = p.getMoisture();
            min = Math.min(moisture, min);
            max = Math.max(moisture, max);
        }

        moisture_increment = (int) ((max - min)/10);
    }

    protected void set_increments(Map<Integer, PolygonWrapper> polygons) {
        set_increments_height(polygons);
        set_increments_moisture(polygons);
    }
}
