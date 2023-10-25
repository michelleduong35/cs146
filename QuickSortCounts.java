import java.util.Random;

/* This increments two variables while doing quicksort
* globalCount is the number of recursive sort calls, globalSize accumulates total size of items
* to be sorted so globalSize/globalCount is average number of nodes to sort per call.
* CS 146 Professor Doug Case - San Jose State University.
*
* Homework assignment: vary NUM_ITEMS in main and make conclusions about count of number of
recursive calls, and average number of nodes to sort per call, based on data characteristics (sorted, random, etc).
* What is the asymptotic growth of these?
*/
public class QuickSortCounts {
  // NOTE using global variables like this is a Bad practice - these two following global variables )-:
  static int globalCount = 0; // counts number of recursive sort calls.
  static int globalSize = 0; // accumulates total size of items to be sorted
  // so globalSize/globalCount is average number of nodes to sort per call
  /* This method takes last element as pivot, places the pivot element
  at its correct position in sorted array, and places all smaller
  (smaller than pivot) to left of pivot and all greater elements
  to the right of pivot.
  This is most-frequently seen implementation of QuickSort partition,
  and this is also known as the Lomuto partition */
int partition(int arr[], int low, int high){
  int pivot = arr[high];
  int i = (low - 1); // index of smaller element
  for (int j = low; j < high; j++){
  // If current element is smaller than or
  // equal to pivot
  if (arr[j] <= pivot)
  {
  i++;
  // swap arr[i] and arr[j]
  int temp = arr[i];
  arr[i] = arr[j];
  arr[j] = temp;
  }
  }
  // swap arr[i+1] and arr[high] (or pivot)
  int temp = arr[i + 1];
  arr[i + 1] = arr[high];
  arr[high] = temp;
  return i + 1;
}
  /* The main method that implements QuickSort()
  arr[] --> Array to be sorted,
  low --> Starting index,
  high --> Ending index.
  This sorts the array, and returns the time (in ms) it takes to run */
long sort(int arr[], int low, int high) {
  globalCount++; // NOTE: count another recursive call
  globalSize += (high - low); // NOTE: add size of array subset that we want to sort
  long startTime = System.currentTimeMillis();
  if (low < high) {
    /* partIdx is partitioning index, arr[partIdx] is now at right place */
    int partIdx = partition(arr, low, high);
    // Recursively sort elements before partition and after partition
    sort(arr, low, partIdx - 1);
    sort(arr, partIdx + 1, high);
  }
  return System.currentTimeMillis() - startTime;
}
/* method to get an already sorted array of size arraySize */
static int[] getAscendingArray(int arraySize) {
  int[] newArray = new int[arraySize];
  for (int i = 0; i < arraySize; i++){
    newArray[i] = i * 3; // arbitrarily increment by 3 each time
  }
  return newArray;
}
/* method to get an array of size arraySize in descending order */
static int[] getDescendingArray(int arraySize) {
  int[] newArray = new int[arraySize];
  int n = 0;
  for (int i = arraySize - 1; i >= 0; i--){
  n += 4; // arbitrarily increment from end by 4 each time
  newArray[i] = n ; // gets the array in descending order
  }
  return newArray;
}
/* method to get an array of size arraySize in descending order */
static int[] getRandomArray(int arraySize){
  Random rand = new Random();
  // Setting upper bound to generate random numbers in specific range
  int upperbound = 1000000;
  int[] newArray = new int[arraySize];
  for (int i = 0; i < arraySize; i++){
    // Generating random values from 0 - 999999 using nextInt()
    newArray[i] = rand.nextInt(upperbound);
  }
  return newArray;
}
/* method to get an array of size arraySize with all values the same */
static int[] getSameArray(int arraySize){
  int[] newArray = new int[arraySize];
  for (int i = 0; i < arraySize; i++)
  {
  newArray[i] = 42; // arbitrary value, same for all elements
  }
  return newArray;
}

// main driver program
public static void main(String args[])
{
// NOTE: if you get StackOverflowError then decrease NUM_ITEMS below
final int NUM_ITEMS = 800; //change this number
QuickSortCounts ob = new QuickSortCounts();
int arr[] = getAscendingArray(NUM_ITEMS);
long ascTime = ob.sort(arr, 0, NUM_ITEMS - 1);
int ascCount = globalCount;
int ascSize = globalSize;
globalCount = 0;
globalSize = 0;
arr = getDescendingArray(NUM_ITEMS);
long descTime = ob.sort(arr, 0, NUM_ITEMS - 1);
int descCount = globalCount;
int descSize = globalSize;
globalCount = 0;
globalSize = 0;
arr = getRandomArray(NUM_ITEMS);
long randomTime = ob.sort(arr, 0, NUM_ITEMS - 1);
int randCount = globalCount;
int randSize = globalSize;
globalCount = 0;
globalSize = 0;
arr = getSameArray(NUM_ITEMS);
long sameTime = ob.sort(arr, 0, NUM_ITEMS - 1);
int sameCount = globalCount;
int sameSize = globalSize;
globalCount = 0;
globalSize = 0;
System.out.println("QuickSort times using Lomuto (default) partitioning follow for this many items: " +
Integer.toString(NUM_ITEMS));
System.out.println("sorted asc, time in ms: " + Long.toString(ascTime) + ", count = " +
Integer.toString(ascCount) + ", size = " + Integer.toString(ascSize) + ", size/count = " +
Integer.toString(ascSize/ascCount));
System.out.println("sorted desc, time in ms: " + Long.toString(descTime) + ", count = " +
Integer.toString(descCount) + ", size = " + Integer.toString(descSize) + ", size/count = " +
Integer.toString(descSize/descCount));
System.out.println("sorted rand, time in ms: " + Long.toString(randomTime) + ", count = " +
Integer.toString(randCount) + ", size = " + Integer.toString(randSize) + ", size/count = " +
Integer.toString(randSize/randCount));
System.out.println("sorted same, time in ms: " + Long.toString(sameTime) + ", count = " +
Integer.toString(sameCount) + ", size = " + Integer.toString(sameSize) + ", size/count = " +
Integer.toString(sameSize/sameCount));
} }