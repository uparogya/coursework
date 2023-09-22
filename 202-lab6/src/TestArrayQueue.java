/**
 * Unit tests to test implementation of queue interface.
 */
import static org.junit.Assert.*;

import org.junit.Test;


public class TestArrayQueue {
	
	/**
	 * 
	 */
	@Test
	public void testArrayQueue() {
		Queue<String> q = new ArrayQueue<String>(6);
		
		q.add("apple");
		q.add("banana");
		q.add("cherry");
		q.add("donut");
		q.add("eggplant");
		
		assertEquals("apple", q.remove());
		assertEquals("banana", q.remove());
		
		q.add("fig");
		q.add("grape");
		q.add("honey");
		
		assertEquals("cherry", q.remove());
		assertEquals("donut", q.remove());
		assertEquals("eggplant", q.remove());
		assertEquals("fig", q.remove());
		assertEquals("grape", q.remove());
		assertEquals("honey", q.remove());
		
		// now trigger creating a larger queue
		q = new ArrayQueue<String>(3);
		
		q.add("apple");
		q.add("banana");
		q.add("cherry");
		
		// this should trigger enlarging the queue
		q.add("donut");
		q.add("eggplant");
		q.add("fig");
		q.add("grape");
		q.add("honey");
		
		assertEquals("apple", q.remove());
		assertEquals("banana", q.remove());
		assertEquals("cherry", q.remove());
		assertEquals("donut", q.remove());
		assertEquals("eggplant", q.remove());
		assertEquals("fig", q.remove());
		assertEquals("grape", q.remove());
		assertEquals("honey", q.remove());
		
		// a more complicated resizing
		
		q = new ArrayQueue<String>(6);
		
		q.add("apple");
		q.add("banana");
		q.add("cherry");
		q.add("donut");
		q.add("eggplant");
		q.add("fig");
		
		assertEquals("apple", q.remove());
		assertEquals("banana", q.remove());
		assertEquals("cherry", q.remove());
		
		q.add("grape");
		q.add("honey");
		q.add("ice cream");
		
		// should trigger resizing
		q.add("jam");
		
		assertEquals("donut", q.remove());
		assertEquals("eggplant", q.remove());
		assertEquals("fig", q.remove());
		assertEquals("grape", q.remove());
		assertEquals("honey", q.remove());
		assertEquals("ice cream", q.remove());
		assertEquals("jam", q.remove());
		
		// show be straightforward additions
		q.add("apple");
		q.add("banana");
		q.add("cherry");
		q.add("donut");
		q.add("eggplant");
		q.add("fig");
		
		assertEquals("apple", q.remove());
		assertEquals("banana", q.remove());
		assertEquals("cherry", q.remove());
		assertEquals("donut", q.remove());
		assertEquals("eggplant", q.remove());
		assertEquals("fig", q.remove());
	}

}
