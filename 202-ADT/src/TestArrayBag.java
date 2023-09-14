import static org.junit.Assert.*;

import org.junit.Test;


public class TestArrayBag {

	@Test
	public void testConstructor() {
		try {
			Bag bag = new ArrayBag(-1);
			fail("Should have caused exception");
		}
		catch (IllegalArgumentException iae) { }
	}
	
	@Test
	public void testAdd() {
		Bag bag = new ArrayBag(5);
		
		bag.add("Apple");
		bag.add("Banana");
		bag.add("Cherry");
		bag.add("Dates");
		bag.add("Eggplant");
		
		assertEquals(5,bag.getCount());
				
		bag.add("Figs");
		
		assertEquals(6,bag.getCount());
	}
	
	@Test
	public void testAddAll() {
		String[] letters = {"A","B","C","D","E","F"};
		
		Bag bag = new ArrayBag(5);
		bag.addAll(letters);
		
		assertEquals(6,bag.getCount());
	}
	
	@Test
	public void testContains() {
		Bag bag = new ArrayBag();
		
		assertFalse(bag.contains("Apple"));
		
		bag.add("Apple");
		
		assertTrue(bag.contains("Apple"));
	}
	
	@Test
	public void testRemove() {
		Bag bag = new ArrayBag();
		
		assertFalse(bag.remove("Apple"));
		
		assertEquals(0,bag.getCount());
		
		bag.add("Apple");
		assertEquals(1,bag.getCount());
		
		assertTrue(bag.remove("Apple"));
		assertEquals(0,bag.getCount());
	}

}
