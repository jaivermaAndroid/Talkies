package com.talkies.callbacks;

import org.json.JSONArray;

public interface HomeCallBack {



    void onSuccessMasterTopImageListHome(JSONArray result);
    void onSuccessMasterTopImageListHome(String result);
    void onFailureMasterTopImageListHome();


    void onSuccessContinueWatchingListHome(JSONArray result);
    void onSuccessContinueWatchingListHome(String result);
    void onFailureContinueWatchingHome();

    void onSuccessCategoryListHome(String result);
    void onFailureCategoryListHome();


    void onSuccessAlertResult(String result);
    void onFailure();
}


