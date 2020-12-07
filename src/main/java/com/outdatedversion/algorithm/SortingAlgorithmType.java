package com.outdatedversion.algorithm;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Ben Watkins
 * @since Dec/05/2020
 */
@Getter
@RequiredArgsConstructor
public enum SortingAlgorithmType {
  /**
   * @see QuickSort
   */
  QUICK("Quick"),

  /**
   * @see MergeSort
   */
  MERGE("Merge"),

  /**
   * @see InsertionSort
   */
  INSERTION("Insertion"),

  /**
   * @see RadixSort
   */
  RADIX("Radix"),

  /**
   * @see HeapSort
   */
  HEAP("Heap"),

  /**
   * @see BubbleSort
   */
  BUBBLE("Bubble");

  private final String friendlyName;
}
