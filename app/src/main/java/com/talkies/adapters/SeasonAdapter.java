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
import com.talkies.model.SeriesModel;
import com.talkies.model.ShowModel;
import com.talkies.model.searchitem.Cast;
import com.talkies.model.searchitem.OtherCrewMember;

import java.util.ArrayList;
import java.util.List;

public class SeasonAdapter extends RecyclerView.Adapter<SeasonAdapter.MyViewHolder>{

private List<SeriesModel> otherCrewMemberList;
        Context mContext;

public SeasonAdapter(Context mContext, ArrayList<SeriesModel> otherCrewMemberList) {
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
    public SeasonAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.season_child, parent, false);


        return new SeasonAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SeasonAdapter.MyViewHolder holder, int position) {
        SeriesModel otherCrewMemberListItem = otherCrewMemberList.get(position);
//        if(otherCrewMemberListItem.getPhoto()!=null)
//        {
//            Picasso.get().load(otherCrewMemberListItem.getPhoto().toString()).into(holder.imageVideo);
//        }
        holder.mTextViewTitle.setText(otherCrewMemberListItem.getTitle());

        Log.d("zzz","Season AdapterLog"+otherCrewMemberListItem.getTitle());




    }




    @Override
    public int getItemCount() {
        return otherCrewMemberList.size();
    }

}
