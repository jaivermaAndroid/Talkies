package com.talkies.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.talkies.R;
import com.squareup.picasso.Picasso;
import com.talkies.model.searchResult.SearchResultResponseList;

import java.util.List;

public class SearchResultVideoAdapter extends RecyclerView.Adapter<SearchResultVideoAdapter.MyViewHolder>{

    private List<SearchResultResponseList> categoryVideosModel;
    Context mContext;

    public interface OnItemClickListener {
        void onItemClick(SearchResultResponseList item);
    }


    private final OnItemClickListener listener;
    public SearchResultVideoAdapter(Context mContext, List<SearchResultResponseList> categoryVideosModel,OnItemClickListener listener) {
        this.mContext = mContext;
        this.categoryVideosModel = categoryVideosModel;
        this.listener=listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageVideo;

        TextView mTextViewTitle, mTextVideoType1,mTextVideoType2,mTextVideoType3,mTextVideoYear ,mTextVideoDuration,mTextVideoCertificate;

        RecyclerView horizontalVideoCategoryList;
        public MyViewHolder(View view) {
            super(view);
            imageVideo = view.findViewById(R.id.searchImage);
            mTextViewTitle = view.findViewById(R.id.searchResultTitle);
            mTextVideoType1 = view.findViewById(R.id.searchResultVideo_type_1);
            mTextVideoType2 = view.findViewById(R.id.searchResultVideo_type_2);
            mTextVideoType3 = view.findViewById(R.id.searchResultVideo_type_3);
            mTextVideoYear = view.findViewById(R.id.searchResultYear);
            mTextVideoDuration = view.findViewById(R.id.searchResultDuration);
            mTextVideoCertificate = view.findViewById(R.id.searchResultCertificate);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_recycler_video_list_item, parent, false);


        return new MyViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SearchResultResponseList recentTransactionModel = categoryVideosModel.get(position);

            holder.mTextViewTitle.setText(recentTransactionModel.getTitle());
            holder.mTextVideoDuration.setText(recentTransactionModel.getRunTime().toString());
            holder.mTextVideoYear.setText(recentTransactionModel.getReleaseYear().toString());
        if(!recentTransactionModel.getCertificates().isEmpty())
        {
            holder.mTextVideoCertificate.setText(recentTransactionModel.getCertificates().get(0).getTitle());

        }
            holder.mTextVideoType1.setText(recentTransactionModel.getTitle());

            if(!recentTransactionModel.getGenres().isEmpty())
            {
                holder.mTextVideoType2.setText(recentTransactionModel.getGenres().get(0).getTitle());

            }
            if(recentTransactionModel.getGenres().size()>1)
        {
            holder.mTextVideoType2.setText(recentTransactionModel.getGenres().get(0).getTitle());
            holder.mTextVideoType3.setText(recentTransactionModel.getGenres().get(1).getTitle());

        }

        // holder.mTextVideoType3.setText(recentTransactionModel.getResults().get(position).getGenres().get(position).getTitle());

//        holder.mTextVideoType2.setText(recentTransactionModel.getGenresTitle2());
//        holder.mTextVideoType3.setText(recentTransactionModel.getGenresTitle3());

            Picasso
                    .get().load(recentTransactionModel.getBanner()).into(holder.imageVideo);
            //Log.e("banner: ", recentTransactionModel.getMainTitle());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(recentTransactionModel);
            }
        });

    }


    @Override
    public int getItemCount() {
        return categoryVideosModel.size();
    }

}
