package islandADT.Exporter;

public interface Exporter <T, U> {
    public U export(T input);
}
