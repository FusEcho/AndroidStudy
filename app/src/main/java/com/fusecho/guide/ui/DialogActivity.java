package com.fusecho.guide.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fusecho.guide.R;

import butterknife.ButterKnife;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        ButterKnife.bind(this);
    }

}
