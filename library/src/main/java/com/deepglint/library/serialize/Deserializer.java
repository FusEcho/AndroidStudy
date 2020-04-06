package com.deepglint.library.serialize;

/**
 * Created by gaofengdeng 2020/4/7
 **/
public interface Deserializer {
    <T> T deserialize(String s, Class<T> clazz);
}
