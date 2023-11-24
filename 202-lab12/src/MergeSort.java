/**
 * MergeSort.java
 * 
 * Implementation of the Merge Sort algorithm.
 * 
 * @author Greg Gagne 
 *
 * @param <T>
 */

public class MergeSort <T extends Comparable<? super T>> extends SortingAlgorithm <T> {

	@Override
	public void sort(T[] array) {
		mergeSort(array, 0, array.length - 1);
	}

	private static <T extends Comparable<? super T>> void mergeSort(T[] array, int first, int last) {
		if (first < last) {
			// determine the midpoint
			int mid = first + (last - first) / 2;
			
			// call mergesort on the left half of the list
			mergeSort(array, first, mid);
			
			// call mergesort on the right half of the list
			mergeSort(array, mid + 1, last);
			
			// merge the two solutions
			merge(array, first, mid, last);
		}
	}
	
	/**
	 * Merge algorithm that merges array[from ... mid] and array[mid+1 ... last]
	 */
	@SuppressWarnings("unchecked")
	private static <T extends Comparable<? super T>> void merge(Comparable[] array, int from, int mid, int last) {
		Comparable[] tempArray = new Comparable[array.length];
		
		// copy the array to be merged to a temporary array
		for (int i = from; i <= last; i++) {
			tempArray[i] = array[i];
		}
		
		// perform the merge step
		int i = from;
		int j = mid + 1;
		int k = from;
		
		while ( (i <= mid) && (j <= last) ) {
			if (tempArray[i].compareTo(tempArray[j]) <= 0) {
				array[k] = tempArray[i];
				i++;
			}
			else {
				array[k] = tempArray[j];
				j++;
			}
			
			k++;
		}
		
		while (i <= mid) {
			array[k] = tempArray[i];
			k++;
			i++;
		}
	}
}
