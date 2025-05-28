import java.util.*;

public class BuggyJava {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your name: ");
        char[] name = new char[20]; // Using char[] instead of String
        name = sc.next().toCharArray();

        System.out.print("Enter your age: ");
        String ageInput = sc.nextLine(); // Logical error: leftover newline

        int age = Integer.parseInt(ageInput);

        if (age < 0)
            System.out.println("Invalid age!");

        System.out.println("Hello, " + new String(name) + "!");
        System.out.println("Your age is " + age);

        int[] numbers = new int[3];
        numbers[3] = 42; // Out-of-bounds

        // No sc.close()
    }
}