/**
 * An implementation of the Set interface where the set is
 * created using an array.
 *
 * @author Arogya Upadhyaya & William Myers
 */

public class ArraySet<T> implements Set<T> {
    public static final int CAPACITY_MULTIPLIER = 2;
    public static final int DEFAULT_SIZE = 15;
    private int size = 0;
    private int numberOfItems = 0;
    private T[] items;
    public ArraySet(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Size must be >= 0");
        }
        this.size = size;
        items = (T[]) new Object[size];
    }

    public ArraySet() {
        this(DEFAULT_SIZE);
    }

    @Override
    public void add(T element) {
        if (!contains(element)) {
            ensureCapacity();
            items[numberOfItems] = element;
            numberOfItems++;
        }
    }

    @Override
    public void addAll(T[] elements) {
        for (int i = 0; i < elements.length; i++) {
            add(elements[i]);
        }
    }

    @Override
    public boolean contains(T element) {
        if(indexOf(element) >= 0) return true;
        return false;
    }

    @Override
    public int getSize() {
        return numberOfItems;
    }

    @Override
    public void remove(T element) {
        int index = indexOf(element);
        if (index >= 0){
            numberOfItems--;
            items[index] = items[numberOfItems];
        }
    }

    @Override
    public Set union(Set anotherSet) {
        Set newSet = new ArraySet();
        newSet = anotherSet.difference(newSet);
        for(int i = 0; i < numberOfItems; i++){
            if (newSet.contains(items[i])) continue;
            newSet.add(items[i]);
        }
        return newSet;
    }

    @Override
    public Set intersection(Set anotherSet) {
        Set newSet = new ArraySet();
        for(int i = 0; i < numberOfItems; i++){
            if (anotherSet.contains(items[i])){
                newSet.add(items[i]);
            }
        }
        return newSet;
    }

    @Override
    public Set difference(Set anotherSet) {
        Set newSet = new ArraySet();
        for(int i = 0; i < numberOfItems; i++){
            if (!anotherSet.contains(items[i])){
                newSet.add(items[i]);
            }
        }
        return newSet;
    }

    private int indexOf(Object element) {
        int index = -1;
        for (int i = 0; i < numberOfItems; i++) {
            if (items[i].equals(element)) {
                index = i;
                break;
            }
        }
        return index;
    }

    private void ensureCapacity() {
        if (numberOfItems == size) {
            T[] newArray = (T[]) new Object[(numberOfItems+1) * CAPACITY_MULTIPLIER];
            System.arraycopy(items,0,newArray,0,numberOfItems);
            items = newArray;
        }
    }
}
