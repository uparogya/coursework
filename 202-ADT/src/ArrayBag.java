/**
 * An array-based bag of infinite capacity.
 *
 * @author Greg Gagne
 *
 */
public class ArrayBag implements Bag {
	public static final int CAPACITY_MULTIPLIER = 2;
	public static final int DEFAULT_CAPACITY = 15;
	
	private int capacity = 0;
	private int numberOfItems = 0;
	private Object[] items;
	
	public ArrayBag() {
		this(DEFAULT_CAPACITY);
	}
	
	public ArrayBag(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException("Capacity must be >= 0");
		}
		
		this.capacity = capacity;
		items = new Object[capacity];
	}

	public void add(Object item) {
		ensureCapacity();
		items[numberOfItems] = item;
		numberOfItems++;
	}

	public void addAll(Object[] items) {
		for (int i = 0; i < items.length; i++) {
			add(items[i]);	
		}

	}

	public boolean contains(Object item) {
		if (indexOf(item) > -1)
			return true;
		else
			return false;
	}

	public int getCount() {
		return numberOfItems;
	}

	public boolean remove(Object item) {
		int index = indexOf(item);
		
		if (index > -1) {
			numberOfItems--;
			items[index] = items[numberOfItems];
			
			return true;
		}

		return false;
	}
	
	/**
	 * Returns the index of a specified element, 
	 * or -1 if the element is not present in the array.
	 */
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
	
	/**
	 * This ensures the array has sufficient capacity to store an additional element.
	 * 
	 * If the bag is full, a new array is created that is CAPACITY_MULTIPLIER times
	 * larger than the original array. All existing elements are copied to the
	 * new array.
	 */
	private void ensureCapacity() {
		if (numberOfItems == capacity) {
			Object[] newArray = new Object[(numberOfItems+1) * CAPACITY_MULTIPLIER];
			System.arraycopy(items,0,newArray,0,numberOfItems);
			items = newArray;
		}
	}

}
