package com.iridium.iridiumskyblock.gui;

import com.iridium.iridiumskyblock.*;
import com.iridium.iridiumskyblock.configs.Schematics;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class IslandMenuGUI extends GUI implements Listener {

    public ConfirmationGUI delete;
    public ConfirmationGUI regen;

    public IslandMenuGUI(Island island) {
        super(island, 27, IridiumSkyblock.getInventories().islandMenuGUITitle);
        IridiumSkyblock.getInstance().registerListeners(this);
        this.delete = new ConfirmationGUI(island, () -> getIsland().delete());
        this.regen = new ConfirmationGUI(island, () -> getIsland().generateIsland());
    }

    @Override
    public void addContent() {
        super.addContent();
        if (IridiumSkyblock.getIslandManager().islands.containsKey(islandID)) {
            setItem(0, Utils.makeItemHidden(IridiumSkyblock.getInventories().home, getIsland()));
            setItem(1, Utils.makeItemHidden(IridiumSkyblock.getInventories().members, getIsland()));
            setItem(2, Utils.makeItemHidden(IridiumSkyblock.getInventories().regen, getIsland()));
            setItem(3, Utils.makeItemHidden(IridiumSkyblock.getInventories().upgrades, getIsland()));
            setItem(4, Utils.makeItemHidden(IridiumSkyblock.getInventories().missions, getIsland()));
            setItem(5, Utils.makeItemHidden(IridiumSkyblock.getInventories().boosters, getIsland()));
            setItem(6, Utils.makeItemHidden(IridiumSkyblock.getInventories().permissions, getIsland()));
            setItem(7, Utils.makeItemHidden(IridiumSkyblock.getInventories().top, getIsland()));
            setItem(8, Utils.makeItemHidden(IridiumSkyblock.getInventories().warps, getIsland()));
            setItem(9, Utils.makeItemHidden(IridiumSkyblock.getInventories().border, getIsland()));
            setItem(26, Utils.makeItemHidden(IridiumSkyblock.getInventories().delete, getIsland()));
        }
    }

    @Override
    public Inventory getInventory() {
        if (getIsland().getSchematic() == null) {
            if (IridiumSkyblock.getInstance().schems.size() == 1) {
                for (Schematics.FakeSchematic schematic : IridiumSkyblock.getInstance().schems.keySet()) {
                    getIsland().setSchematic(schematic.name);
                }
            } else {
                return getIsland().getSchematicSelectGUI().getInventory();
            }
        }
        return super.getInventory();
    }

    @Override
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().equals(getInventory())) {
            e.setCancelled(true);
            Player p = (Player) e.getWhoClicked();
            User u = User.getUser(p);
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
                    if (u.bypassing || getIsland().getPermissions(u.role).regen) {
                        p.openInventory(regen.getInventory());
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
                        p.openInventory(delete.getInventory());
                    } else {
                        p.sendMessage(Utils.color(IridiumSkyblock.getMessages().mustBeIslandOwner.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                    }
                    break;
            }
        }
    }
}
