import java.util.ArrayList;
import java.util.List;

public class ListStack<T> implements Stack<T>{

    private List<T> stack;

    public ListStack () {
        stack = new ArrayList<T>();
    }
    @Override
    public void push(T item) {
        stack.add(item);
    }

    @Override
    public T pop() {
        if (stack.size() < 1) return null;
        T latestItem = stack.get(stack.size() - 1);
        stack.remove(stack.size() - 1);
        return latestItem;
    }

    @Override
    public T peek() {
        if (stack.size() < 1) return null;
        return stack.get(stack.size() - 1);
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
