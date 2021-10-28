package com.talkies.utils;

import static com.facebook.FacebookSdk.getApplicationContext;
import static com.talkies.utils.Constants.STORAGE_USER_DETAILS_RESPONSE;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultListener;
import com.razorpay.PaymentResultWithDataListener;
import com.talkies.R;
import com.talkies.adapters.SubscriptionListAdapter;
import com.talkies.callbacks.ProfileDetailCallBack;
import com.talkies.managers.ProfileDetailManager;
import com.talkies.model.UserDetails;
import com.talkies.model.subscription.SubscriptionResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.paperdb.Paper;

public class CustomDialogClass extends Dialog implements android.view.View.OnClickListener, ProfileDetailCallBack, SubscriptionListAdapter.SubscriptionOnItemClickListener, PaymentResultListener, PaymentResultWithDataListener {

    public Activity c;
    public Dialog d;
    public Button yes, no;
    ProfileDetailCallBack mProfileCallBack;
    GeneralMethods mGeneralMethods;
    String rentalType, isExpired;
    SubscriptionListAdapter mSubscriptionListAdapter;
    RecyclerView mRecyclersubscription;
    ArrayList<SubscriptionResult> subscriptionResultList;
    TextView tvSubscriptionTitle, tvDescription, tvGstAmount, tvBaseAmount, tvAmountPayable;
    SubscriptionListAdapter.SubscriptionOnItemClickListener subscriptionOnRadioSelectedListener;
    Button proceedToPay;
    Gson gson;
    UserDetails storageUserDetails;
    Activity activity;
    int intRadioPosition;
    int position;
    String rzKey;
    String order_id;
    MyPreferenceManager prefManager;
    private List<SubscriptionResult> subscriptionDatumModels;

    public CustomDialogClass(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub

        this.activity = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_subscription);
//        yes = (Button) findViewById(R.id.btn_yes);
//        no = (Button) findViewById(R.id.btn_no);
//        yes.setOnClickListener(this);
//        no.setOnClickListener(this);

        mProfileCallBack = this;
        gson = new Gson();
        mGeneralMethods = new GeneralMethods(getContext(), activity);
        prefManager = new MyPreferenceManager(getContext());
        mRecyclersubscription = findViewById(R.id.subscriptionList);
        tvSubscriptionTitle = findViewById(R.id.subscriptionTitle);
        tvDescription = findViewById(R.id.subDescription);
        tvGstAmount = findViewById(R.id.gstPrizeBreakup);
        tvBaseAmount = findViewById(R.id.basePrizeBreakup);
        tvAmountPayable = findViewById(R.id.amountPayable);
        proceedToPay = findViewById(R.id.buttonProceedToPay);
        this.subscriptionOnRadioSelectedListener = this;
        Paper.book().read(STORAGE_USER_DETAILS_RESPONSE);
        storageUserDetails = Paper.book().read(STORAGE_USER_DETAILS_RESPONSE);

        proceedToPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Appy payment gateways
                if (subscriptionResultList != null) {

                    new ProfileDetailManager(getApplicationContext(), mProfileCallBack, prefManager).getRazorKey(mGeneralMethods, subscriptionResultList.get(position).getData().get(intRadioPosition).getId());

                }
            }
        });

        new ProfileDetailManager(getApplicationContext(), mProfileCallBack, prefManager).getSubscription(mGeneralMethods, rentalType, isExpired);


    }


    @Override
    protected void onStop() {
        super.onStop();
        Checkout.clearUserData(getContext());
    }

    public void startPayment() {
        Checkout.preload(getApplicationContext());
        Checkout checkout = new Checkout();
        checkout.setKeyID(rzKey);

        /**
         * Set your logo here
         */
        checkout.setImage(R.drawable.app_logo);

        /**
         * Reference to current activity
         */
        // final Activity activity = getOwnerActivity();

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */


        try {
            JSONObject options = new JSONObject();

            options.put("name", storageUserDetails.getName());
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("order_id", order_id);//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", subscriptionResultList.get(position).getData().get(intRadioPosition).getPriceDistribution().getTotalAmount().toString());//pass amount in currency subunits
            options.put("prefill.email", storageUserDetails.getEmail());
            options.put("prefill.contact", storageUserDetails.getMobileNumber());
            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            checkout.open(activity, options);

        } catch (Exception e) {
            Log.e("Error Razorpay", e.toString());
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
        Log.e("rental: ", result);
        Type collectionType = new TypeToken<Collection<SubscriptionResult>>() {
        }.getType();
        subscriptionResultList = gson.fromJson(result, collectionType);


        mSubscriptionListAdapter = new SubscriptionListAdapter(getContext(), subscriptionResultList, subscriptionOnRadioSelectedListener);
        mRecyclersubscription.setHasFixedSize(false);
        mRecyclersubscription.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclersubscription.setNestedScrollingEnabled(true);
        mRecyclersubscription.setAdapter(mSubscriptionListAdapter);
        tvSubscriptionTitle.setText(subscriptionResultList.get(0).getData().get(0).getDesc());
        tvDescription.setText(subscriptionResultList.get(0).getData().get(0).getLongDescription());
        tvBaseAmount.setText("₹" + subscriptionResultList.get(0).getData().get(0).getPriceDistribution().getBaseAmount().toString());
        tvGstAmount.setText("₹" + subscriptionResultList.get(0).getData().get(0).getPriceDistribution().getGstAmount().toString());
        tvAmountPayable.setText("₹" + subscriptionResultList.get(0).getData().get(0).getPriceDistribution().getTotalAmount().toString());
        proceedToPay.setText("Proceed to pay ₹ " + subscriptionResultList.get(0).getData().get(0).getPriceDistribution().getTotalAmount().toString());


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
        Log.e("RazorKeyResult: ", result.toString());
        try {
            rzKey = result.getString("key");
            order_id = result.getString("order_id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        startPayment();
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
    public void onRadioItemClick(SubscriptionResult subItem, int intRadioPosition, int position) {
        tvSubscriptionTitle.setText(subItem.getData().get(intRadioPosition).getDesc());
        tvDescription.setText(subItem.getData().get(intRadioPosition).getLongDescription());
        tvBaseAmount.setText("₹" + subItem.getData().get(intRadioPosition).getPriceDistribution().getBaseAmount().toString());
        tvGstAmount.setText("₹" + subItem.getData().get(intRadioPosition).getPriceDistribution().getGstAmount().toString());
        tvAmountPayable.setText("₹" + subItem.getData().get(intRadioPosition).getPriceDistribution().getTotalAmount().toString());
        proceedToPay.setText("Proceed to pay ₹ " + subItem.getData().get(intRadioPosition).getPriceDistribution().getTotalAmount().toString());
        this.position = position;
        this.intRadioPosition = intRadioPosition;

    }

    @Override
    public void onItemClick(SubscriptionResult item, int position) {

    }

    @SuppressWarnings("unused")
    @Override
    public void onPaymentSuccess(String s) {

    }

    @SuppressWarnings("unused")
    @Override
    public void onPaymentError(int i, String s) {

    }


    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {

    }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {

    }
}