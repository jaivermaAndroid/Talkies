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
import com.talkies.model.recyclercategorytab.Datum;
import com.talkies.model.searchitem.Cast;

import java.util.List;


public class RecyclerStaringAdapter extends RecyclerView.Adapter<RecyclerStaringAdapter.MyViewHolder>{

        private List<Cast> castList;
        Context mContext;

        public RecyclerStaringAdapter(Context mContext, List<Cast> castList) {
            this.mContext = mContext;
            this.castList = castList;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public ImageView imageVideo;

            TextView mTextViewTitle, mTextViewMore;
            public MyViewHolder(View view) {
                super(view);
                imageVideo = view.findViewById(R.id.imageView_Recycler_Item);
                mTextViewTitle = view.findViewById(R.id.nameStar);

            }
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_list_item_staring, parent, false);


            return new MyViewHolder(itemView);
        }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Cast castListItem = castList.get(position);
        if(castListItem.getPhoto()!=null)
        {
            Picasso.get().load(castListItem.getPhoto().toString()).into(holder.imageVideo);
        }

        holder.mTextViewTitle.setText(castListItem.getName());



    }




        @Override
        public int getItemCount() {
            return castList.size();
        }

    }
