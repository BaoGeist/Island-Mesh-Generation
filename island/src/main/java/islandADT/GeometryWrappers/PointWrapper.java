package islandADT.GeometryWrappers;

public class PointWrapper {
    private double[] coords;

    public PointWrapper(double[] coords){
        this.coords = coords;
    }

    public double[] getCoords() {return coords;}
}
