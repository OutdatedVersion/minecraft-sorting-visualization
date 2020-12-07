package com.outdatedversion;

import co.aikar.commands.CommandManager;
import co.aikar.commands.PaperCommandManager;
import com.outdatedversion.algorithm.SortingAlgorithmType;
import com.outdatedversion.command.SortCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.stream.Stream;

/**
 * @author Ben Watkins
 * @since May/11/2019
 */
public class AlgorithmVisualizerPlugin extends JavaPlugin {

  @Override
  public void onEnable() {
    final CommandManager commandManager = new PaperCommandManager(this);

    commandManager.getCommandCompletions().registerStaticCompletion("sortingAlgorithm", Stream.of(SortingAlgorithmType.values()).map(Enum::name).map(String::toLowerCase).toArray(String[]::new));
    commandManager.registerCommand(new SortCommand(this));
  }

  @Override
  public void onDisable() {
    // TODO: Store state of currently running visualizers?
  }

}
