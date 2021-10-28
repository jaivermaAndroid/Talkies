package com.talkies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.talkies.R;
import com.talkies.model.faq.Faq;

import java.util.List;

public class FAQChildListAdapter extends RecyclerView.Adapter<FAQChildListAdapter.MyViewHolder>{

    private List<Faq> faqListModel;
    Context mContext;
    Gson gson;
    boolean textDescriptionVisible =false;

    public FAQChildListAdapter(Context mContext, List<Faq> categoryVideosModel) {
        this.mContext = mContext;
        this.faqListModel = categoryVideosModel;
        gson = new Gson();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageVideo;

        TextView mTextViewTitle, mTextViewTitleDescription;
        public MyViewHolder(View view) {
            super(view);
            //imageVideo = view.findViewById(R.id.category_videoList);
            mTextViewTitle = view.findViewById(R.id.tv_question_title);
            mTextViewTitleDescription = view.findViewById(R.id.tv_question_title_details);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_faq_question_item, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Faq recentTransactionModel = faqListModel.get(position);
        holder.mTextViewTitle.setText(recentTransactionModel.getTitle());
        holder.mTextViewTitleDescription.setText(recentTransactionModel.getDescription());
        holder.mTextViewTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!textDescriptionVisible)
                {
                    holder.mTextViewTitleDescription.setVisibility(View.VISIBLE);
                    textDescriptionVisible=true;
                }else{
                    holder.mTextViewTitleDescription.setVisibility(View.GONE);
                    textDescriptionVisible=false;
                }

            }
        });

    }




    @Override
    public int getItemCount() {
        return faqListModel.size();
    }

}
