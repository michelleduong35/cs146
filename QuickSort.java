import java.util.Random;

// Java program for implementations of QuickSort
public class QuickSort {
/* This method takes last element as pivot, places the pivot element at its correct position in sorted array, and places all smaller (smaller than pivot) to left of pivot and all greater elements
to the right of pivot. This is most-frequently seen implementation of QuickSort partition, and this is also known as the Lomuto partition */
  int partition(int arr[], int low, int high){
    int pivot = arr[high];
    int i = (low - 1); // index of smaller element 
    for (int j = low; j < high; j++) {
    // If current element is smaller than or // equal to pivot
      if (arr[j] <= pivot) {
        i++;
        // swap arr[i] and arr[j]
        int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
      } 
    }
    // swap arr[i+1] and arr[high] (or pivot)
    int temp = arr[i + 1]; arr[i + 1] = arr[high]; arr[high] = temp;
    return i + 1; 
  }


    /* The main method that implements QuickSort() arr[] --> Array to be sorted,
    low --> Starting index,
    high --> Ending index.
    This sorts the array, and returns the time (in ms) it takes to run */
  long sort(int arr[], int low, int high) {
  long startTime = System.currentTimeMillis(); 
  if (low < high) {
    /* partIdx is partitioning index, arr[partIdx] is now at right place */
    int partIdx = partition(arr, low, high);
    // Recursively sort elements before partition and after partition
    sort(arr, low, partIdx - 1);
    sort(arr, partIdx + 1, high); }
    return System.currentTimeMillis() - startTime; 
  }


/* The main method that implements QuickSort() with Hoare partitioning 
Q1: Implement this method and make a new method called partitionHoare (sortHoare)
that is conceptually similar to the partition method in this class. But instead of using the "default" (Lomuto) partitioning use
the Hoare partitioning, as described in Prof. David Taylor's https://www.youtube.com/watch?v=v-1EGgaTFuw
and elsewhere. This sorts the array, and returns the time (in ms) it takes to run */
  static int partitionHoare(int arr[], int low, int high) {
    int pivot = arr[low];
    int i = low, j = high;

    //increment i until value is >= pivot
    while (arr[i]<pivot){
      i++;
    }
    //decrement j until value is <= pivot
    while (arr[j]> pivot){
      j--;
    }

    if (i>=j) return j;
    //swap values at indexes i & j
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;

    return i+1;
  }
  
  long sortHoare(int arr[], int low, int high) {
    long startTime = System.currentTimeMillis(); 

    if (low < high) {
      int pivot = partitionHoare(arr, low, high);
      sortHoare(arr, low, pivot-1);
      sortHoare(arr, pivot+1, high);
    }

    return System.currentTimeMillis() - startTime;
  }


/* This is a method to print array of size n.
* NOTE: use of this method is optional.
* But this can be helpful to debug your code to make sure it really sorts.
* If you reduce the main's NUM_ITEMS to some manageable number
* (like 20 or whatever) you can debug your code, and then
* reset main's NUM_ITEMS to a large value to make your measurements */
static void printArray(int arr[]) {
  int n = arr.length;
  for (int i = 0; i < n; ++i) {
  System.out.print(arr[i] + " "); }
  System.out.println(); 
}
  
/* method to get an already sorted array of size arraySize */
static int[] getAscendingArray(int arraySize) {
  int[] newArray = new int[arraySize]; 
  for (int i = 0; i < arraySize; i++) { 
    newArray[i] = i * 3; // arbitrarily increment by 3 each time 
  }
  return newArray; 
  } 
  
  /* method to get an array of size arraySize in descending order */
static int[] getDescendingArray(int arraySize) {
  int[] newArray = new int[arraySize]; int n = 0;
  for (int i = arraySize - 1; i >= 0; i--){
    n += 4; // arbitrarily increment from end by 4 each time
    newArray[i] = n ; // gets the array in descending order 
    }
    return newArray;
  }

/* method to get an array of size arraySize in descending order */
static int[] getRandomArray(int arraySize) {
  Random rand = new Random();
  // Setting upper bound to generate random numbers in specific range 
  int upperbound = 1000;
  int[] newArray = new int[arraySize];
  for (int i = 0; i < arraySize; i++){
    // Generating random values from 0 - 999999 using nextInt()
    newArray[i] = rand.nextInt(upperbound); }
    return newArray; 
  }

