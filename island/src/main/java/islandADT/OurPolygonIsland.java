package islandADT;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import meshcore.ADT.GeometryContainer;
import meshcore.ADT.OurPolygon;

import java.util.List;
import java.util.ArrayList;

import static islandADT.IslandTileEnum.*;
import static meshcore.Utils.PropertyUtils.extractCentroidCoords;
import static meshcore.Utils.PropertyUtils.extractColor;

public class OurPolygonIsland extends OurPolygon {
    @Override
    public ArrayList<Object> create_geometry(int id_self, ArrayList<Object> arrayArgs, float alpha, int thickness, int misc) {
        return super.create_geometry(id_self, arrayArgs, alpha, thickness, misc);
    }

}
