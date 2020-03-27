package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.Role;
import com.iridium.iridiumskyblock.User;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class onPlayerInteract implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        try {
            User u = User.getUser(e.getPlayer());
            if (e.getClickedBlock() != null) {
                Island island = IridiumSkyblock.getIslandManager().getIslandViaLocation(e.getClickedBlock().getLocation());
                if (island != null) {
                    if ((!island.getPermissions((u.islandID == island.getId() || island.isCoop(u.getIsland())) ? (island.isCoop(u.getIsland()) ? Role.Member : u.getRole()) : Role.Visitor).interact) && !u.bypassing) {
                        e.setCancelled(true);
                        return;
                    }
                } else {
                    if (e.getClickedBlock().getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getWorld()) || e.getClickedBlock().getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getNetherWorld())) {
                        if (!u.bypassing) {
                            e.setCancelled(true);
                            return;
                        }
                    }
                }
                if (island != null) {
                    if (e.getPlayer().getItemInHand().getType().equals(Material.BUCKET)) {
                        if (island.failedGenerators.contains(e.getClickedBlock().getLocation())) {
                            island.failedGenerators.remove(e.getClickedBlock().getLocation());
                            if (e.getPlayer().getItemInHand().getAmount() == 1) {
                                e.getPlayer().getItemInHand().setType(Material.LAVA_BUCKET);
                            } else {
                                e.getPlayer().getInventory().addItem(new ItemStack(Material.LAVA_BUCKET));
                                e.getPlayer().getItemInHand().setAmount(e.getPlayer().getItemInHand().getAmount() - 1);
                            }
                            e.getClickedBlock().setType(Material.AIR);
                        }
                    }
                }
            }
            if (IridiumSkyblock.getConfiguration().allowWaterInNether && e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getItem() != null) {
                if (e.getClickedBlock().getWorld().getEnvironment().equals(World.Environment.NETHER)) {
                    if (e.getItem().getType().equals(Material.WATER_BUCKET)) {
                        e.setCancelled(true);
                        e.getClickedBlock().getRelative(e.getBlockFace()).setType(Material.WATER);
                        BlockPlaceEvent event = new BlockPlaceEvent(e.getClickedBlock().getRelative(e.getBlockFace()), e.getClickedBlock().getRelative(e.getBlockFace()).getState(), e.getClickedBlock(), e.getItem(), e.getPlayer(), false);
                        if (event.isCancelled()) {
                            e.getClickedBlock().getRelative(e.getBlockFace()).setType(Material.AIR);
                        } else {
                            if (e.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) {
                                if (e.getItem().getAmount() == 1) {
                                    e.getItem().setType(Material.BUCKET);
                                } else {
                                    e.getItem().setAmount(e.getItem().getAmount() - 1);
                                    e.getPlayer().getInventory().addItem(new ItemStack(Material.BUCKET));
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            IridiumSkyblock.getInstance().sendErrorMessage(ex);
        }
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
        User u = User.getUser(e.getPlayer());
        Island island = IridiumSkyblock.getIslandManager().getIslandViaLocation(e.getRightClicked().getLocation());
        if (island != null) {
            if ((!island.getPermissions((u.islandID == island.getId() || island.isCoop(u.getIsland())) ? (island.isCoop(u.getIsland()) ? Role.Member : u.getRole()) : Role.Visitor).interact) && !u.bypassing) {
                e.setCancelled(true);
            }
        } else {
            if (e.getRightClicked().getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getWorld()) || e.getRightClicked().getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getNetherWorld())) {
                if (!u.bypassing) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
