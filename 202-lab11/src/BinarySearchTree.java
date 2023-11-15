/**
 * Binary search tree implementation.
 * 
 * We do not allow duplicates.
 * 
 * @author Greg Gagne
 */
import java.util.Iterator;
import java.util.Stack;

import bridges.base.BSTElement;
import bridges.base.BinTreeElement;

public class BinarySearchTree <K extends Comparable<? super K>> implements SearchTreeInterface<K> 
{
	// the root of the binary search tree
	private BSTElement<K, String> root;

	/**
	 * Create an empty binary search tree
	 */
	public BinarySearchTree() {
		root = null;
	}

	/**
	 * This method has nothing to do with a binary search tree,
	 * but is necessary to provide the bridges visualization.
	 */
	public BSTElement<K, String> getRoot() {
		return root;
	}

	public boolean isEmpty() {
		if(root == null){
			return true;
		}
		return false;
	}

	/**
	 * Solution that uses recursive helper method.
	 * We disallow duplicate elements in the tree. 
	 */
	public K add(K key) {
		if (contains(key))
			return null;
		else {
			root = add(key, root);

			return key;
		}
	}


	/**
	 * private helper method for adding a node to the binary search tree
	 * @param key
	 * @param subtree
	 * @return the root of the tree
	 */
	private BSTElement<K, String> add(K key, BSTElement<K,String> subtree) {
		if (subtree == null) {
			// we have found the spot for the addition

			// create the new node
			// parameters are (1) label (2) key (3) empty string [not used]
			BSTElement<K, String> newNode = new BSTElement<K, String>(key.toString(), key, "");

			// we also set the color of a new node to red
			newNode.setColor("red");

			return newNode;
		}

		int direction = key.compareTo(subtree.getKey());

		if (direction < 0) {
			subtree.setLeft( add(key, subtree.getLeft()) );
		}
		else if (direction > 0) {
			subtree.setRight( add(key, subtree.getRight()) );
		}

		return subtree;
	}

	public K getLargest() {
		if(isEmpty()) return null;
		return getLargestHelper(root);
	}

	private K getLargestHelper(BSTElement <K, String> node) {
		if(node.getRight() == null){
			node.setColor("Blue");
			return node.getKey();
		}
		return getLargestHelper(node.getRight());
	}

	public K getSmallest() {
		if(isEmpty()) return null;
		return getSmallestHelper(root);
	}

	private K getSmallestHelper(BSTElement <K, String> node) {
		if(node.getLeft() == null){
			node.setColor("Yellow");
			return node.getKey();
		}
		return getSmallestHelper(node.getLeft());
	}

	public boolean contains(K key) {
		if(isEmpty()) return false;
		return containsHelper(key, root);
	}

	private boolean containsHelper(K key, BSTElement<K, String> currentNode) {
		if(currentNode == null) return false;
		int direction = key.compareTo(currentNode.getKey());
		if(direction == 0){
			currentNode.setColor("Pink");
			return true;
		} else if (direction < 0) {
//			if(currentNode.getLeft() == null) return false;
			return containsHelper(key, currentNode.getLeft());
		} else if (direction > 0) {
//			if(currentNode.getRight() == null) return false;
			return containsHelper(key, currentNode.getRight());
		}
		return false;
	}

	public K remove(K key) {
		if(!contains(key)) return null;
		return removeHelper(key, root).getKey();
	}

	private BSTElement<K, String> removeHelper(K key, BSTElement <K, String> currentNode){
		if (currentNode == null) return null;
		int direction = key.compareTo(currentNode.getKey());
		if(direction < 0){
			BSTElement<K, String> left = removeHelper(key, currentNode.getLeft());
			currentNode.setLeft(left);
		} else if (direction > 0) {
			BSTElement<K, String> right = removeHelper(key, currentNode.getRight());
			currentNode.setRight(right);
		} else if (currentNode.getLeft() != null && currentNode.getRight() != null) {
			K successor = getSmallestHelper(currentNode.getRight());
			currentNode.setKey(successor);
			BSTElement<K, String> right = removeHelper(successor, currentNode.getRight());
			currentNode.setRight(right);
		}else {
			if(currentNode.getLeft() != null){
				currentNode = currentNode.getLeft();
			}else{
				currentNode = currentNode.getRight();
			}
		}
		return currentNode;
	}

	public int size() {
		if(isEmpty()) return 0;
		return getSizeHelper(root, 1);
	}

	private int getSizeHelper(BSTElement<K, String> node, int noe){
		if(node.getRight() != null){
			noe = getSizeHelper(node.getRight(), noe + 1);
		}
		if(node.getLeft() != null){
			noe = getSizeHelper(node.getLeft(), noe + 1);
		}
		return noe;
	}

	public Iterator<K> iterator() {
		return new InOrderIterator();
	}

	private class InOrderIterator implements Iterator<K>
	{
		private K[] elements;
		private int next;

		private InOrderIterator() {
			// create an array large enough to hold all elements in the tree
			elements = (K[]) new Comparable[size()];
			next = 0;

			// now perform an preorder traversal
			inOrder(root);

			// reset next back to the beginning of the array
			next = 0;
		}

		private void inOrder(BSTElement<K, String> node) {
			if (node != null) {
				inOrder(node.getLeft());
				elements[next++] = node.getKey();
				inOrder(node.getRight());
			}
		}

		public boolean hasNext() {
			return (next < size());
		}

		public K next() {
			return elements[next++];
		}
	}
}
