/**
 * Illustration of Java ArrayList using generics
 *
 * @author Greg Gagne
 */

import java.util.List;
import java.util.ArrayList;
import java.text.DecimalFormat;

public class UseArrayListGenerics
{
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("0.00");

        List<Item> shoppingList = new ArrayList<Item>();

        // add the items
        Item milk = new Item("Milk", 2, 3.79);
        Item apples = new Item("Apple", 12, 0.50);
        Item bread = new Item("Bread", 2, 2.99);

        shoppingList.add(milk);
        shoppingList.add(apples);
        shoppingList.add(bread);

        // now get the total cost for all items
        Item contents;
        double total = 0;

        for (int i = 0; i < shoppingList.size(); i++) {
            /**
             * ArrayList allows the use of an index to retrieve an item
             */

            contents = shoppingList.get(i); 

            // accumulate total cost
            total += contents.getTotalCost();
        }

        System.out.println("Total cost = $" + df.format(total));
    }
}
