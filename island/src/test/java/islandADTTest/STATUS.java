package islandADTTest;

public enum STATUS {
    PASSED('X'), FAILED('O'), ERROR('!');

    private char symbol;

    private STATUS(char s) {this.symbol = s; }

    @Override
    public String toString() {return "[" + symbol + "]";}
}
