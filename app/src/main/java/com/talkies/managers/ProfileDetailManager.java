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
import com.talkies.callbacks.ProfileDetailCallBack;
import com.talkies.utils.AppURLs;
import com.talkies.utils.BaseServiceHandler;
import com.talkies.utils.GeneralMethods;
import com.talkies.utils.MyPreferenceManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ProfileDetailManager {

    Context mContext;
    private boolean pictureTaken = false;
   // ProgressDialog mProgressDialog;
    boolean updateActivity=false;
    private MyPreferenceManager prefManager;
    ProgressDialog mProgressDialog;
    ProfileDetailCallBack mProfileDetailCallBack;

    public ProfileDetailManager(Context mContext, ProfileDetailCallBack mProfileDetailCallBack,MyPreferenceManager prefManager) {
        this.mContext=mContext;
        this.mProfileDetailCallBack =mProfileDetailCallBack;
        this.prefManager = prefManager;

    }
    private JSONObject getRequestBody() {
        JSONObject object = new JSONObject();


        return object;
    }
    private JSONObject getRequestBodyRazKey(String id) {
        JSONObject object = new JSONObject();

        try {
            object.put("selected_option",id);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return object;
    }

    private JSONObject getRequestConnectTv(String otp) {
        JSONObject object = new JSONObject();
        JSONObject data = new JSONObject();

        try {
            object.put("action","TV_PAIR");
            data.put("otp",otp);
            data.put("name","Android");
            object.put("data",data);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return object;
    }


    private JSONObject getRequestUnpairTv(String device_id) {
        JSONObject object = new JSONObject();
        JSONObject data = new JSONObject();

        try {
            object.put("action","TV_PAIR");
            data.put("device_id",device_id);
            object.put("data",data);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return object;
    }
    private JSONArray getRequestArrayBody() {
        JSONArray object = new JSONArray();
        return object;
    }
    public void getFAQList(GeneralMethods mGeneralMethods, String type) {
       
        try {
            BaseServiceHandler mBaseWebServiceHandler = new BaseServiceHandler("FAQList");
            mBaseWebServiceHandler.callServiceArray(Request.Method.GET, AppURLs.faqList, getRequestBody(), getRequestHeaders(), new BaseServiceCallBack() {
                @Override
                public void onSuccess(JSONObject result) throws JSONException {
                   
                }

                @Override
                public void onSuccess(JSONArray result) throws JSONException {
                   
                    mProfileDetailCallBack.onSuccessFAQ(result);
                }

                @Override
                public void onSuccess(String result) throws JSONException {
                   
                    mProfileDetailCallBack.onSuccessFAQ(result);
                }


                @Override
                public void onErrorResponse(String errorString) {
                   
                    mProfileDetailCallBack.onFailureFAQ();
                }

                @Override
                public void onBadResponse() {
                   
                    mProfileDetailCallBack.onFailureFAQ();
                }


                @Override
                public void getError(VolleyError volleyError) {
                   
                    mProfileDetailCallBack.onFailureFAQ();
                }
            });
        } catch (Exception e) {
            Log.e("getTopBannerImages: ", e.toString());
        }
    }



    public void connectTV(GeneralMethods mGeneralMethods, String otp) {
       
        try {
            BaseServiceHandler mBaseWebServiceHandler = new BaseServiceHandler("connectTV");
            mBaseWebServiceHandler.callService(Request.Method.POST, AppURLs.connectTV, getRequestConnectTv(otp), getRequestHeaders(), new BaseServiceCallBack() {
                @Override
                public void onSuccess(JSONObject result) throws JSONException {
                   
                    mProfileDetailCallBack.onSuccessConnectTv(result);
                }

                @Override
                public void onSuccess(JSONArray result) throws JSONException {
                    //mProfileDetailCallBack.onSuccessConnectTv(result);
                }

                @Override
                public void onSuccess(String result) throws JSONException {
                   
                    mProfileDetailCallBack.onSuccessConnectTv(result);
                }


                @Override
                public void onErrorResponse(String errorString) {
                   
                    mProfileDetailCallBack.onFailureFAQ();
                }

                @Override
                public void onBadResponse() {
                   
                    mProfileDetailCallBack.onFailureFAQ();
                }


                @Override
                public void getError(VolleyError volleyError) {
                   
                    mProfileDetailCallBack.onFailureFAQ();
                }
            });
        } catch (Exception e) {
            Log.e("getTopBannerImages: ", e.toString());
        }
    }


    public void unpairTV(GeneralMethods mGeneralMethods, String otp) {
       
        try {
            BaseServiceHandler mBaseWebServiceHandler = new BaseServiceHandler("connectTV");
            mBaseWebServiceHandler.callService(Request.Method.POST, AppURLs.connectTV, getRequestUnpairTv(otp), getRequestHeaders(), new BaseServiceCallBack() {
                @Override
                public void onSuccess(JSONObject result) throws JSONException {
                   
                    mProfileDetailCallBack.onSuccessConnectTv(result);
                }

                @Override
                public void onSuccess(JSONArray result) throws JSONException {
                   
                    //mProfileDetailCallBack.onSuccessConnectTv(result);
                }

                @Override
                public void onSuccess(String result) throws JSONException {
                   
                    mProfileDetailCallBack.onSuccessConnectTv(result);
                }


                @Override
                public void onErrorResponse(String errorString) {
                   
                    mProfileDetailCallBack.onFailureFAQ();
                }

                @Override
                public void onBadResponse() {
                   
                    mProfileDetailCallBack.onFailureFAQ();
                }


                @Override
                public void getError(VolleyError volleyError) {
                   
                    mProfileDetailCallBack.onFailureFAQ();
                }
            });
        } catch (Exception e) {
            Log.e("getTopBannerImages: ", e.toString());
        }
    }


    public void logoutUser(GeneralMethods mGeneralMethods) {
       
        try {
            BaseServiceHandler mBaseWebServiceHandler = new BaseServiceHandler("FAQList");
            mBaseWebServiceHandler.callServiceForString(Request.Method.GET, AppURLs.logoutUser, getRequestBody(), getRequestHeaders(), new BaseServiceCallBack() {
                @Override
                public void onSuccess(JSONObject result) throws JSONException {
                   
                    mProfileDetailCallBack.onLogoutSuccess();
                }

                @Override
                public void onSuccess(JSONArray result) throws JSONException {
                   
                    mProfileDetailCallBack.onLogoutSuccess();
                }

                @Override
                public void onSuccess(String result) throws JSONException {
                   
                    mProfileDetailCallBack.onLogoutSuccess();
                }


                @Override
                public void onErrorResponse(String errorString) {
                   
                    mProfileDetailCallBack.onLogoutFailure();
                }

                @Override
                public void onBadResponse() {
                   
                    mProfileDetailCallBack.onLogoutFailure();
                }


                @Override
                public void getError(VolleyError volleyError) {
                   
                    mProfileDetailCallBack.onLogoutFailure();
                }
            });
        } catch (Exception e) {
            Log.e("getTopBannerImages: ", e.toString());
        }
    }
    public void submitQuery(GeneralMethods mGeneralMethods, String type,String countryCode, String email, String mobile_number,String description,String issueTitle , String issueId) {

        try {
           
            BaseServiceHandler mBaseWebServiceHandler = new BaseServiceHandler(type);
            mBaseWebServiceHandler.callService(Request.Method.POST, AppURLs.submitQuery, getRequestBodyForSubmitQuery(countryCode,email,mobile_number,description,issueTitle,issueId), getRequestHeaders(), new BaseServiceCallBack() {
                @Override
                public void onSuccess(JSONObject result) throws JSONException {
                   
                    mProfileDetailCallBack.onSuccessSubmitQuery(result);
                }

                @Override
                public void onSuccess(JSONArray result) throws JSONException {
                   
                    //mProfileDetailCallBack.onSuccessFAQ(result);
                }

                @Override
                public void onSuccess(String result) throws JSONException {
                   
                    //mProfileDetailCallBack.onSuccessFAQ(result);
                }


                @Override
                public void onErrorResponse(String errorString) {
                   
                    mProfileDetailCallBack.onFailSubmitQuery();
                }

                @Override
                public void onBadResponse() {
                   
                    mProfileDetailCallBack.onFailSubmitQuery();
                }


                @Override
                public void getError(VolleyError volleyError) {
                   
                    mProfileDetailCallBack.onFailSubmitQuery();
                }
            });
        } catch (Exception e) {
            Log.e("getTopBannerImages: ", e.toString());
        }
    }

    public void saveName(GeneralMethods mGeneralMethods, String type,String name, String lastName) {
        try {
           
            BaseServiceHandler mBaseWebServiceHandler = new BaseServiceHandler("saveNameProfile");
            mBaseWebServiceHandler.callService(Request.Method.PUT, AppURLs.saveNameProfile, getRequestBodyForSubmitName(name), getRequestHeaders(), new BaseServiceCallBack() {
                @Override
                public void onSuccess(JSONObject result) throws JSONException {
                   
                    mProfileDetailCallBack.onSuccessUpdateName(result);
                }

                @Override
                public void onSuccess(JSONArray result) throws JSONException {
                   
                    mProfileDetailCallBack.onSuccessUpdateName(result);
                }

                @Override
                public void onSuccess(String result) throws JSONException {
                   
                    mProfileDetailCallBack.onSuccessUpdateName(result);
                }


                @Override
                public void onErrorResponse(String errorString) {
                   
                    mProfileDetailCallBack.onFailureUpdateName();
                }

                @Override
                public void onBadResponse() {
                   
                    mProfileDetailCallBack.onFailureUpdateName();
                }


                @Override
                public void getError(VolleyError volleyError) {
                   
                    mProfileDetailCallBack.onFailureUpdateName();
                }
            });
        } catch (Exception e) {
            Log.e("getTopBannerImages: ", e.toString());
        }
    }


    public void getRentalDetails(GeneralMethods mGeneralMethods, String rental_type, String is_expired) {
        try {
           
            BaseServiceHandler mBaseWebServiceHandler = new BaseServiceHandler("Continue Watching");
            mBaseWebServiceHandler.callService(Request.Method.GET, AppURLs.rentalDetails+rental_type+"&is_expired="+is_expired, getRequestBody(), getRequestHeaders(), new BaseServiceCallBack() {
                @Override
                public void onSuccess(JSONObject result) throws JSONException {
                   
                    mProfileDetailCallBack.onSuccessRentalDetailsList(result);
                }

                @Override
                public void onSuccess(JSONArray result) throws JSONException {
                   
                    mProfileDetailCallBack.onSuccessRentalDetailsList(result);
                }

                @Override
                public void onSuccess(String result) throws JSONException {
                   
                    mProfileDetailCallBack.onSuccessRentalDetailsList(result);
                }


                @Override
                public void onErrorResponse(String errorString) {
                   
                    mProfileDetailCallBack.onFailureRentalDetailsList();
                }

                @Override
                public void onBadResponse() {
                   
                    mProfileDetailCallBack.onFailureRentalDetailsList();
                }


                @Override
                public void getError(VolleyError volleyError) {
                   
                    mProfileDetailCallBack.onFailureRentalDetailsList();
                }
            });
        } catch (Exception e) {
            Log.e("getTopBannerImages: ", e.toString());
        }
    }


    public void getRazorKey(GeneralMethods mGeneralMethods,String id ) {
        try {
           
            BaseServiceHandler mBaseWebServiceHandler = new BaseServiceHandler("announcements");
            mBaseWebServiceHandler.callService(Request.Method.POST, AppURLs.rentalRazKeyInfo, getRequestBodyRazKey(id), getRequestHeaders(), new BaseServiceCallBack() {
                @Override
                public void onSuccess(JSONObject result) throws JSONException {
                   
                    mProfileDetailCallBack.onSuccessRazorKeyResult(result);
                }

                @Override
                public void onSuccess(JSONArray result) throws JSONException {
                   
                    //mProfileDetailCallBack.onSuccessNotificationResult(result);
                }

                @Override
                public void onSuccess(String result) throws JSONException {
                   

                }


                @Override
                public void onErrorResponse(String errorString) {
                   
                    mProfileDetailCallBack.onFailureRazorKeyResult();
                }

                @Override
                public void onBadResponse() {
                   
                    mProfileDetailCallBack.onFailureRazorKeyResult();
                }


                @Override
                public void getError(VolleyError volleyError) {
                   
                    mProfileDetailCallBack.onFailureRazorKeyResult();
                }
            });
        } catch (Exception e) {
            Log.e("getTopBannerImages: ", e.toString());
        }
    }
    public void getExpiredDetails(GeneralMethods mGeneralMethods, String rental_type, String is_expired) {
        try {
           
            BaseServiceHandler mBaseWebServiceHandler = new BaseServiceHandler("Continue Watching");
            mBaseWebServiceHandler.callService(Request.Method.GET, AppURLs.expiredDetails+is_expired, getRequestBody(), getRequestHeaders(), new BaseServiceCallBack() {
                @Override
                public void onSuccess(JSONObject result) throws JSONException {
                   
                    mProfileDetailCallBack.onSuccessRentalDetailsList(result);
                }

                @Override
                public void onSuccess(JSONArray result) throws JSONException {
                   
                    mProfileDetailCallBack.onSuccessRentalDetailsList(result);
                }

                @Override
                public void onSuccess(String result) throws JSONException {
                   
                    mProfileDetailCallBack.onSuccessRentalDetailsList(result);
                }


                @Override
                public void onErrorResponse(String errorString) {
                   
                    mProfileDetailCallBack.onFailureRentalDetailsList();
                }

                @Override
                public void onBadResponse() {
                   
                    mProfileDetailCallBack.onFailureRentalDetailsList();
                }


                @Override
                public void getError(VolleyError volleyError) {
                   
                    mProfileDetailCallBack.onFailureRentalDetailsList();
                }
            });
        } catch (Exception e) {
            Log.e("getTopBannerImages: ", e.toString());
        }
    }

    //
    public void getIssueTypeList(GeneralMethods mGeneralMethods, String type) {
        try {
           
            BaseServiceHandler mBaseWebServiceHandler = new BaseServiceHandler("Continue Watching");
            mBaseWebServiceHandler.callServiceArray(Request.Method.GET, AppURLs.issueTypeList, getRequestBody(), getRequestHeaders(), new BaseServiceCallBack() {
                @Override
                public void onSuccess(JSONObject result) throws JSONException {
                   
                }

                @Override
                public void onSuccess(JSONArray result) throws JSONException {
                   
                }

                @Override
                public void onSuccess(String result) throws JSONException {
                   
                    mProfileDetailCallBack.onSuccessIssueList(result);

                }


                @Override
                public void onErrorResponse(String errorString) {
                   
                    mProfileDetailCallBack.onFailureIssueList();
                }

                @Override
                public void onBadResponse() {
                   
                    mProfileDetailCallBack.onFailureIssueList();
                }


                @Override
                public void getError(VolleyError volleyError) {
                   
                    mProfileDetailCallBack.onFailureIssueList();
                }
            });
        } catch (Exception e) {
            Log.e("getTopBannerImages: ", e.toString());
        }
    }
//



    public void getTermsConditions(GeneralMethods mGeneralMethods) {
        try {
            BaseServiceHandler mBaseWebServiceHandler = new BaseServiceHandler("Continue Watching");
            //String addParam = "rental_type="+rental_type+"&is_expired="+is_expired;
            String addParam = "?rental_type=STAND_ALONE&is_expired=false";
            Log.e("getRentalDetails: ",AppURLs.rentalDetails+addParam );
            mBaseWebServiceHandler.callService(Request.Method.GET, "https://tv5e-api.talkiesapp.com/api/v3/media/rental_info/history/?rental_type=STAND_ALONE&is_expired=false", getRequestBody(), getRequestHeaders(), new BaseServiceCallBack() {
                @Override
                public void onSuccess(JSONObject result) throws JSONException {
                   
                    mProfileDetailCallBack.onSuccessRentalDetailsList(result);
                }

                @Override
                public void onSuccess(JSONArray result) throws JSONException {
                   
                    mProfileDetailCallBack.onSuccessRentalDetailsList(result);
                }

                @Override
                public void onSuccess(String result) throws JSONException {
                   
                    mProfileDetailCallBack.onSuccessRentalDetailsList(result);
                }


                @Override
                public void onErrorResponse(String errorString) {
                   
                    mProfileDetailCallBack.onFailureRentalDetailsList();
                }

                @Override
                public void onBadResponse() {
                   
                    mProfileDetailCallBack.onFailureRentalDetailsList();
                }


                @Override
                public void getError(VolleyError volleyError) {
                   
                    mProfileDetailCallBack.onFailureRentalDetailsList();
                }
            });
        } catch (Exception e) {
            Log.e("getTopBannerImages: ", e.toString());
        }
    }

    public void getSubscription(GeneralMethods mGeneralMethods, String rental_type, String is_expired) {
        try {
           
            BaseServiceHandler mBaseWebServiceHandler = new BaseServiceHandler("Continue Watching");
            mBaseWebServiceHandler.callServiceArray(Request.Method.GET, AppURLs.subscriptionList, getRequestBody(), getRequestHeaders(), new BaseServiceCallBack() {
                @Override
                public void onSuccess(JSONObject result) throws JSONException {
                   
                    mProfileDetailCallBack.onSuccessSubscriptionList(result);
                }

                @Override
                public void onSuccess(JSONArray result) throws JSONException {
                   
                    mProfileDetailCallBack.onSuccessSubscriptionList(result);
                }

                @Override
                public void onSuccess(String result) throws JSONException {
                   
                    mProfileDetailCallBack.onSuccessSubscriptionList(result);
                }


                @Override
                public void onErrorResponse(String errorString) {
                   
                    mProfileDetailCallBack.onFailureSubscriptionList();
                }

                @Override
                public void onBadResponse() {
                   
                    mProfileDetailCallBack.onFailureSubscriptionList();
                }


                @Override
                public void getError(VolleyError volleyError) {
                   
                    mProfileDetailCallBack.onFailureSubscriptionList();
                }
            });
        } catch (Exception e) {
            Log.e("getTopBannerImages: ", e.toString());
        }
    }



    private JSONObject getRequestBodyForSubmitQuery(String countryCode, String email, String mobile_number,String description,String issueTitle , String issueId) {
        JSONObject object = new JSONObject();

        try {

        object.put("country_code" ,"+"+countryCode);
        object.put("email" ,email);
        object.put("mobile_number" ,mobile_number);
        object.put("description" ,description);

        JSONObject issueObject = new JSONObject();
        issueObject.put("title",issueTitle);
        issueObject.put("id",issueId);

            object.put("issue" ,issueObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("getForSubmitQuery: ",object.toString() );
        return object;
    }

    private JSONObject getRequestBodyForSubmitPhone(String countryCode,  String mobile_number) {
        JSONObject object = new JSONObject();

        try {
            object.put("country_code" ,countryCode);
            object.put("mobile_number" ,mobile_number);



        } catch (JSONException e) {
            e.printStackTrace();
        }


        return object;
    }

    private JSONObject getRequestBodyForSubmitName(String name) {
        JSONObject object = new JSONObject();

        try {
            object.put("first_name" ,name);



        } catch (JSONException e) {
            e.printStackTrace();
        }


        return object;
    }
    private JSONObject getRequestBodyForSubmitEmail(String email) {
        JSONObject object = new JSONObject();

        try {
            object.put("email" ,email);



        } catch (JSONException e) {
            e.printStackTrace();
        }


        return object;
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

    private HashMap<String, String> getRequestHeadersNull() {
        HashMap<String, String> headersForRegisterUser = new HashMap<>();

        return headersForRegisterUser;
    }

}
