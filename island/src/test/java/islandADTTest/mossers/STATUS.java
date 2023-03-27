package islandADTTest.mossers;

public enum STATUS {
    PASSED('P'), FAILED('X'), ERROR('!');

    private char symbol;

    private STATUS(char s) {this.symbol = s; }

    @Override
    public String toString() {return "[" + symbol + "]";}
}
