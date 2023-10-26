/** 
 * List.java
 *
 * An interface representing a List ADT.
 *
 * A list is an ordered collection of elements where each element
 * is at a position in the list beginning from zero. Duplicates
 * are allowed.
 *
 * @author Greg Gagne 
 */

public interface List<T>
{
	/**
	 * appends item to List
	 * 
	 * @param item - The item being added to the list
	 */
    public void add(T item);

    /**
     * adds item to List at specified index. 
     * "Holes" or gaps between indices are disallowed.
     * 
     * @param item - The item being added to the list
     *
     * @param index - The position where item is being inserted.
     */
    public boolean add(T item, int index);

    /**
     * Determines in item is contained in the List.
     * 
     * @param item - The item being searched for.
     * 
     * @return True if items is contained in the List, False otherwise
     */
    public boolean contains(T item);

    /**
     * Returns the item at the specified index in the list.
     * 
     * @param index - The index position in the list 
     *
     * @return The Object at the specified position, or null.
     */
    public T get(int index);

    /**
     * Removes the first occurrence of the item from List
     * 
     * @param item - The element to be removed.
     *
     * @return True if remove was successful, false otherwise/
     */
    public boolean remove (T item);

    /**
     * Removes the item from the specified index in the List
     *
     * @param index - The position of the item to be removed
     *
     * @return The Object removed, or null if no object at the specified index
     */
    public T remove (int index);

    /**
     * Determines the number of items in the List
     * 
     * @return int - The length of the list
     */
    public int getLength();

    /**
     * Determines if the List is empty.
     * 
     * @return True if the List is empty, False otherwise.
     */
    public boolean isEmpty();

    /**
     * Determines the frequency of item appearing in the List
     * 
     * @param item - The item being searched for.
     * 
     * @return int representing the frequency that items occurs in the List
     */
    public int getFrequency(T item);

    /**
     * Clears the list
     */
    public void clear();
}
