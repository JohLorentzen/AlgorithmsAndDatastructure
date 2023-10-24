package quickSort;
import java.util.*;

/**
 * This class creates a large data structure with a quicksort method to sort the data set.
 *
 * @author johanneslorentzen
 */
public class numberData {

  Random random = new Random();

  int[] testArray;

  /**
   * Initializes an array with size 1.000.000 and fills it with random integers.
   */
  public numberData() {
    testArray = new int[1000000];
    for (int i = 0; i < testArray.length; i += 2) {
      testArray[i] = 42;
      testArray[i+1] = random.nextInt(1,Integer.MAX_VALUE);
    }
  }

  /**
   * Creates an array with 1.000.000 integers with no duplicates.
   *
   * @return the array with no duplicates.
   */
  public int[] noDuplicateArray() {
    List<Integer> integerList = new ArrayList<>();
    for (int i = 0; i < 1000000; i++) {
      integerList.add(i);
    }
    Collections.shuffle(integerList);
    int[] simpleArray = new int[1000000];
    for (int i = 0; i < integerList.size(); i++) {
      simpleArray[i] = integerList.get(i);
    }
    return simpleArray;
  }

  /**
   * Copies an array.
   *
   * @param array given array
   * @return a copy of the given array.
   */
  public static int[] copyArray(int[] array) {
    int[] copyArray = new int[array.length];
    for (int i = 0; i < array.length; i++) {
      copyArray[i] = array[i];
    }
    return copyArray;
  }

