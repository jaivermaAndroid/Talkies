package com.talkies.callbacks;

import org.json.JSONArray;
import org.json.JSONObject;

public interface MainActivityCallBack {
    void onSuccessSearchListResult(JSONArray result);
    void onSuccessSearchListResult(String result);
    void onFailureSearchResult();

    void onSuccessSearchResult(JSONArray result);
    void onSuccessSearchResult(String result);

    void onSuccessTabCategoryList(JSONArray result);
    void onSuccessTabCategoryList(String result);
    void onFailureTabCategoryResult();

    void onSuccessSearchIemDetailResult(JSONObject result);
    void onSuccessSearchIemDetailResult(String result);
    void onFailureSearchIemDetailResult();



    void onSuccessAnnouncementsResult(JSONArray result);
    void onSuccessAnnouncementsResult(String result);
    void onFailureAnnouncementsResult();

    void onSuccessMoreClicked(JSONObject result);
    void onSuccessMoreClicked(String result);
    void onFailureMoreClicked();

    void onSuccessNotificationResult(JSONObject result);
    void onSuccessNotificationResult(JSONArray result);
    void onSuccessNotificationResult(String result);
    void onFailureNotificationResult();




}
