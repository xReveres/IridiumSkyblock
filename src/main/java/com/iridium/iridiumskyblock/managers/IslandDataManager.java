package com.iridium.iridiumskyblock.managers;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IslandDataManager {

    public static HashMap<Integer, IslandData> cache = new HashMap<>();

    //This class is used for getting islands in order of value or votes e.g. for /is top or /is visit

    //From Index (Starts at 0 inclusive)
    //To Index exclusive
    public static List<Integer> getIslands(IslandSortType sortType, int fromIndex, int toIndex, boolean ignorePrivate) {

        Stream<Integer> stream;
        switch (sortType) {
            case VALUE:
                 stream = cache.keySet().stream().sorted(Comparator.comparing(integer -> cache.get(integer).value).reversed());
                if (ignorePrivate) {
                    stream = stream.filter(integer -> !cache.get(integer).isPrivate);
                }
                break;
            case VOTES:
                stream = cache.keySet().stream().sorted(Comparator.comparing(integer -> cache.get(integer).votes).reversed());
                if (ignorePrivate) {
                    stream = stream.filter(integer -> !cache.get(integer).isPrivate);
                }
                break;
            default:
                return Collections.emptyList();
        }

        List<Integer> islands = stream.collect(Collectors.toList());
        if (islands.size() < fromIndex + 1) {
            return Collections.emptyList();
        }

        return islands.subList(fromIndex, Math.min(islands.size(), toIndex));
    }

    public static void update(Connection connection) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM islanddata;")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cache.put(resultSet.getInt(1), new IslandData(resultSet.getDouble(2), resultSet.getInt(3), resultSet.getBoolean(4)));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void remove(int island, Connection connection) {
        cache.remove(island);
        try (PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM islanddata where islandID=?;")) {
            deleteStatement.setInt(1, island);
            deleteStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void save(Island island, Connection connection) {

        try (PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM islanddata where islandID=?;")) {
            deleteStatement.setInt(1, island.id);
            deleteStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try (PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO islanddata (islandID,value,votes,private) VALUES (?,?,?,?);")) {
            insertStatement.setInt(1, island.id);
            insertStatement.setDouble(2, island.value);
            insertStatement.setInt(3, island.getVotes());
            insertStatement.setBoolean(4, !island.visit);
            insertStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void save(Island island, boolean async) {
        if (async) {
            Bukkit.getScheduler().runTaskAsynchronously(IridiumSkyblock.getInstance(), () -> save(island, false));
            return;
        }

        try (Connection connection = IridiumSkyblock.getInstance().getSqlManager().getConnection()) {
            save(island, connection);

            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public enum IslandSortType {
        VALUE("value"), VOTES("votes");
        public String name;

        IslandSortType(String name) {
            this.name = name;
        }
    }

    public static class IslandData {
        public double value;
        public int votes;
        public boolean isPrivate;

        public IslandData(double value, int votes, boolean isPrivate) {
            this.value = value;
            this.votes = votes;
            this.isPrivate = isPrivate;
        }
    }
}