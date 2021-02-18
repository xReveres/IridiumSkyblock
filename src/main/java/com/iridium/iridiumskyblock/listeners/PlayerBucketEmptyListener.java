package com.iridium.iridiumskyblock.listeners;

import com.cryptomorin.xseries.XMaterial;
import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.User;
import com.iridium.iridiumskyblock.managers.IslandManager;
import org.bukkit.Bukkit;
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

import java.lang.reflect.InvocationTargetException;

public class PlayerBucketEmptyListener implements Listener {

    public final boolean supports = XMaterial.supports(13);

    @EventHandler
    public void onBucketEmpty(PlayerBucketEmptyEvent event) {
        if (!IslandManager.isIslandWorld(event.getPlayer().getLocation())) return;
        
        final Material type = event.getBucket();
        final ItemStack item = event.getPlayer().getItemInHand();
        Block block;
        final boolean isInPaper113 = Bukkit.getVersion().contains("Paper") && Bukkit.getBukkitVersion().contains("1.13");
        if (supports && !isInPaper113) {
            block = event.getBlock();
        } else {
            try {
                block = (Block) PlayerBucketEmptyEvent.class.getMethod("getBlockClicked").invoke(event);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                return;
            }
        }

        // Temporary fix until https://hub.spigotmc.org/jira/browse/SPIGOT-6351 is resolved
        User user = User.getUser(event.getPlayer());
        Island island = IslandManager.getIslandViaLocation(block.getLocation());
        if ((island != null && user.getIsland() != null && user.islandID != island.id && !island.isCoop(user.getIsland())) || (island != null && user.getIsland() == null)) {
            event.setCancelled(true);
        } 

        final Player player = event.getPlayer();
        if (IridiumSkyblock.getInstance().getConfiguration().allowWaterInNether) {
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
