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
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class onClick implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
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
            }
            if (IridiumSkyblock.getConfiguration().allowWaterInNether && e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getItem() != null) {
                if (e.getClickedBlock().getWorld().getEnvironment().equals(World.Environment.NETHER)) {
                    if (e.getItem().getType().equals(Material.WATER_BUCKET)) {
                        e.setCancelled(true);
                        e.getClickedBlock().getRelative(e.getBlockFace()).setType(Material.WATER);
                        BlockPlaceEvent event = new BlockPlaceEvent(e.getClickedBlock().getRelative(e.getBlockFace()), e.getClickedBlock().getRelative(e.getBlockFace()).getState(), e.getClickedBlock(), e.getItem(), e.getPlayer(), false, EquipmentSlot.HAND);
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
}
