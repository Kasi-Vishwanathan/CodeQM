public class NullBug {
    public static void main(String[] args) {
        String s;
        System.out.println(s.length());  // s not initialized, NullPointerException
    }
}
