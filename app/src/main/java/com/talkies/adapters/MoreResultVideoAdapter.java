package com.talkies.adapters;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.talkies.R;
import com.talkies.model.moredetails.MoreResult;
import com.talkies.model.moredetails.Result;
import com.talkies.model.searchResult.SearchResultResponseList;

import java.util.List;

public class MoreResultVideoAdapter extends RecyclerView.Adapter<MoreResultVideoAdapter.MyViewHolder>{

    private List<Result> categoryVideosModel;
    Context mContext;

    public interface OnItemClickListener {
        void onItemClick(SearchResultResponseList item);
    }


    public MoreResultVideoAdapter(Context mContext, List<Result> categoryVideosModel) {
        this.mContext = mContext;
        this.categoryVideosModel = categoryVideosModel;
//        this.listener=listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageVideo;

        TextView mTextViewTitle, mTextVideoType1,mTextVideoType2,mTextVideoType3,mTextVideoYear ,mTextVideoDuration,mTextVideoCertificate;

        RecyclerView horizontalVideoCategoryList;
        public MyViewHolder(View view) {
            super(view);

            Log.d("zzz", "MyViewHolder: ccc ");
            imageVideo = view.findViewById(R.id.searchImage);
            mTextViewTitle = view.findViewById(R.id.tvTitle);
            mTextVideoType1 = view.findViewById(R.id.tvTulu);
            mTextVideoType2 = view.findViewById(R.id.tvAction);
            mTextVideoType3 = view.findViewById(R.id.tvTalkies_gold);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.more_list_view, parent, false);


        return new MyViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Result recentTransactionModel = categoryVideosModel.get(position);

            holder.mTextViewTitle.setText(recentTransactionModel.getTitle());
//            holder.mTextVideoDuration.setText(recentTransactionModel.getRunTime().toString());
            holder.mTextVideoType3.setText(recentTransactionModel.getMediaRentalPlan().getTitle().toString());
            if(!recentTransactionModel.getCertificates().isEmpty())
            {
                holder.mTextVideoType2.setText(recentTransactionModel.getGenres().get(0).getTitle());

            }
//        holder.mTextVideoType1.setText(recentTransactionModel.getLanguages().get(0).getName());

            if(!recentTransactionModel.getGenres().isEmpty())
            {
//                holder.mTextVideoType2.setText(recentTransactionModel.getGenres().get(0).getTitle());

            }
            if(recentTransactionModel.getGenres().size()>1)
        {
//            holder.mTextVideoType2.setText(recentTransactionModel.getGenres().get(0).getTitle());
            holder.mTextVideoType3.setText(recentTransactionModel.getGenres().get(1).getTitle());

        }

            Picasso.get().load(recentTransactionModel.getBanner()).into(holder.imageVideo);
            //Log.e("banner: ", recentTransactionModel.getMainTitle());
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.onItemClick(recentTransactionModel);
//            }
//        });

    }


    @Override
    public int getItemCount() {
        return categoryVideosModel.size();
    }

}
