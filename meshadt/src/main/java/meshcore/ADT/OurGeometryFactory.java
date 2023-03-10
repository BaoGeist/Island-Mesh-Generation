package meshcore.ADT;

import java.util.ArrayList;

// Interface that allows for geometry creation for all geometries
public interface OurGeometryFactory {
    public ArrayList<Object> create_geometry(int idSelf, ArrayList<Object> arrayArgs,float alpha, int thickness, int misc);
}
