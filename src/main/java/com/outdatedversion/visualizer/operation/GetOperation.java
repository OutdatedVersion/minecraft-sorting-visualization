package com.outdatedversion.visualizer.operation;

import lombok.RequiredArgsConstructor;

/**
 * @author Ben Watkins
 * @since Dec/06/2020
 */
@RequiredArgsConstructor
public class GetOperation implements Operation {

  private final int index;
  private final int value;

  @Override
  public int index() {
    return index;
  }

  @Override
  public int value() {
    return value;
  }

}
