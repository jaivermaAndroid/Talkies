package com.talkies.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.talkies.R;
import com.talkies.model.SeriesModel;
import com.talkies.model.ShowModel;

import java.util.ArrayList;
import java.util.List;

public class NewSeriesAdapter extends RecyclerView.Adapter<NewSeriesAdapter.MyViewHolder>{

    private List<ShowModel> otherCrewMemberList;
    Context mContext;

    public NewSeriesAdapter(Context mContext, ArrayList<ShowModel> otherCrewMemberList) {
        this.mContext = mContext;
        this.otherCrewMemberList = otherCrewMemberList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageVideo;

        TextView mTextViewTitle, mTextViewMore;
        public MyViewHolder(View view) {
            super(view);
//        imageVideo = view.findViewById(R.id.imageView_Recycler_Item);
            mTextViewTitle = view.findViewById(R.id.textSeasonTitle);
        }
    }

    @Override
    public NewSeriesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.season_child, parent, false);


        return new NewSeriesAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewSeriesAdapter.MyViewHolder holder, int position) {
        ShowModel otherCrewMemberListItem = otherCrewMemberList.get(position);
//        if(otherCrewMemberListItem.getPhoto()!=null)
//        {
//            Picasso.get().load(otherCrewMemberListItem.getPhoto().toString()).into(holder.imageVideo);
//        }
        holder.mTextViewTitle.setText(otherCrewMemberListItem.getTitle());

        Log.d("zzz","Season AdapterLog"+otherCrewMemberListItem.getTitle());

        holder.mTextViewTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"done",Toast.LENGTH_SHORT).show();
            }
        });




    }




    @Override
    public int getItemCount() {
        return otherCrewMemberList.size();
    }

}

