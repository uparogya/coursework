import java.util.Iterator;

/**
 * Interface for a binary search tree.
 * 
 * @author Greg Gagne
 *
 * @param <K> - The key of the search tree.
 */
public interface SearchTreeInterface <K extends Comparable<? super K>> 
{
    /**
     * Determines if the tree is empty or not
     *
     * @return boolean - true if the tree is empty, false otherwise.
     */
    public boolean isEmpty();
    
	/**
	 * Determines if the tree contains the specified item.
	 * 
	 * @param item
	 * @return
	 */
	public boolean contains(K item);
	
	/**
	 * Returns the largest element in the tree
	 */
	public K getLargest();
	
	/**
	 * Returns the smallest element in the tree
	 */
	public K getSmallest();
	
	/**
	 * Adds the item to the tree.
	 * 
	 * @param item
	 * @return T - the item added to the tree. Returns null if 
	 * item was not added to the tree.
	 */
	public K add(K item);
	
	/**
	 * Returns the number of elements in the tree
	 * @return int - the number of elements in the tree.
	 */
	public int size();
	
	/**
	 * Removes the specified item from the tree.
	 * 
	 * @param item
	 * @return T - the item that was moved. Returns null if item is
	 * not present in the tree.
	 */
	public K remove(K item);
	
	/**
	 * Returns an iterator of an in order traversal of the tree.
	 * 
	 * @return Iterator
	 */
	public Iterator<K> iterator();
}
