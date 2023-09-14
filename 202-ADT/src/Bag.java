/** 
 * An interface for a bag ADT.
 *
 * A bag is an unordered collection of elements 
 * that may contain duplicates. Implementations of
 * this interface may provide either a finite or
 * infinite capacity.
 *
 * @author Greg Gagne
 */

public interface Bag
{
    /** 
     * Adds a new item to the bag.
     */
    public void add(Object item);

    /**
     * Adds an array of items to the bag.
     */
    public void addAll(Object[] items);

    /**
     * Determines whether a bag contains a specified item.
     *
     * Returns true if bag contains item, false otherwise.
     */
    public boolean contains(Object item);

    /**
     * Returns the number of items in the bag.
     */
    public int getCount();


    /**
     * Removes the specified item from the bag.
     */
    public boolean remove(Object item);
}
