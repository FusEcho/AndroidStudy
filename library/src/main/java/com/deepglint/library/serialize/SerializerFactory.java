package com.deepglint.library.serialize;

/**
 * Created by gaofengdeng 2020/4/7
 **/
public class SerializerFactory {

    public static Serializer createJsonSerializer() {
        return new GsonSerializer();
    }
}
