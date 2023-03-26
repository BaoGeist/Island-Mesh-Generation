package islandADTTest.tests;

import java.util.List;

public class Assertions {
    public static void assertTrue(boolean condition) {
        if (!condition)
            throw new AssertionError();
    }

    public static void assertFalse(boolean condition) {
        assertTrue(!condition);
    }

    public static void assertEquals(Object left, Object right) {
        assertTrue(left.equals(right));
    }

    public static void assertNotEquals(Object left, Object right) {
        assertFalse(left.equals(right));
    }

    public static void assertNotNull(Object middle) {
        if(middle == null) {
            throw new AssertionError("Mesh is null");
        }
    }

    public static void asssertContainsString(List<String> parent, String child) {
        assert(parent.contains(child));
    }

    public static void assertNotZero(int integer) {assertNotEquals(integer, 0);}

}
