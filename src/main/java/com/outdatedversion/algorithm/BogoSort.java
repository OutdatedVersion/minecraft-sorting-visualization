package com.outdatedversion.algorithm;

import java.util.concurrent.ThreadLocalRandom;

import com.outdatedversion.visualizer.VisualizedArray;

/**
 * Implementation of the bogo sorting algorithm: https://en.wikipedia.org/wiki/Bogosort
 */
public class BogoSort implements SortingAlgorithm {
  @Override
  public void performSort(VisualizedArray array) {
    while (!isSorted(array)) {
      shuffle(array);
    }
  }

  private void shuffle(VisualizedArray array) {
    int n = array.length();
    int i, j, temp;
    for (i = 0; i < n ; i++) {
      j = ThreadLocalRandom.current().nextInt();
      temp = array.get(i);
      array.set(i, array.get(j));
      array.set(j, temp);
      array.markCycle();
    }
  }

  private boolean isSorted(VisualizedArray array) {
    for (int i = 1; i < array.length(); i++) {
      if (array.get(i) < array.get(i - 1)) {
        return false;
      }
    }
    return true;
  }

}
