package com.fusecho.guide.ui;

import android.content.Context;
import android.os.Bundle;

import com.deepglint.library.mvp.MVPActivity;
import com.fusecho.guide.working.WorkingContract;

/**
 * Created by gaofengdeng 2020/4/6
 **/
public abstract class WorkingBaseActivity extends MVPActivity<WorkingContract.WorkingView, WorkingContract.WorkingPresenter>
        implements WorkingContract.WorkingView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed(){

    }

    @Override
    protected WorkingContract.WorkingPresenter createPresenter() {
        return new WorkingContract.WorkingPresenter();
    }

    @Override
    public Context getWorkingContext() {
        return this;
    }

}
