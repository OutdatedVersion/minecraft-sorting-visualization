package com.outdatedversion.algorithm;

import com.outdatedversion.visualizer.VisualizedArray;

import java.util.Arrays;

/**
 * Implementation of the radix sorting algorithm from https://www.geeksforgeeks.org/radix-sort/
 *
 * @author Ben Watkins
 * @since Dec/06/2020
 */
public class RadixSort implements SortingAlgorithm {

  @Override
  public void performSort(VisualizedArray array) {
    this.radixSort(array, array.length());
  }

  private int getMax(VisualizedArray array, int n) {
    int mx = array.get(0);
    for (int i = 1; i < n; i++)
      if (array.get(i) > mx)
        mx = array.get(i);
    return mx;
  }

  // A function to do counting sort of arr[] according to
  // the digit represented by exp.
  private void countSort(VisualizedArray array, int n, int exp) {
    int[] output = new int[n]; // output array
    int i;
    int[] count = new int[10];
    Arrays.fill(count, 0);

    // Store count of occurrences in count[]
    for (i = 0; i < n; i++) {
      count[(array.get(i) / exp) % 10]++;
      array.markCycle();
    }

    // Change count[i] so that count[i] now contains
    // actual position of this digit in output[]
    for (i = 1; i < 10; i++) {
      count[i] += count[i - 1];
    }

    // Build the output array
    for (i = n - 1; i >= 0; i--) {
      int cache = array.get(i); // DIFFERENCE: Cached to prevent re-render of get
      output[count[(cache / exp) % 10] - 1] = cache;
      count[(cache / exp) % 10]--;
      array.markCycle();
    }

    // Copy the output array to arr[], so that arr[] now
    // contains sorted numbers according to curent digit
    for (i = 0; i < n; i++) {
      array.set(i, output[i]);
      array.markCycle();
    }
  }

  // The main function to that sorts arr[] of size n using
  // Radix Sort
  private void radixSort(VisualizedArray array, int n) {
    // Find the maximum number to know number of digits
    int m = getMax(array, n);
    array.markCycle();

    // Do counting sort for every digit. Note that
    // instead of passing digit number, exp is passed.
    // exp is 10^i where i is current digit number
    for (int exp = 1; m / exp > 0; exp *= 10) {
      countSort(array, n, exp);
    }
  }

}
