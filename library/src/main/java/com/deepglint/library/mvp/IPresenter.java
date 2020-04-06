package com.deepglint.library.mvp;

/**
 * Created by gaofengdeng 2020/4/6
 **/
public interface IPresenter <V extends IView>{
    void attach(V view);
    void detach();
}