  /* method to get an array of size arraySize with all values the same */
static int[] getSameArray(int arraySize) {
  int[] newArray = new int[arraySize]; 
  for (int i = 0; i < arraySize; i++) {
    newArray[i] = 42; // arbitrary value, same for all elements 
    }
    return newArray; 
}

/*
* This is a bubble sort, runtime n ** 2 worst case.
* TODO: If tryShortCircuit parameter is true, then
* when and if the array is already sorted then
* stop, no need to keep sorting, just return. */

boolean checkSort(int arr[], int len){
  if(len==0 || len==1){
      return true;
  } 
  if(arr[len-2] < arr[len-1]) {
      return false;
  }
  return checkSort(arr, len-1);
}
//bubbleSort code
long bubbleSort(int arr[], boolean tryShortCircuit){
  long startTime = System.currentTimeMillis();
  int len = arr.length;
  if (tryShortCircuit == true){
    //check if sorted, if so, stop sorting and return time
    if (checkSort(arr, len)==true){
      return System.currentTimeMillis() - startTime;
    }
  }

  for (int i = 0; i < len - 1; i++){
    for (int j = 0; j < len - 1; j++){
      if (arr[j] > arr[j+1]) {
        int temp = arr[j+1];
        arr[j+1] = arr[j];
        arr[j] = temp;
      }
    }
  }
  return System.currentTimeMillis() - startTime;
}

//heapSort code; source GeeksforGeeks
    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    void heapify(int arr[], int n, int i)
    {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2
 
        // If left child is larger than root
        if (l < n && arr[l] > arr[largest])
            largest = l;
 
        // If right child is larger than largest so far
        if (r < n && arr[r] > arr[largest])
            largest = r;
 
        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
 
            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }

long hsort(int arr[])
    {
        long startTime = System.currentTimeMillis();
        int n = arr.length;
 
        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);
 
