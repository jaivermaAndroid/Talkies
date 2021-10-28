package com.talkies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.talkies.R;
import com.talkies.model.searchitem.Cast;
import com.talkies.model.searchitem.OtherCrewMember;

import java.util.List;


public class RecyclerCrewMemberAdapter extends RecyclerView.Adapter<RecyclerCrewMemberAdapter.MyViewHolder>{

        private List<OtherCrewMember> otherCrewMemberList;
        Context mContext;

        public RecyclerCrewMemberAdapter(Context mContext, List<OtherCrewMember> otherCrewMemberList) {
            this.mContext = mContext;
            this.otherCrewMemberList = otherCrewMemberList;
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
                    .inflate(R.layout.layout_list_item_image, parent, false);


            return new MyViewHolder(itemView);
        }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        OtherCrewMember otherCrewMemberListItem = otherCrewMemberList.get(position);
        if(otherCrewMemberListItem.getPhoto()!=null)
        {
            Picasso.get().load(otherCrewMemberListItem.getPhoto().toString()).into(holder.imageVideo);
        }
        holder.mTextViewTitle.setText(otherCrewMemberListItem.getName());




    }




        @Override
        public int getItemCount() {
            return otherCrewMemberList.size();
        }

    }
