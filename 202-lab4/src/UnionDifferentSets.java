/**
 * This program demonstrates abstraction by performing a 
 * union of two different sets that are implemented differently.
 */
import java.util.Iterator;

public class UnionDifferentSets {

	public static void main(String[] args) {
		Set<String> s1 = new ArraySet<String>();
		Set<String> s2 = new ListSet<String>();
		
		s1.add("apples");
		s1.add("cherries");
		s1.add("eggplant");
		
		s2.add("brownies");
		s2.add("donuts");
		
		Set<String> s3 = s1.union(s2);
	}

}
