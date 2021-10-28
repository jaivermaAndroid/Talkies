package com.talkies.managers;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.talkies.R;
import com.talkies.callbacks.BaseServiceCallBack;
import com.talkies.callbacks.MainActivityCallBack;
import com.talkies.utils.AppURLs;
import com.talkies.utils.BaseServiceHandler;
import com.talkies.utils.GeneralMethods;
import com.talkies.utils.MyPreferenceManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MainActivityManager {

    Context mContext;
    private boolean pictureTaken = false;
    //ProgressDialog mProgressDialog;
    Dialog mProgressDialog;
    boolean updateActivity=false;
    private MyPreferenceManager prefManager;

    MainActivityCallBack mMainActivityCallBack;

    public MainActivityManager(Context mContext, MainActivityCallBack mHomeCallBack,MyPreferenceManager prefManager) {
        this.mContext=mContext;
        this.mMainActivityCallBack =mHomeCallBack;
        this.prefManager = new MyPreferenceManager(mContext);
    }
    private JSONObject getRequestBody() {
        JSONObject object = new JSONObject();
        return object;
    }



    private JSONObject getRequestBodyForClearNotification(Integer createdOn) {
        JSONObject object = new JSONObject();
        try {
            object.put("notification_create_on",createdOn);
            object.put("status","clear");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return object;
    }

    private JSONObject getRequestBodyForClearSelectedNotification(String notificationId) {
        JSONObject object = new JSONObject();
        try {
            object.put("id",notificationId);
            object.put("status","clear");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("getorClearSe", object.toString());
        return object;
    }

    private JSONArray getRequestArrayBody() {
        JSONArray object = new JSONArray();
        return object;
    }


    public void getSearchListResult(GeneralMethods mGeneralMethods) {
        try {
            BaseServiceHandler mBaseWebServiceHandler = new BaseServiceHandler("SearchList");
            mBaseWebServiceHandler.callServiceArray(Request.Method.GET, AppURLs.searchForQuery, getRequestBody(), getRequestHeaders(), new BaseServiceCallBack() {
                @Override
                public void onSuccess(JSONObject result) throws JSONException {

                }

                @Override
                public void onSuccess(JSONArray result) throws JSONException {
                    mMainActivityCallBack.onSuccessSearchListResult(result);
                }

                @Override
                public void onSuccess(String result) throws JSONException {
                    mMainActivityCallBack.onSuccessSearchListResult(result);
                }


                @Override
                public void onErrorResponse(String errorString) {
                    mMainActivityCallBack.onFailureSearchResult();
                }

                @Override
                public void onBadResponse() {
                    mMainActivityCallBack.onFailureSearchResult();
                }


                @Override
                public void getError(VolleyError volleyError) {
                    mMainActivityCallBack.onFailureSearchResult();
                }
            });
        } catch (Exception e) {
            Log.e( "getSearchListResult: ", e.toString());
        }
    }


    public void getSearchQueryResult(GeneralMethods mGeneralMethods,String searchQuery) {

        try {
            BaseServiceHandler mBaseWebServiceHandler = new BaseServiceHandler("SearchResult");
            mBaseWebServiceHandler.callServiceForString(Request.Method.GET, AppURLs.searchForQuery+searchQuery, getRequestBody(), getRequestHeaders(), new BaseServiceCallBack() {
                @Override
                public void onSuccess(JSONObject result) throws JSONException {

                }

                @Override
                public void onSuccess(JSONArray result) throws JSONException {

                    mMainActivityCallBack.onSuccessSearchResult(result);
                }

                @Override
                public void onSuccess(String result) throws JSONException {

                    mMainActivityCallBack.onSuccessSearchResult(result);
                }


                @Override
                public void onErrorResponse(String errorString) {

                    mMainActivityCallBack.onFailureSearchResult();
                }

                @Override
                public void onBadResponse() {

                    mMainActivityCallBack.onFailureSearchResult();
                }


                @Override
                public void getError(VolleyError volleyError) {

                    mMainActivityCallBack.onFailureSearchResult();
                }
            });
        } catch (Exception e) {

        }
    }





    public void getMoreDetails(GeneralMethods mGeneralMethods, String slug,String tabSlug) {

        try {
            BaseServiceHandler mBaseWebServiceHandler = new BaseServiceHandler("Continue Watching");
            //String addParam = "rental_type="+rental_type+"&is_expired="+is_expired;
            String addParam = "?rental_type=STAND_ALONE&is_expired=false";
            Log.e("getRentalDetails: ",AppURLs.rentalDetails+addParam );
            mBaseWebServiceHandler.callService(Request.Method.GET, AppURLs.moreClicked+tabSlug+"&section="+slug, getRequestBody(), getRequestHeaders(), new BaseServiceCallBack() {
                @Override
                public void onSuccess(JSONObject result) throws JSONException {
                    Log.d("zzz","response MainActivityManager"+result);
                    mMainActivityCallBack.onSuccessMoreClicked(result);
                }

                @Override
                public void onSuccess(JSONArray result) throws JSONException {

                }

                @Override
                public void onSuccess(String result) throws JSONException {

                    mMainActivityCallBack.onSuccessMoreClicked(result);
                }


                @Override
                public void onErrorResponse(String errorString) {

                    mMainActivityCallBack.onFailureMoreClicked();
                }

                @Override
                public void onBadResponse() {

                    mMainActivityCallBack.onFailureMoreClicked();
                }


                @Override
                public void getError(VolleyError volleyError) {

                    mMainActivityCallBack.onFailureMoreClicked();
                }
            });
        } catch (Exception e) {
            Log.e("getTopBannerImages: ", e.toString());
        }
    }



    public void getSearchDetails(GeneralMethods mGeneralMethods,String searchItem) {
        try {

            BaseServiceHandler mBaseWebServiceHandler = new BaseServiceHandler("SearchResult");
            mBaseWebServiceHandler.callService(Request.Method.GET, AppURLs.searchDetails+searchItem+"/", getRequestBody(), getRequestHeaders(), new BaseServiceCallBack() {
                @Override
                public void onSuccess(JSONObject result) throws JSONException {

                    mMainActivityCallBack.onSuccessSearchIemDetailResult(result);
                }

                @Override
                public void onSuccess(JSONArray result) throws JSONException {

                   // mSearchCallBack.onSuccessSearchResult(result);
                }

                @Override
                public void onSuccess(String result) throws JSONException {

                    mMainActivityCallBack.onSuccessSearchIemDetailResult(result);
                }


                @Override
                public void onErrorResponse(String errorString) {

                    mMainActivityCallBack.onFailureSearchIemDetailResult();
                }

                @Override
                public void onBadResponse() {

                    mMainActivityCallBack.onFailureSearchIemDetailResult();
                }


                @Override
                public void getError(VolleyError volleyError) {

                    mMainActivityCallBack.onFailureSearchIemDetailResult();
                }
            });
        } catch (Exception e) {

        }
    }




    public void getTabsCategories(GeneralMethods mGeneralMethods) {
        try {

            BaseServiceHandler mBaseWebServiceHandler = new BaseServiceHandler("TabCategory");
            mBaseWebServiceHandler.callServiceForString(Request.Method.GET, AppURLs.getTabsCategory, getRequestBody(), getRequestHeaders(), new BaseServiceCallBack() {
                @Override
                public void onSuccess(JSONObject result) throws JSONException {

                }

                @Override
                public void onSuccess(JSONArray result) throws JSONException {

                    mMainActivityCallBack.onSuccessTabCategoryList(result);
                }

                @Override
                public void onSuccess(String result) throws JSONException {

                    mMainActivityCallBack.onSuccessTabCategoryList(result);
                }


                @Override
                public void onErrorResponse(String errorString) {

                    mMainActivityCallBack.onFailureTabCategoryResult();
                }

                @Override
                public void onBadResponse() {

                    mMainActivityCallBack.onFailureTabCategoryResult();
                }


                @Override
                public void getError(VolleyError volleyError) {

                    mMainActivityCallBack.onFailureTabCategoryResult();
                }
            });
        } catch (Exception e) {

        }
    }



    public void getAnnouncementsList(GeneralMethods mGeneralMethods) {
        try {

            BaseServiceHandler mBaseWebServiceHandler = new BaseServiceHandler("announcements");
            mBaseWebServiceHandler.callServiceArray(Request.Method.GET, AppURLs.announcements, getRequestBody(), getRequestHeaders(), new BaseServiceCallBack() {
                @Override
                public void onSuccess(JSONObject result) throws JSONException {

                }

                @Override
                public void onSuccess(JSONArray result) throws JSONException {

                    mMainActivityCallBack.onSuccessAnnouncementsResult(result);
                }

                @Override
                public void onSuccess(String result) throws JSONException {

                    mMainActivityCallBack.onSuccessAnnouncementsResult(result);

                }


                @Override
                public void onErrorResponse(String errorString) {

                    mMainActivityCallBack.onFailureAnnouncementsResult();
                }

                @Override
                public void onBadResponse() {

                    mMainActivityCallBack.onFailureAnnouncementsResult();
                }


                @Override
                public void getError(VolleyError volleyError) {

                    mMainActivityCallBack.onFailureAnnouncementsResult();
                }
            });
        } catch (Exception e) {
            Log.e("getTopBannerImages: ", e.toString());

        }
    }



    public void getClearAllNotificationList(GeneralMethods mGeneralMethods,Integer createdOn) {
        try {

            BaseServiceHandler mBaseWebServiceHandler = new BaseServiceHandler("announcements");
            mBaseWebServiceHandler.callService(Request.Method.PUT, AppURLs.notificationList, getRequestBodyForClearNotification(createdOn), getRequestHeaders(), new BaseServiceCallBack() {
                @Override
                public void onSuccess(JSONObject result) throws JSONException {

                    mMainActivityCallBack.onSuccessNotificationResult(result);
                }

                @Override
                public void onSuccess(JSONArray result) throws JSONException {

                    mMainActivityCallBack.onSuccessNotificationResult(result);
                }

                @Override
                public void onSuccess(String result) throws JSONException {

                    mMainActivityCallBack.onSuccessNotificationResult(result);

                }


                @Override
                public void onErrorResponse(String errorString) {

                    mMainActivityCallBack.onFailureNotificationResult();
                }

                @Override
                public void onBadResponse() {

                    mMainActivityCallBack.onFailureNotificationResult();
                }


                @Override
                public void getError(VolleyError volleyError) {

                    mMainActivityCallBack.onFailureNotificationResult();
                }
            });
        } catch (Exception e) {
            Log.e("getTopBannerImages: ", e.toString());

        }
    }
    public void getClearSelectedNotificationList(GeneralMethods mGeneralMethods,String notificationId) {
        try {

            BaseServiceHandler mBaseWebServiceHandler = new BaseServiceHandler("Clear Notification");
            mBaseWebServiceHandler.callService(Request.Method.PUT, AppURLs.notificationList, getRequestBodyForClearSelectedNotification(notificationId), getRequestHeaders(), new BaseServiceCallBack() {
                @Override
                public void onSuccess(JSONObject result) throws JSONException {

                    mMainActivityCallBack.onSuccessNotificationResult(result);
                }

                @Override
                public void onSuccess(JSONArray result) throws JSONException {

                    mMainActivityCallBack.onSuccessNotificationResult(result);
                }

                @Override
                public void onSuccess(String result) throws JSONException {

                    mMainActivityCallBack.onSuccessNotificationResult(result);

                }


                @Override
                public void onErrorResponse(String errorString) {

                    mMainActivityCallBack.onFailureNotificationResult();
                }

                @Override
                public void onBadResponse() {

                    mMainActivityCallBack.onFailureNotificationResult();
                }


                @Override
                public void getError(VolleyError volleyError) {

                    mMainActivityCallBack.onFailureNotificationResult();
                }
            });
        } catch (Exception e) {
            Log.e("getTopBannerImages: ", e.toString());

        }
    }
    public void getNotificationList(GeneralMethods mGeneralMethods) {
        try {

            BaseServiceHandler mBaseWebServiceHandler = new BaseServiceHandler("announcements");
            mBaseWebServiceHandler.callService(Request.Method.GET, AppURLs.notificationList, getRequestBody(), getRequestHeaders(), new BaseServiceCallBack() {
                @Override
                public void onSuccess(JSONObject result) throws JSONException {

                    mMainActivityCallBack.onSuccessNotificationResult(result);
                }

                @Override
                public void onSuccess(JSONArray result) throws JSONException {

                    mMainActivityCallBack.onSuccessNotificationResult(result);
                }

                @Override
                public void onSuccess(String result) throws JSONException {

                    mMainActivityCallBack.onSuccessNotificationResult(result);

                }


                @Override
                public void onErrorResponse(String errorString) {

                    mMainActivityCallBack.onFailureNotificationResult();
                }

                @Override
                public void onBadResponse() {

                    mMainActivityCallBack.onFailureNotificationResult();
                }


                @Override
                public void getError(VolleyError volleyError) {

                    mMainActivityCallBack.onFailureNotificationResult();
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
