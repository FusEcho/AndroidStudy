package com.fusecho.guide.model;

/**
 * Created by gaofengdeng 2020/4/18
 **/
public class ModelMainItem {

    private String name;

    private int image;

    private Class activity;

    public ModelMainItem(String name, int image, Class activity){
        this.name = name;
        this.image = image;
        this.activity = activity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Class getActivity() {
        return activity;
    }

    public void setActivity(Class activity) {
        this.activity = activity;
    }
}
