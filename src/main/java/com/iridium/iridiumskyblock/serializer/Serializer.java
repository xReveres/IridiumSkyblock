package com.iridium.iridiumskyblock.serializer;


import com.iridium.iridiumskyblock.IridiumSkyblock;

public class Serializer {


    /**
     * Saves your class to a .json file.
     */
    public void save(Object instance) {
        IridiumSkyblock.getPersist().save(instance);
    }

    /**
     * Loads your class from a json file
     */
    public <T> T load(T def, Class<T> clazz, String name) {
        return IridiumSkyblock.getPersist().loadOrSaveDefault(def, clazz, name);
    }


}
