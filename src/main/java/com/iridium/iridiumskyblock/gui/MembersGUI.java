package com.iridium.iridiumskyblock.gui;

import com.iridium.iridiumskyblock.*;
import com.iridium.iridiumskyblock.api.IslandDemoteEvent;
import com.iridium.iridiumskyblock.api.IslandPromoteEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.HashMap;

public class MembersGUI extends GUI implements Listener {

    public HashMap<Integer, User> users = new HashMap<>();

    public MembersGUI(Island island) {
        super(island, 27, IridiumSkyblock.getConfiguration().membersGUITitle);
        IridiumSkyblock.getInstance().registerListeners(this);
    }

    @Override
    public void addContent() {
        super.addContent();
        users.clear();
        Island island = getIsland();
        if (island != null) {
            int i = 0;
            for (String member : island.getMembers()) {
                User u = User.getUser(member);
                users.put(i, u);
                ItemStack head = Utils.makeItem(Material.SKULL_ITEM, 1, 3, "&b&l" + u.name);
                SkullMeta m = (SkullMeta) head.getItemMeta();
                m.setLore(Utils.color(Arrays.asList("&bRole: " + u.role, "", "&b&l[!] &bLeft Click to " + (u.role.equals(Roles.Visitor) ? "Kick" : "Demote") + " this Player.", "&b&l[!] &bRight Click to Promote this Player.")));
                m.setOwner(u.name);
                head.setItemMeta(m);
                getInventory().setItem(i, head);
                i++;
            }
        }
    }

    @Override
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().equals(getInventory())) {
            e.setCancelled(true);
            if (users.containsKey(e.getSlot())) {
                User u = users.get(e.getSlot());
                User user = User.getUser((Player) e.getWhoClicked());
                if (e.getClick().equals(ClickType.LEFT)) {
                    if (user.getIsland().getPermissions(user.role).demote) {
                        if (u.role.getRank() < user.role.getRank()) {
                            if (u.role.equals(Roles.Visitor)) {
                                if (user.getIsland().getPermissions(user.role).kickMembers) {
                                    user.getIsland().removeUser(u);
                                    Player player = Bukkit.getPlayer(u.name);
                                    if (player != null)
                                        player.sendMessage(Utils.color(IridiumSkyblock.getMessages().youHaveBeenKicked.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                                } else {
                                    e.getWhoClicked().sendMessage(Utils.color(IridiumSkyblock.getMessages().noPermission.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                                }
                            } else {
                                IslandDemoteEvent event = new IslandDemoteEvent(u.getIsland(), u, user, Roles.getViaRank(u.role.getRank() + 1));
                                Bukkit.getPluginManager().callEvent(event);
                                if (!event.isCancelled()) {
                                    u.role = Roles.getViaRank(u.role.getRank() - 1);
                                    for (String member : u.getIsland().getMembers()) {
                                        Player p = Bukkit.getPlayer(User.getUser(member).name);
                                        if (p != null) {
                                            p.sendMessage(Utils.color(IridiumSkyblock.getMessages().playerDemoted.replace("%rank%", u.role.name()).replace("%player%", u.name).replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                                        }
                                    }
                                }
                            }
                        } else {
                            e.getWhoClicked().sendMessage(Utils.color(IridiumSkyblock.getMessages().cantDemoteUser.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                        }
                    } else {
                        e.getWhoClicked().sendMessage(Utils.color(IridiumSkyblock.getMessages().noPermission.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                    }
                } else {
                    if (user.getIsland().getPermissions(user.role).promote) {
                        if (!(u.role.equals(Roles.Owner))) {
                            if (u.role.getRank() < user.role.getRank()) {
                                if (u.role.getRank() >= Roles.CoOwner.getRank()) {
                                    e.getWhoClicked().sendMessage(Utils.color(IridiumSkyblock.getMessages().transferOwnership.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                                } else {
                                    IslandPromoteEvent event = new IslandPromoteEvent(u.getIsland(), u, user, Roles.getViaRank(u.role.getRank() + 1));
                                    Bukkit.getPluginManager().callEvent(event);
                                    if (!event.isCancelled()) {
                                        u.role = Roles.getViaRank(u.role.getRank() + 1);
                                        for (String member : u.getIsland().getMembers()) {
                                            Player p = Bukkit.getPlayer(User.getUser(member).name);
                                            if (p != null) {
                                                p.sendMessage(Utils.color(IridiumSkyblock.getMessages().playerPromoted.replace("%rank%", u.role.name()).replace("%player%", u.name).replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                                            }
                                        }
                                    }
                                }
                            } else {
                                e.getWhoClicked().sendMessage(Utils.color(IridiumSkyblock.getMessages().cantPromoteUser.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                            }
                        } else {
                            e.getWhoClicked().sendMessage(Utils.color(IridiumSkyblock.getMessages().noPermission.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                        }
                    } else {
                        e.getWhoClicked().sendMessage(Utils.color(IridiumSkyblock.getMessages().cantDemoteOwner.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                    }
                }
            }
        }
    }
}
