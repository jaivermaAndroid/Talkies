package com.talkies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.talkies.R;
import com.talkies.model.connecttv.ConnectTv;
import com.talkies.model.rental.Result;

import java.util.List;

public class ConnectTVListAdapter extends RecyclerView.Adapter<ConnectTVListAdapter.MyViewHolder>{

    private List<ConnectTv> rentalModels;
    Context mContext;

    public ConnectTVListAdapter(Context mContext, List<ConnectTv> rentalModels) {
        this.mContext = mContext;
        this.rentalModels = rentalModels;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView connectedTitle, daysRemaining;
        Button viewDetailsRental;
        public MyViewHolder(View view) {
            super(view);

            connectedTitle = view.findViewById(R.id.tv_connected_tv_title);
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
        ConnectTv rentalItem = rentalModels.get(position);
        //Picasso.with(mContext).load(recentTransactionModel.getBanner()).into(holder.imageVideo);
        //if(rentalItem.!=null) {
            holder.connectedTitle.setText(rentalItem.getData().getDevice().getName());
        //}



    }


    @Override
    public int getItemCount() {
        return rentalModels.size();
    }

}
