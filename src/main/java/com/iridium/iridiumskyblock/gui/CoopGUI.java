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
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class CoopGUI extends GUI implements Listener {

    public HashMap<Integer, Integer> islands = new HashMap<>();

    public CoopGUI(Island island) {
        super(island, IridiumSkyblock.getInventories().coopGUISize, IridiumSkyblock.getInventories().coopGUITitle);
        IridiumSkyblock.getInstance().registerListeners(this);
    }

    @Override
    public void addContent() {
        super.addContent();
        islands.clear();
        Island island = getIsland();
        if (island != null) {
            int i = 0;
            for (int id : island.getCoop()) {
                Island is = IridiumSkyblock.getIslandManager().getIslandViaId(id);
                if (is != null) {
                    islands.put(i, id);
                    User user = User.getUser(is.getOwner());
                    ArrayList<String> lore = new ArrayList<>();
                    lore.add("&b&l * &7Island: &b" + island.getName());
                    lore.add("&b&l * &7Rank: &b" + i);
                    lore.add("&b&l * &7Value: &b" + NumberFormat.getInstance().format(island.getValue()));
                    lore.add("");
                    lore.add("&b&l[!] &bLeft Click to Teleport to this island.");
                    lore.add("&b&l[!] &bRight Click to un co-op this island.");
                    ItemStack head = Utils.makeItem(Material.SKULL_ITEM, 1, 3, "&b&l" + user.name, Utils.color(lore));
                    SkullMeta m = (SkullMeta) head.getItemMeta();
                    m.setOwner(user.name);
                    head.setItemMeta(m);
                    setItem(i, head);
                    i++;
                } else {
                    island.removeCoop(id);
                }
            }
        }
    }

    @Override
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().equals(getInventory())) {
            e.setCancelled(true);
            if (islands.containsKey(e.getSlot())) {
                e.getWhoClicked().closeInventory();
                Island island = IridiumSkyblock.getIslandManager().getIslandViaId(islands.get(e.getSlot()));
                User u = User.getUser((OfflinePlayer) e.getWhoClicked());
                if (e.getClick().equals(ClickType.RIGHT)) {
                    if (u.bypassing || u.getIsland().getPermissions(u.getRole()).coop) {
                        getIsland().removeCoop(island);
                    } else {
                        e.getWhoClicked().sendMessage(Utils.color(IridiumSkyblock.getMessages().noPermission.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                    }
                } else {
                    if (island.isVisit() || u.bypassing) {
                        island.teleportHome((Player) e.getWhoClicked());
                    } else {
                        e.getWhoClicked().sendMessage(Utils.color(IridiumSkyblock.getMessages().playersIslandIsPrivate.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                    }
                }
            }
        }
    }
}
