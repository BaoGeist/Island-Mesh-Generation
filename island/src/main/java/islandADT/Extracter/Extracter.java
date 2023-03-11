package islandADT.Extracter;

public interface Extracter<T, U> {
    public U extract(T input);
}
