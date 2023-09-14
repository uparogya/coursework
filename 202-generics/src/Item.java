/**
 * A typical item that can be purchased at a store.
 */
public class Item
{
    // name of the item
    private String name;

    // cost of each item
    private double cost;

    // quantity purchased
    private int quantity;

    public Item(String name, int quantity, double cost) {
        this.name = name;
        this.quantity = quantity;
        this.cost = cost;
    }

    // returns the total cost
    public double getTotalCost() {
        return quantity * cost;
    }
}
