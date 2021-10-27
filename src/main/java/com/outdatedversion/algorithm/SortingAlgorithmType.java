package com.outdatedversion.algorithm;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

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
  BUBBLE("Bubble"),

  /**
   * @see IntelligentDesignSort
   */
  INTELLIGENT_DESIGN("Intelligent Design Sort");

  private final String friendlyName;
}
