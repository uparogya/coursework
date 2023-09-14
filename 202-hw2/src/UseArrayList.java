/** 
 * Demonstration of using an ArrayList
 */

import java.util.*;

public class UseArrayList
{
    public static void main(String[] args) {
        List books = new ArrayList();

        books.add("Five Tuesdays in Winter");
        books.add("Orwell's Roses");
        books.add("Hope");

        System.out.println(books.contains("Hope"));
        System.out.println(books.contains("Hopeful"));
    }
}
