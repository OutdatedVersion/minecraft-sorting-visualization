package com.outdatedversion.algorithm;

import java.util.Random;
import com.outdatedversion.visualizer.VisualizedArray;

/**
 * Implementation of the bubble sorting algorithm from https://www.geeksforgeeks.org/bubble-sort/
 *
 * @author Keith Wade
 * @since Oct/27/2021
 */
public class BogoSort implements SortingAlgorithm {
  private Random random = new Random();

  @Override
  public void performSort(VisualizedArray array) {
    while (!isSorted(array)) {
      shuffle(array);
    }
  }

  private void shuffle(VisualizedArray array) {
    int n = array.length()
    int i, j, temp;
    for (i = 0; i < n ; i++) {
      j = random.nextInt(n);
      temp = array.get(i);
      array.set(i, array.get(r));
      array.set(r, temp);
      array.markCycle();
    }
  }

  private boolean isSorted(VisualizedArray array) {
    for (int i = 1; i < array.length; i++) {
      if (array.get(i) < array.get(i - 1)) {
        return false;
      }

      return true;
    }
  }

}
