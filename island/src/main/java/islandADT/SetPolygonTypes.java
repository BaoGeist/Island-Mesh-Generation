package islandADT;

import islandADT.Elevation.CraterElevationFixture;
import islandADT.Elevation.ElevationFixture;
import islandADT.Elevation.PlainsElevationFixture;
import islandADT.Elevation.VolcanicElevationFixture;
import islandADT.Shapes.*;
import islandADT.Specifications.IslandSpecifications;

public class SetPolygonTypes {
    public void set_island_shape(GeometryContainer geometryContainer, IslandSpecifications islandSpecifications) {
        Shape shape;
        switch (islandSpecifications.getShape()) {
            case "circle":
                shape = new CircleShape();
                shape.generateIsland(150, geometryContainer);
                break;
            case "oval":
                shape = new OvalShape();
                shape.generateIsland(150, geometryContainer);
                break;
            case "star":
                shape = new StarShape();
                shape.generateIsland(200, geometryContainer);
                break;
            case "country":
                shape = new UkraineShape();
                shape.generateIsland(200, geometryContainer);
                break;
            default:
                shape = new CircleShape();
                shape.generateIsland(150, geometryContainer);
                break;
        }
    }

    public void set_island_elevation(GeometryContainer geometryContainer, IslandSpecifications islandSpecifications) {
        ElevationFixture elevationFixture;
        switch(islandSpecifications.getElevation()) {
            case "plains":
                elevationFixture = new PlainsElevationFixture();
                break;
            case "volcanic":
                elevationFixture = new VolcanicElevationFixture();
                break;
            case "crater":
                elevationFixture = new CraterElevationFixture();
                break;
            default:
                elevationFixture = new PlainsElevationFixture();
                break;
        }
        elevationFixture.set_elevation(geometryContainer);
    }
}
