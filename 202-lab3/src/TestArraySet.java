import org.junit.Test;

import static org.junit.Assert.*;


public class TestArraySet {

	@Test
	public void testConstructor() {
		try {
			Set set = new ArraySet(-1);
			fail("Should have caused exception");
		}
		catch (IllegalArgumentException iae) { }
	}

	@Test
	public void testAdd() {
		Set set = new ArraySet(5);

		set.add("Apple");
		set.add("Banana");
		set.add("Cherry");
		set.add("Dates");
		set.add("Eggplant");

		assertEquals(5,set.getSize());

		set.add("Figs");

		assertEquals(6,set.getSize());
	}

	@Test
	public void testAddDuplicates() {
		Set set = new ArraySet();

		set.add("Apple");
		set.add("Banana");
		set.add("Cherry");

		assertEquals(3,set.getSize());

		set.add("Apple");
		assertEquals(3,set.getSize());		
	}

	@Test
	public void testAddAll() {
		String[] letters = {"A","B","C","D","E","F"};

		Set set = new ArraySet(5);
		set.addAll(letters);

		assertEquals(6,set.getSize());
	}

	@Test
	public void testContains() {
		Set set = new ArraySet();

		assertFalse(set.contains("Apple"));

		set.add("Apple");

		assertTrue(set.contains("Apple"));
	}

	@Test
	public void testRemove() {
		Set set = new ArraySet();

		set.remove("Apple");

		assertEquals(0,set.getSize());

		set.add("Apple");
		assertEquals(1,set.getSize());

		set.remove("Apple");
		assertEquals(0,set.getSize());
	}

	@Test
	public void testUnion() {
		Set s1 = new ArraySet();
		Set s2 = new ArraySet();
		Set s3;
		Set s4 = new ArraySet();
		Set s5;
		Set s6;
		Set s7 = new ArraySet();
		Set s8;

		Integer[] nums1 = {1,2,3,4,5};
		Integer[] nums2 = {6,7,8,9,10};
		Integer[] nums3 = {1,2,3,4,5,6,7,8,9,10};
		Integer[] nums4 = {3,4,5,6,7};
		Integer[] nums5 = {1,2,3,4,5,6,7};
		Integer[] nums6 = {1,2,3,4,5};
		Integer[] nums7 = {1};

		/**
		 * First do a union of two different sets
		 */
		for (int i = 0; i < nums1.length; i++) {
			s1.add(nums1[i]);
			s2.add(nums2[i]);
		}

		// s3 is a union of s1 and s2 {1,2,3,4,5,6,7,8,9,10}
		s3 = s1.union(s2);

		assertEquals(nums3.length,s3.getSize());
		for (int i = 0; i < nums1.length; i++) {
			assertTrue(s3.contains(nums1[i]));
			assertTrue(s3.contains(nums2[i]));
		}



		/**
		 * Next do a union of two partially overlapping sets
		 */
		for (int i = 0; i < nums4.length; i++) {
			s4.add(nums4[i]);
		}

		// s5 is a union of s1 and s4 {1,2,3,4,5,6,7}
		s5 = s1.union(s4);
		assertEquals(nums5.length,s5.getSize());
		for (int i = 0; i < nums5.length; i++)
			assertTrue(s5.contains(nums5[i]));

		/**
		 * do a union of two identical sets
		 * 
		 * s6 contains {1,2,3,4,5}
		 */
		s6 = s1.union(s1);
		for (int i = 0; i < s6.getSize(); i++)
			assertTrue(s6.contains(nums1[i]));

		assertEquals(s1.getSize(),s6.getSize());

		/**
		 * do a union of two sets of different sizes
		 * 
		 * s8 contains {1,2,3,4,5}
		 */
		s7.add(nums7[0]);
		s8 = s7.union(s1);
		assertEquals(s8.getSize(),s1.getSize());
		for (int i = 0; i < s8.getSize(); i++)
			assertTrue(s8.contains(nums1[i]));
	}

	@Test
	public void testIntersection() {
		Set s1 = new ArraySet();
		Set s2 = new ArraySet();
		Set s3 = new ArraySet();

		Integer[] nums1 = {1,2,3,4,5};
		Integer[] nums2 = {10,2,3,15};

		for (int i = 0; i < nums1.length; i++)
			s1.add(nums1[i]);

		for (int i = 0; i < nums2.length; i++)
			s2.add(nums2[i]);

		s3 = s1.intersection(s2);

		assertEquals(2,s3.getSize());

		assertTrue(s3.contains(2));
		assertTrue(s3.contains(3));

	}

	@Test
	public void testDifference() {
		Set s1 = new ArraySet();
		Set s2 = new ArraySet();
		Set s3 = new ArraySet();

		Integer[] nums1 = {1,2,3,4,5};
		Integer[] nums2 = {10,2,3,15};

		for (int i = 0; i < nums1.length; i++)
			s1.add(nums1[i]);

		for (int i = 0; i < nums2.length; i++)
			s2.add(nums2[i]);

		s3 = s1.difference(s2);

		assertEquals(3,s3.getSize());
		assertTrue(s3.contains(1));
		assertTrue(s3.contains(4));
		assertTrue(s3.contains(5));
	}
	
	@Test
	public void testNoChange() {
		/**
		 * Ensures that when you perform a union/intersection/difference
		 * the 'this' Set as well as the parameter are not altered.
		 */
		
		Set s1 = new ArraySet();
		Set s2 = new ArraySet();
		
		Integer[] nums1 = {1,2,3,4,5};
		Integer[] nums2 = {4,5,6,7,8};
		
		for (int i : nums1)
			s1.add(i);
			
		for (int i : nums2)
			s2.add(i);
		
		Set s3 = s1.union(s2);
		
		// Make sure s1 and s2 have not been altered
		
		assertEquals(5,s1.getSize());
		assertEquals(5,s2.getSize());
		
		assertTrue(s1.contains(1));
		assertTrue(s1.contains(2));
		assertTrue(s1.contains(3));
		assertTrue(s1.contains(4));
		assertTrue(s1.contains(5));
		
		assertTrue(s2.contains(4));
		assertTrue(s2.contains(5));
		assertTrue(s2.contains(6));
		assertTrue(s2.contains(7));
		assertTrue(s2.contains(8));
		
		s3 = s1.intersection(s2);
		
		// Make sure s1 and s2 have not been altered
		
		assertEquals(5,s1.getSize());
		assertEquals(5,s2.getSize());
		
		assertTrue(s1.contains(1));
		assertTrue(s1.contains(2));
		assertTrue(s1.contains(3));
		assertTrue(s1.contains(4));
		assertTrue(s1.contains(5));
		
		assertTrue(s2.contains(4));
		assertTrue(s2.contains(5));
		assertTrue(s2.contains(6));
		assertTrue(s2.contains(7));
		assertTrue(s2.contains(8));
		
		s3 = s1.difference(s2);
		
		// Make sure s1 and s2 have not been altered
		
		assertEquals(5,s1.getSize());
		assertEquals(5,s2.getSize());
		
		assertTrue(s1.contains(1));
		assertTrue(s1.contains(2));
		assertTrue(s1.contains(3));
		assertTrue(s1.contains(4));
		assertTrue(s1.contains(5));
		
		assertTrue(s2.contains(4));
		assertTrue(s2.contains(5));
		assertTrue(s2.contains(6));
		assertTrue(s2.contains(7));
		assertTrue(s2.contains(8));
	}

}
