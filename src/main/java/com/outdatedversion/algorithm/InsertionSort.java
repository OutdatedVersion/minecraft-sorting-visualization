package com.outdatedversion.algorithm;

import com.outdatedversion.visualizer.VisualizedArray;

/**
 * Implementation of the insertion sorting algorithm from
 * https://www.geeksforgeeks.org/insertion-sort/
 *
 * @author Ben Watkins
 * @since Dec/05/2020
 */
public class InsertionSort implements SortingAlgorithm {

  @Override
  public void performSort(VisualizedArray array) {
    int length = array.length();
    for (int i = 1; i < length; ++i) {
      int key = array.get(i);
      int j = i - 1;

      while (j >= 0 && array.get(j) > key) {
        array.set(j + 1, array.get(j));
        j = j - 1;
        array.markCycle();
      }
      array.set(j + 1, key);
      array.markCycle();
    }
  }
}
