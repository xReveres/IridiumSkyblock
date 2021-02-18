package com.iridium.iridiumskyblock.nms;

import com.cryptomorin.xseries.XMaterial;
import com.iridium.iridiumskyblock.Color;
import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.User;
import com.iridium.iridiumskyblock.utils.MiscUtils;
import com.iridium.iridiumskyblock.utils.StringUtils;
import net.minecraft.server.v1_16_R2.*;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.craftbukkit.v1_16_R2.CraftChunk;
import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;

import java.util.List;

public class v1_16_R2 implements NMS {
    @Override
    public void setBlockFast(Block block, int blockId, byte data) {
        BlockState state = block.getState();
        if (state.getType().name().endsWith("AIR") && blockId == 0) return;
        if (state instanceof InventoryHolder) {
            ((InventoryHolder) state).getInventory().clear();
        }
        XMaterial material = MiscUtils.getXMaterialFromId(blockId, (byte) 0);
        if (material != null && material.parseMaterial() != null) {
            block.setBlockData(IridiumSkyblock.getInstance().fromLegacy(material.parseMaterial(), data), false);
        }
    }

    @Override
    public void sendChunk(Player player, Chunk chunk) {
        PacketPlayOutMapChunk packetPlayOutMapChunk = new PacketPlayOutMapChunk(((CraftChunk) chunk).getHandle(), 65535);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetPlayOutMapChunk);
    }

    @Override
    public void sendWorldBorder(Player player, Color color, double size, Location centerLocation) {
        WorldBorder worldBorder = new WorldBorder();
        worldBorder.world = ((CraftWorld) centerLocation.getWorld()).getHandle();
        worldBorder.setCenter(centerLocation.getBlockX() + 0.5, centerLocation.getBlockZ() + 0.5);

        if (color == Color.Off) {
            worldBorder.setSize(Integer.MAX_VALUE);
        } else {
            worldBorder.setSize(size);
        }

        worldBorder.setWarningDistance(0);
        worldBorder.setWarningTime(0);

        if (color == Color.Red) {
            worldBorder.transitionSizeBetween(size, size - 1.0D, 20000000L);
        } else if (color == Color.Green) {
            worldBorder.transitionSizeBetween(size - 0.1D, size, 20000000L);
        }
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutWorldBorder(worldBorder, PacketPlayOutWorldBorder.EnumWorldBorderAction.INITIALIZE));
    }

    @Override
    public void sendSubTitle(Player player, String message, int fadeIn, int displayTime, int fadeOut) {
        IChatBaseComponent iChatBaseComponent = IChatBaseComponent.ChatSerializer.a(ChatColor.translateAlternateColorCodes('&', "{\"text\":\"" + message + "\"}"));
        PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, iChatBaseComponent, fadeIn, displayTime, fadeOut);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetPlayOutTitle);
    }

    @Override
    public void sendTitle(Player player, String message, int fadeIn, int displayTime, int fadeOut) {
        IChatBaseComponent iChatBaseComponent = IChatBaseComponent.ChatSerializer.a(ChatColor.translateAlternateColorCodes('&', "{\"text\":\"" + message + "\"}"));
        PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, iChatBaseComponent, fadeIn, displayTime, fadeOut);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetPlayOutTitle);
    }

    @Override
    public void sendHologram(Player player, Location location, List<String> text) {
        location = location.add(0, 0.4 * (text.size() - 1), 0);
        User user = User.getUser(player);
        CraftWorld craftWorld = (CraftWorld) location.getWorld();
        for (int i = -1; ++i < text.size(); ) {
            EntityArmorStand entityArmorStand = new EntityArmorStand(craftWorld.getHandle(), location.getX(), location.getY(), location.getZ());

            entityArmorStand.setInvisible(true);
            entityArmorStand.setCustomNameVisible(true);
            entityArmorStand.setCustomName(new ChatMessage(StringUtils.color(text.get(i))));

            user.addHologram(entityArmorStand);

            PacketPlayOutSpawnEntityLiving packetPlayOutSpawnEntityLiving = new PacketPlayOutSpawnEntityLiving(entityArmorStand);
            PacketPlayOutEntityMetadata packetPlayOutEntityMetadata = new PacketPlayOutEntityMetadata(entityArmorStand.getId(), entityArmorStand.getDataWatcher(), true);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetPlayOutSpawnEntityLiving);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetPlayOutEntityMetadata);
            location = location.subtract(0, 0.4, 0);
        }
    }

    @Override
    public void removeHologram(Player player, Object hologram) {
        EntityArmorStand entityArmorStand = (EntityArmorStand) hologram;
        PacketPlayOutEntityDestroy packetPlayOutEntityDestroy = new PacketPlayOutEntityDestroy(entityArmorStand.getId());
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetPlayOutEntityDestroy);
    }
}
