package com.iridium.iridiumskyblock;

import org.apache.commons.lang.WordUtils;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TransactionLogger {
    public enum TransactionType {
        MONEY,
        CRYSTALS,
        EXPERIENCE;

        @Override
        public String toString() {
            return WordUtils.capitalize(name().toLowerCase());
        }
    }

    public static class Transaction {
        private final Map<TransactionType, Double> transactionAmounts = new HashMap<>();

        public Transaction add(TransactionType type, double amount) {
            transactionAmounts.put(type, amount);
            return this;
        }

        public double getTransactionAmount() {
            return transactionAmounts.values().stream().filter(value -> value != 0).findAny().orElse(0.0);
        }

        @Override
        public String toString() {
            String[] transactionParts = transactionAmounts.keySet().stream()
                    .filter(type -> transactionAmounts.get(type) != 0)
                    .map(type -> Math.abs(transactionAmounts.get(type)) + " " + type.toString())
                    .toArray(String[]::new);
            return String.join(", ", transactionParts);
        }

    }

    /**
     * @param transaction positive amount = sale, negative amount = purchase
     */
    public static void saveTransaction(Player player, Transaction transaction) {
        if (!IridiumSkyblock.getConfiguration().logTransactions) {
            return;
        }

        if (transaction.transactionAmounts.isEmpty()) {
            return;
        }

        if (transaction.getTransactionAmount() == 0) {
            return;
        }

        StringBuilder logEntry = new StringBuilder();
        addTimestamp(logEntry);
        addPlayerInfo(logEntry, player);
        logEntry.append(transaction.getTransactionAmount() < 0 ? "Purchased for " : "Sold for ");
        logEntry.append(transaction.toString());

        appendToFile(logEntry);
    }

    /**
     * @param transaction positive amount = deposit, negative amount = withdraw
     */
    public static void saveBankBalanceChange(Player player, Transaction transaction) {
        if (!IridiumSkyblock.getConfiguration().logBankBalanceChange) {
            return;
        }

        if (transaction.transactionAmounts.isEmpty()) {
            return;
        }

        if (transaction.getTransactionAmount() == 0) {
            return;
        }

        StringBuilder logEntry = new StringBuilder();
        addTimestamp(logEntry);
        addPlayerInfo(logEntry, player);
        logEntry.append(transaction.getTransactionAmount() < 0 ? "Withdrew " : "Deposited ");
        logEntry.append(transaction.toString());

        appendToFile(logEntry);
    }

    private static void addTimestamp(StringBuilder stringBuilder) {
        // Append: Year-Month-Day Hour:Minute:Second
        Date currentDate = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        stringBuilder.append("[").append(dateFormat.format(currentDate)).append("] ");
    }

    private static void addPlayerInfo(StringBuilder stringBuilder, Player player) {
        // Append: PlayerName (PlayerUUID):
        stringBuilder.append(player.getName()).append(" (").append(player.getUniqueId().toString()).append("): ");
    }

    private static void appendToFile(StringBuilder logEntry) {
        logEntry.append("\r\n");
        Path path = Paths.get("plugins", "IridiumSkyblock", "logs", "transactions.log");

        try {
            path.getParent().toFile().mkdirs();
            Files.write(path, logEntry.toString().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
