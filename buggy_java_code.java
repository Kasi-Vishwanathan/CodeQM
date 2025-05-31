// File: buggy_java_code.java
public class BuggyJava {

    public static int subtract(int a, int b) {
        int result = a - b;
        return result;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    public static int[] createArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (i + 1) * 2;
        }
        return arr;
    }

    public static void main(String[] args) {
        int a = 10, b = 7;
        System.out.println("Subtraction: " + subtract(a, b));

        int[] myArr = createArray(5);
        printArray(myArr);

        System.out.println("Factorial of 5: " + factorial(5));
    }
}