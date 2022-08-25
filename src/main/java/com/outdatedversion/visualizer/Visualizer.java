package com.outdatedversion.visualizer;

import com.outdatedversion.visualizer.operation.Operation;
import org.jetbrains.annotations.NotNull;

public interface Visualizer {

  enum RenderIntent {
    /**
     * Something happened that we believe the user should have time to process.
     */
    WAIT,
    /**
     * We believe moving to the next frame right away will be ok.
     */
    CONTINUE
  }

  /**
   * Hook to play the provided operation onto the visualizer stage.
   *
   * @param operation The array operation to process
   *
   * @return Instructs the playback controller if the rendering of the option
   * provided should pause before moving on.
   */
  @NotNull Visualizer.RenderIntent tick(@NotNull Operation operation);

  /**
   * Called before playback starts (before {@link #tick(Operation)} starts)
   */
  default void onStart() {
  }

  /**
   * Called after playback ends (before {@link #tick(Operation)} starts)
   */
  default void onEnd() {
  }

}
