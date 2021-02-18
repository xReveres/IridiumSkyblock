package com.iridium.iridiumskyblock.managers;

import com.iridium.iridiumskyblock.*;
import com.iridium.iridiumskyblock.configs.Config;
import com.iridium.iridiumskyblock.configs.Schematics;
import com.iridium.iridiumskyblock.utils.NumberFormatter;
import com.iridium.iridiumskyblock.utils.StringUtils;
import org.bukkit.*;
import org.bukkit.World.Environment;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class IslandManager {

    public static Map<Integer, Island> cache = new HashMap<>();

    public static transient Integer id = 0;

    public static int length;
    public static int current;

    public static Direction direction;
    public static Location nextLocation;

    public static int nextID;

    public static World getWorld() {
        return Bukkit.getWorld(IridiumSkyblock.getInstance().getConfiguration().worldName);
    }

    public static World getNetherWorld() {
        return Bukkit.getWorld(IridiumSkyblock.getInstance().getConfiguration().netherWorldName);
    }

    public static void createIsland(Player player) {
        User user = User.getUser(player);
        if (user.isOnCooldown()) {
            //The user cannot create an island
            player.sendMessage(StringUtils.color(user.getCooldownTimeMessage()));
            return;
        }
        Calendar c = Calendar.getInstance();
        c.add(Calendar.SECOND, IridiumSkyblock.getInstance().getConfiguration().regenCooldown);
        user.lastCreate = c.getTime();

        Location pos1 = nextLocation.clone().subtract((IridiumSkyblock.getInstance().getUpgrades().islandSizeUpgrade.getIslandUpgrade(1).size / 2.00), 0, (IridiumSkyblock.getInstance().getUpgrades().islandSizeUpgrade.getIslandUpgrade(1).size / 2.00));
        Location pos2 = nextLocation.clone().add((IridiumSkyblock.getInstance().getUpgrades().islandSizeUpgrade.getIslandUpgrade(1).size / 2.00), 0, (IridiumSkyblock.getInstance().getUpgrades().islandSizeUpgrade.getIslandUpgrade(1).size / 2.00));
        Location center = nextLocation.clone().add(0, 100, 0);
        Location home = nextLocation.clone();
        Island island = new Island(player, pos1, pos2, center, home, nextID);
        island.updateIslandData();

        cache.put(nextID, island);

        user.islandID = nextID;
        user.role = Role.Owner;

        if (IridiumSkyblock.getInstance().getSchematics().schematicList.size() == 1) {
            for (Schematics.FakeSchematic schematic : IridiumSkyblock.getInstance().getSchematics().schematicList) {
                island.schematic = schematic.overworldData.schematic;
                island.netherschematic = schematic.netherData.schematic;
                island.home = island.home.add(schematic.x, schematic.y, schematic.z);
                break;
            }
            island.pasteSchematic(player, false);
        } else {
            player.openInventory(island.schematicSelectGUI.getInventory());
        }

        switch (direction) {
            case NORTH:
                nextLocation.add(IridiumSkyblock.getInstance().getConfiguration().distance, 0, 0);
                break;
            case EAST:
                nextLocation.add(0, 0, IridiumSkyblock.getInstance().getConfiguration().distance);
                break;
            case SOUTH:
                nextLocation.subtract(IridiumSkyblock.getInstance().getConfiguration().distance, 0, 0);
                break;
            case WEST:
                nextLocation.subtract(0, 0, IridiumSkyblock.getInstance().getConfiguration().distance);
                break;
        }

        current++;

        if (current == length) {
            current = 0;
            direction = direction.next();
            if (direction == Direction.SOUTH || direction == Direction.NORTH) {
                length++;
            }
        }
        nextID++;
    }

    public static void startCounting() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        new Timer().schedule(new TimerTask() {
            public void run() {
                LocalDateTime ldt = LocalDateTime.now();
                if (ldt.getDayOfWeek().equals(DayOfWeek.MONDAY) && IridiumSkyblock.getInstance().getConfiguration().missionRestart.equals(MissionRestart.Weekly) || IridiumSkyblock.getInstance().getConfiguration().missionRestart.equals(MissionRestart.Daily)) {
                    for (Island island : IslandManager.getLoadedIslands()) {
                        island.resetMissions();
                    }
                }
                for (User user : UserManager.cache.values()) {
                    user.tookInterestMessage = false;
                }
                for (Island island : IslandManager.getLoadedIslands()) {
                    island.interestMoney = island.getMoney() * IridiumSkyblock.getInstance().getConfiguration().dailyMoneyInterest / 100.00;
                    island.interestCrystal = (int) (island.getCrystals() * IridiumSkyblock.getInstance().getConfiguration().dailyCrystalsInterest / 100.00);
                    island.interestExp = (int) (island.getExperience() * IridiumSkyblock.getInstance().getConfiguration().dailyExpInterest / 100.00);

                    island.setMoney(island.getMoney() + island.interestMoney);
                    island.setCrystals(island.getCrystals() + island.interestCrystal);
                    island.setExperience(island.getExperience() + island.interestExp);
                    island.members.stream().map(member -> Bukkit.getPlayer(UUID.fromString(member))).filter(Objects::nonNull).forEach(player -> {
                        if (island.interestMoney != 0 || island.interestExp != 0 || island.interestCrystal != 0) {
                            player.sendMessage(StringUtils.color(IridiumSkyblock.getInstance().getMessages().islandInterest
                                    .replace("%exp%", NumberFormatter.format(island.interestExp))
                                    .replace("%crystals%", NumberFormatter.format(island.interestCrystal))
                                    .replace("%money%", NumberFormatter.format(island.interestMoney))
                                    .replace("%prefix%", IridiumSkyblock.getInstance().getConfiguration().prefix)));
                        }
                    });
                }
                Bukkit.getScheduler().runTask(IridiumSkyblock.getInstance(), () -> startCounting());
            }

        }, c.getTime());
    }

    public static void islandValueManager() {
        //Loop through all online islands and make sure Island#valuableBlocks is accurate
        Bukkit.getScheduler().scheduleSyncRepeatingTask(IridiumSkyblock.getInstance(), new Runnable() {
            ListIterator<Integer> islands = IslandManager.getLoadedIslands().stream().map(is -> is.id).collect(Collectors.toList()).listIterator();

            @Override
            public void run() {
                if (!islands.hasNext()) {
                    islands = IslandManager.getLoadedIslands().stream().map(is -> is.id).collect(Collectors.toList()).listIterator();
                }
                if (islands.hasNext()) {
                    int id = islands.next();
                    Island island = IslandManager.getIslandViaId(id);
                    if (island != null) {
                        island.initBlocks();
                    }
                }
            }
        }, 0, IridiumSkyblock.getInstance().getConfiguration().valueUpdateInterval);
    }

    public static int purgeIslands(int days, CommandSender sender) {
        List<Integer> ids = getLoadedIslands().stream().filter(island -> oldIsland(days, island)).map(is -> is.id).collect(Collectors.toList());
        final ListIterator<Integer> islandIds = ids.listIterator();
        id = Bukkit.getScheduler().scheduleSyncRepeatingTask(IridiumSkyblock.getInstance(), new Runnable() {
            int amount = 0;

            @Override
            public void run() {
                if (islandIds.hasNext()) {
                    int i = islandIds.next();
                    Island island = getIslandViaId(i);
                    island.delete();
                    amount++;
                } else {
                    sender.sendMessage(StringUtils.color(IridiumSkyblock.getInstance().getMessages().purgingFinished.replace("%amount%", String.valueOf(amount)).replace("%prefix%", IridiumSkyblock.getInstance().getConfiguration().prefix)));
                    Bukkit.getScheduler().cancelTask(id);
                    id = 0;
                }
            }
        }, 0, 20 * 5);
        return ids.size();
    }

    private static boolean oldIsland(int days, Island island) {
        LocalDateTime now = LocalDateTime.now();
        for (OfflinePlayer player : island.members.stream().map(s -> Bukkit.getOfflinePlayer(UUID.fromString(s))).collect(Collectors.toList())) {
            if (player == null) continue;
            LocalDateTime lastLogin = LocalDateTime.ofInstant(Instant.ofEpochMilli(player.getLastPlayed()), TimeZone.getDefault().toZoneId());
            Duration duration = Duration.between(lastLogin, now);
            if (duration.toDays() < days) {
                return false;
            }
        }
        return true;
    }

    public static void makeWorlds() {
        makeWorld(Environment.NORMAL, IridiumSkyblock.getInstance().getConfiguration().worldName);
        if (IridiumSkyblock.getInstance().getConfiguration().netherIslands)
            makeWorld(Environment.NETHER, IridiumSkyblock.getInstance().getConfiguration().netherWorldName);
    }

    private static void makeWorld(Environment env, String name) {
        WorldCreator wc = new WorldCreator(name);
        wc.type(WorldType.FLAT);
        wc.generateStructures(false);
        wc.generator(new SkyblockGenerator());
        wc.environment(env);
        wc.createWorld();
    }

    public static List<Island> getLoadedIslands() {
        return cache.values().stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    public static Island getIslandViaLocation(Location location) {
        if (location == null) return null;
        if (!isIslandWorld(location)) return null;

        final Chunk chunk = location.getChunk();

        final double x = location.getX();
        final double z = location.getZ();
        final Set<Integer> islandIds = ClaimManager.getIslands(chunk.getX(), chunk.getZ());

        for (int id : islandIds) {
            final Island island = getIslandViaId(id);
            if (island == null) continue;
            if (island.isInIsland(x, z)) return island;
        }

        for (Island island : getLoadedIslands()) {
            if (!island.isInIsland(x, z)) continue;
            ClaimManager.addClaim(chunk.getX(), chunk.getZ(), island.id);
            return island;
        }

        return null;
    }

    public static Island getIslandViaId(int id) {

        if (cache.containsKey(id)) return cache.get(id);
        try (Connection connection = IridiumSkyblock.getInstance().getSqlManager().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM islands WHERE id =?;")) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    //There is a value
                    Island island = IridiumSkyblock.getInstance().getPersist().load(Island.class, resultSet.getString("json"));
                    cache.put(id, island);

                    island.init();
                    if (island.getName().length() > IridiumSkyblock.getInstance().getConfiguration().maxIslandName) {
                        island.name = island.getName().substring(0, IridiumSkyblock.getInstance().getConfiguration().maxIslandName);
                    }
                    if (island.getName().length() < IridiumSkyblock.getInstance().getConfiguration().minIslandName) {
                        OfflinePlayer owner = Bukkit.getOfflinePlayer(UUID.fromString(island.owner));
                        island.name = owner.getName();
                    }

                    return island;
                }
            }

            cache.put(id, null);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static boolean isIslandWorld(Location location) {
        if (location == null) return false;
        return isIslandWorld(location.getWorld());
    }

    public static boolean isIslandWorld(World world) {
        if (world == null) return false;
        final String name = world.getName();
        return isIslandWorld(name);
    }

    public static boolean isIslandWorld(String name) {
        final Config config = IridiumSkyblock.getInstance().getConfiguration();
        return (name.equals(config.worldName) || name.equals(config.netherWorldName));
    }

    public static void save(Island island, Connection connection) {

        String json = IridiumSkyblock.getInstance().getPersist().toString(island);
        try (PreparedStatement insert = connection.prepareStatement("REPLACE INTO islands (id,json) VALUES (?,?);")) {
            insert.setInt(1, island.id);
            insert.setString(2, json);
            insert.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void removeIsland(Island island, Connection connection) {
        final int id = island.id;
        cache.remove(id);
        try (PreparedStatement insert = connection.prepareStatement("DELETE FROM islands WHERE id=?;")) {
            insert.setInt(1, id);
            insert.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void removeIsland(Island island) {
        Bukkit.getScheduler().runTaskAsynchronously(IridiumSkyblock.getInstance(), () -> {
            try (Connection connection = IridiumSkyblock.getInstance().getSqlManager().getConnection()) {

                removeIsland(island, connection);
                ClaimManager.removeClaims(island.id, connection);
                IslandDataManager.remove(island.id, connection);
                connection.commit();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }
}
