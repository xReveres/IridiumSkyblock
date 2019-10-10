package com.peaches.iridiumskyblock.gui;

import com.peaches.iridiumskyblock.IridiumSkyblock;
import com.peaches.iridiumskyblock.Island;
import com.peaches.iridiumskyblock.User;
import com.peaches.iridiumskyblock.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;
import java.util.HashMap;

public class WarpGUI implements Listener {

    public Inventory inventory;
    public int islandID;
    public int scheduler;
    public HashMap<Integer, Island.Warp> warps = new HashMap<>();

    public WarpGUI(Island island) {
        this.inventory = Bukkit.createInventory(null, 27, Utils.color(IridiumSkyblock.getConfiguration().warpGUITitle));
        islandID = island.getId();
        scheduler = Bukkit.getScheduler().scheduleAsyncRepeatingTask(IridiumSkyblock.getInstance(), this::addContent, 0, 10);
        IridiumSkyblock.getInstance().registerListeners(this);
    }

    public void addContent() {
        try {
            if (IridiumSkyblock.getIslandManager().islands.containsKey(islandID)) {
                Island island = IridiumSkyblock.getIslandManager().islands.get(islandID);
                for (int i = 0; i < 27; i++) {
                    inventory.setItem(i, Utils.makeItem(Material.STAINED_GLASS_PANE, 1, 15, " "));
                }
                int i = 9;
                warps.clear();
                for (Island.Warp warp : island.getWarps()) {
                    warps.put(i, warp);
                    inventory.setItem(i, Utils.makeItem(Material.STAINED_GLASS_PANE, 1, 4, Utils.color("&b&l" + warp.getName()), Utils.color(Arrays.asList("", "&b&l[!] &bLeft Click to Teleport to this warp.", "&b&l[!] &bRight Click to Delete to warp."))));
                    i++;
                }
            }
        } catch (Exception e) {
            IridiumSkyblock.getInstance().sendErrorMessage(e);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().equals(inventory)) {
            Player p = (Player) e.getWhoClicked();
            User u = User.getUser(p);
            e.setCancelled(true);
            if (warps.containsKey(e.getSlot())) {
                Island.Warp warp = warps.get(e.getSlot());
                if (e.getClick().equals(ClickType.RIGHT)) {
                    u.getIsland().removeWarp(warps.get(e.getSlot()));
                } else {
                    if (warp.getPassword().isEmpty()) {
                        p.teleport(warp.getLocation());
                        p.sendMessage(Utils.color(IridiumSkyblock.getMessages().teleporting.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                    } else {
                        p.sendMessage(Utils.color(IridiumSkyblock.getMessages().enterPassword.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                        u.warp = warp;
                    }
                }
                p.closeInventory();
            }
        }
    }
}
