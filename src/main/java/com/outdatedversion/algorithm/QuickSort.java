package com.outdatedversion.algorithm;

import com.outdatedversion.visualizer.VisualizedArray;

/**
 * Implementation of the radix sorting algorithm pulled from
 * https://www.geeksforgeeks.org/quick-sort/
 */
public class QuickSort implements SortingAlgorithm {

  @Override
  public void performSort(VisualizedArray array) {
    this.quickSort(array, 0, array.length() - 1);
  }

  /* The main function that implements QuickSort()
    arr[] --> Array to be sorted,
    low  --> Starting index,
    high  --> Ending index */
  private void quickSort(VisualizedArray array, int low, int high) {
    if (low < high) {
            /* pi is partitioning index, arr[pi] is
              now at right place */
      int pi = partition(array, low, high);

      // Recursively sort elements before
      // partition and after partition
      this.quickSort(array, low, pi - 1);
      this.quickSort(array, pi + 1, high);
    }
  }

  private int partition(VisualizedArray array, int low, int high) {
    int pivot = array.get(high);
    int i = (low - 1); // index of smaller element
    for (int j = low; j < high; j++) {
      // If current element is smaller than the pivot
      if (array.get(j) < pivot) {
        i++;

        // swap arr[i] and arr[j]
        int temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
      }
      array.markCycle();
    }

    // swap arr[i+1] and arr[high] (or pivot)
    int temp = array.get(i + 1);
    array.set(i + 1, array.get(high));
    array.set(high, temp);
    array.markCycle();

    return i + 1;
  }

}
