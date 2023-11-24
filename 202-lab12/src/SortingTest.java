import static org.junit.Assert.*;

import org.junit.Test;

/**
 * A JUnit test case class for the insertion, selection, and merge sorts.
 * 
 * @author Greg Gagne
 */
public class SortingTest {

	/** test isSorted method from SortingAlgorithm */
	@Test
	public void testIsSorted() {
		assertTrue(SortingAlgorithm.isSorted(new Integer[] {1,2,3}));
		assertTrue(SortingAlgorithm.isSorted(new Integer[] {1,1,1}));
		assertTrue(SortingAlgorithm.isSorted(new Integer[] {-3,1,3}));
		assertTrue(SortingAlgorithm.isSorted(new Integer[] {0,0,0}));
		assertFalse(SortingAlgorithm.isSorted(new Integer[] {1,3,2}));
		assertFalse(SortingAlgorithm.isSorted(new Integer[] {2,1,3}));
		assertFalse(SortingAlgorithm.isSorted(new Integer[] {2,3,1}));
		assertFalse(SortingAlgorithm.isSorted(new Integer[] {3,1,2}));
		assertFalse(SortingAlgorithm.isSorted(new Integer[] {3,2,1}));
	}



	/** test SelectionSort.sort */

	@Test
	public void testSelectionSort() {
		SortingAlgorithm<Integer> sorter = new SelectionSort<Integer>();
		// all combinations of 1, 2, 3
		Integer[] array = {1,2,3};
		sorter.sort(array);
		assertTrue(SortingAlgorithm.isSorted(array));
		array = new Integer[] {1,3,2};
		sorter.sort(array);
		assertTrue(SortingAlgorithm.isSorted(array));
		array = new Integer[] {2,1,3};
		sorter.sort(array);
		assertTrue(SortingAlgorithm.isSorted(array));
		array = new Integer[] {2,3,1};
		sorter.sort(array);
		assertTrue(SortingAlgorithm.isSorted(array));
		array = new Integer[] {3,1,2};
		sorter.sort(array);
		assertTrue(SortingAlgorithm.isSorted(array));
		array = new Integer[] {3,2,1};
		sorter.sort(array);
		// double-check negative numbers
		assertTrue(SortingAlgorithm.isSorted(array));
		array = new Integer[] {0,-1,-3};
		sorter.sort(array);
		// duplicate 0's
		assertTrue(SortingAlgorithm.isSorted(array));
		array = new Integer[] {1,0,0};
		sorter.sort(array);
		// duplicate positive numbers
		assertTrue(SortingAlgorithm.isSorted(array));
		array = new Integer[] {3,1,1};
		sorter.sort(array);
		assertTrue(SortingAlgorithm.isSorted(array));

		// stress test selection sort

		Integer[] values = new Integer[10000];

		values = Algorithm.createRandomArray(10000);

		assertFalse(SortingAlgorithm.isSorted(values));

		sorter = new SelectionSort<Integer>();

		sorter.sort(values);

		assertTrue(SortingAlgorithm.isSorted(values));
	}

	/** test InsertionSort.sort */
	

	@Test
	public void testInsertionSort() {
		SortingAlgorithm<Integer> sorter = new InsertionSort<Integer>();
		// all combinations of 1, 2, 3
		Integer[] array = {1,2,3};
		sorter.sort(array);
		assertTrue(SortingAlgorithm.isSorted(array));
		array = new Integer[] {1,3,2};
		sorter.sort(array);
		assertTrue(SortingAlgorithm.isSorted(array));
		array = new Integer[] {2,1,3};
		sorter.sort(array);
		assertTrue(SortingAlgorithm.isSorted(array));
		array = new Integer[] {2,3,1};
		sorter.sort(array);
		assertTrue(SortingAlgorithm.isSorted(array));
		array = new Integer[] {3,1,2};
		sorter.sort(array);
		assertTrue(SortingAlgorithm.isSorted(array));
		array = new Integer[] {3,2,1};
		sorter.sort(array);
		// double-check negative numbers
		assertTrue(SortingAlgorithm.isSorted(array));
		array = new Integer[] {0,-1,-3};
		sorter.sort(array);
		// duplicate 0's
		assertTrue(SortingAlgorithm.isSorted(array));
		array = new Integer[] {1,0,0};
		sorter.sort(array);
		// duplicate positive numbers
		assertTrue(SortingAlgorithm.isSorted(array));
		array = new Integer[] {3,1,1};
		sorter.sort(array);
		assertTrue(SortingAlgorithm.isSorted(array));

		// stress test insertion sort
		Integer[] values = new Integer[10000];

		values = Algorithm.createRandomArray(10000);

		assertFalse(SortingAlgorithm.isSorted(values));

		sorter = new InsertionSort<Integer>();

		sorter.sort(values);

		assertTrue(SortingAlgorithm.isSorted(values));
	}

