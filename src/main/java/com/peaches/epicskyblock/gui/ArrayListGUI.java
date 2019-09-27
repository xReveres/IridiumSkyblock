package com.peaches.epicskyblock.gui;

import com.peaches.epicskyblock.EpicSkyblock;
import com.peaches.epicskyblock.Utils;
import com.peaches.epicskyblock.configs.Boosters;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class ArrayListGUI implements Listener {

    public HashMap<Integer, ArrayListGUI> pages = new HashMap<>();

    private HashMap<Integer, ConfigGUI> configGui = new HashMap<>();

    private Inventory inventory;

    private Field field;
    private Object instance;

    public ArrayListGUI(Field field, Object instance) {
        this.field = field;
        this.instance = instance;

        if (field.getType().equals(List.class)) {
            try {
                List<Object> list = (List<Object>) field.get(instance);
                int p = (int) Math.ceil(list.size() / 45.0);
                for (int page = 1; page <= p; page++) {
                    pages.put(page, new ArrayListGUI(field, instance, page));
                }

            } catch (IllegalAccessException e) {

            }
        }
        if (field.getType().equals(HashSet.class)) {
            try {
                HashSet<Object> list = (HashSet<Object>) field.get(instance);
                int p = (int) Math.ceil(list.size() / 45.0);
                for (int page = 1; page <= p; page++) {
                    pages.put(page, new ArrayListGUI(field, instance, page));
                }

            } catch (IllegalAccessException e) {

            }
        }

        Bukkit.getPluginManager().registerEvents(this, EpicSkyblock.getInstance());
    }

    public ArrayListGUI(Field field, Object instance, int page) {
        this.field = field;
        this.instance = instance;
        init(field, instance, page);
        this.inventory = Bukkit.createInventory(null, 54, "Editing " + field.getName());
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(EpicSkyblock.getInstance(), () -> addItems(field, instance, page), 0, 5);
    }

    public void init(Field field, Object instance, int page) {
        if (field.getType().equals(List.class)) {
            try {
                List<Object> list = (List<Object>) field.get(instance);

                int slot = 0;
                for (int i = 0; i < list.size(); i++) {
                    if (i >= 45 * (page - 1) && i < 45 * page) {
                        Object object = list.get(i);
                        slot++;
                    }
                }

            } catch (IllegalAccessException e) {

            }
        }
        if (field.getType().equals(HashSet.class)) {
            try {
                HashSet<Object> list = (HashSet<Object>) field.get(instance);

                int i = 0;
                int slot = 0;
                for (Object object : list) {
                    if (i >= 45 * (page - 1) && i < 45 * page) {

                        slot++;
                    }
                    i++;
                }

            } catch (IllegalAccessException e) {

            }
        }
    }

    public void addItems(Field field, Object instance, int page) {
        if (field.getType().equals(List.class)) {
            try {
                List<Object> list = (List<Object>) field.get(instance);

                int slot = 0;
                for (int i = 0; i < list.size(); i++) {
                    if (i >= 45 * (page - 1) && i < 45 * page) {
                        Object object = list.get(i);
                        slot++;
                    }
                }

            } catch (IllegalAccessException e) {

            }
        }
        if (field.getType().equals(HashSet.class)) {
            try {
                HashSet<Object> list = (HashSet<Object>) field.get(instance);

                int slot = 0;
                int i = 0;
                for (Object object : list) {
                    if (i >= 45 * (page - 1) && i < 45 * page) {
                        slot++;
                    }
                    i++;
                }

            } catch (IllegalAccessException e) {

            }
        }

        this.inventory.setItem(45, new ItemStack(Material.AIR));
        this.inventory.setItem(46, new ItemStack(Material.AIR));
        this.inventory.setItem(47, Utils.makeItem(Material.STAINED_GLASS_PANE, 1, 14, "&c&lPrevious Page"));
        this.inventory.setItem(48, new ItemStack(Material.AIR));
        this.inventory.setItem(49, new ItemStack(Material.AIR));
        this.inventory.setItem(50, new ItemStack(Material.AIR));
        this.inventory.setItem(51, Utils.makeItem(Material.STAINED_GLASS_PANE, 1, 5, "&a&lNext Page"));
        this.inventory.setItem(52, new ItemStack(Material.AIR));
        this.inventory.setItem(53, new ItemStack(Material.AIR));
    }

    public Inventory getInventory() {
        return inventory;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        for (int page : pages.keySet()) {
            if (e.getInventory().equals(pages.get(page).getInventory())) {
                e.setCancelled(true);
                if (pages.get(page).configGui.containsKey(e.getSlot())) {
                    if (e.getClick().equals(ClickType.RIGHT)) {
                    }
                    e.getWhoClicked().openInventory(pages.get(page).configGui.get(e.getSlot()).getInventory());
                }
                if (e.getSlot() == 47) {
                    if (pages.containsKey(page - 1)) {
                        e.getWhoClicked().openInventory(pages.get(page - 1).getInventory());
                    }
                }
                if (e.getSlot() == 51) {
                    if (pages.containsKey(page + 1)) {
                        e.getWhoClicked().openInventory(pages.get(page + 1).getInventory());
                    }
                }
                EpicSkyblock.getInstance().saveConfigs();
                EpicSkyblock.getInstance().loadConfigs();
            }
        }
    }
}