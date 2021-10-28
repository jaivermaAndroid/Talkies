package com.talkies.managers;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.talkies.R;
import com.talkies.callbacks.BaseServiceCallBack;
import com.talkies.callbacks.HomeCallBack;
import com.talkies.utils.AppURLs;
import com.talkies.utils.BaseServiceHandler;
import com.talkies.utils.GeneralMethods;
import com.talkies.utils.MyPreferenceManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class TabHomeManager {

    Context mContext;
    private boolean pictureTaken = false;
    //ProgressDialog mProgressDialog;
    boolean updateActivity=false;
    private MyPreferenceManager prefManager;
    ProgressDialog mProgressDialog;
    HomeCallBack mTabHomeCallBack;

    public TabHomeManager(Context mContext, HomeCallBack mHomeCallBack,MyPreferenceManager prefManager) {
        this.mContext=mContext;
        this.mTabHomeCallBack=mHomeCallBack;
        this.prefManager=prefManager;

    }
    private JSONObject getRequestBody() {
        JSONObject object = new JSONObject();


        return object;
    }
    private JSONArray getRequestArrayBody() {
        JSONArray object = new JSONArray();
        return object;
    }



    public void getNotificationAlterResult(GeneralMethods mGeneralMethods) {

        try {
            BaseServiceHandler mBaseWebServiceHandler = new BaseServiceHandler("SearchList");
            mBaseWebServiceHandler.callServiceArray(Request.Method.GET, AppURLs.notificationAlert, getRequestBody(), getRequestHeaders(), new BaseServiceCallBack() {
                @Override
                public void onSuccess(JSONObject result) throws JSONException {
                }

                @Override
                public void onSuccess(JSONArray result) throws JSONException {
                }

                @Override
                public void onSuccess(String result) throws JSONException {
                    mTabHomeCallBack.onSuccessAlertResult(result);

                }


                @Override
                public void onErrorResponse(String errorString) {
                    mTabHomeCallBack.onFailure();
                }

                @Override
                public void onBadResponse() {
                    mTabHomeCallBack.onFailure();
                }


                @Override
                public void getError(VolleyError volleyError) {
                    mTabHomeCallBack.onFailure();
                }
            });
        } catch (Exception e) {
        }
    }
    public void getTopBannerImages(GeneralMethods mGeneralMethods, String type) {
        try {
            BaseServiceHandler mBaseWebServiceHandler = new BaseServiceHandler("TopBanner");
            mBaseWebServiceHandler.callServiceArray(Request.Method.GET, AppURLs.topImageBanner+type, getRequestBody(), getRequestHeaders(), new BaseServiceCallBack() {
                @Override
                public void onSuccess(JSONObject result) throws JSONException {

                }

                @Override
                public void onSuccess(JSONArray result) throws JSONException {
                    mTabHomeCallBack.onSuccessMasterTopImageListHome(result);
                }

                @Override
                public void onSuccess(String result) throws JSONException {
                    mTabHomeCallBack.onSuccessMasterTopImageListHome(result);
                }


                @Override
                public void onErrorResponse(String errorString) {
                    mTabHomeCallBack.onFailureMasterTopImageListHome();
                }

                @Override
                public void onBadResponse() {
                    mTabHomeCallBack.onFailureMasterTopImageListHome();
                }


                @Override
                public void getError(VolleyError volleyError) {
                    mTabHomeCallBack.onFailureMasterTopImageListHome();
                }
            });
        } catch (Exception e) {
            Log.e("getTopBannerImages: ", e.toString());

        }
    }




    public void getContinueWatchingList(GeneralMethods mGeneralMethods, String type) {
        try {
            
            BaseServiceHandler mBaseWebServiceHandler = new BaseServiceHandler("Continue Watching");
            mBaseWebServiceHandler.callServiceArray(Request.Method.GET, AppURLs.continueWatchingList+type, getRequestBody(), getRequestHeaders(), new BaseServiceCallBack() {
                @Override
                public void onSuccess(JSONObject result) throws JSONException {
                   
                }

                @Override
                public void onSuccess(JSONArray result) throws JSONException {
                   
                    mTabHomeCallBack.onSuccessContinueWatchingListHome(result);
                }

                @Override
                public void onSuccess(String result) throws JSONException {
                   
                    mTabHomeCallBack.onSuccessContinueWatchingListHome(result);
                }


                @Override
                public void onErrorResponse(String errorString) {
                   
                    mTabHomeCallBack.onFailureContinueWatchingHome();
                }

                @Override
                public void onBadResponse() {
                   
                    mTabHomeCallBack.onFailureContinueWatchingHome();
                }


                @Override
                public void getError(VolleyError volleyError) {
                   
                    mTabHomeCallBack.onFailureContinueWatchingHome();
                }
            });
        } catch (Exception e) {
            Log.e("continuewacting: ", e.toString());
           
        }
    }


    public void getCategoryList(GeneralMethods mGeneralMethods, String type) {
        try {
            
            BaseServiceHandler mBaseWebServiceHandler = new BaseServiceHandler("Continue Watching");
            mBaseWebServiceHandler.callServiceArray(Request.Method.GET, AppURLs.categoryList+type, getRequestBody(), getRequestHeaders(), new BaseServiceCallBack() {
                @Override
                public void onSuccess(JSONObject result) throws JSONException {
                   
                }

                @Override
                public void onSuccess(JSONArray result) throws JSONException {
                   
                }

                @Override
                public void onSuccess(String result) throws JSONException {
                   
                    mTabHomeCallBack.onSuccessCategoryListHome(result);

                }


                @Override
                public void onErrorResponse(String errorString) {
                   
                    mTabHomeCallBack.onFailureCategoryListHome();
                }

                @Override
                public void onBadResponse() {
                   
                    mTabHomeCallBack.onFailureCategoryListHome();
                }


                @Override
                public void getError(VolleyError volleyError) {
                   
                    mTabHomeCallBack.onFailureCategoryListHome();
                }
            });
        } catch (Exception e) {
            Log.e("getTopBannerImages: ", e.toString());
           
        }
    }






    private HashMap<String, String> getRequestHeaders() {
        HashMap<String, String> headersForRegisterUser = new HashMap<>();
        headersForRegisterUser.put("Cookie",prefManager.getCookies());
        Log.e("getRequestHeaders: ",prefManager.getCookies() );
        return headersForRegisterUser;
    }
    private HashMap<String, String> getRequestHeadersJson() {
        HashMap<String, String> headersForRegisterUser = new HashMap<>();
        headersForRegisterUser.put("Cookie",prefManager.getCookies());

        return headersForRegisterUser;
    }

}
