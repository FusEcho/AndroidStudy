package com.deepglint.library.serialize;

import com.google.gson.Gson;

/**
 * Created by gaofengdeng 2020/4/7
 **/
public interface GsonFactory {
    Gson createGsonByType(Object type);
}