	/** test MergeSort.sort */
	
	/***
	@Test
	public void testMergeSort() {
		SortingAlgorithm<Integer> sorter = new MergeSort<Integer>();
		// all combinations of 1, 2, 3
		Integer[] array = {1,2,3};
		sorter.sort(array);
		assertTrue(SortingAlgorithm.isSorted(array));
		array = new Integer[] {1,3,2};
		sorter.sort(array);
		assertTrue(SortingAlgorithm.isSorted(array));
		array = new Integer[] {2,1,3};
		sorter.sort(array);
		assertTrue(SortingAlgorithm.isSorted(array));
		array = new Integer[] {2,3,1};
		sorter.sort(array);
		assertTrue(SortingAlgorithm.isSorted(array));
		array = new Integer[] {3,1,2};
		sorter.sort(array);
		assertTrue(SortingAlgorithm.isSorted(array));
		array = new Integer[] {3,2,1};
		sorter.sort(array);
		// double-check negative numbers
		assertTrue(SortingAlgorithm.isSorted(array));
		array = new Integer[] {0,-1,-3};
		sorter.sort(array);
		// duplicate 0's
		assertTrue(SortingAlgorithm.isSorted(array));
		array = new Integer[] {1,0,0};
		sorter.sort(array);
		// duplicate positive numbers
		assertTrue(SortingAlgorithm.isSorted(array));
		array = new Integer[] {3,1,1};
		sorter.sort(array);
		assertTrue(SortingAlgorithm.isSorted(array));
		
		// stress test merge sort
		
		Integer[] values = new Integer[10000];

		values = Algorithm.createRandomArray(10000);

		assertFalse(SortingAlgorithm.isSorted(values));

		sorter = new MergeSort<Integer>();

		sorter.sort(values);

		assertTrue(SortingAlgorithm.isSorted(values));
	}
	***/

	/** test QuickSort.sort */
	
	/***
	@Test
	public void testQuickSort() {
		SortingAlgorithm<Integer> sorter = new QuickSort<Integer>();
		// all combinations of 1, 2, 3
		Integer[] array = {1,2,3};
		sorter.sort(array);
		assertTrue(SortingAlgorithm.isSorted(array));
		array = new Integer[] {1,3,2};
		sorter.sort(array);
		assertTrue(SortingAlgorithm.isSorted(array));
		array = new Integer[] {2,1,3};
		sorter.sort(array);
		assertTrue(SortingAlgorithm.isSorted(array));
		array = new Integer[] {2,3,1};
		sorter.sort(array);
		assertTrue(SortingAlgorithm.isSorted(array));
		array = new Integer[] {3,1,2};
		sorter.sort(array);
		assertTrue(SortingAlgorithm.isSorted(array));
		array = new Integer[] {3,2,1};
		sorter.sort(array);
		// double-check negative numbers
		assertTrue(SortingAlgorithm.isSorted(array));
		array = new Integer[] {0,-1,-3};
		sorter.sort(array);
		// duplicate 0's
		assertTrue(SortingAlgorithm.isSorted(array));
		array = new Integer[] {1,0,0};
		sorter.sort(array);
		// duplicate positive numbers
		assertTrue(SortingAlgorithm.isSorted(array));
		array = new Integer[] {3,1,1};
		sorter.sort(array);
		assertTrue(SortingAlgorithm.isSorted(array));
		
		// stress test quick sort
		
		Integer[] values = new Integer[10000];

		values = Algorithm.createRandomArray(10000);

		assertFalse(SortingAlgorithm.isSorted(values));

		sorter = new QuickSort<Integer>();

		sorter.sort(values);

		assertTrue(SortingAlgorithm.isSorted(values));
	}

	****/

}
