package com.peaches.epicskyblock.gui;

import com.peaches.epicskyblock.EpicSkyblock;
import com.peaches.epicskyblock.MissionRestart;
import com.peaches.epicskyblock.Utils;
import com.peaches.epicskyblock.configs.Boosters;
import com.peaches.epicskyblock.configs.Messages;
import com.peaches.epicskyblock.configs.Missions;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Biome;
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

public class ConfigGUI implements Listener {

    private Inventory inventory;
    public HashMap<Integer, ConfigGUI> pages = new HashMap<>();

    private int rows;

    private Class clazz;
    private Field instance;

    private HashMap<Integer, MaterialGUI> materialGUI;
    private HashMap<Integer, StringGUI> stringGUI;
    private HashMap<Integer, ArrayListGUI> arrayListGUI;
    private HashMap<Integer, ConfigGUI> configGUI;
    private HashMap<Integer, String> items;

    public ConfigGUI(Class clazz, Field instance) {
        try {
            rows = (int) Math.ceil(clazz.getFields().length / 9.0);
            if (rows == 0) {
                rows = 1;
            }
            if (rows >= 6) {
                int p = (int) Math.ceil(clazz.getFields().length / 45.0);
                for (int page = 1; page <= p; page++) {
                    pages.put(page, new ConfigGUI(clazz, instance, page));
                }
                Bukkit.getPluginManager().registerEvents(this, EpicSkyblock.getInstance());
            } else {
                inventory = Bukkit.createInventory(null, 9 * rows, "Editing " + clazz.getSimpleName());
                items = new HashMap<>();
                materialGUI = new HashMap<>();
                configGUI = new HashMap<>();
                stringGUI = new HashMap<>();
                arrayListGUI = new HashMap<>();
                this.instance = instance;
                this.clazz = clazz;
                Bukkit.getPluginManager().registerEvents(this, EpicSkyblock.getInstance());
                Bukkit.getScheduler().scheduleAsyncRepeatingTask(EpicSkyblock.getInstance(), this::addItems, 0, 5);
                init();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ConfigGUI(Class clazz, Field instance, int page) {
        int rows = (int) Math.ceil(clazz.getFields().length / 9.0);
        inventory = Bukkit.createInventory(null, 9 * rows, "Editing " + clazz.getSimpleName());
        items = new HashMap<>();
        materialGUI = new HashMap<>();
        stringGUI = new HashMap<>();
        arrayListGUI = new HashMap<>();
        this.instance = instance;
        this.clazz = clazz;
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(EpicSkyblock.getInstance(), () -> addItems(page), 0, 5);
        init();
    }

    public void init() {
        try {
            for (int i = 0; i < clazz.getFields().length; i++) {
                Field field = clazz.getFields()[i];
                items.put(i, field.getName());
                if (field.getType().equals(String.class)) {
                    stringGUI.put(i, new StringGUI(field, instance.get(EpicSkyblock.getInstance())));
                } else if (field.getType().equals(List.class) || field.getType().equals(HashSet.class)) {
                    arrayListGUI.put(i, new ArrayListGUI(field, instance.get(EpicSkyblock.getInstance())));
                } else if (field.getType().equals(Material.class)) {
                    materialGUI.put(i, new MaterialGUI(field, instance.get(EpicSkyblock.getInstance())));
                } else if (field.getType().equals(Missions.Mission.class) || field.getType().equals(Boosters.Booster.class)) {
                    configGUI.put(i, new ConfigGUI(field.getType(), field));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addItems() {
        for (int i = 0; i < clazz.getFields().length; i++) {
            Field field = clazz.getFields()[i];
            items.put(i, field.getName());
            try {
                //TODO for translations
                if (field.getType().equals(boolean.class)) {
                    inventory.setItem(i, Utils.makeItem(Material.STAINED_GLASS_PANE, 1, field.getBoolean(instance.get(EpicSkyblock.getInstance())) ? 5 : 14, "&b&l" + field.getName(), Arrays.asList("&bCurrent Value: &7" + field.getBoolean(instance.get(EpicSkyblock.getInstance())), "&b&l[!]! &bLeft click to " + (field.getBoolean(instance.get(EpicSkyblock.getInstance())) ? "Enabled" : "Disable"))));
                } else if (field.getType().equals(int.class)) {
                    inventory.setItem(i, Utils.makeItem(Material.PAPER, 1, 0, "&b&l" + field.getName(), Arrays.asList("&bCurrent Value: &7" + field.getInt(instance.get(EpicSkyblock.getInstance())), "&b&l[!]! &bLeft click to Subtract ", "&b&l[!]! &bRight click to Add")));
                } else if (field.getType().equals(Biome.class)) {
                    inventory.setItem(i, Utils.makeItem(Material.PAPER, 1, 0, "&b&l" + field.getName(), Arrays.asList("&bCurrent Value: &7" + ((Biome) field.get(instance.get(EpicSkyblock.getInstance()))).name(), "&b&l[!]! &bLeft click to Subtract ", "&b&l[!]! &bRight click to Add")));
                } else if (field.getType().equals(MissionRestart.class)) {
                    inventory.setItem(i, Utils.makeItem(Material.PAPER, 1, 0, "&b&l" + field.getName(), Arrays.asList("&bCurrent Value: &7" + ((MissionRestart) field.get(instance.get(EpicSkyblock.getInstance()))).name(), "&b&l[!]! &bLeft click to go to Previous ", "&b&l[!]! &bRight click to go to Next")));
                } else if (field.getType().equals(Boosters.Booster.class)) {
                    inventory.setItem(i, Utils.makeItem(Material.NETHER_STAR, 1, 0, "&b&l" + field.getName(), Arrays.asList("&b&l[!]! &bLeft click to Edit")));
                } else if (field.getType().equals(Missions.Mission.class)) {
                    inventory.setItem(i, Utils.makeItem(Material.NETHER_STAR, 1, 0, "&b&l" + field.getName(), Arrays.asList("&b&l[!]! &bLeft click to Edit")));
                } else if (field.getType().equals(String.class)) {
                    inventory.setItem(i, Utils.makeItem(Material.PAPER, 1, 0, "&b&l" + field.getName(), Arrays.asList("&bCurrent Value: &7" + field.get(instance.get(EpicSkyblock.getInstance())), "&b&l[!]! &bLeft click to Edit")));
                } else if (field.getType().equals(List.class)) {
                    inventory.setItem(i, Utils.makeItem(Material.PAPER, 1, 0, "&b&l" + field.getName(), Arrays.asList("", "&b&l[!]! &bLeft click to Edit")));
                } else if (field.getType().equals(HashSet.class)) {
                    inventory.setItem(i, Utils.makeItem(Material.PAPER, 1, 0, "&b&l" + field.getName(), Arrays.asList("", "&b&l[!]! &bLeft click to Edit")));
                } else if (field.getType().equals(HashMap.class)) {
                    inventory.setItem(i, Utils.makeItem(Material.PAPER, 1, 0, "&b&l" + field.getName(), Arrays.asList("", "&b&l[!]! &bLeft click to Edit")));
                } else if (field.getType().equals(Material.class)) {
                    inventory.setItem(i, Utils.makeItem((Material) field.get(instance.get(EpicSkyblock.getInstance())), 1, 0, "&b&l" + field.getName(), Arrays.asList("&bCurrent Value: &7" + field.get(instance.get(EpicSkyblock.getInstance())), "&b&l[!]! &bLeft click to Edit")));
                }
            } catch (IllegalAccessException e) {
            }
        }
    }

    public void addItems(int page) {
        for (int i = 0; i < clazz.getFields().length; i++) {
            if (i >= 45 * (page - 1) && i < 45 * page) {
                Field field = clazz.getFields()[i];
                items.put(i, field.getName());
                try {
                    //TODO for translations
                    if (field.getType().equals(boolean.class)) {
                        inventory.setItem(i - (45 * (page - 1)), Utils.makeItem(Material.STAINED_GLASS_PANE, 1, field.getBoolean(instance.get(EpicSkyblock.getInstance())) ? 5 : 14, "&b&l" + field.getName(), Arrays.asList("&bCurrent Value: &7" + field.getBoolean(instance.get(EpicSkyblock.getInstance())), "&b&l[!]! &bLeft click to " + (field.getBoolean(instance.get(EpicSkyblock.getInstance())) ? "Enabled" : "Disable"))));
                    } else if (field.getType().equals(int.class)) {
                        inventory.setItem(i - (45 * (page - 1)), Utils.makeItem(Material.PAPER, 1, 0, "&b&l" + field.getName(), Arrays.asList("&bCurrent Value: &7" + field.getInt(instance.get(EpicSkyblock.getInstance())), "&b&l[!]! &bLeft click to Subtract ", "&b&l[!]! &bRight click to Add")));
                    } else if (field.getType().equals(Biome.class)) {
                        inventory.setItem(i - (45 * (page - 1)), Utils.makeItem(Material.PAPER, 1, 0, "&b&l" + field.getName(), Arrays.asList("&bCurrent Value: &7" + ((Biome) field.get(instance.get(EpicSkyblock.getInstance()))).name(), "&b&l[!]! &bLeft click to Subtract ", "&b&l[!]! &bRight click to Add")));
                    } else if (field.getType().equals(MissionRestart.class)) {
                        inventory.setItem(i - (45 * (page - 1)), Utils.makeItem(Material.PAPER, 1, 0, "&b&l" + field.getName(), Arrays.asList("&bCurrent Value: &7" + ((MissionRestart) field.get(instance.get(EpicSkyblock.getInstance()))).name(), "&b&l[!]! &bLeft click to go to Previous ", "&b&l[!]! &bRight click to go to Next")));
                    } else if (field.getType().equals(Boosters.Booster.class)) {
                        inventory.setItem(i - (45 * (page - 1)), Utils.makeItem(Material.NETHER_STAR, 1, 0, "&b&l" + field.getName(), Arrays.asList("&b&l[!]! &bLeft click to Edit")));
                    } else if (field.getType().equals(Missions.Mission.class)) {
                        inventory.setItem(i - (45 * (page - 1)), Utils.makeItem(Material.NETHER_STAR, 1, 0, "&b&l" + field.getName(), Arrays.asList("&b&l[!]! &bLeft click to Edit")));
                    } else if (field.getType().equals(String.class)) {
                        inventory.setItem(i - (45 * (page - 1)), Utils.makeItem(Material.PAPER, 1, 0, "&b&l" + field.getName(), Arrays.asList("&bCurrent Value: &7" + Utils.unColour((String) field.get(instance.get(EpicSkyblock.getInstance()))), "&b&l[!]! &bLeft click to Edit")));
                    } else if (field.getType().equals(List.class)) {
                        inventory.setItem(i - (45 * (page - 1)), Utils.makeItem(Material.PAPER, 1, 0, "&b&l" + field.getName(), Arrays.asList("", "&b&l[!]! &bLeft click to Edit")));
                    } else if (field.getType().equals(HashSet.class)) {
                        inventory.setItem(i - (45 * (page - 1)), Utils.makeItem(Material.PAPER, 1, 0, "&b&l" + field.getName(), Arrays.asList("", "&b&l[!]! &bLeft click to Edit")));
                    } else if (field.getType().equals(HashMap.class)) {
                        inventory.setItem(i - (45 * (page - 1)), Utils.makeItem(Material.PAPER, 1, 0, "&b&l" + field.getName(), Arrays.asList("", "&b&l[!]! &bLeft click to Edit")));
                    } else if (field.getType().equals(Material.class)) {
                        inventory.setItem(i - (45 * (page - 1)), Utils.makeItem((Material) field.get(instance.get(EpicSkyblock.getInstance())), 1, 0, "&b&l" + field.getName(), Arrays.asList("&bCurrent Value: &7" + field.get(instance.get(EpicSkyblock.getInstance())), "&b&l[!]! &bLeft click to Edit")));
                    }
                } catch (IllegalAccessException e) {
                }
            }
        }

        this.inventory.setItem(45, Utils.makeItem(Material.STAINED_GLASS_PANE, 1, 0, " "));
        this.inventory.setItem(46, Utils.makeItem(Material.STAINED_GLASS_PANE, 1, 0, " "));
        this.inventory.setItem(47, Utils.makeItem(Material.STAINED_GLASS_PANE, 1, 14, "&c&lPrevious Page"));
        this.inventory.setItem(48, Utils.makeItem(Material.STAINED_GLASS_PANE, 1, 0, " "));
        this.inventory.setItem(49, Utils.makeItem(Material.STAINED_GLASS_PANE, 1, 0, " "));
        if (clazz.equals(Messages.class)) {
            this.inventory.setItem(49, Utils.makeItem(Material.NETHER_STAR, 1, 0, "&b&lLanguages"));
        }
        this.inventory.setItem(50, Utils.makeItem(Material.STAINED_GLASS_PANE, 1, 0, " "));
        this.inventory.setItem(51, Utils.makeItem(Material.STAINED_GLASS_PANE, 1, 5, "&a&lNext Page"));
        this.inventory.setItem(52, Utils.makeItem(Material.STAINED_GLASS_PANE, 1, 0, " "));
        this.inventory.setItem(53, Utils.makeItem(Material.STAINED_GLASS_PANE, 1, 0, " "));
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().equals(inventory)) {
            e.setCancelled(true);
            if (configGUI.containsKey(e.getSlot())) {
                e.getWhoClicked().openInventory(configGUI.get(e.getSlot()).getInventory());
            }
            if (items.containsKey(e.getSlot())) {
                try {
                    Field field = clazz.getField(items.get(e.getSlot()));
                    if (configGUI.containsKey(e.getSlot())) {
                        e.getWhoClicked().openInventory(configGUI.get(e.getSlot()).getInventory());
                    }
                    if (field.getType().equals(boolean.class)) {
                        field.setBoolean(instance.get(EpicSkyblock.getInstance()), !field.getBoolean(instance.get(EpicSkyblock.getInstance())));
                    } else if (field.getType().equals(int.class)) {
                        if (e.getClick().equals(ClickType.LEFT)) {
                            if (field.getInt(instance.get(EpicSkyblock.getInstance())) > 1)
                                field.setInt(instance.get(EpicSkyblock.getInstance()), field.getInt(instance.get(EpicSkyblock.getInstance())) - 1);
                        } else {
                            field.setInt(instance.get(EpicSkyblock.getInstance()), field.getInt(instance.get(EpicSkyblock.getInstance())) + 1);
                        }
                    } else if (field.getType().equals(String.class)) {
                        e.getWhoClicked().openInventory(stringGUI.get(e.getSlot()).getInventory());
                    } else if (field.getType().equals(List.class)) {
                        e.getWhoClicked().openInventory(arrayListGUI.get(e.getSlot()).pages.get(1).getInventory());
                    } else if (field.getType().equals(HashSet.class)) {
                        //New GUI with whatever it contains and a green + button at the end to add new stuff?
                    } else if (field.getType().equals(HashMap.class)) {

                    } else if (field.getType().equals(Material.class)) {
                        e.getWhoClicked().openInventory(materialGUI.get(e.getSlot()).getInventory());
                    } else if (field.getType().equals(MissionRestart.class)) {
                        if (e.getClick().equals(ClickType.LEFT)) {
                            field.set(instance.get(EpicSkyblock.getInstance()), ((MissionRestart) field.get(instance.get(EpicSkyblock.getInstance()))).getPrevious());
                        } else {
                            field.set(instance.get(EpicSkyblock.getInstance()), ((MissionRestart) field.get(instance.get(EpicSkyblock.getInstance()))).getNext());
                        }
                    }
                    EpicSkyblock.getInstance().saveConfigs();
                    EpicSkyblock.getInstance().loadConfigs();
                } catch (NoSuchFieldException | IllegalAccessException ex) {
                }
            }
        }
        for (int page : pages.keySet()) {
            if (e.getInventory().equals(pages.get(page).getInventory())) {
                e.setCancelled(true);
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

    public Inventory getInventory() {
        return inventory;
    }
}
