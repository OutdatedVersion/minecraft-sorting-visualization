package com.outdatedversion.algorithm;

import com.outdatedversion.visualizer.VisualizedArray;

/**
 * Implementation of the intelligent design sorting algorithm from
 * https://www.dangermouse.net/esoteric/intelligentdesignsort.html
 *
 * This algorithm is constant in time, and sorts the list in-place, requiring no
 * additional memory at all. In fact, it doesn't even require any of that
 * suspicious technological computer stuff. Praise the Sorter!
 */
public class IntelligentDesignSort implements SortingAlgorithm {

  @Override
  public void performSort(VisualizedArray array) {
    /*
     * The probability of the original input list being in the exact order it's
     * in is 1/(n!). There is such a small likelihood of this that it's clearly
     * absurd to say that this happened by chance, so it must have been
     * consciously put in that order by an intelligent Sorter. Therefore it's
     * safe to assume that it's already optimally Sorted in some way that
     * transcends our naïve mortal understanding of "ascending order". Any
     * attempt to change that order to conform to our own preconceptions would
     * actually make it less sorted.
     */
  }

}
