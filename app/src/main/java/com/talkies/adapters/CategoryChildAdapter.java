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

import com.talkies.R;
import com.talkies.model.recyclercategorytab.Datum;
import com.squareup.picasso.Picasso;
import com.talkies.model.searchResult.SearchResultResponseList;

import java.util.List;


public class CategoryChildAdapter extends RecyclerView.Adapter<CategoryChildAdapter.MyViewHolder>{

        private List<Datum> categoryVideosModel;
        Context mContext;


    public interface OnCategoryItemClickListener {
        void OnCategoryItemClickListener(Datum item);
    }

    private final OnCategoryItemClickListener listener;

        public CategoryChildAdapter(Context mContext, List<Datum> categoryVideosModel,OnCategoryItemClickListener listener) {
            this.mContext = mContext;
            this.categoryVideosModel = categoryVideosModel;
            this.listener = listener;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public ImageView imageVideo;

            TextView mTextViewTitle, mTextViewMore;
            public MyViewHolder(View view) {
                super(view);
                imageVideo = view.findViewById(R.id.child_horizontal_imageView);

            }
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.home_child_horizontal_list_item, parent, false);


            return new MyViewHolder(itemView);
        }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Datum recentTransactionModel = categoryVideosModel.get(position);
        //holder.mTextViewTitle.setText(recentTransactionModel.getMainTitle());


        Log.e("banner: ", recentTransactionModel.getBanner());
        Log.e("zzz: ","CategoryChildAdapter"+ recentTransactionModel.getBanner());
        Picasso.get().load(recentTransactionModel.getBanner()).into(holder.imageVideo);

        holder.imageVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnCategoryItemClickListener(recentTransactionModel);
            }
        });
    }

        @Override
        public int getItemCount() {
            return categoryVideosModel.size();
        }

    }
