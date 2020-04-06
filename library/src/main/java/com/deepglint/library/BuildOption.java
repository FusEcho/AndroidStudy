package com.deepglint.library;

/**
 * Created by gaofengdeng 2020/4/6
 **/
public class BuildOption {

    private static String deviceID = "alias";

    public static String getDeviceID() {
        return deviceID;
    }

    public static void setDeviceID(String deviceID) {
        BuildOption.deviceID = deviceID;
    }
}
