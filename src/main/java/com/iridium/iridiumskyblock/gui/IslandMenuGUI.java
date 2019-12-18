package com.iridium.iridiumskyblock.gui;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.User;
import com.iridium.iridiumskyblock.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class IslandMenuGUI extends GUI implements Listener {

    public ConfirmationGUI delete;
    public ConfirmationGUI regen;

    public IslandMenuGUI(Island island) {
        super(island, IridiumSkyblock.getInventories().islandMenuGUISize, IridiumSkyblock.getInventories().islandMenuGUITitle);
        IridiumSkyblock.getInstance().registerListeners(this);
        this.delete = new ConfirmationGUI(island, () -> getIsland().delete(), IridiumSkyblock.getMessages().deleteAction);
        this.regen = new ConfirmationGUI(island, () -> {
            getIsland().generateIsland();
            if (IridiumSkyblock.getConfiguration().restartUpgradesOnRegen) {
                getIsland().resetMissions();
                getIsland().setSizeLevel(1);
                getIsland().setMemberLevel(1);
                getIsland().setWarpLevel(1);
                getIsland().setOreLevel(1);
                getIsland().setFlightBooster(0);
                getIsland().setExpBooster(0);
                getIsland().setFarmingBooster(0);
                getIsland().setSpawnerBooster(0);
            }
        }, IridiumSkyblock.getMessages().resetAction);
    }

    @Override
    public void addContent() {
        super.addContent();
        if (IridiumSkyblock.getIslandManager().islands.containsKey(islandID)) {
            setItem(IridiumSkyblock.getInventories().home.slot == null ? 0 : IridiumSkyblock.getInventories().home.slot, Utils.makeItemHidden(IridiumSkyblock.getInventories().home, getIsland()));
            setItem(IridiumSkyblock.getInventories().members.slot == null ? 1 : IridiumSkyblock.getInventories().members.slot, Utils.makeItemHidden(IridiumSkyblock.getInventories().members, getIsland()));
            setItem(IridiumSkyblock.getInventories().regen.slot == null ? 2 : IridiumSkyblock.getInventories().regen.slot, Utils.makeItemHidden(IridiumSkyblock.getInventories().regen, getIsland()));
            setItem(IridiumSkyblock.getInventories().upgrades.slot == null ? 3 : IridiumSkyblock.getInventories().upgrades.slot, Utils.makeItemHidden(IridiumSkyblock.getInventories().upgrades, getIsland()));
            setItem(IridiumSkyblock.getInventories().missions.slot == null ? 4 : IridiumSkyblock.getInventories().missions.slot, Utils.makeItemHidden(IridiumSkyblock.getInventories().missions, getIsland()));
            setItem(IridiumSkyblock.getInventories().boosters.slot == null ? 5 : IridiumSkyblock.getInventories().boosters.slot, Utils.makeItemHidden(IridiumSkyblock.getInventories().boosters, getIsland()));
            setItem(IridiumSkyblock.getInventories().permissions.slot == null ? 6 : IridiumSkyblock.getInventories().permissions.slot, Utils.makeItemHidden(IridiumSkyblock.getInventories().permissions, getIsland()));
            setItem(IridiumSkyblock.getInventories().top.slot == null ? 7 : IridiumSkyblock.getInventories().top.slot, Utils.makeItemHidden(IridiumSkyblock.getInventories().top, getIsland()));
            setItem(IridiumSkyblock.getInventories().warps.slot == null ? 8 : IridiumSkyblock.getInventories().warps.slot, Utils.makeItemHidden(IridiumSkyblock.getInventories().warps, getIsland()));
            setItem(IridiumSkyblock.getInventories().border.slot == null ? 9 : IridiumSkyblock.getInventories().border.slot, Utils.makeItemHidden(IridiumSkyblock.getInventories().border, getIsland()));
            setItem(IridiumSkyblock.getInventories().coop.slot == null ? 10 : IridiumSkyblock.getInventories().coop.slot, Utils.makeItemHidden(IridiumSkyblock.getInventories().coop, getIsland()));
            setItem(IridiumSkyblock.getInventories().bank.slot == null ? 11 : IridiumSkyblock.getInventories().bank.slot, Utils.makeItemHidden(IridiumSkyblock.getInventories().bank, getIsland()));
            setItem(IridiumSkyblock.getInventories().delete.slot == null ? 26 : IridiumSkyblock.getInventories().delete.slot, Utils.makeItemHidden(IridiumSkyblock.getInventories().delete, getIsland()));
        }
    }

    @Override
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().equals(getInventory())) {
            e.setCancelled(true);
            if (e.getClickedInventory() == null || !e.getClickedInventory().equals(getInventory())) return;
            Player p = (Player) e.getWhoClicked();
            User u = User.getUser(p);
            if (e.getSlot() == (IridiumSkyblock.getInventories().home.slot == null ? 0 : IridiumSkyblock.getInventories().home.slot)) {
                p.closeInventory();
                getIsland().teleportHome(p);
            } else if (e.getSlot() == (IridiumSkyblock.getInventories().members.slot == null ? 1 : IridiumSkyblock.getInventories().members.slot)) {
                p.closeInventory();
                p.openInventory(getIsland().getMembersGUI().getInventory());
            } else if (e.getSlot() == (IridiumSkyblock.getInventories().regen.slot == null ? 2 : IridiumSkyblock.getInventories().regen.slot)) {
                p.closeInventory();
                if (u.bypassing || getIsland().getPermissions(u.role).regen) {
                    p.openInventory(regen.getInventory());
                } else {
                    p.sendMessage(Utils.color(IridiumSkyblock.getMessages().noPermission.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                }
            } else if (e.getSlot() == (IridiumSkyblock.getInventories().upgrades.slot == null ? 3 : IridiumSkyblock.getInventories().upgrades.slot)) {
                p.closeInventory();
                p.openInventory(getIsland().getUpgradeGUI().getInventory());
            } else if (e.getSlot() == (IridiumSkyblock.getInventories().missions.slot == null ? 4 : IridiumSkyblock.getInventories().missions.slot)) {
                p.closeInventory();
                p.openInventory(getIsland().getMissionsGUI().getInventory());
            } else if (e.getSlot() == (IridiumSkyblock.getInventories().boosters.slot == null ? 5 : IridiumSkyblock.getInventories().boosters.slot)) {
                p.closeInventory();
                p.openInventory(getIsland().getBoosterGUI().getInventory());
            } else if (e.getSlot() == (IridiumSkyblock.getInventories().permissions.slot == null ? 6 : IridiumSkyblock.getInventories().permissions.slot)) {
                p.closeInventory();
                p.openInventory(getIsland().getPermissionsGUI().getInventory());
            } else if (e.getSlot() == (IridiumSkyblock.getInventories().top.slot == null ? 7 : IridiumSkyblock.getInventories().top.slot)) {
                p.closeInventory();
                p.openInventory(IridiumSkyblock.topGUI.getInventory());
            } else if (e.getSlot() == (IridiumSkyblock.getInventories().warps.slot == null ? 8 : IridiumSkyblock.getInventories().warps.slot)) {
                p.closeInventory();
                p.openInventory(getIsland().getWarpGUI().getInventory());
            } else if (e.getSlot() == (IridiumSkyblock.getInventories().border.slot == null ? 9 : IridiumSkyblock.getInventories().border.slot)) {
                p.closeInventory();
                p.openInventory(getIsland().getBorderColorGUI().getInventory());
            } else if (e.getSlot() == (IridiumSkyblock.getInventories().coop.slot == null ? 10 : IridiumSkyblock.getInventories().coop.slot)) {
                p.closeInventory();
                p.openInventory(getIsland().getCoopGUI().getInventory());
            } else if (e.getSlot() == (IridiumSkyblock.getInventories().bank.slot == null ? 11 : IridiumSkyblock.getInventories().bank.slot)) {
                p.closeInventory();
                p.openInventory(getIsland().getBankGUI().getInventory());
            } else if (e.getSlot() == (IridiumSkyblock.getInventories().delete.slot == null ? 26 : IridiumSkyblock.getInventories().delete.slot)) {
                p.closeInventory();
                if (u.bypassing || getIsland().getOwner().equalsIgnoreCase(u.player)) {
                    p.openInventory(delete.getInventory());
                } else {
                    p.sendMessage(Utils.color(IridiumSkyblock.getMessages().mustBeIslandOwner.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
                }
            }
        }
    }
}
