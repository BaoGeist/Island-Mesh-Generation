package islandADT.Resources;

import islandADT.GeometryContainer;
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
            int score = 0;
            double moisture = p.getMoisture();
            double height = p.getHeight();
            if (p.getResource() == "sand") { 
                if (moisture <= 2) {score = 5;}
                else if (moisture <= 4) {score = 4;}
                else if (moisture <= 6) {score = 3;}
                else if (moisture <= 8) {score = 2;}
                else {score = 1;}

                if (height < 100) {score += 5;}
                else if (height < 200) {score += 4;}
                else if (height < 300) {score += 3;}
                else if (height < 400) {score += 2;}
                else {score += 1;}

                p.setScore(score);
            }
            else if (p.getResource() == "wood") {
                if (moisture <= 2) {score = 1;}
                else if (moisture <= 4) {score = 3;}
                else if (moisture <= 6) {score = 5;}
                else if (moisture <= 8) {score = 4;}
                else {score = 2;}

                if (height < 100) {score += 1;}
                else if (height < 200) {score += 3;}
                else if (height < 300) {score += 5;}
                else if (height < 400) {score += 4;}
                else {score += 2;}

                p.setScore(score);
            }
            else if (p.getResource() == "snow") {
                if (moisture <= 2) {score = 1;}
                else if (moisture <= 4) {score = 2;}
                else if (moisture <= 6) {score = 3;}
                else if (moisture <= 8) {score = 4;}
                else {score = 5;}

                if (height < 100) {score += 1;}
                else if (height < 200) {score += 2;}
                else if (height < 300) {score += 3;}
                else if (height < 400) {score += 4;}
                else {score += 5;}

                p.setScore(score);
            }
            else if (p.getResource() == "livestock") {
                if (moisture <= 2) {score = 1;}
                else if (moisture <= 4) {score = 3;}
                else if (moisture <= 6) {score = 5;}
                else if (moisture <= 8) {score = 4;}
                else {score = 2;}

                if (height < 100) {score += 1;}
                else if (height < 200) {score += 3;}
                else if (height < 300) {score += 5;}
                else if (height < 400) {score += 4;}
                else {score += 2;}

                p.setScore(score);
            }
            else if (p.getResource() == "berries") {
                if (moisture <= 2) {score = 1;}
                else if (moisture <= 4) {score = 3;}
                else if (moisture <= 6) {score = 5;}
                else if (moisture <= 8) {score = 4;}
                else {score = 2;}

                if (height < 100) {score += 1;}
                else if (height < 200) {score += 3;}
                else if (height < 300) {score += 5;}
                else if (height < 400) {score += 4;}
                else {score += 2;}

                p.setScore(score);
            }
            else if (p.getResource() == "mushrooms") {
                if (moisture <= 2) {score = 1;}
                else if (moisture <= 4) {score = 3;}
                else if (moisture <= 6) {score = 5;}
                else if (moisture <= 8) {score = 4;}
                else {score = 2;}

                if (height < 100) {score += 1;}
                else if (height < 200) {score += 3;}
                else if (height < 300) {score += 5;}
                else if (height < 400) {score += 4;}
                else {score += 2;}

                p.setScore(score);
            }
            else {
                if (moisture <= 2) {score = 1;}
                else if (moisture <= 4) {score = 3;}
                else if (moisture <= 6) {score = 5;}
                else if (moisture <= 8) {score = 4;}
                else {score = 2;}

                if (height < 100) {score += 1;}
                else if (height < 200) {score += 3;}
                else if (height < 300) {score += 5;}
                else if (height < 400) {score += 4;}
                else {score += 2;}

                p.setScore(score);
            }
        }
    }
}
