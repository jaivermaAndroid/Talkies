package com.talkies.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.talkies.R;
import com.talkies.model.announcements.Datum;

import java.util.List;


public class AnnounceChildAdapter extends RecyclerView.Adapter<AnnounceChildAdapter.MyViewHolder> {

    Context mContext;
    private final List<Datum> categoryVideosModel;

    public AnnounceChildAdapter(Context mContext, List<Datum> categoryVideosModel) {
        this.mContext = mContext;
        this.categoryVideosModel = categoryVideosModel;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_child_horizontal_list_item, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Datum recentTransactionModel = categoryVideosModel.get(0);
        //holder.mTextViewTitle.setText(recentTransactionModel.getMainTitle());
        Log.e("banner: ", recentTransactionModel.getBanner());
        Picasso.get().load(recentTransactionModel.getBanner()).into(holder.imageVideo);


    }

    @Override
    public int getItemCount() {
        return categoryVideosModel.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageVideo;

        public MyViewHolder(View view) {
            super(view);
            imageVideo = view.findViewById(R.id.child_horizontal_imageView);

        }
    }

}
