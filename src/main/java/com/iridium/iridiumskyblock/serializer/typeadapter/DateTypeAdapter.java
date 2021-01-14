package com.iridium.iridiumskyblock.serializer.typeadapter;

import com.google.gson.*;
import com.iridium.iridiumskyblock.utils.MiscUtils;

import java.lang.reflect.Type;
import java.util.Date;

public class DateTypeAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {

    @Override
    public JsonElement serialize(Date date, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(MiscUtils.getCurrentTimeStamp(date, "MMM d, yyyy, hh:mm:ss aaa"));
    }


    @Override
    public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
        return MiscUtils.getLocalDateTime(jsonElement.getAsString(), "MMM d, yyyy, hh:mm:ss aaa");
    }
}