        // One by one extract an element from heap
        for (int i = n - 1; i >= 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
 
            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
        return System.currentTimeMillis() - startTime;
    }

// main driver program
public static void main(String args[]) {
  // NOTE: if you get StackOverflowError then decrease NUM_ITEMS below
  final int NUM_ITEMS = 100; 
  QuickSort ob = new QuickSort();
  int arr[] = getAscendingArray(NUM_ITEMS); 
  long ascTime = ob.sort(arr, 0, NUM_ITEMS - 1);
  arr = getDescendingArray(NUM_ITEMS);
  long descTime = ob.sort(arr, 0, NUM_ITEMS - 1);
  arr = getRandomArray(NUM_ITEMS);
  long randomTime = ob.sort(arr, 0, NUM_ITEMS - 1);
  //printArray(arr); // NOTE: can verify sorted correctly (for small arrays)
  arr = getSameArray(NUM_ITEMS);
  long sameTime = ob.sort(arr, 0, NUM_ITEMS - 1);
  System.out.println("QuickSort times using Lomuto (default) partitioning follow:"); System.out.println("sorted asc array, time in ms: " + Long.toString(ascTime)); System.out.println("sorted desc array, time in ms: " + Long.toString(descTime)); System.out.println("sorted random array, time in ms: " + Long.toString(randomTime)); System.out.println("sorted same values array, time: " + Long.toString(sameTime));
  
  //HeapSort Implementation from GeeksforGeeks

  arr = getAscendingArray(NUM_ITEMS);
  ascTime = ob.hsort(arr);
  arr = getDescendingArray(NUM_ITEMS); descTime = ob.hsort(arr);
  arr = getRandomArray(NUM_ITEMS);
  randomTime = ob.hsort(arr);
  printArray(arr);
  arr = getSameArray(NUM_ITEMS);
  sameTime = ob.hsort(arr);
  System.out.println("\nHeapSort times follow:"); System.out.println("sorted asc array, time in ms: " + Long.toString(ascTime)); System.out.println("sorted desc array, time in ms: " + Long.toString(descTime)); System.out.println("sorted random array, time in ms: " + Long.toString(randomTime)); System.out.println("sorted same values array, time: " + Long.toString(sameTime));
  
  /*arr = getAscendingArray(NUM_ITEMS);
  ascTime = ob.sortHoare(arr, 0, NUM_ITEMS - 1);
  arr = getDescendingArray(NUM_ITEMS); descTime = ob.sortHoare(arr, 0, NUM_ITEMS - 1);
  arr = getRandomArray(NUM_ITEMS);
  randomTime = ob.sortHoare(arr, 0, NUM_ITEMS - 1);
  //printArray(arr); // NOTE: can verify sorted correctly (for small arrays)
  arr = getSameArray(NUM_ITEMS);
  sameTime = ob.sortHoare(arr, 0, NUM_ITEMS - 1);
  System.out.println("\nQuickSort times using Hoare partitioning follow:"); System.out.println("sorted asc array, time in ms: " + Long.toString(ascTime)); System.out.println("sorted desc array, time in ms: " + Long.toString(descTime)); System.out.println("sorted random array, time in ms: " + Long.toString(randomTime)); System.out.println("sorted same values array, time: " + Long.toString(sameTime));
  
  //bubble sort
  arr = getAscendingArray(NUM_ITEMS);
  ascTime = ob.bubbleSort(arr, false);
  arr = getDescendingArray(NUM_ITEMS);
  descTime = ob.bubbleSort(arr, false);
  arr = getRandomArray(NUM_ITEMS);
  randomTime = ob.bubbleSort(arr, false);
  arr = getSameArray(NUM_ITEMS);
  sameTime = ob.bubbleSort(arr, false);
  System.out.println("\nBubble Sort times follow:");
  System.out.println("sorted asc array, time in ms: " + Long.toString(ascTime));
  System.out.println("sorted desc array, time in ms: " + Long.toString(descTime));
  System.out.println("sorted random array, time in ms: " + Long.toString(randomTime));
  System.out.println("sorted same values array, time: " + Long.toString(sameTime));
  arr = getAscendingArray(NUM_ITEMS);
  ascTime = ob.bubbleSort(arr, true);
  arr = getDescendingArray(NUM_ITEMS);
  descTime = ob.bubbleSort(arr, true);
  arr = getRandomArray(NUM_ITEMS);
  randomTime = ob.bubbleSort(arr, true);
  //printArray(arr); // NOTE: can verify sorted correctly (for small arrays)
  arr = getSameArray(NUM_ITEMS);
  sameTime = ob.bubbleSort(arr, true);
  System.out.println("\nBubble Sort times with possible short circuit follow:");
  System.out.println("sorted asc array, time in ms: " + Long.toString(ascTime));
  System.out.println("sorted desc array, time in ms: " + Long.toString(descTime));
  System.out.println("sorted random array, time in ms: " + Long.toString(randomTime));
  System.out.println("sorted same values array, time: " + Long.toString(sameTime));
  */
}
}

/*OUTPUT

NUM_ITEMS = 1000

QuickSort times using Lomuto (default) partitioning follow:
sorted asc array, time in ms: 18
sorted desc array, time in ms: 3
sorted random array, time in ms: 0
sorted same values array, time: 4

QuickSort times using Hoare partitioning follow:
sorted asc array, time in ms: 11
sorted desc array, time in ms: 0
sorted random array, time in ms: 0
sorted same values array, time: 1

Bubble Sort times follow:
sorted asc array, time in ms: 12
sorted desc array, time in ms: 4
sorted random array, time in ms: 4
sorted same values array, time: 4

Bubble Sort times with possible short circuit follow:
sorted asc array, time in ms: 3
sorted desc array, time in ms: 0
sorted random array, time in ms: 2
sorted same values array, time: 1
  */