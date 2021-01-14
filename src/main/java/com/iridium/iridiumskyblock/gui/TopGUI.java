package com.iridium.iridiumskyblock.gui;

import com.cryptomorin.xseries.XMaterial;
import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.User;
import com.iridium.iridiumskyblock.api.IridiumSkyblockAPI;
import com.iridium.iridiumskyblock.managers.IslandDataManager;
import com.iridium.iridiumskyblock.managers.IslandManager;
import com.iridium.iridiumskyblock.utils.ItemStackUtils;
import com.iridium.iridiumskyblock.utils.NumberFormatter;
import com.iridium.iridiumskyblock.utils.Placeholder;
import com.iridium.iridiumskyblock.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class TopGUI extends GUI implements Listener {

    public Map<Integer, Integer> islands = new HashMap<>();

    public TopGUI() {
        super(IridiumSkyblock.getInventories().topGUISize, IridiumSkyblock.getInventories().topGUITitle, 40);
        IridiumSkyblock.getInstance().registerListeners(this);
    }

    @Override
    public void addContent() {
        super.addContent();
        if (getInventory().getViewers().isEmpty()) return;
        ItemStack filler = ItemStackUtils.makeItem(IridiumSkyblock.getInventories().topfiller);
        for (int i : IridiumSkyblock.getConfiguration().islandTopSlots.keySet()) {
            List<Integer> islandid = IridiumSkyblockAPI.getInstance().getIslands(IslandDataManager.IslandSortType.VALUE, i - 1, i, false);
            if (islandid.size() > 0) {
                Island island = IslandManager.getIslandViaId(islandid.get(0));
                if (island == null) break;
                Bukkit.getScheduler().runTaskAsynchronously(IridiumSkyblock.getInstance(), () -> {
                    User owner = User.getUser(island.owner);
                    ArrayList<Placeholder> placeholders = new ArrayList<>(Arrays.asList(new Placeholder("player", owner.name), new Placeholder("votes", island.getVotes() + ""), new Placeholder("name", island.getName()), new Placeholder("rank", i + ""), new Placeholder("level", island.getFormattedLevel()), new Placeholder("value", island.getFormattedValue()), new Placeholder("members", island.members.size() + "")));
                    for (XMaterial item : IridiumSkyblock.getBlockValues().blockvalue.keySet()) {
                        placeholders.add(new Placeholder(item.name() + "_amount", "" + island.valuableBlocks.getOrDefault(item.name(), 0)));
                    }
                    for (String item : IridiumSkyblock.getBlockValues().spawnervalue.keySet()) {
                        placeholders.add(new Placeholder(item + "_amount", "" + island.spawners.getOrDefault(item, 0)));
                    }
                    placeholders.add(new Placeholder("ISLANDBANK_value", IridiumSkyblock.getConfiguration().islandMoneyPerValue != 0 ? NumberFormatter.format(island.getMoney() / IridiumSkyblock.getConfiguration().islandMoneyPerValue) : "0"));
                    ItemStack head = ItemStackUtils.makeItem(IridiumSkyblock.getInventories().topisland, placeholders);
                    islands.put(IridiumSkyblock.getConfiguration().islandTopSlots.get(i), island.id);
                    setItem(IridiumSkyblock.getConfiguration().islandTopSlots.get(i), head);
                });
            } else {
                setItem(IridiumSkyblock.getConfiguration().islandTopSlots.get(i), filler);
            }
        }
        if (IridiumSkyblock.getInventories().backButtons)
            setItem(getInventory().getSize() - 5, ItemStackUtils.makeItem(IridiumSkyblock.getInventories().back));
    }

    @EventHandler
    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().equals(getInventory())) {
            e.setCancelled(true);
            if (e.getClickedInventory() == null || !e.getClickedInventory().equals(getInventory())) return;
            if (e.getSlot() == getInventory().getSize() - 5 && IridiumSkyblock.getInventories().backButtons) {
                if (User.getUser((Player) e.getWhoClicked()).getIsland() != null) {
                    e.getWhoClicked().openInventory(User.getUser((Player) e.getWhoClicked()).getIsland().islandMenuGUI.getInventory());
                } else {
                    e.getWhoClicked().closeInventory();
                }
            }
            if (islands.containsKey(e.getSlot())) {
                e.getWhoClicked().closeInventory();
                Island island = IslandManager.getIslandViaId(islands.get(e.getSlot()));
                if (island.visit || User.getUser((OfflinePlayer) e.getWhoClicked()).bypassing) {
                    island.teleportHome((Player) e.getWhoClicked());
                } else {
                    e.getWhoClicked().sendMessage(StringUtils.color(IridiumSkyblock.getMessages().playersIslandIsPrivate.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                }
            }
        }
    }
}
