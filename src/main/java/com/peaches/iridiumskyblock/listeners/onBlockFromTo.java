package com.peaches.iridiumskyblock.listeners;

import com.peaches.iridiumskyblock.IridiumSkyblock;
import com.peaches.iridiumskyblock.Island;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

import java.util.*;

public class onBlockFromTo implements Listener {

    @EventHandler
    public void onBlockFromTo(BlockFromToEvent e) {
        try {
            if (e.getFace() != BlockFace.DOWN) {
                Block b = e.getToBlock();
                Location fromLoc = b.getLocation();
                Bukkit.getScheduler().runTask(IridiumSkyblock.getInstance(), () -> {
                    if (b.getType().equals(Material.COBBLESTONE) || b.getType().equals(Material.STONE)) {
                        if (!isSurroundedByWater(fromLoc)) {
                            return;
                        }

                        Island island = IridiumSkyblock.getIslandManager().getIslandViaLocation(fromLoc);
                        if (island != null) {
                            Random r = new Random();
                            ArrayList<String> items = new ArrayList<>();
                            for (String item : IridiumSkyblock.getUpgrades().ores.get(island.getOreLevel()).getOres()) {
                                Integer i1 = Integer.parseInt(item.split(":")[1]);
                                for (int i = 0; i <= i1; i++) {
                                    items.add(item.split(":")[0]);
                                }
                            }
                            String item = items.get(r.nextInt(items.size()));
                            if (Material.getMaterial(item) == null) return;
                            e.setCancelled(true);
                            b.setType(Material.getMaterial(item));
                            b.getState().update(true);
                        }
                    }
                });
            }
        } catch (Exception ex) {
            IridiumSkyblock.getInstance().sendErrorMessage(ex);
        }
    }

    public boolean isSurroundedByWater(Location fromLoc) {
        // Sets gives better performance than arrays when used wisely
        Set<Block> blocksSet = new HashSet<>(Arrays.asList(fromLoc.getWorld().getBlockAt(fromLoc.getBlockX() + 1, fromLoc.getBlockY(), fromLoc.getBlockZ()),
                fromLoc.getWorld().getBlockAt(fromLoc.getBlockX() - 1, fromLoc.getBlockY(), fromLoc.getBlockZ()),
                fromLoc.getWorld().getBlockAt(fromLoc.getBlockX(), fromLoc.getBlockY(), fromLoc.getBlockZ() + 1),
                fromLoc.getWorld().getBlockAt(fromLoc.getBlockX(), fromLoc.getBlockY(), fromLoc.getBlockZ() - 1)));

        for (Block b : blocksSet) {
            if (b.getType().toString().contains("WATER")) return true;
            continue;
        }
        return false;

    }
}
