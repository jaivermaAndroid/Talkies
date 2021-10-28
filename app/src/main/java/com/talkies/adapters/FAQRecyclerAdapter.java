package com.talkies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.talkies.R;
import com.talkies.model.faq.FAQResponseModel;

import java.util.List;

public class FAQRecyclerAdapter extends RecyclerView.Adapter<FAQRecyclerAdapter.MyViewHolder>{

        private List<FAQResponseModel> categoryVideosModel;
        Context mContext;
        FAQChildListAdapter faqChildListAdapter;
        Gson gson;

public FAQRecyclerAdapter(Context mContext, List<FAQResponseModel> categoryVideosModel) {
        this.mContext = mContext;
        this.categoryVideosModel = categoryVideosModel;
        gson = new Gson();
        }

public class MyViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageVideo;

    TextView mTextViewTitle, mTextViewMore;
    RecyclerView verticleQuestionList;
    public MyViewHolder(View view) {
        super(view);
        //imageVideo = view.findViewById(R.id.category_videoList);
        mTextViewTitle = view.findViewById(R.id.tv_faq_header);
        verticleQuestionList = view.findViewById(R.id.faq_question_List);

    }
}

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_faq_header, parent, false);


        return new MyViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        FAQResponseModel recentTransactionModel = categoryVideosModel.get(position);
        holder.mTextViewTitle.setText(recentTransactionModel.getTitle());
        // Picasso.with(mContext).load(recentTransactionModel.getDataBanner()).into(holder.imageVideo);



        // recyclerCategoryList.add(uiConfig);

        faqChildListAdapter = new FAQChildListAdapter(mContext, recentTransactionModel.getFaqs());
        RecyclerView.LayoutManager manager = new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL, false);
        holder.verticleQuestionList.setLayoutManager(manager);
        holder.verticleQuestionList.setHasFixedSize(false);
        holder.verticleQuestionList.setNestedScrollingEnabled(true);
        holder.verticleQuestionList.setAdapter(faqChildListAdapter);
        faqChildListAdapter.notifyDataSetChanged();



    }


    @Override
    public int getItemCount() {
        return categoryVideosModel.size();
    }

}
