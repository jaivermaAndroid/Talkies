package com.talkies.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultListener;
import com.razorpay.PaymentResultWithDataListener;
import com.talkies.R;
import com.talkies.adapters.RentalListAdapter;
import com.talkies.adapters.SubscriptionListAdapter;
import com.talkies.callbacks.ProfileDetailCallBack;
import com.talkies.managers.ProfileDetailManager;
import com.talkies.model.continuewatching.ContinueWatching;
import com.talkies.model.rental.Rental;
import com.talkies.model.rental.Result;
import com.talkies.model.subscription.Datum;
import com.talkies.model.subscription.SubscriptionResult;
import com.talkies.utils.GeneralMethods;
import com.talkies.utils.MyPreferenceManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MySubscriptionFragment extends Fragment implements  View.OnClickListener, ProfileDetailCallBack,SubscriptionListAdapter.SubscriptionOnItemClickListener{

//    final static String DATA_FLAG = "DATA_FLAG";
//
//    RadioGroup radioGroupScreenSize, radioGroupAutoPlay;
//    RadioButton radioButton_FullScreen, radioButton_16_09, radioButton_best_Size, radioButton_4_3, radioButtonAutoPlayOn, radioButtonAutoPlayOFF;
//    ProfileDetailCallBack mProfileCallBack;
//    GeneralMethods mGeneralMethods;
//    String rentalType, isExpired;
//    SubscriptionListAdapter mSubscriptionListAdapter;
//    RecyclerView mRecyclersubscription;
//    private List<SubscriptionResult> subscriptionDatumModels;
//
//    TextView tvSubscriptionTitle, tvDescription,tvGstAmount,tvBaseAmount,tvAmountPayable;
//    SubscriptionListAdapter.SubscriptionOnItemClickListener subscriptionOnRadioSelectedListener;
//    Button proceedToPay;
//    Gson gson;



    final static String DATA_FLAG = "DATA_FLAG";
    ProfileDetailCallBack mProfileCallBack;
    GeneralMethods mGeneralMethods;
    String rentalType, isExpired;
    RentalListAdapter mRentalAdapter;
    RecyclerView mRecyclerRental;
    TextView tvNoPlans;
    Gson gson;
    MyPreferenceManager prefManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rental, container, false);
    }

    public MySubscriptionFragment() {
        setRetainInstance(true);  //Hear put this line

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

////        radioGroupScreenSize.setOnCheckedChangeListener(this);
////        switch (checkedId) {
////            case R.id.rb_full_screen:
////                Toast.makeText(getContext(), "FullScree", Toast.LENGTH_SHORT).show();
////
////        }
//
//        mProfileCallBack = this;
//        gson = new Gson();
//        mGeneralMethods = new GeneralMethods(getContext(), getActivity());
//        mRecyclersubscription = view.findViewById(R.id.subscriptionList);
//        tvSubscriptionTitle = view.findViewById(R.id.subscriptionTitle);
//        tvDescription = view.findViewById(R.id.subDescription);
//        tvGstAmount = view.findViewById(R.id.gstPrizeBreakup);
//        tvBaseAmount = view.findViewById(R.id.basePrizeBreakup);
//        tvAmountPayable = view.findViewById(R.id.amountPayable);
//        proceedToPay = view.findViewById(R.id.buttonProceedToPay);
//        this.subscriptionOnRadioSelectedListener = this;
//        proceedToPay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Appy payment gateways
//                startPayment();
//            }
//        });




        tvNoPlans=view.findViewById(R.id.noPlansText);
        mProfileCallBack = this;
        gson = new Gson();
        mGeneralMethods = new GeneralMethods(getContext(),getActivity());
        mRecyclerRental=view.findViewById(R.id.rental_list);
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


    public void startPayment() {
        //
        /**
         * Instantiate Checkout
         */
        Checkout checkout = new Checkout();
        checkout.setKeyID("<YOUR_KEY_ID>");

        /**
         * Set your logo here
         */
        checkout.setImage(R.drawable.app_logo);

        /**
         * Reference to current activity
         */
        final Activity activity = getActivity();

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            options.put("name", "Merchant Name");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", "5");//pass amount in currency subunits
            options.put("prefill.email", "gaurav.kumar@example.com");
            options.put("prefill.contact","9988776655");
            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            checkout.open(activity, options);

        } catch(Exception e) {
            Log.e( "Error Razorpay", e.toString());
        }
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
    public void onResume() {
        super.onResume();
        rentalType= "SUBSCRIPTION";
        isExpired="false";
        new ProfileDetailManager(getActivity(), mProfileCallBack,prefManager).getRentalDetails(mGeneralMethods,rentalType, isExpired);


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
        tvNoPlans.setText("You have no Active subscription yet");

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
        tvNoPlans.setText("We are facing some issues");
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


    @Override
    public void onRadioItemClick(SubscriptionResult item, int intRadioPosition, int position) {

    }

    @Override
    public void onItemClick(SubscriptionResult item, int intRadioPosition) {

    }
}
