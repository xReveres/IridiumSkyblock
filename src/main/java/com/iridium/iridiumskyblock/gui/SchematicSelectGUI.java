package com.iridium.iridiumskyblock.gui;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.Utils;
import com.iridium.iridiumskyblock.configs.Schematics;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class SchematicSelectGUI extends GUI implements Listener {

    public SchematicSelectGUI(Island island) {
        super(island, 27, IridiumSkyblock.getConfiguration().schematicselectGUITitle);
        IridiumSkyblock.getInstance().registerListeners(this);
    }

    @Override
    public void addContent() {
        super.addContent();
        int i = 0;
        for (Schematics.FakeSchematic fakeSchematic : IridiumSkyblock.getSchematics().schematics) {
            getInventory().setItem(i, Utils.makeItem(fakeSchematic.item, 1, 0, fakeSchematic.displayname, fakeSchematic.lore));
            i++;
        }
    }

    @EventHandler
    @Override
    public void onInventoryClick(InventoryClickEvent e) {
        int i = 0;
        for (Schematics.FakeSchematic fakeSchematic : IridiumSkyblock.getSchematics().schematics) {
            if (e.getSlot() == i && (fakeSchematic.permission.isEmpty() || e.getWhoClicked().hasPermission(fakeSchematic.permission))) {
                getIsland().setSchematic(fakeSchematic.name);
                getIsland().generateIsland();
                getIsland().teleportHome((Player) e.getWhoClicked());
            }
            i++;
        }
    }
}
