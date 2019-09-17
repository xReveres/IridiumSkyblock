package com.peaches.epicskyblock.gui;

import com.peaches.epicskyblock.EpicSkyblock;
import com.peaches.epicskyblock.Island;
import com.peaches.epicskyblock.User;
import com.peaches.epicskyblock.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TopGUI implements Listener {

    public Inventory inventory;

    public int scheduler;

    public HashMap<Integer, Integer> islands = new HashMap<>();

    public TopGUI() {
        inventory = Bukkit.createInventory(null, 27, Utils.color(EpicSkyblock.getConfiguration().TopGUITitle));
        scheduler = Bukkit.getScheduler().scheduleAsyncRepeatingTask(EpicSkyblock.getInstance(), this::addContent, 0, 20);
    }

    public void addContent() {
        try {
            for (int i = 0; i < 27; i++) {
                inventory.setItem(i, Utils.makeItem(Material.STAINED_GLASS_PANE, 1, 15, " "));
            }
            List<Island> top = Utils.getTopIslands();
            for (int i = 1; i <= 10; i++) {
                if (top.size() >= i) {
                    ArrayList<String> lore = new ArrayList<>();
                    Island island = top.get(i - 1);
                    User owner = User.getUser(island.getOwner());
                    lore.add("&b&l * &7Leader: &b" + owner.name);
                    lore.add("&b&l * &7Rank: &b" + i);
                    lore.add("&b&l * &7Value: &b" + island.getValue());
                    lore.add("");
                    lore.add("&b&l[!] &bLeft Click to Teleport to this island.");
                    ItemStack head = Utils.makeItem(Material.SKULL_ITEM, 1, 3, "&b&l" + owner.name, Utils.color(lore));
                    SkullMeta m = (SkullMeta) head.getItemMeta();
                    m.setOwner(owner.name);
                    head.setItemMeta(m);
                    islands.put(EpicSkyblock.getConfiguration().islandTopSlots.get(i), island.getId());
                    inventory.setItem(EpicSkyblock.getConfiguration().islandTopSlots.get(i), head);
                }
            }
        } catch (Exception e) {
            EpicSkyblock.getInstance().sendErrorMessage(e);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().equals(inventory)) {
            e.setCancelled(true);
            if (islands.containsKey(e.getSlot())) {
                e.getWhoClicked().closeInventory();
                EpicSkyblock.getIslandManager().getIslandViaId(islands.get(e.getSlot())).teleportHome((Player) e.getWhoClicked());
            }
        }
    }
}