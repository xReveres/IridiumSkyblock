package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerBucketEmptyListener implements Listener {

  @EventHandler
  public void onBucketEmpty(PlayerBucketEmptyEvent event) {
    final Material type = event.getBucket();
    final ItemStack item = event.getPlayer().getItemInHand();
    Block block = event.getBlock();
    final Player player = event.getPlayer();
    if (IridiumSkyblock.getConfiguration().allowWaterInNether) {
      final World world = block.getWorld();
      if (!world.getEnvironment().equals(World.Environment.NETHER)) return;
      if (type != Material.WATER_BUCKET) return;

      // To prevent waterloggable blocks from being replaced by the water
      if (block.getType() != Material.AIR) {
        block = block.getRelative(event.getBlockFace());
      }

      event.setCancelled(true);

      final BlockFace face = event.getBlockFace();
      block.setType(Material.WATER);

      final BlockPlaceEvent blockPlaceEvent = new BlockPlaceEvent(block, block.getState(), block.getRelative(face.getOppositeFace()), item, player, false);
      if (blockPlaceEvent.isCancelled()) {
        block.setType(Material.AIR);
      } else if (player.getGameMode().equals(GameMode.SURVIVAL)) {
        if (item.getAmount() == 1) {
          item.setType(Material.BUCKET);
        } else {
          item.setAmount(item.getAmount() - 1);
          player.getInventory().addItem(new ItemStack(Material.BUCKET));
        }
      }
    }
  }

}
