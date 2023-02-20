package ca.mcmaster.cas.se2aa4.a2.generator;

import org.locationtech.jts.geom.PrecisionModel;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.MultiPoint;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class OurIrregular {
    public static void main(String[] args) {
        PrecisionModel newModel = new PrecisionModel(PrecisionModel.FLOATING_SINGLE);
        System.out.println(newModel.getType());

    }
}
