
public class QuickSort <T extends Comparable<? super T>> extends SortingAlgorithm <T> 
{

	@Override
	public void sort(T[] array) {
		quickSort(array, 0, array.length-1);
	}

	public static <T extends Comparable<? super T>> void quickSort(T[] a, int left, int right)  {
		int l = left;
		int r = right;

		T pivot = a[r];

		while(l <= r){
//			int cmp1 = a[l].compareTo(pivot);
			while (a[l].compareTo(pivot)<0){
				l++;
			}
//			int cmp2 = a[r].compareTo(pivot);
			while (a[r].compareTo(pivot)>0){
				r--;
			}
			if(l <= r){
				T temp = a[l];
				a[l] = a[r];
				a[r] = temp;
				l++;
				r--;
			}
		}
		if(left < r){
			quickSort(a, left, r);
		}
		if (l < right){
			quickSort(a, l, right);
		}
	}
}
