public class ArrayQueue<T> implements Queue<T> {
    private T[] queue;
    private int size = 15;
    private int numberOfItems = 0;
    private int frontIndex = 0;
    private int rearIndex = 0;
    public ArrayQueue() {
        queue = (T[]) new Object[size];
    }
    public ArrayQueue(int size) {
        queue = (T[]) new Object[size];
    }
    @Override
    public void add(T item) {
        ensureCapacity();
        if (rearIndex >= queue.length) {
            queue[0] = item;
            rearIndex = 1;
        } else {
            queue[rearIndex] = item;
            rearIndex++;
        }
        numberOfItems++;
    }

    @Override
    public T remove() {
        if (isEmpty()) return null;
        T element = queue[frontIndex];
        frontIndex++;
        if (frontIndex == queue.length) {
            frontIndex = 0;
        }
        numberOfItems--;
        return element;
    }

    @Override
    public boolean isEmpty() {
        if (numberOfItems == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int size() {
        return numberOfItems;
    }

    private void ensureCapacity() {
        if(numberOfItems == queue.length){
            T[] newArray = (T[])new Object[(numberOfItems+1) * 2];
            int counter = 0;
            for (int i = frontIndex; i < queue.length; i++) {
                newArray[counter] = queue[i];
                counter++;
            }
            for (int i = 0; i < rearIndex; i++) {
                newArray[counter] = queue[i];
                counter++;
            }
            frontIndex = 0;
            rearIndex = numberOfItems;
            queue = newArray;
        }
    }
}
