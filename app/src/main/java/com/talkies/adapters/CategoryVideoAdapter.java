package com.talkies.adapters;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.talkies.R;
import com.talkies.model.recyclercategorytab.RecyclerCategoryList;
import com.talkies.model.recyclercategorytab.Datum;
import java.util.List;

public class CategoryVideoAdapter extends RecyclerView.Adapter<CategoryVideoAdapter.MyViewHolder>{

    private List<RecyclerCategoryList> categoryVideosModel;
    Context mContext;
    CategoryChildAdapter mCategoryChildAdapter;
    Gson gson;
    CategoryChildAdapter.OnCategoryItemClickListener listener;

    private final onMoreItemClickListener moreListener;

    public interface onMoreItemClickListener {
        void onMoreItemClickListener(String slug);
    }


    public CategoryVideoAdapter(Context mContext, List<RecyclerCategoryList> categoryVideosModel, CategoryChildAdapter.OnCategoryItemClickListener listener,onMoreItemClickListener moreListener) {
        this.mContext = mContext;
        this.listener=listener;
        this.categoryVideosModel = categoryVideosModel;
        this.moreListener=moreListener;
        gson = new Gson();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageVideo;

        TextView mTextViewTitle, mTextViewMore;
        RecyclerView horizontalVideoCategoryList;
        public MyViewHolder(View view) {
            super(view);
            //imageVideo = view.findViewById(R.id.category_videoList);
            mTextViewTitle = view.findViewById(R.id.tv_video_title);
            mTextViewMore = view.findViewById(R.id.tv_video_more);
            horizontalVideoCategoryList= view.findViewById(R.id.recyclerview_home_videoList_horizontal);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_recyclerview_verticle_list_item, parent, false);


        return new MyViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RecyclerCategoryList recentTransactionModel = categoryVideosModel.get(position);
        holder.mTextViewTitle.setText(recentTransactionModel.getTitle());
       // Picasso.with(mContext).load(recentTransactionModel.getDataBanner()).into(holder.imageVideo);



        holder.mTextViewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moreListener.onMoreItemClickListener(recentTransactionModel.getSlug());
                Log.d("getslug", "vieoindex "+recentTransactionModel.getSlug());
            }
        });
       // recyclerCategoryList.add(uiConfig);

        mCategoryChildAdapter = new CategoryChildAdapter(mContext, recentTransactionModel.getData().get(0),listener);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL, false);
        holder.horizontalVideoCategoryList.setLayoutManager(manager);
        holder.horizontalVideoCategoryList.setHasFixedSize(false);
        holder.horizontalVideoCategoryList.setNestedScrollingEnabled(true);
        holder.horizontalVideoCategoryList.setAdapter(mCategoryChildAdapter);
        mCategoryChildAdapter.notifyDataSetChanged();



    }


    @Override
    public int getItemCount() {
        return categoryVideosModel.size();
    }

}
