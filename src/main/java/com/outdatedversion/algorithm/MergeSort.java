package com.outdatedversion.algorithm;

import com.outdatedversion.visualizer.VisualizedArray;

/**
 *  Implementation of the merging sorting algorithm from
 * https://www.geeksforgeeks.org/merge-sort/
 *
 * @author Ben Watkins
 * @since Dec/06/2020
 */
public class MergeSort implements SortingAlgorithm {

  @Override
  public void performSort(VisualizedArray array) {
    this.mergeSort(array, 0, array.length() - 1);
  }

  private void mergeSort(VisualizedArray array, int left, int right) {
    if (left < right) {
      // Find the middle point
      int m = (left + right) / 2;

      // Sort first and second halves
      this.mergeSort(array, left, m);
      this.mergeSort(array, m + 1, right);

      // Merge the sorted halves
      merge(array, left, m, right);
    }
    array.markCycle();
  }

  private void merge(VisualizedArray array, int l, int m, int r) {
    // Find sizes of two subarrays to be merged
    int n1 = m - l + 1;
    int n2 = r - m;

    /* Create temp arrays */
    int[] L = new int[n1];
    int[] R = new int[n2];

    /*Copy data to temp arrays*/
    for (int i = 0; i < n1; ++i)
      L[i] = array.get(l + i);
    for (int j = 0; j < n2; ++j)
      R[j] = array.get(m + 1 + j);

    /* Merge the temp arrays */

    // Initial indexes of first and second subarrays
    int i = 0, j = 0;

    // Initial index of merged subarry array
    int k = l;
    while (i < n1 && j < n2) {
      if (L[i] <= R[j]) {
        array.set(k, L[i]);
        i++;
      }
      else {
        array.set(k, R[j]);
        j++;
      }
      k++;
    }

    /* Copy remaining elements of L[] if any */
    while (i < n1) {
      array.set(k, L[i]);
      i++;
      k++;
    }

    /* Copy remaining elements of R[] if any */
    while (j < n2) {
      array.set(k, R[j]);
      j++;
      k++;
    }
  }

}
