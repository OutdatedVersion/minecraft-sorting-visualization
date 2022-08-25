package com.outdatedversion.visualizer;

import com.outdatedversion.visualizer.operation.CycleOperation;
import com.outdatedversion.visualizer.operation.GetOperation;
import com.outdatedversion.visualizer.operation.Operation;
import com.outdatedversion.visualizer.operation.SetOperation;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.block.data.type.WallSign;
import org.jetbrains.annotations.NotNull;

public class BlockWallVisualizer implements Visualizer {

  private final VisualizedArray array;

  private int length; // array size is on X axis
  private int maxHeight; // array entries are on Y axis
  private Location origin;
  private Location topRight;

  public BlockWallVisualizer(VisualizedArray array, Location centerPoint) {
    this.array = array;

    this.length = array.getWorkingArray().length;
    this.maxHeight = array.getWorkingArray()[array.getWorkingArray().length - 1];
    this.origin = centerPoint.clone().subtract(length / 2, maxHeight / 2, 0);
    this.topRight = centerPoint.clone().add(length / 2, maxHeight / 2, 0); // idk what this is called
  }

  @NotNull
  @Override
  public Visualizer.RenderIntent tick(@NotNull Operation operation) {
    // For now, we don't add any sort of indicator for array access
    if (operation instanceof GetOperation) {
      this.setColumn(Material.BLUE_CONCRETE, operation.index(), operation.value());
      return RenderIntent.WAIT;
    }

    if (operation instanceof CycleOperation) {
      this.resetGraph();
      return RenderIntent.WAIT;
    }

    this.setColumn(Material.YELLOW_CONCRETE, operation.index(), operation.value());
    return RenderIntent.WAIT;
  }

  @Override
  public void onStart() {
    origin.getBlock().setType(Material.RED_CONCRETE);
    topRight.getBlock().setType(Material.BLUE_CONCRETE);

    for (int i = 0; i < length; i++) {
      this.setColumn(Material.LIGHT_GRAY_CONCRETE, i, array.getInitialArray()[i]);
    }
  }

  @Override
  public void onEnd() {
    for (int i = 0; i < length; i++) {
      for (int j = 0; j <= maxHeight; j++) {
        Block block = origin.clone().add(i, j, 0).getBlock();

        if (block.getType() == Material.AIR) {
          this.setSign(block.getLocation().clone().add(0, -1, 1), "Index: " + i, "Value: " + j);
          break;
        }

        block.setType(Material.GREEN_CONCRETE);
      }
    }

    long accessCount = array.getOperationCount(GetOperation.class);
    long setCount = array.getOperationCount(SetOperation.class);
    this.setSign(topRight.clone().add(0, 0, 1), "Accesses: " + accessCount, "Sets: " + setCount);
  }

  private void resetGraph() {
    for (int i = 0; i < length; i++) {
      for (int j = 0; j < maxHeight; j++) {
        Block block = origin.clone().add(i, j, 0).getBlock();

        // remove colors
        if (block.getType() != Material.AIR && block.getType() != Material.LIGHT_GRAY_CONCRETE) {
          block.setType(Material.LIGHT_GRAY_CONCRETE);
        }
      }
    }
  }

  private void setColumn(Material fillMaterial, int index, int height) {
    for (int i = 0; i < maxHeight; i++) {
      origin.clone().add(index, i, 0).getBlock().setType(i < height ? fillMaterial : Material.AIR);
    }
  }

  private void setSign(Location location, String... lines) {
    Block signBlock = location.getBlock();
    signBlock.setType(Material.SPRUCE_WALL_SIGN);
    Sign sign = (Sign) signBlock.getState();
    WallSign blockData = ((WallSign) signBlock.getBlockData());
    blockData.setFacing(BlockFace.SOUTH);
    sign.setBlockData(blockData);

    for (int i = 0; i < lines.length; i++) {
      sign.setLine(i, lines[i]);
    }

    sign.update();
  }

}
