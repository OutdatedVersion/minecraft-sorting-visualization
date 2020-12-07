package com.outdatedversion.algorithm;

import com.outdatedversion.visualizer.VisualizedArray;

/**
 * Implementation of the heap sorting algorithm from
 * https://www.geeksforgeeks.org/heap-sort/
 *
 * @author Ben Watkins
 * @since Dec/06/2020
 */
public class HeapSort implements SortingAlgorithm {

  @Override
  public void performSort(VisualizedArray array) {
    int n = array.length();

    // Build heap (rearrange array)
    for (int i = n / 2 - 1; i >= 0; i--) {
      heapify(array, n, i);
    }

    // One by one extract an element from heap
    for (int i = n - 1; i > 0; i--) {
      // Move current root to end
      int temp = array.get(0);
      array.set(0, array.get(i));
      array.set(i, temp);
      array.markCycle();

      // call max heapify on the reduced heap
      heapify(array, i, 0);
    }
  }

  // To heapify a subtree rooted with node i which is
  // an index in arr[]. n is size of heap
  private void heapify(VisualizedArray array, int n, int i) {
    int largest = i; // Initialize largest as root
    int l = 2 * i + 1; // left = 2*i + 1
    int r = 2 * i + 2; // right = 2*i + 2

    // If left child is larger than root
    if (l < n && array.get(l) > array.get(largest))
      largest = l;

    // If right child is larger than largest so far
    if (r < n && array.get(r) > array.get(largest))
      largest = r;

    // If largest is not root
    if (largest != i) {
      int swap = array.get(i);
      array.set(i, array.get(largest));
      array.set(largest, swap);

      array.markCycle();
      // Recursively heapify the affected sub-tree
      heapify(array, n, largest);
    }
  }

}
