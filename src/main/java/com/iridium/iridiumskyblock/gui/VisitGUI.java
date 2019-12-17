package com.iridium.iridiumskyblock.gui;

import com.iridium.iridiumskyblock.*;
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
import java.util.List;

public class VisitGUI extends GUI implements Listener {

    public HashMap<Integer, Integer> islands = new HashMap<>();

    private int page;

    public VisitGUI(int page) {
        super(IridiumSkyblock.getInventories().visitGUISize, IridiumSkyblock.getInventories().visitGUITitle);
        IridiumSkyblock.getInstance().registerListeners(this);
        this.page = page;
    }

    @Override
    public void addContent() {
        super.addContent();
        List<Island> top = Utils.getIslands();
        int slot = 0;
        int i = 45 * (page - 1);
        while (slot < 45) {
            if (top.size() > i && i >= 0) {
                Island island = top.get(i);
                if (island.isVisit()) {
                    ArrayList<String> lore = new ArrayList<>();
                    User owner = User.getUser(island.getOwner());
                    lore.add("&b&l * &7Island: &b" + island.getName());
                    lore.add("&b&l * &7Rank: &b" + Utils.getIslandRank(island));
                    lore.add("&b&l * &7Value: &b" + NumberFormat.getInstance().format(island.getValue()));
                    lore.add("&b&l * &7Votes: &b" + NumberFormat.getInstance().format(island.getVotes()));
                    lore.add("");
                    lore.add("&b&l[!] &bLeft Click to Teleport to this island.");
                    lore.add("&b&l[!] &bRight Click to (un)vote for this island.");
                    ItemStack head = Utils.makeItem(MultiversionMaterials.PLAYER_HEAD, 1, "&b&l" + owner.name, Utils.color(lore));
                    SkullMeta m = (SkullMeta) head.getItemMeta();
                    m.setOwner(owner.name);
                    head.setItemMeta(m);
                    islands.put(slot, island.getId());
                    setItem(slot, head);
                    slot++;
                } else {
                    setItem(slot, Utils.makeItemHidden(IridiumSkyblock.getInventories().background));
                }
                i++;
            } else {
                setItem(slot, Utils.makeItemHidden(IridiumSkyblock.getInventories().background));
                slot++;
            }
        }
        setItem(getInventory().getSize() - 3, Utils.makeItem(IridiumSkyblock.getInventories().nextPage));
        setItem(getInventory().getSize() - 7, Utils.makeItem(IridiumSkyblock.getInventories().previousPage));
    }

    @EventHandler
    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().equals(getInventory())) {
            e.setCancelled(true);
            if (islands.containsKey(e.getSlot())) {
                Island island = IridiumSkyblock.getIslandManager().getIslandViaId(islands.get(e.getSlot()));
                User u = User.getUser((OfflinePlayer) e.getWhoClicked());
                if (island.isVisit() || u.bypassing) {
                    if (e.getClick() == ClickType.RIGHT) {
                        if (island.hasVoted(u)) {
                            island.removeVote(u);
                        } else {
                            island.addVote(u);
                        }
                    } else {
                        e.getWhoClicked().closeInventory();
                        island.teleportHome((Player) e.getWhoClicked());
                    }
                } else {
                    e.getWhoClicked().sendMessage(Utils.color(IridiumSkyblock.getMessages().playersIslandIsPrivate.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                }
            } else if (e.getSlot() == getInventory().getSize() - 7) {
                if (IridiumSkyblock.visitGUI.containsKey(page - 1))
                    e.getWhoClicked().openInventory(IridiumSkyblock.visitGUI.get(page - 1).getInventory());
            } else if (e.getSlot() == getInventory().getSize() - 3) {
                if (IridiumSkyblock.visitGUI.containsKey(page + 1))
                    e.getWhoClicked().openInventory(IridiumSkyblock.visitGUI.get(page + 1).getInventory());
            }
        }
    }
}