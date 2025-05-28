public class r2 {

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5, 6}; // Fixed missing comma
        int sum = 0;
        double average = 0;

        for (int i = 0; i < numbers.length; i++) { // Fixed loop condition
            sum += numbers[i]; // Fixed += operator
        }

        average = (double) sum / numbers.length; // Corrected average calculation

        System.out.println("Sum of the array is: " + sum);
        System.out.println("Average of the array is: " + average);

        // Correctly find maximum element
        int max = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }

        System.out.println("Maximum element in the array is: " + max);

        // Correctly reverse the array
        for (int i = 0; i < numbers.length / 2; i++) {
            int temp = numbers[i];
            int mirrorIndex = numbers.length - 1 - i;
            numbers[i] = numbers[mirrorIndex];
            numbers[mirrorIndex] = temp;
        }

        System.out.println("Reversed array: ");
        for (int i = 0; i < numbers.length; i++) { // Fixed loop condition
            System.out.print(numbers[i] + " ");
        }
        System.out.println();

        // Generate and print a random number
        int randomNum = new java.util.Random().nextInt();
        System.out.println("This is a random number: " + randomNum);

        // Initialize previously uninitialized variable
        int uninitVar = 0;
        System.out.println("Uninitialized variable: " + uninitVar);

        // Commented out invalid access to prevent exception
        // System.out.println("Accessing index 10: " + numbers[10]);
    }
}