package com.outdatedversion.algorithm;

import com.outdatedversion.visualizer.VisualizedArray;

/**
 * Implementation of the bubble sorting algorithm pulled from https://www.geeksforgeeks.org/bubble-sort/
 */
public class BubbleSort implements SortingAlgorithm {

  @Override
  public void performSort(VisualizedArray array) {
    int n = array.length();
    int i, j, temp;
    boolean swapped;
    for (i = 0; i < n - 1; i++) {
      swapped = false;
      for (j = 0; j < n - i - 1; j++) {
        if (array.get(j) > array.get(j + 1)) {
          temp = array.get(j);
          array.set(j, array.get(j + 1));
          array.set(j + 1, temp);
          swapped = true;
        }
        array.markCycle();
      }

      if (!swapped) {
        break;
      }
    }
  }

}
