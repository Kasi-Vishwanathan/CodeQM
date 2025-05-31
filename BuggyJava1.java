public class BuggyJava1 {
    public static void main(String[] args) {
        String str = null;
        System.out.println(str.length()); // Bug: NullPointerException
    }
}