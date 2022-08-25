package com.outdatedversion.visualizer.operation;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SetOperation implements Operation {

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
