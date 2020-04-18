package com.fusecho.guide.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.deepglint.library.tool.FusImageTool;
import com.fusecho.guide.R;
import com.fusecho.guide.adapter.AdapterRecyclerViewMain;
import com.fusecho.guide.model.ModelMainItem;
import com.fusecho.guide.tools.RecyclerViewDividerTool;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    /**
     * milliseconds, desired time passed between two back presses.
     */
    private static final int TIME_INTERVAL = 2000;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    private int mColumnCount = 3;
    private List<ModelMainItem> mData;
    private long mBackPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initData();
        initView();
    }

    private void initData(){
        mData = new ArrayList<>();

    }

    private void initView(){
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, mColumnCount));
        }

        recyclerView.addItemDecoration(new RecyclerViewDividerTool(FusImageTool.dp2px(5f)));
        AdapterRecyclerViewMain recyclerViewMain = new AdapterRecyclerViewMain(mData);

        recyclerView.setAdapter(recyclerViewMain);
    }

    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(getBaseContext(), "再次点击返回键退出", Toast.LENGTH_SHORT).show();
        }
        mBackPressed = System.currentTimeMillis();
    }
}

