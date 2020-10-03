package com.iridium.iridiumskyblock.gui;

import com.iridium.iridiumskyblock.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class VisitorGUI extends GUI implements Listener {
    public Map<Integer,Player> visitors = new HashMap<>();

    public VisitorGUI(Island island) {
        super(island, IridiumSkyblock.getInventories().visitorGUISize, IridiumSkyblock.getInventories().visitorGUITitle);
        IridiumSkyblock.getInstance().registerListeners(this);
    }
    @Override
    public void addContent() {
        super.addContent();
        if (getInventory().getViewers().isEmpty()) return;
        Island island = getIsland();
        if (island == null) return;
        visitors.clear();
        setItem(getInventory().getSize() - 5, Utils.makeItem(IridiumSkyblock.getInventories().back));
        int i = 0;
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (island.isInIsland(p.getLocation()) && (island != User.getUser(p).getIsland())) {
                ItemStack head = Utils.makeItem(IridiumSkyblock.getInventories().islandVisitors, Collections.singletonList(new Utils.Placeholder("player", p.getName())));
                setItem(i, head);
                visitors.put(i,p);
                i++;
            }
        }
        for (int x = 0; x < getInventory().getSize(); x++) {
            if (!visitors.containsKey(x) && x != getInventory().getSize() - 5 ){
                setItem(x,Utils.makeItemHidden(IridiumSkyblock.getInventories().background));
            }
        }
    }
    @Override
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().equals(getInventory())) {
            Player p = (Player) e.getWhoClicked();
            Island island = getIsland();
            e.setCancelled(true);
            int i = e.getSlot();
            if (i == getInventory().getSize() - 5) {
                e.getWhoClicked().openInventory(getIsland().getIslandMenuGUI().getInventory());
            }
            if (e.getClickedInventory() == null || !e.getClickedInventory().equals(getInventory())) return;
            if (visitors.containsKey(i)) {
                Player visitor = visitors.get(i);
                if (island.isInIsland(visitor.getLocation())) {
                    if (User.getUser(visitor).bypassing) {
                        e.getWhoClicked().sendMessage(Utils.color(IridiumSkyblock.getMessages().cantExpelPlayer.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix).replace("%player%", visitor.getName() + "")));
                    } else {
                        island.spawnPlayer(visitor);
                        visitors.clear();
                        p.sendMessage(Utils.color(IridiumSkyblock.getMessages().expelledVisitor.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix).replace("%player%", visitor.getName() + "")));
                        visitor.sendMessage(Utils.color(IridiumSkyblock.getMessages().youHaveBeenExpelled.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix).replace("%kicker%", p.getName() + "")));
                        p.updateInventory();
                    }
                }
            }
        }
    }
}