/**
 * 
 * An abstract class representing a sorting algorithm.
 * Classes extending this class implement specific sorting algorithms.
 */
public abstract class SortingAlgorithm <T extends Comparable <? super T>> extends Algorithm <T>
{
	/**
	 * sorts the parameter array in ascending order (from smallest to largest)
	 */
	public abstract void sort (T[] array);


	/**
	 * Invoke the appropriate sorting algorithm.
	 */
	public void apply(T[] array) {
		this.sort(array);
	}

	public static <T extends Comparable<? super T>> boolean isSorted(Comparable[] array) {
		if (array.length == 1) return true;
		for (int i = 1; i < array.length; i++) {
			int compare_result = array[i-1].compareTo(array[i]);
			if(compare_result > 0){
				return false;
			}
		}
		return true;
	}
}
