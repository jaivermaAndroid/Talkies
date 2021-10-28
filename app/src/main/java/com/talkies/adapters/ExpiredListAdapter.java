package com.talkies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.talkies.R;
import com.talkies.model.expired.Result;

import java.util.List;

public class ExpiredListAdapter extends RecyclerView.Adapter<ExpiredListAdapter.MyViewHolder>{

    private List<Result> rentalModels;
    Context mContext;

    public ExpiredListAdapter(Context mContext, List<Result> rentalModels) {
        this.mContext = mContext;
        this.rentalModels = rentalModels;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView expiredTitle, daysRemaining;
        Button viewDetailsRental;
        public MyViewHolder(View view) {
            super(view);

            expiredTitle = view.findViewById(R.id.tv_expired_title);
            daysRemaining = view.findViewById(R.id.tv_days_remaining_expired);
            viewDetailsRental = view.findViewById(R.id.btnViewDetailsExpired);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_expired_item_adapter, parent, false);
        return new MyViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Result expiredResult = rentalModels.get(position);
        //Picasso.with(mContext).load(recentTransactionModel.getBanner()).into(holder.imageVideo);
        if(expiredResult.getMedia()!=null)
        {

            holder.expiredTitle.setText(expiredResult.getMedia().getTitle());
            holder.daysRemaining.setText(expiredResult.getExpiredOn().toString());
        }



    }


    @Override
    public int getItemCount() {
        return rentalModels.size();
    }

}
