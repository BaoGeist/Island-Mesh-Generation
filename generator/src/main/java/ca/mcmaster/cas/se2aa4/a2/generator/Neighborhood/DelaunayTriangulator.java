package ca.mcmaster.cas.se2aa4.a2.generator.Neighborhood;

import ca.mcmaster.cas.se2aa4.a2.generator.ADT.GeometryContainer;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.triangulate.DelaunayTriangulationBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ca.mcmaster.cas.se2aa4.a2.generator.Utils.PropertyUtils.*;

public class DelaunayTriangulator {

    //Receives the coordinates of all centroids and outputs them in an ArrayList
    private static ArrayList<Coordinate> coordinatesfromcentroids(ArrayList<Structs.Vertex> centroids, PrecisionModel precisionModel) {
        GeometryFactory geometryFactory = new GeometryFactory(precisionModel);
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        for(Structs.Vertex centroid: centroids) {
            Point returnpoint = geometryFactory.createPoint(new Coordinate(centroid.getX(), centroid.getY()));
            coordinates.add(returnpoint.getCoordinate());
        }
        return coordinates;
    }

    // Creates a new ArrayList of JTS polygons from our Structs.Polygon class
    private static ArrayList<Polygon> jtspolygonfromstructspolygon(ArrayList<Structs.Polygon> polygons, PrecisionModel precisionModel) {
        ArrayList<Polygon> return_jts_polygon = new ArrayList<>();
        GeometryFactory geometryFactory = new GeometryFactory(precisionModel);
        for(Structs.Polygon p: polygons) {
            ArrayList<float[]> coords = extractCoordsforPolygons(p.getPropertiesList());
            float[] x_coords = coords.get(0);
            float[] y_coords = coords.get(1);
            Coordinate[] polygoncoordinates = new Coordinate[y_coords.length + 1];
            for(int i = 0; i < y_coords.length; i++) {
                polygoncoordinates[i] = (new Coordinate(x_coords[i], y_coords[i]));
            }
            polygoncoordinates[y_coords.length] = new Coordinate(x_coords[0], y_coords[0]);
            Polygon jtspolygon = geometryFactory.createPolygon(polygoncoordinates);
            return_jts_polygon.add(jtspolygon);
        }
        return return_jts_polygon;
    }

    // This method calculates neighborhood relations provided centroids, polygons, geometryContainer, and precision model
    public static ArrayList<ArrayList<Integer>> triangulate(ArrayList<Structs.Vertex> centroids, ArrayList<Structs.Polygon> polygons, GeometryContainer geometryContainer, PrecisionModel precisionModel) {
        GeometryFactory geometryFactory = new GeometryFactory(precisionModel);
        DelaunayTriangulationBuilder triangulationBuilder = new DelaunayTriangulationBuilder();
        ArrayList<ArrayList<Integer>> neighbour_polygons_all = new ArrayList<>();

        ArrayList<Coordinate> coordinates = coordinatesfromcentroids(centroids, precisionModel);

        triangulationBuilder.setSites(coordinates);
        triangulationBuilder.setTolerance(precisionModel.getScale());
        Geometry triangulatedGeometry = triangulationBuilder.getTriangles(geometryFactory);

        ArrayList<Polygon> return_jts_polygon = jtspolygonfromstructspolygon(polygons, precisionModel);

        // Goes through all polygons
        for(int j = 0; j < return_jts_polygon.size(); j++) {
            Geometry jtspolygon = return_jts_polygon.get(j);
            Coordinate centroid_coords = jtspolygon.getCentroid().getCoordinate();
            ArrayList<Integer> polygon_neigbours = new ArrayList<>();

            // For each polygon, goes through all the Delaunay triangulations
            for(int i = 0; i < triangulatedGeometry.getNumGeometries(); i++) {

                List<Coordinate> triangulated_coords = Arrays.stream(triangulatedGeometry.getGeometryN(i).getCoordinates()).toList();
                // Causes the triangulation to fit our precision model
                for(Coordinate c : triangulated_coords) {
                    precisionModel.makePrecise(c);
                }

                //Calculates all neighborhood relations if centroid is in the Delaunay list
                if(triangulated_coords.contains(centroid_coords)) {
                    for(Coordinate c: triangulated_coords) {
                        Geometry polygon_others = return_jts_polygon.get(geometryContainer.return_polygon_id_from_centroid_coordinate(c));
                        if(polygon_others.intersects(jtspolygon) && ! polygon_neigbours.contains(geometryContainer.return_polygon_id_from_centroid_coordinate(c))
                        && geometryContainer.return_polygon_id_from_centroid_coordinate(c)!=j) {
                            polygon_neigbours.add(geometryContainer.return_polygon_id_from_centroid_coordinate(c));
                        }
                    }
                }
            }
            neighbour_polygons_all.add(polygon_neigbours);
        }
        return neighbour_polygons_all;
    }

}
