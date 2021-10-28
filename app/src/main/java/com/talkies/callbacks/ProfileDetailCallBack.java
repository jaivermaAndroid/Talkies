package com.talkies.callbacks;

import org.json.JSONArray;
import org.json.JSONObject;

public interface ProfileDetailCallBack {



    void onSuccessFAQ(JSONArray result);
    void onSuccessFAQ(String result);
    void onFailureFAQ();

    void onSuccessUpdateName(JSONObject result);
    void onSuccessUpdateName(JSONArray result);
    void onSuccessUpdateName(String result);
    void onFailureUpdateName();


    void onSuccessIssueList(JSONArray result);
    void onSuccessIssueList(String result);
    void onFailureIssueList();

    void onSuccessRentalDetailsList(JSONArray result);
    void onSuccessRentalDetailsList(JSONObject result);
    void onSuccessRentalDetailsList(String result);
    void onFailureRentalDetailsList();

    void onSuccessSubmitQuery(JSONObject result);
    void onFailSubmitQuery( );



    void onSuccessSubscriptionList(JSONArray result);
    void onSuccessSubscriptionList(JSONObject result);
    void onSuccessSubscriptionList(String result);
    void onFailureSubscriptionList();

    void onLogoutSuccess();
    void onLogoutFailure();

    void onSuccessRazorKeyResult(JSONObject result);
    void onFailureRazorKeyResult();


    void onSuccessConnectTv(JSONObject result);
    void onSuccessConnectTv(String result);
    void onFailure();
}


