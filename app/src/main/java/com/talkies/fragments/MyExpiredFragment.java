package com.talkies.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.talkies.R;
import com.talkies.adapters.ExpiredListAdapter;
import com.talkies.adapters.RentalListAdapter;
import com.talkies.callbacks.ProfileDetailCallBack;
import com.talkies.managers.ProfileDetailManager;
import com.talkies.model.expired.ExpiredResult;
import com.talkies.model.expired.Result;
import com.talkies.utils.GeneralMethods;
import com.talkies.utils.MyPreferenceManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MyExpiredFragment extends Fragment implements  View.OnClickListener, ProfileDetailCallBack {

    final static String DATA_FLAG = "DATA_FLAG";
    ProfileDetailCallBack mProfileCallBack;
    GeneralMethods mGeneralMethods;
    String rentalType, isExpired;
    ExpiredListAdapter mExpiredAdapter;
    RecyclerView mRecyclerExpired;
    TextView tvNoPlans;
    MyPreferenceManager prefManager;
    Gson gson;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_expired, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        tvNoPlans=view.findViewById(R.id.noPlansText);
        mProfileCallBack = this;
        gson = new Gson();
        mGeneralMethods = new GeneralMethods(getContext(),getActivity());
        mRecyclerExpired =view.findViewById(R.id.expired_list);
        prefManager = new MyPreferenceManager(getContext());

    }

    private void setUIVisibility() {


    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        if (args != null) {
            //flagForVisibility= args.getString(DATA_FLAG);
            setUIVisibility();
        }


    }


    public MyExpiredFragment() {
        setRetainInstance(true);  //Hear put this line

    }
    @Override
    public void onResume() {
        super.onResume();
        Log.e( "onResume: ", "Rental");
        isExpired= "true";
        new ProfileDetailManager(getActivity(), mProfileCallBack,prefManager).getExpiredDetails(mGeneralMethods,rentalType, isExpired);


    }
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.iv_tell_your_friends_next:
                //visibilityForAllViewGone();
                //layoutAboutUsDetail.setVisibility(View.VISIBLE);
                break;
        }
    }


    @Override
    public void onSuccessFAQ(JSONArray result) {

    }

    @Override
    public void onSuccessFAQ(String result) {

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
        Type collectionType = new TypeToken<ExpiredResult>(){}.getType();

        ExpiredResult rental = gson.fromJson(result.toString(), collectionType);
        Log.e("rental: ",rental.toString() );
        ArrayList<Result> expiredList = new ArrayList<>() ;
        for(int item=0; item<rental.getResults().size();item++)
        {
            expiredList.add(rental.getResults().get(item));

        }
        Log.e("expiredList: ",expiredList.toString() );


        mExpiredAdapter = new ExpiredListAdapter(getContext(), expiredList);
        mRecyclerExpired.setHasFixedSize(false);
        mRecyclerExpired.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerExpired.setNestedScrollingEnabled(true);
        mRecyclerExpired.setAdapter(mExpiredAdapter);

    }

    @Override
    public void onSuccessRentalDetailsList(String result) {

        Type collectionType = new TypeToken<ExpiredResult>(){}.getType();

        ExpiredResult rental = gson.fromJson(result.toString(), collectionType);
        Log.e("rental: ",rental.toString() );
        if(rental.getCount()==0)
        { tvNoPlans.setVisibility(View.VISIBLE);

        }else {

            ArrayList<Result> expiredList = new ArrayList<>();
            for (int item = 0; item < rental.getResults().size(); item++) {
                expiredList.add(rental.getResults().get(item));

            }
            Log.e("expiredList: ", expiredList.toString());


            mExpiredAdapter = new ExpiredListAdapter(getContext(), expiredList);
            mRecyclerExpired.setHasFixedSize(false);
            mRecyclerExpired.setLayoutManager(new LinearLayoutManager(getActivity()));
            mRecyclerExpired.setNestedScrollingEnabled(true);
            mRecyclerExpired.setAdapter(mExpiredAdapter);
        }


    }

    @Override
    public void onFailureRentalDetailsList() {
        tvNoPlans.setVisibility(View.VISIBLE);
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