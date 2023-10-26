import java.util.ArrayList;
import java.util.List;

public class ListQueue<T> implements Queue<T> {

    private List<T> queue;

    public ListQueue() {
        queue = new ArrayList<T>();
    }
    @Override
    public void add(T item) { queue.add(item); }

    @Override
    public T remove() {
        if (isEmpty()) return null;
        return queue.remove(0);
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public int size() {
        return queue.size();
    }
}
