/**
 * Bridges demo program
 */

import bridges.connect.Bridges;
import bridges.base.BinTreeElement;

import java.util.Iterator;

public class BinaryTreeExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		//create the Bridges object
		// Use YOUR API Sha1 key and username
		Bridges bridge = new Bridges(3, "au0304", "450444019629");

		// create elements
		// First parameter is String depicted when using mouseover with bridges animation
		// Second parameter is data object stored at that tree element
		BinTreeElement<String> root = new BinTreeElement<String>("M", "Mango");

		BinaryTree<String> tree = new BinaryTree<String>(root);
		
		// set the color and label of an Element
		root.setColor("red");
		root.setLabel("Root");

		BinTreeElement<String> fTree = new BinTreeElement<String>("F", "Fig");
		BinTreeElement<String> rTree = new BinTreeElement<String>("R", "Ramen");
		BinTreeElement<String> tTree = new BinTreeElement<String>("T", "Tea");

		BinTreeElement<String> dTree = new BinTreeElement<String>("D", "Dates");
		BinTreeElement<String> hTree = new BinTreeElement<String>("H", "Honey melon");
		BinTreeElement<String> eTree = new BinTreeElement<String>("E", "Eggplant");
		BinTreeElement<String> gTree = new BinTreeElement<String>("G", "Grapes");
		BinTreeElement<String> lTree = new BinTreeElement<String>("L", "Lemon");

//		BinTreeElement<String> zTree = new BinTreeElement<String>("Z", "Z Fruit");
//		BinTreeElement<String> yTree = new BinTreeElement<String>("Y", "Y Fruit");

		// link elements
		rTree.setRight(tTree);
		
		root.setLeft(fTree);
		root.setRight(rTree);

		fTree.setRight(hTree);
		fTree.setLeft(dTree);
		dTree.setRight(eTree);
		hTree.setLeft(gTree);
		hTree.setRight(lTree);

//		eTree.setLeft(zTree);
//		zTree.setLeft(yTree);

		tree.printLevels();
		tree.getHeight();
		tree.getSize();
		tree.removeRightMostNode();
		tree.getLeftMostData();
		tree.getRightMostData();

		Iterator<String> itr = tree.getInOrderIterator();
		System.out.println("\n PRINTING ITERATION \n");
		while (itr.hasNext()){
			System.out.println(itr.next());
		}

		if (tree.getRoot() != null) {
			// make sure we have a data structure to visualize!
			
			//pass root element of data structure
			bridge.setDataStructure(root);

			//visualize data structure
			// THIS SHOULD BE THE LAST METHOD YOU CALL!
			bridge.visualize();
		}
	}

}
