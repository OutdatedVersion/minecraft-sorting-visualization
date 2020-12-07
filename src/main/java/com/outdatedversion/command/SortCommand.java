package com.outdatedversion.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Default;
import com.outdatedversion.AlgorithmVisualizerPlugin;
import com.outdatedversion.algorithm.SortingAlgorithm;
import com.outdatedversion.algorithm.SortingAlgorithmFactory;
import com.outdatedversion.algorithm.SortingAlgorithmType;
import com.outdatedversion.visualizer.BlockWallVisualizer;
import com.outdatedversion.visualizer.VisualizedArray;
import com.outdatedversion.visualizer.Visualizer;
import com.outdatedversion.visualizer.operation.GetOperation;
import com.outdatedversion.visualizer.operation.Operation;
import com.outdatedversion.visualizer.operation.SetOperation;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Ben Watkins
 * @since Nov/17/2020
 */
@CommandAlias("sort|sorting")
@RequiredArgsConstructor
public class SortCommand extends BaseCommand {

  private final AlgorithmVisualizerPlugin plugin;

  @Default
  @CommandCompletion("@sortingAlgorithm")
  public void onSort(Player player, SortingAlgorithmType algorithm, @Default("55") int elementCount, @Default("30") int elementMaxValue) {
    SortingAlgorithm sorter = SortingAlgorithmFactory.fromType(algorithm);

    int[] array = new int[elementCount];
    for (int i = 0; i < elementCount; i++) {
      array[i] = ThreadLocalRandom.current().nextInt(1, elementMaxValue);
    }

    player.sendMessage(ChatColor.GOLD + "Performing " + algorithm.getFriendlyName() + " sort on " + elementCount + " elements");
    player.sendMessage(ChatColor.GRAY + "Array: " + Arrays.toString(array));

    VisualizedArray visualizedArray = new VisualizedArray(array);
    long sortStartedAt = System.nanoTime();
    sorter.performSort(visualizedArray);
    long sortEndedAt = System.nanoTime();

    // TODO: Use player#getTargetBlock(int)- the visualizer needs to support navigating differently on axises
    Visualizer visualizer = new BlockWallVisualizer(visualizedArray, player.getLocation().add(0, player.getEyeHeight(), -30));
    visualizer.onStart();

    Iterator<Operation> iterator = visualizedArray.getOperations().iterator();
    Bukkit.getScheduler().runTaskTimer(plugin, task -> {
      if (iterator.hasNext()) {
        Visualizer.RenderIntent intent = visualizer.tick(iterator.next());
        while (intent == Visualizer.RenderIntent.CONTINUE) {
          intent = visualizer.tick(iterator.next());
        }
      } else {
        task.cancel();
        visualizer.onEnd();

        long accessCount = visualizedArray.getOperationCount(GetOperation.class);
        long setCount = visualizedArray.getOperationCount(SetOperation.class);
        long sortTimeNs = sortEndedAt - sortStartedAt;
        player.sendMessage(new ComponentBuilder().append("Completed sort in ").color(ChatColor.GREEN).append(sortTimeNs + "ns").color(ChatColor.YELLOW).create());
        player.sendMessage(new ComponentBuilder().append("Accesses: ").color(ChatColor.DARK_AQUA).append("" + accessCount).italic(true).append(", ").italic(false).append("Sets: ").append("" + setCount).italic(true).create());
        player.sendMessage(new ComponentBuilder().append("Sorted array: ").color(ChatColor.GRAY).italic(false).underlined(false).append(Arrays.toString(array)).italic(true).create());
      }
    }, 0, 1 /* 50ms */);
  }

}