  /**
   * Swap method for swapping two integers in an array.
   *
   * @param array given array
   * @param i index of first integer
   * @param j index of second integer
   */
  public static void swap(int[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

  /**
   * Sorts the first, middle and last element in an array.
   *
   * @param array given array
   * @param left left index
   * @param right right index
   * @return the median of the three elements.
   */
  public static int median3sort(int[] array, int left, int right) {
    int median = (left + right) / 2;
    if (array[left] > array[median]) swap(array, left, median);
    if (array[median] > array[right]) {
      swap(array, median, right);
      if (array[left] > array[median]) swap(array, left, median);
    }
    return median;
  }

  /**
   * Partitions the array.
   *
   * @param array given array
   * @param left left index
   * @param right right index
   * @return the index of the partition.
   */
  public static int partition(int[] array, int left, int right) {
    int iLeft, iRight;
    int median = median3sort(array, left, right);
    int dLeft = array[median];
    swap(array, median, right - 1);
    for (iLeft = left, iRight = right - 1; ; ) {
      while (array[++iLeft] < dLeft) ;
      while (array[--iRight] > dLeft) ;
      if (iLeft >= iRight) break;
      swap(array, iLeft, iRight);
    }
    swap(array, iLeft, right - 1);
    return iLeft;
  }

  /**
   * Standard quicksort method.
   *
   * @param array given array
   * @param left left index
   * @param right right index
   */
  public static void quicksort(int[] array, int left, int right) {
    if (right-left > 2) {
      int pivot = partition(array, left, right);
      quicksort(array, left, pivot-1);
      quicksort(array,pivot+1, right);
    } else median3sort(array, left, right);
  }

  /**
   * Improved quicksort method for arrays with many duplicates-
   *
   * @param array given array
   * @param left left index
   * @param right right index
   */
  public static void improvedQuicksort(int[] array, int left, int right) {
    if (left < right) {
      // Checks if the part of the array lies out of bounds, then checks for equal pivots on both sides of the array.
      if (left > 0 && right < array.length - 1 && array[left - 1] == array[right + 1]) {
        // the return value is null, when the part of the array is all equal numbers.
        return;
      }
      if (right - left > 2) {
        int pivot = partition(array, left, right);
        improvedQuicksort(array, left, pivot - 1);
        improvedQuicksort(array, pivot + 1, right);
      } else {
        median3sort(array, left, right);
      }
    }
  }

  /**
   * Tests if the array is sorted correctly.
   *
   * @param sortedArray a sorted array.
   */
  public static void sortTest(int[] sortedArray) {
    for (int i = 1; i < sortedArray.length-1 ; i++) {
        if( sortedArray[i] < sortedArray[i-1]){
          System.out.println("Algorithm fail, array not sorted correctly");
          break;
        }
    }
  }

  /**
   * Checks if two arrays are equal by comparing the sum of the two arrays.
   *
   * @param array original array
   * @param sortArray sorted array
   */
  public static void sumTest(int[] array, int[] sortArray) {
    int originalSum = 0;
    int sortedSum = 0;
    for (int i = 0; i < array.length; i++) {
      originalSum += array[i];
      sortedSum += sortArray[i];
    }
    if (originalSum != sortedSum) System.out.println("Algorithm failed, numbers overwritten");
  }


  /**
   * Main method for testing the quicksort algorithms.
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    int count = 1;
    do {
      System.out.printf("\nRun %x:\n", count);
      // Declaring start-time and end-time for each of the three tests
      Long sT1, et1, sT2, et2, sT3, et3, sT4, et4, sT5, et5, sT6, et6;
      numberData nd = new numberData();

      numberData nd2 = new numberData();

      int[] originalArray = copyArray(nd.testArray);
      int[] sortArray = copyArray(nd.testArray);



      //takes the time of the first sort.
      sT1 = System.currentTimeMillis();

      quicksort(sortArray, 0, sortArray.length-1);

      et1 = System.currentTimeMillis();

      sortTest(sortArray);
      sumTest(originalArray, sortArray);

      Long esT1 = et1 - sT1;

      //Takes the time of a fully sorted array.
      sT2 = System.currentTimeMillis();
      quicksort(sortArray, 0, sortArray.length-1);
      et2 = System.currentTimeMillis();
      Long esT2 = et2 - sT2;

      sortTest(sortArray);
      sumTest(originalArray, sortArray);

      //Prints the standard quicksort times.
      System.out.printf("\nStandard quicksort:\nRuntime: %d milliseconds \nRuntime fully sorted: %d milliseconds\n\n", esT1, esT2);



      //Improved quicksort
      int[] improvedSortArray = copyArray(nd.testArray);
      sT3 = System.currentTimeMillis();
      improvedQuicksort(improvedSortArray, 0, improvedSortArray.length-1);
      et3 = System.currentTimeMillis();

      Long esT3 = et3 - sT3;

      sumTest(originalArray, improvedSortArray);
      sortTest(improvedSortArray);

      //sorted array
      sT4 = System.currentTimeMillis();
      improvedQuicksort(improvedSortArray, 0, improvedSortArray.length-1);
      et4 = System.currentTimeMillis();

      Long esT4 = et4 - sT4;

      sumTest(originalArray, improvedSortArray);
      sortTest(improvedSortArray);


      //Prints the improved quicksort times.
      System.out.printf("\nImproved quicksort:\nRuntime: %d milliseconds.\nRuntime fully sorted: %d milliseconds.\n\n", esT3, esT4);

      //Tests the improved algorithm on an array with no duplicates.
      int[] noDuplicates = copyArray(nd.noDuplicateArray());
      int[] sortNoDuplicates = copyArray(nd.noDuplicateArray());

      sT5 = System.currentTimeMillis();
      improvedQuicksort(sortNoDuplicates, 0, sortNoDuplicates.length-1);
      et5 = System.currentTimeMillis();
      Long esT5 = et5 - sT5;

      sumTest(noDuplicates, sortNoDuplicates);
      sortTest(sortNoDuplicates);
      //Fully sorted array with zero duplicates.
      sT6 = System.currentTimeMillis();
      improvedQuicksort(sortNoDuplicates,0, sortNoDuplicates.length-1);
      et6 = System.currentTimeMillis();
      Long esT6 = et6 - sT6;

      sumTest(noDuplicates, sortNoDuplicates);
      sortTest(sortNoDuplicates);

      //Prints the improved quicksort on a list with zero duplicates.
      System.out.printf("\nImproved quicksort with no duplicates:\nRuntime: %d milliseconds.\nRuntime fully sorted: %d milliseconds.\n\n", esT5, esT6);
      count++;
    } while (count != 4);
  }
}
