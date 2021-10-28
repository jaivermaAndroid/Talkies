package com.talkies.adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.talkies.R;
import com.talkies.model.announcements.Anouncement;

import java.util.List;

public class AnnouncementsVideoAdapter extends RecyclerView.Adapter<AnnouncementsVideoAdapter.MyViewHolder> {

    Context mContext;
    AnnounceChildAdapter mAnnouncementChildAdapter;
    Gson gson;
    private final List<Anouncement> anouncementList;

    public AnnouncementsVideoAdapter(Context mContext, List<Anouncement> anouncementList) {
        this.mContext = mContext;
        this.anouncementList = anouncementList;
        gson = new Gson();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_recyclerview_verticle_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (position == 0) {
            Anouncement anouncementListItem = anouncementList.get(0);/// Only recent release has to displayed

            holder.mTextViewTitle.setText(anouncementListItem.getTitle());
            holder.mTextViewMore.setText("");
            mAnnouncementChildAdapter = new AnnounceChildAdapter(mContext, anouncementListItem.getData());
            RecyclerView.LayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
            holder.horizontalVideoCategoryList.setLayoutManager(manager);
            holder.horizontalVideoCategoryList.setHasFixedSize(false);
            holder.horizontalVideoCategoryList.setNestedScrollingEnabled(true);
            holder.horizontalVideoCategoryList.setAdapter(mAnnouncementChildAdapter);
            mAnnouncementChildAdapter.notifyDataSetChanged();
        }


    }

    @Override
    public int getItemCount() {
        return anouncementList.size();
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
            horizontalVideoCategoryList = view.findViewById(R.id.recyclerview_home_videoList_horizontal);

        }
    }

}
