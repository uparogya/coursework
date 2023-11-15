/**
 * Illustration of Java iterators and generics
 *
 * @author Greg Gagne
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.text.DecimalFormat;

public class UseIterator
{
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("0.00");

        // we will use Java ArrayList
        List<Item> list = new ArrayList<Item>();

        // add the items
        Item milk = new Item("Milk", 2, 3.79);
        Item apples = new Item("Apple", 12, 0.50);
        Item bread = new Item("Bread", 2, 2.99);

        list.add(milk);
        list.add(apples);
        list.add(bread);

        // now get the total cost for all items
        Item contents;
        double total = 0;

		/**
		 * This demonstrates how to get and use an iterator
		 * for traversing through all the elements in the list.
		 *
		 * This indicates the iterator will return an iteration
		 * of Item objects.
		 */
        Iterator<Item> itr = list.iterator();

        while (itr.hasNext()) {
            contents = itr.next();

            System.out.println(contents);

            total += contents.getTotalCost();
        }

        System.out.println("Total cost = $" + df.format(total));
    }
}
