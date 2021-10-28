package com.talkies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.talkies.R;
import com.talkies.model.notifications.Result;

import java.util.ArrayList;
import java.util.List;

public class NotificationListAdapter extends RecyclerView.Adapter<NotificationListAdapter.MyViewHolder>{

    private List<Result> notificationDataModel;
    Context mContext;

    NotificationOnItemClickListener listener;



    public interface NotificationOnItemClickListener {
        void onItemClick(Result item);
    }


    public NotificationListAdapter(Context mContext, ArrayList<Result> notificationDataModel, NotificationOnItemClickListener listener) {
        this.mContext = mContext;
        this.notificationDataModel = notificationDataModel;
        this.listener=listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageVideo;

        TextView mTextViewTitle, mTextDesc;

        public MyViewHolder(View view) {
            super(view);
            imageVideo = view.findViewById(R.id.notificationImage);
            mTextViewTitle = view.findViewById(R.id.notificationTitle);
            mTextDesc = view.findViewById(R.id.notificationShortDesc);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_notification_item, parent, false);


        return new MyViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Result notificationModel = notificationDataModel.get(position);

        holder.mTextViewTitle.setText(notificationModel.getTitle());
        holder.mTextDesc.setText(notificationModel.getShortDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(notificationModel);
            }
        });

    }


    @Override
    public int getItemCount() {
        return notificationDataModel.size();
    }

}