// File: buggy_java_test.java
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BuggyJavaTest {

    @Test
    public void testSubtract() {
        assertEquals(3, BuggyJava.subtract(10, 7), "10 - 7 should equal 3");
    }

    @Test
    public void testFactorial() {
        assertEquals(120, BuggyJava.factorial(5), "Factorial of 5 should be 120");
    }

    @Test
    public void testArray() {
        int[] arr = BuggyJava.createArray(3);
        assertEquals(3, arr.length, "Array length should be 3");
        assertEquals(2, arr[0], "arr[0] should be 2");
        assertEquals(4, arr[1], "arr[1] should be 4");
        assertEquals(6, arr[2], "arr[2] should be 6");
    }

    @Test
    public void testCreateArrayNegativeSize() {
        assertThrows(NegativeArraySizeException.class, () -> BuggyJava.createArray(-1),
            "Creating array with negative size should throw NegativeArraySizeException");
    }
}