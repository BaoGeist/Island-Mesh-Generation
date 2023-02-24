package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.ArrayList;

public interface OurGeometryFactory {
    public ArrayList<Object> create_geometry(int idSelf, ArrayList<Object> arrayArgs, float alpha, int thickness, int misc);
}
