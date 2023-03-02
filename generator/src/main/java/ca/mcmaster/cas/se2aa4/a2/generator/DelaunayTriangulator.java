package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.geom.impl.CoordinateArraySequence;
import org.locationtech.jts.triangulate.DelaunayTriangulationBuilder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ca.mcmaster.cas.se2aa4.a2.generator.PropertyUtils.*;

import ca.mcmaster.cas.se2aa4.a2.generator.GeometryContainer.*;

import static org.locationtech.jts.algorithm.Centroid.getCentroid;

public class DelaunayTriangulator {

    private static ArrayList<Coordinate> coordinatesfromcentroids(ArrayList<Structs.Vertex> centroids, PrecisionModel precisionModel) {
        GeometryFactory geometryFactory = new GeometryFactory(precisionModel);
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        for(Structs.Vertex centroid: centroids) {
            Point returnpoint = geometryFactory.createPoint(new Coordinate(centroid.getX(), centroid.getY()));
            coordinates.add(returnpoint.getCoordinate());
        }
        return coordinates;
    }

    private static List<LineString> polygonboundariesfromtriangulation(Geometry triangulatedGeometry) {
        List<LineString> polygonBoundaries = new ArrayList<>();
        for(int i = 0; i < triangulatedGeometry.getNumGeometries(); i++) {
            Polygon polygon = (Polygon) triangulatedGeometry.getGeometryN(i);
            polygonBoundaries.add(polygon.getExteriorRing());
        }
        return polygonBoundaries;
    }

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

    public static ArrayList<ArrayList<Integer>> triangulate(ArrayList<Structs.Vertex> centroids, ArrayList<Structs.Polygon> polygons, GeometryContainer geometryContainer, PrecisionModel precisionModel) {
        GeometryFactory geometryFactory = new GeometryFactory(precisionModel);
        DelaunayTriangulationBuilder triangulationBuilder = new DelaunayTriangulationBuilder();
        ArrayList<ArrayList<Integer>> neighbour_polygons_all = new ArrayList<>();

        ArrayList<Coordinate> coordinates = coordinatesfromcentroids(centroids, precisionModel);

        triangulationBuilder.setSites(coordinates);
        triangulationBuilder.setTolerance(precisionModel.getScale());
        Geometry triangulatedGeometry = triangulationBuilder.getTriangles(geometryFactory);

        ArrayList<Polygon> return_jts_polygon = jtspolygonfromstructspolygon(polygons, precisionModel);



        for(int j = 0; j < return_jts_polygon.size(); j++) {
            Geometry jtspolygon = return_jts_polygon.get(j);
            Coordinate centroid_coords = jtspolygon.getCentroid().getCoordinate();
            ArrayList<Integer> polygon_neigbours = new ArrayList<>();
            for(int i = 0; i < triangulatedGeometry.getNumGeometries(); i++) {

                List<Coordinate> triangulated_coords = Arrays.stream(triangulatedGeometry.getGeometryN(i).getCoordinates()).toList();
                for(Coordinate cccc : triangulated_coords) {
                    precisionModel.makePrecise(cccc);
                }

//                System.out.println(triangulated_coords);
                for (Coordinate c : triangulated_coords){
                    if (((int)c.getX() == (int)centroid_coords.getX()) && ((int)c.getY() == (int)centroid_coords.getY())){
                        System.out.printf("cen: %f, %f \t tri: %f, %f\n", centroid_coords.getX(), centroid_coords.getY(), c.getX(), c.getY());
                    }
                }

                if(triangulated_coords.contains(centroid_coords)) {
                    System.out.printf("polygon %d has a centroid in the triangulation \n", j);
                    for(Coordinate c: triangulated_coords) {
                        Geometry polygon_others = return_jts_polygon.get(geometryContainer.return_polygon_id_from_centroid_coordinate(c));
                        if(polygon_others.intersects(jtspolygon) && ! polygon_neigbours.contains(geometryContainer.return_polygon_id_from_centroid_coordinate(c))
                        && geometryContainer.return_polygon_id_from_centroid_coordinate(c)!=j) {
                            polygon_neigbours.add(geometryContainer.return_polygon_id_from_centroid_coordinate(c));
                            System.out.printf("polygon: %d, and its neighbours %s \n", j, polygon_neigbours.toString());
                        }
                    }
                }
            }
            neighbour_polygons_all.add(polygon_neigbours);
        }
        return neighbour_polygons_all;
    }

}
