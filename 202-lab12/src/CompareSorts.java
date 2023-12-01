public class CompareSorts {
    static final int NUMBER = 4;

    public static void main(String[] args){
        compareSorts(1000);
    }
    public static void compareSorts(int n){
        SortingAlgorithm[] sorts = new SortingAlgorithm[NUMBER];

        sorts[0] = new SelectionSort();
        sorts[1] = new InsertionSort();
        sorts[2] = new MergeSort();
        sorts[3] = new QuickSort();

        Integer[][] sortArray = new Integer[NUMBER][n];
        sortArray[0] = Algorithm.createRandomArray(n);

        System.arraycopy(sortArray[0], 0, sortArray[1], 0, sortArray[0].length);
        System.arraycopy(sortArray[0], 0, sortArray[2], 0, sortArray[0].length);
        System.arraycopy(sortArray[0], 0, sortArray[3], 0, sortArray[0].length);

        for (int i=0; i < sorts.length; i++) {
            switch (i){
                case (0):
                    System.out.print("Selection Sort = ");
                    break;
                case (1):
                    System.out.print("Insertion Sort = ");
                    break;
                case (2):
                    System.out.print("Merge Sort = ");
                    break;
                case (3):
                    System.out.print("Quick Sort = ");
                    break;
            }
            System.out.println(sorts[i].time(sortArray[i]));
        }
    }
}
