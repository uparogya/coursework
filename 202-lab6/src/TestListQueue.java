/**
 * Unit tests to test implementation of queue interface.
 */
import static org.junit.Assert.*;

import org.junit.Test;


public class TestListQueue {

	@Test
	public void testListQueue() {
		Queue<String> q = new ListQueue<String>();
		
		assertTrue(q.isEmpty());
		assertEquals(0,q.size());
		assertNull(q.remove());
		
		q.add("apple");
		q.add("banana");
		q.add("cherry");
		q.add("date");
		
		assertFalse(q.isEmpty());
		assertEquals(4,q.size());
		
		assertEquals("apple", q.remove());
		assertEquals("banana", q.remove());
		assertEquals("cherry", q.remove());
		assertEquals("date", q.remove());
		
		assertTrue(q.isEmpty());
		assertEquals(0,q.size());
		assertNull(q.remove());
	}
	
	
}
