package com.fusecho.guide.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fusecho.guide.R;
import com.fusecho.guide.model.ModelMainItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gaofengdeng 2020/4/18
 **/
public class AdapterRecyclerViewMain extends RecyclerView.Adapter<AdapterRecyclerViewMain.ViewHolder> {

    private Context mContext;
    private List<ModelMainItem> mValues;

    public AdapterRecyclerViewMain(List<ModelMainItem> items){
        this.mValues = items;
    }

    @Override
    public AdapterRecyclerViewMain.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_main, parent, false);
        mContext = view.getContext();
      /*  mScreenWidth = RxDeviceTool.getScreenWidth(context) > RxDeviceTool.getScreenHeight(context) ? RxDeviceTool.getScreenHeight(context) : RxDeviceTool.getScreenWidth(context);
        mItemWidth = (mScreenWidth - 50) / 3;
        mItemHeight = mItemWidth * 6 / 4;
        GridLayoutManager.LayoutParams layoutParams = new GridLayoutManager.LayoutParams(mItemWidth, mItemHeight);
        view.setLayoutParams(layoutParams);*/
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.mItem = mValues.get(position);

        holder.tvName.setText(holder.mItem.getName());

        Glide.with(mContext).
                load(holder.mItem.getImage()).
                thumbnail(0.5f).
                into(holder.imageView);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext, holder.mItem.getActivity());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public ModelMainItem mItem;
        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.tv_name)
        TextView tvName;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);
        }
    }

}
