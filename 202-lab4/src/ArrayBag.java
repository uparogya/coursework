import java.util.Iterator;

/**
 * An array-based bag
 *
 * A bag is an unordered collection of elements 
 * that may contain duplicates.
 *
 * @author Greg Gagne 
 *
 */

// This suppresses warnings we may get
@SuppressWarnings("unchecked")

public class ArrayBag<T> implements Bag<T> {
	public static final int CAPACITY_MULTIPLIER = 2;
	public static final int DEFAULT_CAPACITY = 15;
	
	private int numberOfElements = 0;
	
	private T[] elements;
	
	public ArrayBag() {
		this(DEFAULT_CAPACITY);
	}
	
	
	public ArrayBag(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException("Capacity must be >= 0");
		}
		
		// note how we must create the array
		elements = (T[])new Object[capacity];
	}

	/** 
     * Adds a new element to the bag.
     */
	public void add(T element) {
		ensureCapacity();
		elements[numberOfElements] = element;
		numberOfElements++;
	}

	/**
     * Adds an array of elements to the bag.
     */
	public void addAll(T[] elements) {
		for (int i = 0; i < elements.length; i++) {
			add(elements[i]);	
		}

	}

	/**
     * Determines whether a bag contains a specified element.
     *
     * Returns true if bag contains element, false otherwise.
     */
	public boolean contains(T element) {
		if (indexOf(element) > -1)
			return true;
		else
			return false;
	}

	/**
     * Returns the number of elements in the bag.
     */
	public int getCount() {
		return numberOfElements;
	}

	

	/**
     * Removes the specified element from the bag.
     */
	public boolean remove(T element) {
		int index = indexOf(element);
		
		if (index > -1) {
			numberOfElements--;
			elements[index] = elements[numberOfElements];

			return true;
		}

		return false;
	}
	
		
	/**
	 * Returns the index of a specified element, 
	 * or -1 if the element is not present in the array.
	 */
	private int indexOf(T element) {
		int index = -1;
		for (int i = 0; i < numberOfElements; i++) {
			if (elements[i].equals(element)) {
				index = i;
				break;
			}
		}
		
		return index;
	}
	
	/**
	 * This ensures the array has sufficient capacity to store an additional element.
	 */
	private void ensureCapacity() {
		if (numberOfElements == elements.length) {
			T[] newArray = (T[])new Object[(numberOfElements+1) * CAPACITY_MULTIPLIER];
			System.arraycopy(elements,0,newArray,0,numberOfElements);
			elements = newArray;
		}
	}

}
