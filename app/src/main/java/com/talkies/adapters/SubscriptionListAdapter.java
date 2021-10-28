package com.talkies.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.talkies.R;
import com.talkies.model.subscription.Datum;
import com.talkies.model.subscription.SubscriptionResult;

import java.util.List;

public class SubscriptionListAdapter extends RecyclerView.Adapter<SubscriptionListAdapter.MyViewHolder> {

    private final List<SubscriptionResult> subscriptionDataModels;
    Context mContext;
    SubscriptionListAdapter.SubscriptionOnItemClickListener listener;
    private List<Datum> subscriptionDatumModels;


    public SubscriptionListAdapter(Context mContext, List<SubscriptionResult> rentalModels, SubscriptionOnItemClickListener listener) {
        this.mContext = mContext;
        this.subscriptionDataModels = rentalModels;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_subscription_card_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        SubscriptionResult subItem = subscriptionDataModels.get(position);
        Picasso.get().load(subItem.getIcon()).into(holder.subscriptionLogo);
        if (subItem.getIcon() != null) {
            holder.tvSubscriptionTitle.setText(subItem.getTitle());
        }
        if (!subItem.getData().isEmpty()) {

            if (subItem.getData().size() == 1) {
                holder.radioButtonType1.setText("R" + subItem.getData().get(0).getPriceDistribution().getTotalAmount().toString());
                holder.radioButtonType1.setVisibility(View.VISIBLE);
                holder.radioButtonType1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {

                            listener.onRadioItemClick(subItem, 0, position);
                        }
                    }
                });

            }
            if (subItem.getData().size() == 2) {
                holder.radioButtonType1.setText("₹" + subItem.getData().get(0).getPriceDistribution().getTotalAmount().toString());
                holder.radioButtonType2.setText("₹" + subItem.getData().get(1).getPriceDistribution().getTotalAmount().toString());
                holder.radioButtonType1.setVisibility(View.VISIBLE);
                holder.radioButtonType2.setVisibility(View.VISIBLE);
                holder.radioButtonType1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            listener.onRadioItemClick(subItem, 0, position);
                        }
                    }
                });
                holder.radioButtonType2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            listener.onRadioItemClick(subItem, 1, position);
                        }
                    }
                });
            }

//            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    listener.onItemClick(subItem,position);
//                }
//            });
        }


    }

    @Override
    public int getItemCount() {
        return subscriptionDataModels.size();
    }


    public interface SubscriptionOnItemClickListener {
        void onRadioItemClick(SubscriptionResult item, int intRadioPosition, int position);

        void onItemClick(SubscriptionResult item, int intRadioPosition);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvSubscriptionTitle;
        RadioGroup radioGroupSubscriptionType;
        RadioButton radioButtonType1, radioButtonType2, radioButtonType3;

        LinearLayout linearLayout;

        ImageView subscriptionLogo;

        public MyViewHolder(View view) {
            super(view);

            tvSubscriptionTitle = view.findViewById(R.id.subscriptionCardTitle);
            linearLayout = view.findViewById(R.id.llCardItem);
            subscriptionLogo = view.findViewById(R.id.subscriptionCardTitleLogo);
            radioGroupSubscriptionType = view.findViewById(R.id.radioSubscriptionPay);
            radioButtonType1 = view.findViewById(R.id.rb_subscription_pay_type1);
            radioButtonType2 = view.findViewById(R.id.rb_subscription_pay_type2);


        }
    }


}
