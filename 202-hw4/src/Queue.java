/**
 * Queue.java
 *
 * Interface for queue operations.
 *
 * @author Greg Gagne
 */

public interface Queue<T>
{
    // add item to the rear of the queue
    public void add(T item);

    // remove and return the item at the front of the queue
    public T remove();

    // return true if the queue is empty or false otherwise
    public boolean isEmpty();

    // return th number of items in the queue
    public int size();
}
