package com.iridium.iridiumskyblock.listeners;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.Utils;
import com.iridium.iridiumskyblock.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class onEntityExplode implements Listener {

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e) {
        if (e.getEntity().getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getWorld()) || e.getEntity().getLocation().getWorld().equals(IridiumSkyblock.getIslandManager().getNetherWorld())) {
            if (!IridiumSkyblock.getConfiguration().alowExplosions) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onMonitorEntityExplode(EntityExplodeEvent e) {
        if (IridiumSkyblock.getInstance().entities.containsKey(e.getEntity().getUniqueId())) {
            if (!IridiumSkyblock.getInstance().entities.get(e.getEntity().getUniqueId()).isInIsland(e.getEntity().getLocation())) {
                e.setCancelled(true);
                e.getEntity().remove();
                IridiumSkyblock.getInstance().entities.remove(e.getEntity().getUniqueId());
                return;
            }
        }
        Island island = IridiumSkyblock.getIslandManager().getIslandViaLocation(e.getLocation());
        if (island != null) {
            for (Block b : e.blockList()) {
                if (!island.isInIsland(b.getLocation())) {
                    final BlockState state = b.getState();
                    IridiumSkyblock.nms.setBlockFast(b, 0, (byte) 0);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(IridiumSkyblock.getInstance(), () -> state.update(true, true));
                }
                if (Utils.isBlockValuable(b)) {
                    if (!(b.getState() instanceof CreatureSpawner)) {
                        if (island.valuableBlocks.containsKey(XMaterial.matchXMaterial(b.getType()).name())) {
                            island.valuableBlocks.put(XMaterial.matchXMaterial(b.getType()).name(), island.valuableBlocks.get(XMaterial.matchXMaterial(b.getType()).name()) - 1);
                        }
                    }
                    if (island.updating) {
                        island.tempValues.remove(b.getLocation());
                    }
                }
            }
            island.calculateIslandValue();
        }
    }
}
