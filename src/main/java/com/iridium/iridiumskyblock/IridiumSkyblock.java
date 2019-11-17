package com.iridium.iridiumskyblock;

import com.iridium.iridiumskyblock.commands.CommandManager;
import com.iridium.iridiumskyblock.configs.*;
import com.iridium.iridiumskyblock.gui.TopGUI;
import com.iridium.iridiumskyblock.listeners.*;
import com.iridium.iridiumskyblock.placeholders.ClipPlaceholderAPIManager;
import com.iridium.iridiumskyblock.placeholders.MVDWPlaceholderAPIManager;
import com.iridium.iridiumskyblock.serializer.Persist;
import com.iridium.iridiumskyblock.support.Vault;
import com.iridium.iridiumskyblock.support.Wildstacker;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentMap;

public class IridiumSkyblock extends JavaPlugin {

    private static IridiumSkyblock instance;

    public HashMap<Schematics.FakeSchematic, Schematic> schems = new HashMap<>();
    public HashMap<Schematics.FakeSchematic, Schematic> netherschems = new HashMap<>();

    public static Config configuration;
    public static Messages messages;
    public static Missions missions;
    public static Upgrades upgrades;
    public static Boosters boosters;
    public static Inventories inventories;
    public static Schematics schematics;
    public static Commands commands;

    private static Persist persist;

    private static IslandManager islandManager;

    private static CommandManager commandManager;

    public static TopGUI topGUI;

    public boolean updatingBlocks = false;

    private String latest;

    @Override
    public void onEnable() {
        try {
            instance = this;

            super.onEnable();
            getDataFolder().mkdir();

            persist = new Persist();

            Bukkit.getScheduler().runTask(this, () -> { // Call this a tick later to ensure all worlds are loaded

                loadConfigs();
                saveConfigs();

                commandManager = new CommandManager("island");
                commandManager.registerCommands();

                if (Bukkit.getPluginManager().getPlugin("Vault") != null) new Vault();
                if (Bukkit.getPluginManager().isPluginEnabled("WildStacker")) new Wildstacker();

                // Call it as a delayed task to wait for the server to properly load first
                Bukkit.getScheduler().scheduleSyncDelayedTask(IridiumSkyblock.getInstance(), IridiumSkyblock.getInstance()::islandValueManager);

                topGUI = new TopGUI();

                registerListeners(new onPlayerCommandPreprocess(), new onPlayerPortal(), new onBlockBreak(), new onBlockPlace(), new onClick(), new onBlockFromTo(), new onSpawnerSpawn(), new onEntityDeath(), new onPlayerJoinLeave(), new onBlockGrow(), new onPlayerTalk(), new onPlayerMove(), new onEntityDamageByEntity(), new onPlayerExpChange(), new onPlayerFish(), new onEntityExplode());

                new Metrics(IridiumSkyblock.getInstance());

                Bukkit.getScheduler().scheduleAsyncRepeatingTask(IridiumSkyblock.getInstance(), () -> getPersist().save(islandManager), 0, 20);

                setupPlaceholderAPI();

                startCounting();
                try {
                    latest = new BufferedReader(new InputStreamReader(new URL("https://api.spigotmc.org/legacy/update.php?resource=62480").openConnection().getInputStream())).readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                getLogger().info("-------------------------------");
                getLogger().info("");
                getLogger().info(getDescription().getName() + " Enabled!");
                if (!latest.equals(getDescription().getVersion()))
                    getLogger().info("Newer version available: " + latest);
                getLogger().info("");
                getLogger().info("-------------------------------");
            });
        } catch (Exception e) {
            sendErrorMessage(e);
        }
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

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        if (worldName.equals(getIslandManager().worldName) || worldName.equals(getIslandManager().netherName))
            return new SkyblockGenerator();
        return super.getDefaultWorldGenerator(worldName, id);
    }

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
            Iterator<ConcurrentMap.Entry<Integer, Island>> islands = islandManager.islands.entrySet().iterator();

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
        Plugin mvdw = getServer().getPluginManager().getPlugin("MVdWPlaceholderAPI");
        if (clip != null && clip.isEnabled()) {
            if (new ClipPlaceholderAPIManager().register()) {
                getLogger().info("Successfully registered placeholders with PlaceholderAPI.");
            }
        }
        if (mvdw != null && mvdw.isEnabled()) {
            new MVDWPlaceholderAPIManager().register();
            getLogger().info("Successfully registered placeholders with MVDWPlaceholderAPI.");
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
            if (!new File(schematicFolder, "nether.schematic").exists()) {
                if (getResource("schematics/nether.schematic") != null) {
                    saveResource("schematics/nether.schematic", false);
                }
            }
        }

        schems.clear();

        for (Schematics.FakeSchematic fakeSchematic : schematics.schematics) {
            schems.put(fakeSchematic, Schematic.loadSchematic(new File(schematicFolder, fakeSchematic.name)));
            if (fakeSchematic.netherisland == null) {
                fakeSchematic.netherisland = fakeSchematic.name;
            }
            netherschems.put(fakeSchematic, Schematic.loadSchematic(new File(schematicFolder, fakeSchematic.netherisland)));
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
        commands = persist.getFile(Commands.class).exists() ? persist.load(Commands.class) : new Commands();

        if (getBoosters().flightBooster.time == 0) getBoosters().flightBooster.time = 3600;
        if (getBoosters().experianceBooster.time == 0) getBoosters().experianceBooster.time = 3600;
        if (getBoosters().farmingBooster.time == 0) getBoosters().farmingBooster.time = 3600;
        if (getBoosters().spawnerBooster.time == 0) getBoosters().spawnerBooster.time = 3600;

        for (Island island : islandManager.islands.values()) {
            island.init();
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
        if (commands != null) persist.save(commands);
    }

    public String getLatest() {
        return latest;
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

    public static Commands getCommands() {
        return commands;
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