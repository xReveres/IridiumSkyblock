package com.peaches.epicskyblock.gui;

import com.peaches.epicskyblock.EpicSkyblock;
import com.peaches.epicskyblock.Island;
import com.peaches.epicskyblock.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class EditorGUI {

    public static Inventory inventory;

    public static int scheduler;

    public ItemStack Configuration;
    public ItemStack Messages; // Connect to server to get different languages
    public ItemStack Missions;
    public ItemStack Upgrades;
    public ItemStack Boosters;

    static {
        inventory = Bukkit.createInventory(null, 27, Utils.color("&8EpicSkyblock Editor"));
        scheduler = Bukkit.getScheduler().scheduleAsyncRepeatingTask(EpicSkyblock.getInstance(), EditorGUI::addContent, 0, 20);
        for (int i = 0; i < 27; i++) {
            inventory.setItem(i, Utils.makeItem(Material.STAINED_GLASS_PANE, 1, 7, " "));
        }

    }

    public static void addContent() {
    }
}