package com.outdatedversion.algorithm;

import org.apache.commons.lang.NotImplementedException;

public class SortingAlgorithmFactory {

  public static SortingAlgorithm fromType(SortingAlgorithmType type) {
    switch (type) {
      case MERGE:
        return new MergeSort();
      case INSERTION:
        return new InsertionSort();
      case QUICK:
        return new QuickSort();
      case RADIX:
        return new RadixSort();
      case HEAP:
        return new HeapSort();
      case BUBBLE:
        return new BubbleSort();
      case INTELLIGENT_DESIGN:
        return new IntelligentDesignSort();
      default:
        throw new NotImplementedException("No implementation for sorting type: " + type.name());
    }
  }

}
