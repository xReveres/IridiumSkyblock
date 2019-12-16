package com.iridium.iridiumskyblock.serializer.typeadapter;

import com.google.gson.*;
import com.iridium.iridiumskyblock.MultiversionMaterials;

import java.lang.reflect.Type;

public class MultiversionMaterialsTypeAdapter implements JsonSerializer<MultiversionMaterials>, JsonDeserializer<MultiversionMaterials> {

    @Override
    public JsonElement serialize(MultiversionMaterials material, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(material.name());
    }


    @Override
    public MultiversionMaterials deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
        return MultiversionMaterials.fromString(jsonElement.getAsString().replace("LEGACY_", ""));
    }
}