package islandADT;

import islandADT.Container.GeometryContainer;
import islandADT.Elevation.CraterElevationFixture;
import islandADT.Elevation.ElevationFixture;
import islandADT.Elevation.PlainsElevationFixture;
import islandADT.Elevation.VolcanicElevationFixture;
import islandADT.Shapes.*;
import islandADT.Specifications.IslandSpecifications;

public class SetPolygonTypes {
    public void set_island_shape(GeometryContainer geometryContainer, IslandSpecifications islandSpecifications) {
        Shape shape;
        int radius = 150;
        switch (islandSpecifications.getShape()) {
            case "circle":
                shape = new CircleShape();
                break;
            case "oval":
                shape = new OvalShape();
                break;
            case "star":
                shape = new StarShape();
                radius = 200;
                break;
            case "country":
                shape = new UkraineShape();
                break;
            default:
                shape = new CircleShape();
                break;
        }
        shape.generateIsland(radius, geometryContainer);
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
