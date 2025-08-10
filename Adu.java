public class StudentGrades {

    public static void main(String[] args) {
        String[] studentNames = new String[3];
        int[] marks = new int[3];
        double average;

        studentNames[0] = "Alice";
        studentNames[1] = "Bob";
        studentNames[2] = null;   // BUG: null name will cause issues

        marks[0] = 85;
        marks[1] = 90;
        marks[2] = 95;

        int sum = 0;
        for (int i = 0; i <= marks.length; i++) {  // BUG: i <= length causes ArrayIndexOutOfBoundsException
            sum += marks[i];
        }

        average = sum / marks.length;

        System.out.println("Student Grades:");
        for (int i = 0; i < studentNames.length; i++) {
            System.out.println("Student: " + studentNames[i].toUpperCase());  // NullPointerException when i=2
            if (marks[i] >= 90) {
                System.out.println("Grade: A");
            } else if (marks[i] >= 80) {
                System.out.println("Grade: B");
            } else {
                System.out.println("Grade: C");
            }
        }

        System.out.println("Class Average: " + average);

        // Divide by zero error:
        int divisor = 0;
        int result = 100 / divisor;  // ArithmeticException: division by zero
        System.out.println("Result of division: " + result);
    }
}
