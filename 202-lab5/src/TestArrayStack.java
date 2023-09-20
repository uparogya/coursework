/**
 * Unit tests to test implementation of Stack interface.
 */
import static org.junit.Assert.*;

import org.junit.Test;


public class TestArrayStack {
	/**
	 * Tests an array-based version of the Stack
	 */
	@Test
	public void testArrayStack() {

		Stack<String> s = new ArrayStack<String>();

		assertTrue(s.isEmpty());
		assertNull(s.peek());
		assertNull(s.pop());

		s.push("apple");
		s.push("banana");
		s.push("cherry");

		assertFalse(s.isEmpty());

		assertEquals("cherry", s.peek());
		assertEquals("cherry", s.pop());
		assertEquals("banana", s.peek());
		assertEquals("banana", s.pop());
		assertEquals("apple", s.peek());
		assertEquals("apple", s.pop());

		assertTrue(s.isEmpty());
		assertNull(s.pop());

		s.push("donut");

		assertFalse(s.isEmpty());

		assertEquals("donut", s.pop());
	}



	@Test
	public void testManiacalArrayStack() {
		// performs a stress-test of the implementations

		Stack<Integer> s = new ArrayStack<Integer>();

		for (int i = 0; i < 100000; i++) {
			s.push(Integer.valueOf(i));
		}

		for (int i = 99999; i >= 0; i--) {
			assertEquals(Integer.valueOf(i), s.pop());
		}
	}



}
