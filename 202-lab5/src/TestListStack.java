/**
 * Unit tests to test implementation of Stack interface.
 */
import static org.junit.Assert.*;

import org.junit.Test;


public class TestListStack {

	/**
	 * Tests a list-based version of the stack
	 */
	@Test
	public void testListStack() {

		Stack<String> s = new ListStack<String>();

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
	public void testManiacalListStack() {
		// performs a stress-test of the implementation

		Stack<Integer> s = new ListStack<Integer>();

		for (int i = 0; i < 100000; i++) {
			s.push(Integer.valueOf(i));
		}

		for (int i = 99999; i >= 0; i--) {
			assertEquals(Integer.valueOf(i), s.pop());
		}
	}

}
