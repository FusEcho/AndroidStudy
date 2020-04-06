package com.deepglint.library.serialize;

import android.util.SparseArray;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by gaofengdeng 2020/4/7
 **/
public class SparseArraySerializer implements JsonSerializer<SparseArray<Object>> {
    @Override
    public JsonElement serialize(SparseArray<Object> src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonElement = new JsonObject();
        for (int i = 0; i < src.size(); i++) {
            int key = src.keyAt(i);
            Object value = src.valueAt(i);
            jsonElement.add(String.valueOf(key), context.serialize(value));
        }
        return jsonElement;
    }
}
