/**
 * 
 * An abstract class representing an algorithm
 * 
 */
public abstract class Algorithm  < T extends Comparable < ? super T > > 
{
	/**
	 * Classes that extend Algorithm must implement this method
	 * @param array
	 */
	public abstract void apply(T[] array);
	
	/**
	 * This method returns the amount of time (in milliseconds)
	 * required to run the algorithms in the array
	 * @param array
	 * @return
	 */
	public long  time(T[] array) {
        long start, end;
          start = System.currentTimeMillis();
         
          // invoke the apply method
          this.apply(array);
         
          end = System.currentTimeMillis();

          // returns elapsed time
          return  (end - start);
    }
	
	/**
	 * Create an array containing n random Integers.
	 */
	public static Integer[] createRandomArray(int n) {
		java.util.Random r = new java.util.Random();
		
		Integer[] numbers = new Integer[n];
		
		for (int i = 0; i < n; i++)
			numbers[i] = r.nextInt();
		
		return numbers;
	}


}
