package com.iridium.iridiumskyblock.managers;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ClaimManager {

    public static Map<List<Integer>, Set<Integer>> cache = new HashMap<>();

    public static Set<Integer> getIslands(int x, int z) {
        List<Integer> chunkKey = Collections.unmodifiableList(Arrays.asList(x, z));
        if (cache.containsKey(chunkKey)) return cache.get(chunkKey);
        Set<Integer> islands = new HashSet<>();
        try (Connection connection = IridiumSkyblock.getInstance().getSqlManager().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM claims WHERE x =? AND z=?;")) {
            statement.setInt(1, x);
            statement.setInt(2, z);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    islands.add(resultSet.getInt("island"));
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        cache.put(chunkKey, islands);
        return islands;
    }

    public static void addClaim(int x, int z, int island, Connection connection) {
        try (PreparedStatement insert = connection.prepareStatement("INSERT INTO claims (x,z,island) VALUES (?,?,?);")) {

            insert.setInt(1, x);
            insert.setInt(2, z);
            insert.setInt(3, island);
            insert.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void addClaim(int x, int z, int island) {
        List<Integer> chunkKey = Collections.unmodifiableList(Arrays.asList(x, z));
        Set<Integer> islandIds = cache.getOrDefault(chunkKey, new HashSet<>());
        islandIds.add(island);
        cache.put(chunkKey, islandIds);
        Bukkit.getScheduler().runTaskAsynchronously(IridiumSkyblock.getInstance(), () -> {
            try (Connection connection = IridiumSkyblock.getInstance().getSqlManager().getConnection()) {
                addClaim(x, z, island, connection);
                connection.commit();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    public static void removeClaims(int island, Connection connection) {
        try (PreparedStatement insert = connection.prepareStatement("DELETE FROM claims WHERE island=?;")) {
            insert.setInt(1, island);
            insert.executeUpdate();
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}