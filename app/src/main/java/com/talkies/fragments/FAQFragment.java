package com.talkies.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.talkies.R;
import com.talkies.adapters.FAQRecyclerAdapter;
import com.talkies.callbacks.ProfileDetailCallBack;
import com.talkies.managers.ProfileDetailManager;
import com.talkies.model.Header;
import com.talkies.model.faq.FAQResponseModel;
import com.talkies.utils.GeneralMethods;
import com.talkies.utils.MyPreferenceManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FAQFragment extends Fragment implements  View.OnClickListener , ProfileDetailCallBack{

    final static String DATA_FLAG = "DATA_FLAG";
    LinearLayoutManager linearLayoutManager;
    RecyclerView mFAQList;
    ProfileDetailCallBack mProfileDetailCallBack;
    GeneralMethods mGeneralMethods;
    Gson gson;
    MyPreferenceManager prefManager;

    public FAQFragment() {
            setRetainInstance(true);  //Hear put this line
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_faq_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gson = new Gson();
        mGeneralMethods = new GeneralMethods(getContext(),getActivity());
        mFAQList = (RecyclerView)view.findViewById(R.id.faq_list);
        linearLayoutManager = new LinearLayoutManager(getContext());
        mProfileDetailCallBack = this;
        prefManager = new MyPreferenceManager(getContext());
        new ProfileDetailManager(getActivity(), mProfileDetailCallBack,prefManager).getFAQList(mGeneralMethods,"faq");


    }


    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        if (args != null) {
//            flagForVisibility= args.getString(DATA_FLAG);
//            setUIVisibility();
        }


    }

    @Override
    public void onClick(View view) {


    }

    public  Header getHeader(String headerTitle)
    {
        Header header = new Header();
        header.setHeader(headerTitle);
        return header;
    }

    public List<FAQResponseModel> getListItems()
    {
        List<FAQResponseModel> listItems = new ArrayList<FAQResponseModel>();
        for (int i = 0; i<10; i++) {
            FAQResponseModel item = new FAQResponseModel();
            item.setTitle("image" + i);
//            if (i % 2 == 0)
//                item.setId(R.drawable.sweetlife);
//            else
//                //item.setId(R.drawable.young);
            listItems.add(item);
        }
        return listItems;
    }


    @Override
    public void onSuccessFAQ(JSONArray result) {

    }

    @Override
    public void onSuccessFAQ(String result) {

        try {
            JSONArray jsonArray = new JSONArray(result);

            Type collectionType = new TypeToken<Collection<FAQResponseModel>>(){}.getType();
            ArrayList<FAQResponseModel> recyclerFAQList = gson.fromJson(result, collectionType);
            for (int i = 0; i < recyclerFAQList.size(); i++) {
                FAQRecyclerAdapter adapter = new FAQRecyclerAdapter(getContext(), recyclerFAQList);
                mFAQList.setLayoutManager(linearLayoutManager);
                mFAQList.setAdapter(adapter);
            }


        }catch (JSONException e) {
            e.printStackTrace();
        }
//
    }

    @Override
    public void onFailureFAQ() {

    }

    @Override
    public void onSuccessUpdateName(JSONObject result) {

    }

    @Override
    public void onSuccessUpdateName(JSONArray result) {

    }

    @Override
    public void onSuccessUpdateName(String result) {

    }

    @Override
    public void onFailureUpdateName() {

    }

    @Override
    public void onSuccessIssueList(JSONArray result) {

    }

    @Override
    public void onSuccessIssueList(String result) {

    }

    @Override
    public void onFailureIssueList() {

    }

    @Override
    public void onSuccessRentalDetailsList(JSONArray result) {

    }

    @Override
    public void onSuccessRentalDetailsList(JSONObject result) {

    }

    @Override
    public void onSuccessRentalDetailsList(String result) {

    }

    @Override
    public void onFailureRentalDetailsList() {

    }

    @Override
    public void onSuccessSubmitQuery(JSONObject result) {

    }

    @Override
    public void onFailSubmitQuery() {

    }

    @Override
    public void onSuccessSubscriptionList(JSONArray result) {

    }

    @Override
    public void onSuccessSubscriptionList(JSONObject result) {

    }

    @Override
    public void onSuccessSubscriptionList(String result) {

    }

    @Override
    public void onFailureSubscriptionList() {

    }

    @Override
    public void onLogoutSuccess() {

    }

    @Override
    public void onLogoutFailure() {

    }

    @Override
    public void onSuccessRazorKeyResult(JSONObject result) {

    }

    @Override
    public void onFailureRazorKeyResult() {

    }

    @Override
    public void onSuccessConnectTv(JSONObject result) {

    }

    @Override
    public void onSuccessConnectTv(String result) {

    }

    @Override
    public void onFailure() {

    }
}