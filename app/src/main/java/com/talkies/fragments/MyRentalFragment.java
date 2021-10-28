package com.talkies.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.talkies.R;
import com.talkies.adapters.RentalListAdapter;
import com.talkies.callbacks.ProfileDetailCallBack;
import com.talkies.managers.ProfileDetailManager;
import com.talkies.model.rental.Rental;
import com.talkies.model.rental.Result;
import com.talkies.utils.GeneralMethods;
import com.talkies.utils.MyPreferenceManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

public class MyRentalFragment extends Fragment implements  View.OnClickListener, ProfileDetailCallBack {

    final static String DATA_FLAG = "DATA_FLAG";
    ProfileDetailCallBack mProfileCallBack;
    GeneralMethods mGeneralMethods;
    String rentalType, isExpired;
    RentalListAdapter mRentalAdapter;
    RecyclerView mRecyclerRental;
    TextView tvNoPlans;
    MyPreferenceManager prefManager;
    Gson gson;
 @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rental, container, false);
    }

    public MyRentalFragment() {
        setRetainInstance(true);  //Hear put this line

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        prefManager = new MyPreferenceManager(getContext());
        tvNoPlans=view.findViewById(R.id.noPlansText);
        mProfileCallBack = this;
        gson = new Gson();
        mGeneralMethods = new GeneralMethods(getContext(),getActivity());
        mRecyclerRental=view.findViewById(R.id.rental_list);

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


    @Override
    public void onResume() {
        super.onResume();
        new ProfileDetailManager(getActivity(), mProfileCallBack,prefManager).getRentalDetails(mGeneralMethods,rentalType, isExpired);


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
        Type collectionType = new TypeToken<Rental>(){}.getType();

        Rental rental = gson.fromJson(result.toString(), collectionType);
        Log.e("rental: ",rental.toString() );
        ArrayList<Result>rentalList = new ArrayList<>() ;
        for(int item=0; item<rental.getResults().size();item++)
        {
            rentalList.add(rental.getResults().get(item));

        }
        Log.e("rental: ",rentalList.toString() );


        mRentalAdapter = new RentalListAdapter(getContext(), rentalList);
        mRecyclerRental.setHasFixedSize(false);
        mRecyclerRental.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerRental.setNestedScrollingEnabled(true);
        mRecyclerRental.setAdapter(mRentalAdapter);

    }

    @Override
    public void onSuccessRentalDetailsList(String result) {
        Log.e("rental: ",result );
        Type collectionType = new TypeToken<Rental>(){}.getType();

                Rental rental = gson.fromJson(result, collectionType);
        if(rental.getCount()==0)
        { tvNoPlans.setVisibility(View.VISIBLE);

        }else {
            ArrayList<Result> rentalList = new ArrayList<>();
            for (int item = 0; item < rental.getResults().size(); item++) {
                rentalList.add(rental.getResults().get(item));

            }
            Log.e("rental: ", rentalList.toString());


            mRentalAdapter = new RentalListAdapter(getContext(), rentalList);
            mRecyclerRental.setHasFixedSize(false);
            mRecyclerRental.setLayoutManager(new LinearLayoutManager(getActivity()));
            mRecyclerRental.setNestedScrollingEnabled(true);
            mRecyclerRental.setAdapter(mRentalAdapter);
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
