package com.fusecho.guide.working;

import android.content.Context;

import com.deepglint.library.mvp.IPresenter;
import com.deepglint.library.mvp.IView;

import java.lang.ref.WeakReference;

/**
 * Created by gaofengdeng 2020/4/6
 **/
public class WorkingContract {

    public interface WorkingView extends IView{
        Context getWorkingContext();

    }

    public static class WorkingPresenter implements IPresenter<WorkingView> {
        private WeakReference<WorkingView> mView;

        public void attach(WorkingView view) {
            mView = new WeakReference<>(view);
        }

        public void detach() {
            if (mView != null) {
                mView.clear();
            }
        }
    }
}
