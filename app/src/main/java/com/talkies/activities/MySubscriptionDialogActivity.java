package com.talkies.activities;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.talkies.R;
import com.talkies.adapters.SubscriptionListAdapter;
import com.talkies.callbacks.ProfileDetailCallBack;
import com.talkies.managers.ProfileDetailManager;
import com.talkies.model.UserDetails;
import com.talkies.model.subscription.SubscriptionResult;
import com.talkies.utils.GeneralMethods;
import com.talkies.utils.MyPreferenceManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.paperdb.Paper;

import static com.talkies.utils.Constants.STORAGE_USER_DETAILS_RESPONSE;

public class MySubscriptionDialogActivity extends Activity implements View.OnClickListener, ProfileDetailCallBack,SubscriptionListAdapter.SubscriptionOnItemClickListener, PaymentResultListener {

    final static String DATA_FLAG = "DATA_FLAG";

    RadioGroup radioGroupScreenSize, radioGroupAutoPlay;
    RadioButton radioButton_FullScreen, radioButton_16_09, radioButton_best_Size, radioButton_4_3, radioButtonAutoPlayOn, radioButtonAutoPlayOFF;
    ProfileDetailCallBack mProfileCallBack;
    GeneralMethods mGeneralMethods;
    String rentalType, isExpired;
    SubscriptionListAdapter mSubscriptionListAdapter;
    RecyclerView mRecyclersubscription;
    private List<SubscriptionResult> subscriptionDatumModels;
    ArrayList<SubscriptionResult> subscriptionList;
    TextView tvSubscriptionTitle, tvDescription,tvGstAmount,tvBaseAmount,tvAmountPayable;
    SubscriptionListAdapter.SubscriptionOnItemClickListener subscriptionOnRadioSelectedListener;
    Button proceedToPay;
    Gson gson;
    UserDetails storageUserDetails;
    ArrayList<SubscriptionResult> subscriptionResultList;
    int intRadioPosition;
    int position;
    String rzKey;
    MyPreferenceManager prefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Checkout.preload(getApplicationContext());
        setTheme(android.R.style.Theme_DeviceDefault_Dialog);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_subscription);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


    }

    @Override
    protected void onResume() {
        super.onResume();
        mProfileCallBack = this;
        gson = new Gson();
        mGeneralMethods = new GeneralMethods(this, this);
        prefManager= new MyPreferenceManager(this);
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
                if(subscriptionList!=null)
                {
                    new ProfileDetailManager(getApplicationContext(), mProfileCallBack,prefManager).getRazorKey(mGeneralMethods,subscriptionResultList.get(position).getData().get(intRadioPosition).getId());


                }
            }
        });

        new ProfileDetailManager(this, mProfileCallBack,prefManager).getSubscription(mGeneralMethods,rentalType, isExpired);

    }
    public void startPayment() {
        //
        /**
         * Instantiate Checkout
         */
        Checkout checkout = new Checkout();

        checkout.setKeyID(rzKey);

        /**
         * Set your logo here
         */
        checkout.setImage(R.drawable.app_logo);

        /**
         * Reference to current activity
         */
        final Activity activity = this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */


        try {
            JSONObject options = new JSONObject();

            options.put("name", storageUserDetails.getName());
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("order_id", subscriptionList.get(0).getData().get(0).getId());//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", subscriptionList.get(0).getData().get(0).getPriceDistribution().getTotalAmount().toString());//pass amount in currency subunits
            options.put("prefill.email", storageUserDetails.getEmail());
            options.put("prefill.contact",storageUserDetails.getMobileNumber());
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
        Log.e("rental: ",result );
        Type collectionType = new TypeToken<Collection<SubscriptionResult>>(){}.getType();
        subscriptionResultList = gson.fromJson(result, collectionType);



        mSubscriptionListAdapter = new SubscriptionListAdapter(this, subscriptionResultList,subscriptionOnRadioSelectedListener);
        mRecyclersubscription.setHasFixedSize(false);
        mRecyclersubscription.setLayoutManager(new LinearLayoutManager(this));
        mRecyclersubscription.setNestedScrollingEnabled(true);
        mRecyclersubscription.setAdapter(mSubscriptionListAdapter);
        tvSubscriptionTitle.setText(subscriptionResultList.get(0).getData().get(0).getDesc());
        tvDescription.setText(subscriptionResultList.get(0).getData().get(0).getLongDescription());
        tvBaseAmount.setText("₹"+subscriptionResultList.get(0).getData().get(0).getPriceDistribution().getBaseAmount().toString());
        tvGstAmount.setText("₹"+subscriptionResultList.get(0).getData().get(0).getPriceDistribution().getGstAmount().toString());
        tvAmountPayable.setText("₹"+subscriptionResultList.get(0).getData().get(0).getPriceDistribution().getTotalAmount().toString());
        proceedToPay.setText("Proceed to pay ₹ "+subscriptionResultList.get(0).getData().get(0).getPriceDistribution().getTotalAmount().toString());
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
        Log.e( "RazorKeyResult: ",result.toString());
        try {
            rzKey = result.getString("key");
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
    public void onPaymentSuccess(String s) {

    }

    @Override
    public void onPaymentError(int i, String s) {

    }

    @Override
    public void onRadioItemClick(SubscriptionResult subItem, int intRadioPosition, int position) {
        tvSubscriptionTitle.setText(subItem.getData().get(intRadioPosition).getDesc());
        tvDescription.setText(subItem.getData().get(intRadioPosition).getLongDescription());
        tvBaseAmount.setText("₹"+subItem.getData().get(intRadioPosition).getPriceDistribution().getBaseAmount().toString());
        tvGstAmount.setText("₹"+subItem.getData().get(intRadioPosition).getPriceDistribution().getGstAmount().toString());
        tvAmountPayable.setText("₹"+subItem.getData().get(intRadioPosition).getPriceDistribution().getTotalAmount().toString());
        proceedToPay.setText("Proceed to pay ₹ "+subItem.getData().get(intRadioPosition).getPriceDistribution().getTotalAmount().toString());
        this.position=position;
        this.intRadioPosition=intRadioPosition;

    }

    @Override
    public void onItemClick(SubscriptionResult item, int intRadioPosition) {

    }
}