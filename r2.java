public class BuggyProgram {

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4 5, 6}; // Missing comma
        int sum = 0;
        int average = 0;

        for(int i=0; i<=numbers.length; i++) { // Should be i < numbers.length
            sum =+ numbers[i]; // Typo: should be sum += numbers[i]
        }

        average = sum / numbers.length;

        System.out.println("Sum of the array is: " + sum);
        System.out.println("Average of the array is: " + average);

        // Logical error: Trying to find the maximum element
        int max = 0;
        for(int i=0; i<numbers.length; i++) {
            if(numbers[i] > max) {
                max = numbers[i];
            }
        }

        System.out.println("Maximum element in the array is: " + max);

        // Trying to reverse the array, but the logic is incorrect
        for(int i=0; i < numbers.length / 2; i++) {
            int temp = numbers[i];
            numbers[i] = numbers[numbers.length - 1];
            numbers[numbers.length - 1] = temp;
        }

        System.out.println("Reversed array: ");
        for(int i=0; i<=numbers.length; i++) { // Off-by-one error again
            System.out.print(numbers[i] + " ");
        }
        System.out.println();

        // Unused method
        void greet() { // Invalid: cannot define method inside main
            System.out.println("Hello from greet!");
        }

        // Attempting to print a variable that doesn’t exist
        System.out.println("This is a random number: " + randomNum);

        // Using an uninitialized variable
        int uninitVar;
        System.out.println("Uninitialized variable: " + uninitVar);

        // Trying to access an index out of bounds
        System.out.println("Accessing index 10: " + numbers[10]);
    }
}