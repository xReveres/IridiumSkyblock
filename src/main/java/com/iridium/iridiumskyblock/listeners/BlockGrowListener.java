package com.iridium.iridiumskyblock.listeners;

import com.cryptomorin.xseries.XBlock;
import com.cryptomorin.xseries.XMaterial;
import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.managers.IslandManager;
import org.bukkit.CropState;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NetherWartsState;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.material.Crops;
import org.bukkit.material.MaterialData;
import org.bukkit.material.NetherWarts;

public class BlockGrowListener implements Listener {

    @EventHandler
    public void onBlockGrow(BlockGrowEvent event) {
        final Block block = event.getBlock();
        final Location location = block.getLocation();
        final Island island = IslandManager.getIslandViaLocation(location);
        if (island == null) return;

        if (island.getBoosterTime(IridiumSkyblock.getInstance().getBoosters().islandFarmingBooster.name) == 0) return;

        final Material material = block.getType();
        if (!XBlock.isCrop(XMaterial.matchXMaterial(material))) return;

        event.setCancelled(true);

        final BlockState blockState = block.getState();
        MaterialData data;
        if (XBlock.isNetherWart(material)) {
            data = new NetherWarts(NetherWartsState.RIPE);
        } else {
            data = new Crops(CropState.RIPE);
        }
        blockState.setData(data);
        blockState.update();
    }
}
