package islandADT.Resources;

import islandADT.Container.GeometryContainer;
import islandADT.GeometryWrappers.PolygonWrapper;
import islandADT.Specifications.IslandSpecifications;

import java.util.Map;

public class ResourceCalculator {
    private IslandSpecifications islandSpecifications;

    public ResourceCalculator(IslandSpecifications islandSpecifications) {
        this.islandSpecifications = islandSpecifications;
    }

    public void calculateResources(GeometryContainer geometryContainer) {
        Map<Integer, PolygonWrapper> polygons= geometryContainer.get_polygons();

        for (PolygonWrapper p: polygons.values()) {
            int resourceScore = 0;
            double moisture = p.getMoisture();
            double height = p.getHeight();
            if (p.isWaterOrNah()) {
                p.setScore(resourceScore);
            }
            if (p.getResource() == "sand") { 
                if (moisture <= 2) {resourceScore = 5;}
                else if (moisture <= 4) {resourceScore = 4;}
                else if (moisture <= 6) {resourceScore = 3;}
                else if (moisture <= 8) {resourceScore = 2;}
                else {resourceScore = 1;}

                if (height < 100) {resourceScore += 5;}
                else if (height < 200) {resourceScore += 4;}
                else if (height < 300) {resourceScore += 3;}
                else if (height < 400) {resourceScore += 2;}
                else {resourceScore += 1;}

                p.setScore(resourceScore);
            }
            else if (p.getResource() == "wood") {
                if (moisture <= 2) {resourceScore = 1;}
                else if (moisture <= 4) {resourceScore = 3;}
                else if (moisture <= 6) {resourceScore = 5;}
                else if (moisture <= 8) {resourceScore = 4;}
                else {resourceScore = 2;}

                if (height < 100) {resourceScore += 1;}
                else if (height < 200) {resourceScore += 3;}
                else if (height < 300) {resourceScore += 5;}
                else if (height < 400) {resourceScore += 4;}
                else {resourceScore += 2;}

                p.setScore(resourceScore);
            }
            else if (p.getResource() == "snow") {
                if (moisture <= 2) {resourceScore = 1;}
                else if (moisture <= 4) {resourceScore = 2;}
                else if (moisture <= 6) {resourceScore = 3;}
                else if (moisture <= 8) {resourceScore = 4;}
                else {resourceScore = 5;}

                if (height < 100) {resourceScore += 1;}
                else if (height < 200) {resourceScore += 2;}
                else if (height < 300) {resourceScore += 3;}
                else if (height < 400) {resourceScore += 4;}
                else {resourceScore += 5;}

                p.setScore(resourceScore);
            }
            else if (p.getResource() == "livestock") {
                if (moisture <= 2) {resourceScore = 1;}
                else if (moisture <= 4) {resourceScore = 3;}
                else if (moisture <= 6) {resourceScore = 5;}
                else if (moisture <= 8) {resourceScore = 4;}
                else {resourceScore = 2;}

                if (height < 100) {resourceScore += 1;}
                else if (height < 200) {resourceScore += 3;}
                else if (height < 300) {resourceScore += 5;}
                else if (height < 400) {resourceScore += 4;}
                else {resourceScore += 2;}

                p.setScore(resourceScore);
            }
            else if (p.getResource() == "berries") {
                if (moisture <= 2) {resourceScore = 1;}
                else if (moisture <= 4) {resourceScore = 3;}
                else if (moisture <= 6) {resourceScore = 5;}
                else if (moisture <= 8) {resourceScore = 4;}
                else {resourceScore = 2;}

                if (height < 100) {resourceScore += 1;}
                else if (height < 200) {resourceScore += 3;}
                else if (height < 300) {resourceScore += 5;}
                else if (height < 400) {resourceScore += 4;}
                else {resourceScore += 2;}

                p.setScore(resourceScore);
            }
            else if (p.getResource() == "mushrooms") {
                if (moisture <= 2) {resourceScore = 1;}
                else if (moisture <= 4) {resourceScore = 3;}
                else if (moisture <= 6) {resourceScore = 5;}
                else if (moisture <= 8) {resourceScore = 4;}
                else {resourceScore = 2;}

                if (height < 100) {resourceScore += 1;}
                else if (height < 200) {resourceScore += 3;}
                else if (height < 300) {resourceScore += 5;}
                else if (height < 400) {resourceScore += 4;}
                else {resourceScore += 2;}

                p.setScore(resourceScore);
            }
            else {
                if (moisture <= 2) {resourceScore = 1;}
                else if (moisture <= 4) {resourceScore = 3;}
                else if (moisture <= 6) {resourceScore = 5;}
                else if (moisture <= 8) {resourceScore = 4;}
                else {resourceScore = 2;}

                if (height < 100) {resourceScore += 1;}
                else if (height < 200) {resourceScore += 3;}
                else if (height < 300) {resourceScore += 5;}
                else if (height < 400) {resourceScore += 4;}
                else {resourceScore += 2;}

                p.setScore(resourceScore);
            }
        }
    }
}
