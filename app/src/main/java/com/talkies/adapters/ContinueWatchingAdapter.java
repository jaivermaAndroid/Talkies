package com.talkies.adapters;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.talkies.R;
import com.squareup.picasso.Picasso;
import com.talkies.activities.VideoPlayerActivity;
import com.talkies.activities.VideoPlayerActivity_2;
import com.talkies.model.continuewatching.ContinueWatching;

import java.util.List;

public class ContinueWatchingAdapter extends RecyclerView.Adapter<ContinueWatchingAdapter.MyViewHolder>{

    private List<ContinueWatching> continueWatchingModels;
    Context mContext;

    public ContinueWatchingAdapter(Context mContext, List<ContinueWatching> recentTransactionModels) {
        this.mContext = mContext;
        this.continueWatchingModels = recentTransactionModels;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageVideo;

        public MyViewHolder(View view) {
            super(view);
            imageVideo = view.findViewById(R.id.continue_child_horizontal_imageView);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_continue_watching_list_item, parent, false);
        return new MyViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ContinueWatching recentTransactionModel = continueWatchingModels.get(position);
        Picasso.get().load(recentTransactionModel.getBanner()).into(holder.imageVideo);

        holder.imageVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("zzz","slug:    "+continueWatchingModels.get(position).getVideoSectionType().getSlug().equals("series"));

                if (continueWatchingModels.get(position).getVideoSectionType().getSlug().equals("series"))
                {
                    Intent vd = new Intent(mContext, VideoPlayerActivity_2.class);
                    vd.putExtra("slug", continueWatchingModels.get(position).getSlug());
                    mContext.startActivity(vd);
                }
                else {
                    Intent vd = new Intent(mContext, VideoPlayerActivity.class);
                    vd.putExtra("slug", continueWatchingModels.get(position).getSlug());
                    mContext.startActivity(vd);
                }
            }
        });

        


    }


    @Override
    public int getItemCount() {
        return continueWatchingModels.size();
    }

}
