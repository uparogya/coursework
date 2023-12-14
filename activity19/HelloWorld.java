/**
 * HelloWorld.java
 *
 * A program that prints a message.
 */

// In Java, the name of the class needs to match the name of the file!
// (The HelloWorld class needs to be in HelloWorld.java.)
public class HelloWorld {

    private static void print(String message) {
        System.out.println(message);
    }

    // Java's main() method always has to have this signature
    // (The args parameter can also be a String[])
    public static void main(String... args) {
        print("Hello, world!");
    }
}
