package com.fusecho.guide;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.deepglint.library.base.BaseActivity;
import com.deepglint.library.utils.PermissionCheck;

/**
 * Created by gaofengdeng 2020/4/6
 **/
public class SplashActivity extends BaseActivity {

    Runnable startMainActivityRunable = new Runnable() {
        @Override
        public void run() {
            startActivity();
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onStart(){
        super.onStart();
        if (checkPermissionNetwork()) {
            runOnUiThread(startMainActivityRunable, 2000);
        }
    }

    @Override
    protected void onStop(){
        super.onStop();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (!PermissionCheck.hasNetwork(this)) {
            checkPermissionNetwork();
        } else {
            runOnUiThread(startMainActivityRunable, 2000);
        }
    }

    private void startActivity() {
        Intent intent = new Intent(BuildConfig.APPLICATION_ID + "." + BuildConfig.FLAVOR + ".MAIN_VIEW");
//        intent.putExtras(getActivityArguments());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
