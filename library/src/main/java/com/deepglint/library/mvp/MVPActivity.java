package com.deepglint.library.mvp;

import android.os.Bundle;

import com.deepglint.library.base.BaseActivity;

/**
 * Created by gaofengdeng 2020/4/6
 **/
public abstract class MVPActivity<V extends IView, P extends IPresenter<V>> extends BaseActivity {
    private P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.mPresenter == null) {
            this.mPresenter = createPresenter();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.mPresenter.attach(getMvpView());
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.mPresenter.detach();
    }

    @Override
    protected synchronized void onDestroy() {
        super.onDestroy();
    }

    protected abstract P createPresenter();

    public V getMvpView() {
        return (V) this;
    }


    public P getPresenter() {
        return mPresenter;
    }
}
