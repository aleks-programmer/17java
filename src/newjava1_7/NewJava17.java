package newjava1_7;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


class A implements AutoCloseable {
    public void close() {
        System.out.println("A.close()");
    }
}
class B implements AutoCloseable {
    public void close() {
        System.out.println("B.close()");
    }
}
public class NewJava17 {
    // Underscores in numeric literals
    private static final Integer ONE_MILLION = 1_000_000;

    public static void main(String[] args) {
        // Improved type inference for generic instance creation (diamond)
        List<String> strs = new ArrayList<>();
        //  try-with-resources statement
        try(A a = new A(); B b = new B();) {
            System.out.println("1");
           // throw new RuntimeException();
        }

        // Binary integral literals
        int mask = 0b10000001; // = 8
        System.out.println(mask);

        try {
            multiCatch();
        } catch (IOException e) {
            System.out.println("IOException");
        }
        finalThrow();

        // Simplified varargs method invocation to suppress warnings

    }

    // Multi-catch and more precise rethrow
    private static void multiCatch() throws IOException {
        try (FileInputStream fis = new FileInputStream("/tmp/1.txt")) {
            fis.read();
        } catch (FileNotFoundException | SecurityException e) {
            System.out.println(e.toString());
        }
    }

    private static void finalThrow() {
        try {
            throw new RuntimeException("test");
        } catch (final Exception e) {
            throw e;
        }
    }

}
