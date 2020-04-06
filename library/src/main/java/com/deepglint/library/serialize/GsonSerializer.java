package com.deepglint.library.serialize;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gaofengdeng 2020/4/7
 **/
public class GsonSerializer implements Serializer {

    private Map<Class, Gson> gsonMap;
    private GsonFactory factory;

    public GsonSerializer() {
        this(new DefaultGsonFactory());
    }

    public GsonSerializer(GsonFactory factory) {
        this.gsonMap = new HashMap<>();
        this.factory = factory;
    }

    private Gson getGson(Object o) {
        Gson gson = gsonMap.get(o.getClass());
        if (gson == null) {
            gson = factory.createGsonByType(o);
            if (gson == null) {
                gson = gsonMap.get(Object.class);
                if (gson == null) gson = getGson(new Object());
            }
            gsonMap.put(o.getClass(), gson);
        }

        return gson;
    }

    public String serialize(Object o) {
        return getGson(o).toJson(o);
    }
}
