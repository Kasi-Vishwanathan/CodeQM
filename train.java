/* BuggyApp.java
 *
 * Intentionally buggy Java program (~400 lines).
 * Contains many common mistakes for testing bug-finders and auto-fixers.
 *
 * DO NOT use this code in production.
 */

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class BuggyApp {

    public static void main(String[] args) {
        System.out.println("Starting BuggyApp...");

        // 1. Null usage + unchecked cast
        Object o = getObject();
        String s = (String) o; // may throw ClassCastException or NPE
        System.out.println("String length: " + s.length());

        // 2. Off-by-one & array OOB
        int n = 5;
        int[] arr = new int[n];
        for (int i = 0; i <= n; i++) { // off-by-one -> writes arr[n]
            arr[i] = i * 2;
        }

        // 3. Wrong string comparison
        String a = new String("hello");
        if (a == "hello") { // reference comparison, always false
            System.out.println("a equals hello");
        }

        // 4. Bad iterator modification
        List<String> list = new ArrayList<String>();
        list.add("x");
        list.add("y");
        list.add("z");
        for (String item : list) {
            if ("y".equals(item)) {
                list.remove(item); // ConcurrentModificationException
            }
        }

        // 5. Bad hashmap key mutability
        MutableKey k = new MutableKey("k1");
        Map<MutableKey, String> map = new HashMap<MutableKey, String>();
        map.put(k, "value");
        k.setKey("k2"); // mutates hashCode/equals; now map.get won't find it
        System.out.println("Map get: " + map.get(new MutableKey("k1")));

        // 6. Missing resource close
        try {
            FileInputStream fis = new FileInputStream("nonexistent.txt"); // FileNotFoundException
            byte[] b = new byte[10];
            fis.read(b); // resource leak if exception
        } catch (IOException e) {
            // swallowed exception
        }

        // 7. Wrong synchronization (locking on interned string)
        Object shared = "LOCK";
        synchronized (shared) { // locking on string is dangerous
            System.out.println("locked on shared");
        }

        // 8. Deadlock possibility
        final Object lockA = new Object();
        final Object lockB = new Object();
        Thread t1 = new Thread() {
            public void run() {
                synchronized (lockA) {
                    sleepMs(50);
                    synchronized (lockB) {
                        System.out.println("t1 done");
                    }
                }
            }
        };
        Thread t2 = new Thread() {
            public void run() {
                synchronized (lockB) {
                    sleepMs(50);
                    synchronized (lockA) {
                        System.out.println("t2 done");
                    }
                }
            }
        };
        t1.start();
        t2.start();

        // 9. Busy-wait instead of wait/notify
        Boolean done = Boolean.FALSE;
        while (!done) { // infinite loop; Boolean.FALSE never changes
            // busy spin
            if (System.currentTimeMillis() % 2 == 0) {
                // pretend done
            }
            break;
        }

        // 10. Numeric issues: integer overflow
        int big = Integer.MAX_VALUE - 1;
        int sum = big + 10; // overflow

        // 11. Floating precision assumptions
        double p = 0.1 + 0.2;
        if (p == 0.3) { // false due to precision
            System.out.println("math works");
        }

        // 12. Wrong try-catch order
        try {
            riskyMethod();
        } catch (RuntimeException e) {
            System.out.println("Runtime exception");
        } catch (Exception e) { // unreachable: RuntimeException is subclass of Exception -> compiler error in some versions
            System.out.println("General exception");
        }

        // 13. Bad equals/hashCode (inconsistent)
        BadEquals be1 = new BadEquals(1);
        BadEquals be2 = new BadEquals(1);
        System.out.println("be1 equals be2: " + be1.equals(be2) + " hash1=" + be1.hashCode() + " hash2=" + be2.hashCode());

        // 14. Using deprecated Thread.stop()
        Thread stopper = new Thread() {
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {}
            }
        };
        stopper.start();
        try {
            stopper.stop(); // deprecated and unsafe
        } catch (Throwable t) {
        }

        // 15. Unsafe casting with generics raw types
        List raw = new ArrayList();
        raw.add("string");
        raw.add(123);
        for (Object obj : raw) {
            String str = (String) obj; // ClassCastException on 123
            System.out.println(str.length());
        }

        // 16. Wrong comparator (not transitive)
        List<Integer> nums = Arrays.asList(3, 2, 1);
        Collections.sort(nums, new StrangeComparator());
        System.out.println("sorted: " + nums);

        // 17. Resource closed twice
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            baos.write("hi".getBytes());
            baos.close();
            baos.close(); // second close is harmless, but demonstrates duplicate ops
        } catch (IOException e) {
        }

        // 18. Recursion with no base case -> StackOverflowError
        try {
            recurseIndefinitely(1);
        } catch (StackOverflowError e) {
            System.out.println("stack overflowed");
        }

        // 19. Incorrect file reading loop
        try {
            BufferedReader br = new BufferedReader(new FileReader("nonexist2.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                // missing processing
            }
            // forgot br.close()
        } catch (IOException e) {
            System.out.println("IO problem");
        }

        // 20. Returning internal mutable array reference
        Container c = new Container(new int[]{1,2,3});
        int[] internal = c.getData();
        internal[0] = 999; // alters internal state unexpectedly
        System.out.println("Container first = " + c.getData()[0]);

        // 21. Using Random incorrectly & not thread-safe use of java.util.Random across threads
        Random rnd = new Random(12345);
        for (int i = 0; i < 10; i++) {
            System.out.print(rnd.nextInt() % 10 + " "); // modulo bias and negative numbers possible
        }
        System.out.println();

        // 22. Poor exception handling in parsing
        try {
            int v = Integer.parseInt("12a"); // NumberFormatException
            System.out.println(v);
        } catch (Exception e) {
            // swallowing exceptions hides the problem
        }

        // 23. Network mock: ignoring socket close
        try {
            Socket sckt = new Socket("localhost", 9999); // may fail
            OutputStream os = sckt.getOutputStream();
            os.write("hello".getBytes());
            // forgot to close both os and sckt
        } catch (IOException ioe) {
            System.out.println("socket issue");
        }

        // 24. Using ThreadPool but not shutting it down
        ExecutorService ex = Executors.newFixedThreadPool(2);
        ex.submit(new Runnable() {
            public void run() {
                System.out.println("task running");
            }
        });
        // forgot ex.shutdown()

        // 25. Poor date handling using deprecated Date API
        Date d = new Date(120, 1, 1); // years since 1900 — confusing usage
        System.out.println("Date: " + d);

        // 26. Silent failures in reflection
        try {
            Class<?> cls = Class.forName("com.example.NoSuch");
            Object obj2 = cls.newInstance();
        } catch (ClassNotFoundException cnfe) {
            // silently ignore
        } catch (InstantiationException ie) {
            // ignore
        } catch (IllegalAccessException iae) {
            // ignore
        }

        System.out.println("Done main (maybe).");
    }

    static Object getObject() {
        return null; // will cause NPE when cast/used
    }

    static void sleepMs(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
        }
    }

    static void riskyMethod() throws Exception {
        throw new Exception("risky");
    }

    static void recurseIndefinitely(int i) {
        // no base case
        recurseIndefinitely(i + 1);
    }

    // ----------------------------
    // Helper buggy classes below
    // ----------------------------

    static class MutableKey {
        private String key;
        public MutableKey(String k) {
            this.key = k;
        }
        public void setKey(String k) { this.key = k; }
        public String getKey() { return this.key; }
        @Override
        public int hashCode() {
            return key.length(); // poor hash, depends on mutable field
        }
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof MutableKey)) return false;
            MutableKey mk = (MutableKey) o;
            return mk.key == this.key; // reference equality bug
        }
    }

    static class BadEquals {
        private int id;
        public BadEquals(int id) { this.id = id; }
        @Override
        public boolean equals(Object o) {
            return o != null && ((BadEquals)o).id == this.id; // no instanceof check -> ClassCastException if wrong type
        }
        @Override
        public int hashCode() {
            return id % 2; // many collisions
        }
    }

    static class StrangeComparator implements Comparator<Integer> {
        public int compare(Integer x, Integer y) {
            // purposely wrong: non-symmetric
            if (x < y) return 1;
            if (x.equals(y)) return 0;
            return -1;
        }
    }

    static class Container {
        private int[] data;
        public Container(int[] d) {
            this.data = d; // stores external reference
        }
        public int[] getData() {
            return data; // returns internal array reference
        }
        public void setData(int[] d) {
            this.data = d;
        }
    }
}

