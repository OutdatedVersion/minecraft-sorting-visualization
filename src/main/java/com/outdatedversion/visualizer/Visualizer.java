package com.outdatedversion.visualizer;

import com.outdatedversion.visualizer.operation.Operation;
import org.jetbrains.annotations.NotNull;

/**
 * @author Ben Watkins
 * @since Dec/05/2020
 */
public interface Visualizer {

  enum RenderIntent {
    // Something happened that we need time to look at
    WAIT,
    // Continues
    CONTINUE
  }

  @NotNull Visualizer.RenderIntent tick(@NotNull Operation operation);

  default void onStart() {};
  default void onEnd() {};

}
