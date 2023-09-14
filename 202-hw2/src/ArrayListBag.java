import java.util.ArrayList;

public class ArrayListBag implements Bag
{
    private ArrayList allItems;
    public ArrayListBag () {
        allItems = new ArrayList();
    }
    @Override
    public void add(Object item) {
        allItems.add(item);
    }

    @Override
    public void addAll(Object[] items) {
        for (int i = 0; i < items.length; i++){
            this.add(items[i]);
        }
    }

    @Override
    public boolean contains(Object item) {
        return allItems.contains(item);
    }

    @Override
    public int getCount() {
        return allItems.size();
    }

    @Override
    public boolean remove(Object item) {
        return allItems.remove(item);
    }
}