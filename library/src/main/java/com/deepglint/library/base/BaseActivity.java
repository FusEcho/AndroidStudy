package com.deepglint.library.base;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialog;
import android.util.Log;
import android.widget.Toast;

import com.deepglint.library.R;
import com.deepglint.library.utils.HandlerThreadHandler;
import com.deepglint.library.utils.PermissionCheck;

/**
 * Created by gaofengdeng 2020/4/6
 **/
public class BaseActivity extends AppCompatActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();

    /** UI 操作的handler*/
    private final Handler mUIHandler = new Handler(Looper.getMainLooper());
    private final Thread mUiThread = mUIHandler.getLooper().getThread();

    /** 在工作线程上处理的Handler*/
    private Handler mWorkerHandler;
    private long mWorkerThreadID = -1;

    @Override
    protected void onCreate(final Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        if (mWorkerHandler == null) {
            mWorkerHandler = HandlerThreadHandler.createHandler(TAG);
            mWorkerThreadID = mWorkerHandler.getLooper().getThread().getId();
        }

    }

    @Override
    protected void onPause() {
        clearToast();
        super.onPause();
    }

    @Override
    protected synchronized void onDestroy() {
        if (mWorkerHandler != null) {
            try {
                mWorkerHandler.getLooper().quit();
            } catch (final Exception e) {
                //
            }
            mWorkerHandler = null;
        }
        super.onDestroy();
    }

    /**
     * 在UI线程上运行Runnable的辅助方法
     * @param task
     * @param duration
     */
    public final void runOnUiThread(final Runnable task, final long duration) {
        if (task == null) return;
        mUIHandler.removeCallbacks(task);
        if ((duration > 0) || Thread.currentThread() != mUiThread) {
            mUIHandler.postDelayed(task, duration);
        } else {
            try {
                task.run();
            } catch (final Exception e) {
                Log.w(TAG, e);
            }
        }
    }

    /**
     * 如果UI线程上指定的Runnable正在等待执行，请释放执行等待
     * @param task
     */
    public final void removeFromUiThread(final Runnable task) {
//		if (task == null) return;
        mUIHandler.removeCallbacks(task);
    }

    /**
     * 在工作线程上执行指定的Runnable
     * 如果没有相同的Runnable尚未执行，它将被取消（仅执行稍后指定的那个.
     * @param task
     * @param delayMillis
     */
    protected final synchronized void queueEvent(final Runnable task, final long delayMillis) {
        if ((task == null) || (mWorkerHandler == null)) return;
        try {
            mWorkerHandler.removeCallbacks(task);
            if (delayMillis > 0) {
                mWorkerHandler.postDelayed(task, delayMillis);
            } else if (mWorkerThreadID == Thread.currentThread().getId()) {
                task.run();
            } else {
                mWorkerHandler.post(task);
            }
        } catch (final Exception e) {
            // ignore
        }
    }

    /**
     * 如果要在工作线程上执行，请取消指定的Runnable
     * @param task
     */
    protected final synchronized void removeEvent(final Runnable task) {
        if (task == null) return;
        try {
            mWorkerHandler.removeCallbacks(task);
        } catch (final Exception e) {
            // ignore
        }
    }

    //==============================================================================================
    private Toast mToast;
    /**
     * 显示Toast
     * @param msg
     */
    protected void showToast(@StringRes final int msg, final Object... args) {
        removeFromUiThread(mShowToastTask);
        mShowToastTask = new ShowToastTask(msg, args);
        runOnUiThread(mShowToastTask, 0);
    }

    /**
     * 如果显示Toast,则取消
     */
    protected void clearToast() {
        removeFromUiThread(mShowToastTask);
        mShowToastTask = null;
        try {
            if (mToast != null) {
                mToast.cancel();
                mToast = null;
            }
        } catch (final Exception e) {
            // ignore
        }
    }

    private ShowToastTask mShowToastTask;
    private final class ShowToastTask implements Runnable {
        final int msg;
        final Object args;
        private ShowToastTask(@StringRes final int msg, final Object... args) {
            this.msg = msg;
            this.args = args;
        }

        @Override
        public void run() {
            try {
                if (mToast != null) {
                    mToast.cancel();
                    mToast = null;
                }
                final String _msg = (args != null) ? getString(msg, args) : getString(msg);
                mToast = Toast.makeText(BaseActivity.this, _msg, Toast.LENGTH_SHORT);
                mToast.show();
            } catch (final Exception e) {
                // ignore
            }
        }
    }

    //==============================================================================================

    /**
     * 接收权限请求结果的方法
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions, @NonNull final int[] grantResults){
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        final int n = Math.min(permissions.length, grantResults.length);
        for (int i = 0; i < n; i++) {
            checkPermissionResult(requestCode, permissions[i], grantResults[i] == PackageManager.PERMISSION_GRANTED);
        }
    }

    /**
     * 检查权限的结果
     * 在这里，当无法获得许可时，在Toast中显示一条消息
     * @param requestCode
     * @param permission
     * @param result
     */
    protected void checkPermissionResult(final int requestCode, final String permission, final boolean result) {
        // 没有权限时显示一条消息
        if (!result && (permission != null)) {
            if (Manifest.permission.INTERNET.equals(permission)) {
                showToast(R.string.permission_network);
            }
        }
    }

    /** 动态权限请求的请求代码*/
    protected static final int REQUEST_PERMISSION_NETWORK = 0x345678;

    /**
     * 检查是否具有网络访问权限
     * 如果没有，显示说明对话框
     * @return true 具有网络访问权限
     */
    protected boolean checkPermissionNetwork() {
        if (!PermissionCheck.hasNetwork(this)) {
//            MessageDialogFragmentV4.showDialog(this, REQUEST_PERMISSION_NETWORK,
//                    R.string.permission_title, R.string.permission_network_request,
//                    new String[]{Manifest.permission.INTERNET});
            return false;
        }
        return true;
    }
}
