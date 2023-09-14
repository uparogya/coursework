/*
 * Arogya Upadhyaya & William Myers
 * */
import java.util.ArrayList;
import java.util.List;

public class ListSet<T> implements Set<T> {

    private ArrayList allItems;
    public ListSet () {
        allItems = new ArrayList();
    }

    @Override
    public void add(T element) {
        if(!allItems.contains(element)) {
            allItems.add(element);
        }
    }

    @Override
    public void addAll(T[] elements) {
        for (int i = 0; i < elements.length; i++){
            add(elements[i]);
        }
    }

    @Override
    public boolean contains(T element) {
        return allItems.contains(element);
    }

    @Override
    public int getSize() {
        return allItems.size();
    }

    @Override
    public void remove(T element) {
        allItems.remove(element);
    }

    @Override
    public Set<T> union(Set<T> anotherSet) {
        Set<T> newSet = new ArraySet<T>();
        newSet = anotherSet.difference(newSet);
        for(int i = 0; i < getSize(); i++){
            if (newSet.contains((T) allItems.get(i))) continue;
            newSet.add((T) allItems.get(i));
        }
        return newSet;
    }

    @Override
    public Set<T> intersection(Set<T> anotherSet) {
        Set<T> newSet = new ArraySet<T>();
        for (int i = 0; i < getSize(); i++){
            if (anotherSet.contains((T) allItems.get(i))){
                newSet.add((T) allItems.get(i));
            }
        }
        return newSet;
    }

    @Override
    public Set<T> difference(Set<T> anotherSet) {
        Set<T> newSet = new ArraySet<T>();
        for(int i = 0; i < getSize(); i++){
            if (!anotherSet.contains((T) allItems.get(i))){
                newSet.add((T) allItems.get(i));
            }
        }
        return newSet;
    }
}
