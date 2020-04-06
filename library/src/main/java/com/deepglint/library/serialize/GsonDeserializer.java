package com.deepglint.library.serialize;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gaofengdeng 2020/4/7
 **/
public class GsonDeserializer implements Deserializer {

    private Map<Class, Gson> gsonMap;
    private GsonFactory factory;

    public GsonDeserializer() {
        this(new DefaultGsonFactory());
    }

    public GsonDeserializer(GsonFactory factory) {
        this.gsonMap = new HashMap<>();
        this.factory = factory;
    }

    private Gson getGson(Class clazz) {
        Gson gson = gsonMap.get(clazz);
        if (gson == null) {
            gson = factory.createGsonByType(clazz);
            if (gson == null) {
                gson = gsonMap.get(Object.class);
                if (gson == null) gson = getGson(Object.class);
            }
            gsonMap.put(clazz, gson);
        }

        return gson;
    }

    @Override
    public <T> T deserialize(String s, Class<T> clazz) {
        return getGson(clazz).fromJson(s, clazz);
    }
}
