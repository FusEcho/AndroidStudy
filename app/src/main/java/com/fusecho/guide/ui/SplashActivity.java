package com.fusecho.guide.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.deepglint.library.base.BaseActivity;
import com.deepglint.library.utils.PermissionCheck;
import com.fusecho.guide.BuildConfig;
import com.fusecho.guide.R;
import com.fusecho.guide.model.ModelSVG;
import com.jaredrummler.android.widget.AnimatedSvgView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gaofengdeng 2020/4/6
 **/
public class SplashActivity extends BaseActivity {

    @BindView(R.id.animated_svg_view)
    AnimatedSvgView mSvgView;

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
        ButterKnife.bind(this);
        setSvg(ModelSVG.values()[4]);

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

    private void setSvg(ModelSVG modelSvg) {
        mSvgView.setGlyphStrings(modelSvg.glyphs);
        mSvgView.setFillColors(modelSvg.colors);
        mSvgView.setViewportSize(modelSvg.width, modelSvg.height);
        mSvgView.setTraceResidueColor(0x32000000);
        mSvgView.setTraceColors(modelSvg.colors);
        mSvgView.rebuildGlyphData();
        mSvgView.start();
    }

    private void startActivity() {
        Intent intent = new Intent(BuildConfig.APPLICATION_ID + "." + BuildConfig.FLAVOR + ".MAIN_VIEW");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
