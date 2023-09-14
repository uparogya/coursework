import java.util.Iterator;

/** 
 * An interface for a set ADT.
 *
 * A set is an unordered collection of elements 
 * that may not contain duplicates.
 *
 * @author Greg Gagne 
 */

public interface Set<T>
{
    /** 
     * Adds a new element to the set as long as
     * it is not present in the set.
     */
    public void add(T element);

    /**
     * Adds an array of elements to the set as long
     * as an element is not present in the set.
     */
    public void addAll(T[] elements);

    /**
     * Determines whether a set contains a specified element.
     *
     * Returns true if set contains element, false otherwise.
     */
    public boolean contains(T element);

    /**
     * Returns the size (in elements) in the set.
     */
    public int getSize();


    /**
     * Removes the specified element from the set.
     */
    public void remove(T element);

    /** 
     * Creates a new set that combines the contents if this
     * set and anotherSet. If the same item occurs in each
     * set, only one item appears in the new set.
     *
     * Both this set and anotherSet are unchanged.
     *
     * Returns the new set.
     */
    public Set<T> union(Set<T> anotherSet);

    /**
     * Creates a new set that contains the objects that occur
     * in both this set and anotherSet.
     *
     * Returns the new set.
     */
    public Set<T> intersection(Set<T> anotherSet);
    
    /**
     * Creates a new set containing the objects that would
     * left in this set after removing those that also 
     * occur in anotherSet.
     *
     * Returns the new set.
     */
    public Set<T> difference(Set<T> anotherSet);
}
