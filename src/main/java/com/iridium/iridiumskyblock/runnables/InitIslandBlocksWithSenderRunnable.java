package com.iridium.iridiumskyblock.runnables;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.Utils;
import com.iridium.iridiumskyblock.configs.Config;
import com.iridium.iridiumskyblock.configs.Messages;
import com.iridium.iridiumskyblock.iterators.InitIslandBlocksIterator;
import org.bukkit.command.CommandSender;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class InitIslandBlocksWithSenderRunnable implements Runnable {
    private final Config config = IridiumSkyblock.getConfiguration();
    private final Messages messages = IridiumSkyblock.getMessages();

    private final int blocksPerTick;
    private final CommandSender sender;
    private final String name;
    private final Runnable callback;

    private final Iterator<Long> initBlocksIterator;
    private final String updatePercentMessage;

    private final long maxBlocks;
    private int percentage = 0;

    public InitIslandBlocksWithSenderRunnable(Island island, int blocksPerTick, CommandSender sender, String name, Runnable callback) {
        this.blocksPerTick = blocksPerTick;
        this.sender = sender;
        this.name = name;
        this.callback = callback;

        initBlocksIterator = new InitIslandBlocksIterator(island);
        updatePercentMessage = messages.updatePercent
                .replace("%player%", name)
                .replace("%prefix%", config.prefix);

        maxBlocks = ((config.netherIslands) ? 2 : 1) * island.getBlockCount();
    }

    @Override
    public void run() {
        try {
            long currentBlock = 0;
            for (int i = 0; i < blocksPerTick; i++) {
                try {
                    currentBlock = initBlocksIterator.next();
                } catch (NoSuchElementException e) {
                    if (sender != null)
                        sender.sendMessage(Utils.color(messages.updateFinished
                                .replace("%player%", name)
                                .replace("%prefix%", config.prefix)));
                    callback.run();
                    return;
                }
            }

            final int currentPercentage = (int) ((currentBlock / maxBlocks) * 100);
            if (currentPercentage == percentage) return;
            percentage = currentPercentage;

            if (sender == null) return;
            sender.sendMessage(Utils.color(updatePercentMessage
                    .replace("%percent%", percentage + "")));
        } catch (Exception e) {
            IridiumSkyblock.getInstance().sendErrorMessage(e);
        }
    }

}