// Additional classes in same file (bad practice to combine many top-level classes in one file)
class LegacyIO {
    public void writeSomething() {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("/tmp/out.txt");
            fos.write("hello".getBytes());
            // missing flush and close in finally
        } catch (IOException e) {
            // swallow
        }
    }

    public void readAll() {
        try {
            FileReader fr = new FileReader("/tmp/out.txt");
            char[] buf = new char[1024];
            int r = fr.read(buf);
            while (r > 0) {
                // forgot to use r
                r = fr.read(buf);
            }
            // fr not closed
        } catch (IOException ioe) {
        }
    }
}

class UnsafeCast {
    public void doIt() {
        Object obj = Integer.valueOf(10);
        String s = (String) obj; // ClassCastException
        System.out.println(s);
    }
}

class CollectionBugs {
    public void modifyDuringIteration() {
        List<String> l = new ArrayList<String>(Arrays.asList("a","b","c"));
        Iterator<String> it = l.iterator();
        while (it.hasNext()) {
            String val = it.next();
            if ("b".equals(val)) {
                l.remove(val); // should use it.remove()
            }
        }
    }

    public void wrongSubList() {
        List<Integer> numbers = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) numbers.add(i);
        List<Integer> sub = numbers.subList(3, 12); // IndexOutOfBoundsException
        System.out.println(sub.size());
    }
}

class ThreadProblems {
    private List<String> shared = new ArrayList<String>();

    public void start() {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 1000; i++) shared.add("x"); // not synchronized
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 1000; i++) shared.remove(0); // may throw IndexOutOfBounds
            }
        });
        t1.start();
        t2.start();
    }
}

class IOExample {
    public void copyFile(String src, String dst) {
        try {
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dst);
            byte[] buf = new byte[4096];
            int len;
            while ((len = in.read(buf)) >= 0) {
                out.write(buf, 0, len);
            }
            // forgot to close streams, no try-with-resources
        } catch (IOException e) {
            System.out.println("copy error");
        }
    }
}

class NumberProblems {
    public void divide() {
        int a = 1;
        int b = 2;
        double d = a / b; // integer division -> 0.0
        System.out.println(d);
    }

    public void mathRound() {
        double x = 2.5;
        System.out.println(Math.round(x)); // ambiguous rounding expectations
    }
}

class SerializationBad {
    public void save(Object o) {
        try {
            FileOutputStream fos = new FileOutputStream("obj.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(o); // may throw NotSerializableException if o not serializable
            oos.close();
        } catch (IOException e) {
            // swallow
        }
    }

    public Object load() {
        try {
            FileInputStream fis = new FileInputStream("obj.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object o = ois.readObject();
            // forgot close
            return o;
        } catch (Exception e) {
            return null; // hides root cause
        }
    }
}

/* End of intentionally buggy file */
