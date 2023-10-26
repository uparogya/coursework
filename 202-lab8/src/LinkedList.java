/**
 * Implementation of the List interface.
 * 
 * This implementation uses a singly-linked list.
 * 
 * @author Greg Gagne 
 *
 */
public class LinkedList<T> implements List<T> {
	// reference to the head of the linked list
	private Node head;

	// number of elements in the list
	private int numberOfElements;

	public LinkedList() {
		head = null;
	}

	/** 
	 * Inner class representing a node in the linked list
	 */

	private class Node
	{
		private T data;
		private Node next;

		private Node(T data) {
			this(data,null);
		}

		private Node (T data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

	// Methods

	@Override
	public void add(T item) {

		// adds (appends) an item to the rear of the list

		Node newNode = new Node(item);
		Node current = head;

		if (isEmpty()) {
			// special case - first element being added to the list
			head = newNode;
		}
		else {
			while (current.next != null) {
				current = current.next;
			}

			// current now references the last item in the list
			current.next = newNode;
		}

		++numberOfElements;
	}

	@Override
	public boolean add(T item, int index) {
		Node newNode = new Node(item);
		Node current = head;
		if(index == 0){
			newNode.next = current;
			head = newNode;
		}else{
			current = head;
			for (int i = 0; i < index; i++) {
				if(i == index-1) {
					newNode.next = current.next;
					current.next = newNode;
				}
			}
		}
		numberOfElements++;
		return false;
	}

	@Override
	public boolean contains(T item) {
		Node current = head;
		boolean found = false;

		while (current != null && !found) {
			if (current.data.equals(item)) {
				found = true;
			}

			current = current.next;
		}

		return found;

	}

	@Override
	public T get(int index) {
		Node current = head;
		int thisIndex = 0;
		if(index > numberOfElements-1) return null;
		while (current != null) {
			if(thisIndex == index){
				return current.data;
			}
			thisIndex++;
			current = current.next;
		}
		return null;
	}

	@Override
	public boolean remove(T item) {
		if (!contains(item)) return false;
		Node current = head;
		if(current.data.equals(item)){
			head = current.next;
			numberOfElements--;
			return true;
		}
		while (current.next != null){
			if (current.next.data.equals(item)){
				current.next = current.next.next;
				numberOfElements--;
				return true;
			}
			current = current.next;
		}
		return false;
	}

	@Override
	public T remove(int index) {
		T rv = null;

		if (isEmpty() || index >= numberOfElements) {
			rv = null;
		}
		else if (index == 0) {
			// special case - first element in the list
			rv = head.data;
			head = head.next;
			numberOfElements--;
		}
		else {
			int currentIndex = 0;
			Node current = head;

			while (currentIndex < (index - 1)) {
				current = current.next;
				currentIndex++;
			}

			// current references the node we want to remove
			rv = current.next.data;
			current.next = current.next.next;
			numberOfElements--;
		}

		return rv;
	}

	@Override
	public int getLength() {
		return numberOfElements;
	}

	@Override
	public boolean isEmpty() {
		if(head == null){
			return true;
		}
		return false;
	}

	@Override
	public int getFrequency(T item) {
		if(!contains(item)) return 0;
		Node current = head;
		int count = 0;
		while (current != null) {
			if (current.data.equals(item)) {
				count++;
			}
			current = current.next;
		}
//		if(current.data == item) count++;
		return count;
	}

	@Override
	public void clear() {
		head = null;
		numberOfElements = 0;
	}

}
