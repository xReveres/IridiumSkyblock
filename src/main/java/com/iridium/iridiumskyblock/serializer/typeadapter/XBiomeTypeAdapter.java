package com.iridium.iridiumskyblock.serializer.typeadapter;

import com.cryptomorin.xseries.XBiome;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Optional;

public class XBiomeTypeAdapter implements JsonSerializer<XBiome>, JsonDeserializer<XBiome> {

    @Override
    public JsonElement serialize(XBiome biome, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(biome.name());
    }


    @Override
    public XBiome deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
        String name = jsonElement.getAsString();
        if (name.equalsIgnoreCase("NETHER")) return XBiome.NETHER_WASTES;
        Optional<XBiome> optionalXBiome = XBiome.matchXBiome(name);
        return optionalXBiome.orElse(XBiome.PLAINS);
    }
}