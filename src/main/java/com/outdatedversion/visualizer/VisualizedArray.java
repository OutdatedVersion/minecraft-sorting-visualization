package com.outdatedversion.visualizer;

import com.outdatedversion.visualizer.operation.CycleOperation;
import com.outdatedversion.visualizer.operation.GetOperation;
import com.outdatedversion.visualizer.operation.Operation;
import com.outdatedversion.visualizer.operation.SetOperation;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class VisualizedArray {

  @Getter(AccessLevel.PACKAGE) private final int[] initialArray;
  @Getter(AccessLevel.PACKAGE) private final int[] workingArray;
  private final List<Operation> operations = new ArrayList<>();

  public VisualizedArray(int[] array) {
    this.workingArray = array;
    this.initialArray = Arrays.copyOf(array, array.length);
  }

  public void set(int index, int value) {
    this.workingArray[index] = value;
    this.operations.add(new SetOperation(index, value));
  }

  public int get(int index) {
    int value = workingArray[index];
    this.operations.add(new GetOperation(index, value));
    return value;
  }

  public int length() {
    return this.workingArray.length;
  }

  public void markCycle() {
    this.operations.add(new CycleOperation());
  }

  public List<Operation> getOperations() {
    return Collections.unmodifiableList(this.operations);
  }

  public long getOperationCount(Class<? extends Operation> clazz) {
    return this.operations.stream().filter(clazz::isInstance).count();
  }

}
