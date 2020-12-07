package com.outdatedversion.algorithm;

import com.outdatedversion.visualizer.VisualizedArray;

/**
 * @author Ben Watkins
 * @since Dec/05/2020
 */
public interface SortingAlgorithm {

  /**
   * Start the <strong>in-place</strong> sorting of an array. We use the {@link
   * VisualizedArray} wrapper here so we may store operations to replay later.
   *
   * @param array Array to sort
   */
  void performSort(VisualizedArray array);

}
