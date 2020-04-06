package com.deepglint.library.serialize;

import android.util.SparseArray;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

/**
 * Created by gaofengdeng 2020/4/7
 **/
public class DefaultGsonFactory implements GsonFactory {
    @Override
    public Gson createGsonByType(Object type) {
        if (type.equals(Object.class) || type.getClass().equals(Object.class)) {
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Map.class, new MapSerializer());
            builder.registerTypeAdapter(SparseArray.class, new SparseArraySerializer());
            return builder.create();
        }

        return null;
    }
}
