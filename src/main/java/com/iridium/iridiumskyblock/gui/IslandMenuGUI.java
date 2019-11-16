package com.iridium.iridiumskyblock.gui;

import com.iridium.iridiumskyblock.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class IslandMenuGUI extends GUI implements Listener {

    public IslandMenuGUI(Island island) {
        super(island, 27, IridiumSkyblock.getInventories().islandMenuGUITitle);
        IridiumSkyblock.getInstance().registerListeners(this);
    }

    @Override
    public void addContent() {
        super.addContent();
        setItem(0, Utils.makeItem(IridiumSkyblock.getInventories().home, getIsland()));
        setItem(1, Utils.makeItem(IridiumSkyblock.getInventories().members, getIsland()));
        setItem(2, Utils.makeItem(IridiumSkyblock.getInventories().regen, getIsland()));
        setItem(3, Utils.makeItem(IridiumSkyblock.getInventories().upgrades, getIsland()));
        setItem(4, Utils.makeItem(IridiumSkyblock.getInventories().missions, getIsland()));
        setItem(5, Utils.makeItem(IridiumSkyblock.getInventories().boosters, getIsland()));
        setItem(6, Utils.makeItem(IridiumSkyblock.getInventories().permissions, getIsland()));
        setItem(7, Utils.makeItem(IridiumSkyblock.getInventories().top, getIsland()));
        setItem(8, Utils.makeItem(IridiumSkyblock.getInventories().warps, getIsland()));
        setItem(9, Utils.makeItem(IridiumSkyblock.getInventories().border, getIsland()));
        setItem(26, Utils.makeItem(IridiumSkyblock.getInventories().delete, getIsland()));
    }

    @Override
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().equals(getInventory())) {
            e.setCancelled(true);
            Player p = (Player) e.getWhoClicked();
            User u = User.getUser(p);
            Island island = User.getUser(p).getIsland();
            switch (e.getSlot()) {
                case 0:
                    p.closeInventory();
                    getIsland().teleportHome(p);
                    break;
                case 1:
                    p.closeInventory();
                    p.openInventory(getIsland().getMembersGUI().getInventory());
                    break;
                case 2:
                    p.closeInventory();
                    if (u.bypassing || getIsland().getPermissions(u.role).kickMembers) {
                        getIsland().generateIsland();
                        p.sendMessage(Utils.color(IridiumSkyblock.getMessages().regenIsland.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                    } else {
                        p.sendMessage(Utils.color(IridiumSkyblock.getMessages().noPermission.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                    }
                    break;
                case 3:
                    p.closeInventory();
                    p.openInventory(getIsland().getUpgradeGUI().getInventory());
                    break;
                case 4:
                    p.closeInventory();
                    p.openInventory(getIsland().getMissionsGUI().getInventory());
                    break;
                case 5:
                    p.closeInventory();
                    p.openInventory(getIsland().getBoosterGUI().getInventory());
                    break;
                case 6:
                    p.closeInventory();
                    p.openInventory(getIsland().getPermissionsGUI().getInventory());
                    break;
                case 7:
                    p.closeInventory();
                    p.openInventory(IridiumSkyblock.topGUI.getInventory());
                    break;
                case 8:
                    p.closeInventory();
                    p.openInventory(getIsland().getWarpGUI().getInventory());
                    break;
                case 9:
                    p.closeInventory();
                    p.openInventory(getIsland().getBorderColorGUI().getInventory());
                    break;
                case 26:
                    p.closeInventory();
                    if (u.bypassing || getIsland().getOwner().equalsIgnoreCase(u.player)) {
                        getIsland().delete();
                    } else {
                        p.sendMessage(Utils.color(IridiumSkyblock.getMessages().mustBeIslandOwner.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                    }
                    break;
            }
        }
    }
}
