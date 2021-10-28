package com.talkies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.talkies.R;
import com.talkies.model.recyclercategorytab.Datum;

import java.util.List;

public class ReleaseListAdapter extends RecyclerView.Adapter<ReleaseListAdapter.MyViewHolder>{

    private List<Datum> rentalModels;
    Context mContext;

    public ReleaseListAdapter(Context mContext, List<Datum> rentalModels) {
        this.mContext = mContext;
        this.rentalModels = rentalModels;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title, releaseDate;
        Button viewDetailsRental;
        public MyViewHolder(View view) {
            super(view);

            title = view.findViewById(R.id.upcomingListItemTitle);
            releaseDate = view.findViewById(R.id.upcomingListItemReleaseDateValue);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_upcoming_list_item, parent, false);
        return new MyViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Datum datumItem = rentalModels.get(position);
        //Picasso.with(mContext).load(recentTransactionModel.getBanner()).into(holder.imageVideo);
        if(datumItem!=null) {
            holder.title.setText(datumItem.getTitle());
            //holder.releaseDate.setText(datumItem.get);
        }



    }


    @Override
    public int getItemCount() {
        return rentalModels.size();
    }

}
