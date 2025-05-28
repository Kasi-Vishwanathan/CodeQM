import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReverseString {

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String input;
            while (true) {
                System.out.print("Enter a string (type 'exit' to quit): ");
                input = br.readLine();
                if (input == null || "exit".equalsIgnoreCase(input)) {
                    break;
                }
                String reversed = reverseString(input);
                System.out.println("Reversed: " + reversed);
            }
        }
    }

    public static String reverseString(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder reversedString = new StringBuilder();
        for (int i = str.length(); i > 0; i--) {
            reversedString.append(str.charAt(i - 1));
        }
        return reversedString.toString();
    }
}