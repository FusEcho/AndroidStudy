package com.fusecho.guide;

import android.app.Application;
import android.content.Context;

import com.deepglint.library.tool.FusTool;

/**
 * Created by gaofengdeng 2020/4/6
 **/
public class App extends Application {

    private static App sApp;

    @Override
    public void onCreate(){
        super.onCreate();
        init();
    }

    private void init(){
        FusTool.init(this);
    }

    public static Context getAppContext() {
        return sApp;
    }

    public static App getAppInstance() {
        return sApp;
    }

}
