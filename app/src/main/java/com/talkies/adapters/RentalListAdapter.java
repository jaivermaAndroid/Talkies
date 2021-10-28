package com.talkies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.talkies.R;
import com.talkies.model.rental.Result;

import java.util.List;

public class RentalListAdapter extends RecyclerView.Adapter<RentalListAdapter.MyViewHolder>{

    private List<Result> rentalModels;
    Context mContext;

    public RentalListAdapter(Context mContext, List<Result> rentalModels) {
        this.mContext = mContext;
        this.rentalModels = rentalModels;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView rentalTitle, daysRemaining;
        Button viewDetailsRental;
        public MyViewHolder(View view) {
            super(view);

            rentalTitle = view.findViewById(R.id.tv_rental_title);
            daysRemaining = view.findViewById(R.id.tv_days_remaining_rental);
            viewDetailsRental = view.findViewById(R.id.btnViewDetailsRental);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_rental_item_adapter, parent, false);
        return new MyViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Result rentalItem = rentalModels.get(position);
        //Picasso.with(mContext).load(recentTransactionModel.getBanner()).into(holder.imageVideo);
        if(rentalItem.getMedia()!=null) {
            holder.rentalTitle.setText(rentalItem.getMedia().getTitle());
            holder.daysRemaining.setText(rentalItem.getExpiredOn().toString());
        }



    }


    @Override
    public int getItemCount() {
        return rentalModels.size();
    }

}
