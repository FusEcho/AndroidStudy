package com.deepglint.library.serialize;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by gaofengdeng 2020/4/7
 **/
public class MapSerializer implements JsonSerializer<Map<Object, Object>> {

    @Override
    public JsonElement serialize(Map<Object, Object> src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonElement = new JsonObject();
        for (Map.Entry<Object, Object> entry : src.entrySet()) {
            if (entry.getValue() != null) jsonElement.add(entry.getKey().toString(), context.serialize(entry.getValue()));
        }
        return jsonElement;
    }
}
