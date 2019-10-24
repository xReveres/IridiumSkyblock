package com.iridium.iridiumskyblock;

import com.iridium.iridiumskyblock.commands.CommandManager;
import com.iridium.iridiumskyblock.configs.*;
import com.iridium.iridiumskyblock.listeners.*;
import com.iridium.iridiumskyblock.gui.TopGUI;
import com.iridium.iridiumskyblock.placeholders.ClipPlaceholderAPIManager;
import com.iridium.iridiumskyblock.serializer.Persist;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class IridiumSkyblock extends JavaPlugin {

    private static IridiumSkyblock instance;

    public HashMap<Schematics.FakeSchematic, Schematic> schems = new HashMap<>();

    public static Config configuration;
    public static Messages messages;
    public static Missions missions;
    public static Upgrades upgrades;
    public static Boosters boosters;
    public static Inventories inventories;
    public static Schematics schematics;


    private static Persist persist;

    private static IslandManager islandManager;

    private static CommandManager commandManager;

    public static TopGUI topGUI;

    public boolean updatingBlocks = false;

    private ClipPlaceholderAPIManager clipPlaceholderAPIManager;

    @Override
    public void onEnable() {
        try {
            instance = this;

            super.onEnable();
            getDataFolder().mkdir();

            persist = new Persist();

            commandManager = new CommandManager("island");
            commandManager.registerCommands();

            loadConfigs();

            if (getConfiguration().enabledWorlds == null) {
                getConfiguration().enabledWorlds = new ArrayList<>();
            }

            if (getConfiguration().enabledWorlds.isEmpty() && !getConfiguration().enabledWorldsIsBlacklist) {
                for (World w : Bukkit.getWorlds()) {
                    getConfiguration().enabledWorlds.add(w.getName());
                }
            }
            saveConfigs();

            // Call it as a delayed task to wait for the server to properly load first
            Bukkit.getScheduler().scheduleSyncDelayedTask(this, this::islandValueManager);

            topGUI = new TopGUI();

            registerListeners(topGUI, new onBlockBreak(), new onBlockPlace(), new onClick(), new onBlockFromTo(), new onSpawnerSpawn(), new onEntityDeath(), new onPlayerJoinLeave(), new onBlockGrow(), new onPlayerTalk(), new onPlayerMove(), new onEntityDamageByEntity(), new onPlayerExpChange(), new onPlayerFish(), new onEntityExplode());

            new Metrics(this);

            Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, () -> getPersist().save(islandManager), 0, 20);

            setupPlaceholderAPI();

            startCounting();

            getLogger().info("-------------------------------");
            getLogger().info("");
            getLogger().info(getDescription().getName() + " Enabled!");
            getLogger().info("");
            getLogger().info("-------------------------------");
        } catch (Exception e) {
            sendErrorMessage(e);
        }
    }
    // name change

    public void startCounting() {
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, () -> {
            try {
                if ((LocalDateTime.now().getDayOfWeek().equals(DayOfWeek.MONDAY) || configuration.missionRestart == MissionRestart.Daily) && LocalDateTime.now().getHour() == 0 && LocalDateTime.now().getMinute() == 0 && LocalDateTime.now().getSecond() == 0) {
                    for (Island island : getIslandManager().islands.values()) {
                        island.treasureHunter = 0;
                        island.competitor = 0;
                        island.miner = 0;
                        island.farmer = 0;
                        island.hunter = 0;
                        island.fisherman = 0;
                        island.builder = 0;
                    }
                }
            } catch (Exception e) {
                sendErrorMessage(e);
            }
        }, 20, 20);
    }

    public void islandValueManager() {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            Iterator<Map.Entry<Integer, Island>> islands = islandManager.islands.entrySet().iterator();

            @Override
            public void run() {
                if (!updatingBlocks) {
                    if (!islands.hasNext()) {
                        islands = islandManager.islands.entrySet().iterator();
                    }
                    if (islands.hasNext()) {
                        updatingBlocks = true;
                        islands.next().getValue().initBlocks();
                    }
                }
            }
        }, 0, 0);
    }

    public void sendErrorMessage(Exception e) {
        e.printStackTrace();
    }

    public void registerListeners(Listener... listener) {
        for (Listener l : listener) {
            Bukkit.getPluginManager().registerEvents(l, this);
        }
    }

    private void setupPlaceholderAPI() {
        Plugin clip = getServer().getPluginManager().getPlugin("PlaceholderAPI");
        if (clip != null && clip.isEnabled()) {
            this.clipPlaceholderAPIManager = new ClipPlaceholderAPIManager();
            if (this.clipPlaceholderAPIManager.register()) {
                getLogger().info("Successfully registered placeholders with PlaceholderAPI.");
            }
        }
    }

    public void loadSchematics() throws IOException {
        File schematicFolder = new File(getDataFolder(), "schematics");
        if (!schematicFolder.exists()) {
            schematicFolder.mkdir();
            if (!new File(schematicFolder, "island.schematic").exists()) {
                if (getResource("schematics/island.schematic") != null) {
                    saveResource("schematics/island.schematic", false);
                }
            }
        }

        schems.clear();

        for (Schematics.FakeSchematic fakeSchematic : schematics.schematics) {
            schems.put(fakeSchematic, Schematic.loadSchematic(new File(schematicFolder, fakeSchematic.name)));
        }
    }

    public void loadConfigs() {
        configuration = persist.getFile(Config.class).exists() ? persist.load(Config.class) : new Config();
        missions = persist.getFile(Missions.class).exists() ? persist.load(Missions.class) : new Missions();
        islandManager = persist.getFile(IslandManager.class).exists() ? persist.load(IslandManager.class) : new IslandManager();
        messages = persist.getFile(Messages.class).exists() ? persist.load(Messages.class) : new Messages();
        upgrades = persist.getFile(Upgrades.class).exists() ? persist.load(Upgrades.class) : new Upgrades();
        boosters = persist.getFile(Boosters.class).exists() ? persist.load(Boosters.class) : new Boosters();
        inventories = persist.getFile(Inventories.class).exists() ? persist.load(Inventories.class) : new Inventories();
        schematics = persist.getFile(Schematics.class).exists() ? persist.load(Schematics.class) : new Schematics();

        for (Island island : islandManager.islands.values()) {
            island.init();
        }
        if (getConfiguration().enabledWorlds.contains(getIslandManager().worldName) && getConfiguration().enabledWorldsIsBlacklist) {
            getConfiguration().enabledWorlds.remove(getIslandManager().worldName);
            saveConfigs();
        }
        try {
            loadSchematics();
        } catch (Exception e) {

        }
    }

    public void saveData() {
        if (islandManager != null) persist.save(islandManager);
    }

    public void saveConfigs() {
        if (configuration != null) persist.save(configuration);
        if (missions != null) persist.save(missions);
        if (islandManager != null) persist.save(islandManager);
        if (messages != null) persist.save(messages);
        if (upgrades != null) persist.save(upgrades);
        if (boosters != null) persist.save(boosters);
        if (inventories != null) persist.save(inventories);
        if (schematics != null) persist.save(schematics);
    }

    @Override
    public void onDisable() {
        try {
            super.onDisable();

            saveData();

            for (Player p : Bukkit.getOnlinePlayers()) {
                p.closeInventory();
            }

            getLogger().info("-------------------------------");
            getLogger().info("");
            getLogger().info(getDescription().getName() + " Disabled!");
            getLogger().info("");
            getLogger().info("-------------------------------");
        } catch (Exception e) {
            sendErrorMessage(e);
        }
    }


    public static IridiumSkyblock getInstance() {
        return instance;
    }

    public static IslandManager getIslandManager() {
        return islandManager;
    }

    public static Config getConfiguration() {
        return configuration;
    }

    public static Missions getMissions() {
        return missions;
    }

    public static CommandManager getCommandManager() {
        return commandManager;
    }

    public static Messages getMessages() {
        return messages;
    }

    public static Upgrades getUpgrades() {
        if (upgrades == null) {
            upgrades = new Upgrades();
            IridiumSkyblock.getPersist().getFile(upgrades).delete();
            IridiumSkyblock.getInstance().saveConfigs();
        }
        return upgrades;
    }

    public static Boosters getBoosters() {
        return boosters;
    }

    public static Schematics getSchematics() {
        return schematics;
    }

    public static Inventories getInventories() {
        return inventories;
    }

    public static Persist getPersist() {
        return persist;
    }
}