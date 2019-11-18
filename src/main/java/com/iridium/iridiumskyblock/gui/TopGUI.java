package com.iridium.iridiumskyblock.gui;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.User;
import com.iridium.iridiumskyblock.Utils;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TopGUI extends GUI implements Listener {

    public HashMap<Integer, Integer> islands = new HashMap<>();

    public TopGUI() {
        super(27, IridiumSkyblock.getInventories().topGUITitle);
        IridiumSkyblock.getInstance().registerListeners(this);
    }

    @Override
    public void addContent() {
        super.addContent();
        List<Island> top = Utils.getTopIslands();
        for (int i = 1; i <= 10; i++) {
            if (top.size() >= i) {
                ArrayList<String> lore = new ArrayList<>();
                Island island = top.get(i - 1);
                User owner = User.getUser(island.getOwner());
                lore.add("&b&l * &7Leader: &b" + owner.name);
                lore.add("&b&l * &7Rank: &b" + i);
                lore.add("&b&l * &7Value: &b" + NumberFormat.getInstance().format(island.getValue()));
                lore.add("");
                lore.add("&b&l[!] &bLeft Click to Teleport to this island.");
                ItemStack head = Utils.makeItem(Material.SKULL_ITEM, 1, 3, "&b&l" + owner.name, Utils.color(lore));
                SkullMeta m = (SkullMeta) head.getItemMeta();
                m.setOwner(owner.name);
                head.setItemMeta(m);
                islands.put(IridiumSkyblock.getConfiguration().islandTopSlots.get(i), island.getId());
                setItem(IridiumSkyblock.getConfiguration().islandTopSlots.get(i), head);
            } else {
                setItem(IridiumSkyblock.getConfiguration().islandTopSlots.get(i), Utils.makeItemHidden(IridiumSkyblock.getInventories().background));
            }
        }
    }

    @EventHandler
    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().equals(getInventory())) {
            e.setCancelled(true);
            if (islands.containsKey(e.getSlot())) {
                e.getWhoClicked().closeInventory();
                Island island = IridiumSkyblock.getIslandManager().getIslandViaId(islands.get(e.getSlot()));
                if (island.isVisit() || User.getUser((OfflinePlayer) e.getWhoClicked()).bypassing) {
                    island.teleportHome((Player) e.getWhoClicked());
                } else {
                    e.getWhoClicked().sendMessage(Utils.color(IridiumSkyblock.getMessages().playersIslandIsPrivate.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                }
            }
        }
    }
}