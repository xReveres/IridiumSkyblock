package com.iridium.iridiumskyblock.runnables;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.iterators.InitIslandBlocksIterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class InitIslandBlocksRunnable implements Runnable {
    private final int blocksPerTick;
    private final Runnable callback;

    private final Iterator<Long> initBlocksIterator;

    public InitIslandBlocksRunnable(Island island, int blocksPerTick, Runnable callback) {
        this.blocksPerTick = blocksPerTick;
        this.callback = callback;

        initBlocksIterator = new InitIslandBlocksIterator(island);
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < blocksPerTick; i++) {
                try {
                    initBlocksIterator.next();
                } catch (NoSuchElementException e) {
                    callback.run();
                    return;
                }
            }
        } catch (Exception e) {
            IridiumSkyblock.getInstance().sendErrorMessage(e);
        }
    }

}
