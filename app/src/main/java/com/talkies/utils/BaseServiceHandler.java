package com.talkies.utils;


import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.talkies.callbacks.BaseServiceCallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class BaseServiceHandler {

    private RequestQueue mRequestQueue;
    private String mTagForRequest;
    /// SERVICE REQUEST PARAMS
    private int SERVICE_CALL_INITIAL_TIMEOUT = 20 * 1000;
    private int SERVICE_CALL_MAX_RETRIES = 2;
    public BaseServiceHandler(String tag) {
        this.mTagForRequest = tag;
    }
    public BaseServiceHandler(String tag, int timeout, int maxRetries) {
        this.mTagForRequest = tag;
        this.SERVICE_CALL_INITIAL_TIMEOUT = timeout;
        this.SERVICE_CALL_MAX_RETRIES = maxRetries;
    }
    public BaseServiceHandler(Context securityQuestionActivity) {

    }

    public void callService(int requestMethod, String url, JSONObject bodyObject, final HashMap<String, String> headersMap, final BaseServiceCallBack callback) throws JSONException {

        if (callback != null) {
            mRequestQueue = MyApplication.getInstance().getRequestQueue();
            mRequestQueue.getCache().clear();

            JsonObjectRequest request = new JsonObjectRequest(requestMethod, url, bodyObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject object) {
                    if (object != null) {
                        try {
                            callback.onSuccess(object);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        callback.onBadResponse();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    callback.onErrorResponse(volleyError.toString());
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    return headersMap;
                }
                @Override
                protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                    try {
                        String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                        return Response.success(new JSONObject(jsonString),
                                HttpHeaderParser.parseCacheHeaders(response));

                    } catch (UnsupportedEncodingException e) {
                        return Response.error(new ParseError(e));

                    } catch (JSONException je) {
                        return Response.error(new ParseError(je));
                    }

                }
            };
            request.setTag(mTagForRequest);
            request.setRetryPolicy(new DefaultRetryPolicy(SERVICE_CALL_INITIAL_TIMEOUT, SERVICE_CALL_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            MyApplication.getInstance().getRequestQueue().add(request);

        }
    }

    public void callServiceForString(int requestMethod, String url, final JSONObject bodyObject, final HashMap<String, String> headersMap, final BaseServiceCallBack callback) throws AuthFailureError {


        if (callback != null) {
            mRequestQueue = MyApplication.getInstance().getRequestQueue();
            mRequestQueue.getCache().clear();
            StringRequest request = new StringRequest(requestMethod, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response != null) {
                        try {
                            callback.onSuccess(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        callback.onBadResponse();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (error != null) {
                        callback.onErrorResponse(error.toString());
                    }
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    return headersMap;
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    byte[] sendData = new byte[bodyObject.toString().length()];
                    try {
                        sendData = bodyObject.toString().getBytes("utf-8");
                    } catch (UnsupportedEncodingException e) {
                        Log.d("UnsupportedEncoding", e.toString());
                    }
                    return sendData;
                }
            };

            request.setTag(mTagForRequest);
            request.setRetryPolicy(new DefaultRetryPolicy(SERVICE_CALL_INITIAL_TIMEOUT, SERVICE_CALL_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            MyApplication.getInstance().getRequestQueue().add(request);
        }
    }

    /**
     * Calls Service For Returning {@link JSONArray}, JSONException needs to be handled
     *
     * @param requestMethod type of Service Request(Get, POST etc)
     * @param bodyObject    {@link JSONArray} containing body of request
     * @param headersMap    {@link HashMap} containing headers for request
     * @param callback      {@link BaseServiceCallBack} interface for handling data returned
     * @throws JSONException Needs to be handled by caller
     */
    public void callServiceForArray(int requestMethod, String url, JSONArray bodyObject, final HashMap<String, String> headersMap, final BaseServiceCallBack callback) throws JSONException {

        if (callback != null) {
            mRequestQueue = MyApplication.getInstance().getRequestQueue();
            mRequestQueue.getCache().clear();

            JsonArrayRequest request = new JsonArrayRequest(requestMethod, url, bodyObject, new Response.Listener<JSONArray>() {

                @Override
                public void onResponse(JSONArray response) {
                    if (response != null) {
                        try {
                            callback.onSuccess(response.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        callback.onBadResponse();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (error != null) {
                        callback.onErrorResponse(error.toString());
                    }
                }
            });

            request.setTag(mTagForRequest);
            request.setRetryPolicy(new DefaultRetryPolicy(SERVICE_CALL_INITIAL_TIMEOUT, SERVICE_CALL_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            MyApplication.getInstance().getRequestQueue().add(request);
        }
    }

    public void callServiceArray(int requestMethod, String url, JSONObject bodyObject, final HashMap<String, String> headersMap, final BaseServiceCallBack callback) throws JSONException {
        if (callback != null) {
            mRequestQueue = MyApplication.getInstance().getRequestQueue();
            mRequestQueue.getCache().clear();

            JsonRequest<JSONArray> request = new JsonRequest<JSONArray>(requestMethod, url, bodyObject.toString(), new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    if (response != null) {
                        try {
                            callback.onSuccess(response.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        callback.onBadResponse();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    callback.onErrorResponse(error.toString());
                }
            }) {

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    return headersMap;
                }

                @Override
                protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
                    try {
                        String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                        return Response.success(new JSONArray(jsonString),
                                HttpHeaderParser.parseCacheHeaders(response));

                    } catch (UnsupportedEncodingException e) {
                        return Response.error(new ParseError(e));

                    } catch (JSONException je) {
                        return Response.error(new ParseError(je));
                    }

                }
            };

            request.setTag(mTagForRequest);
            request.setRetryPolicy(new DefaultRetryPolicy(SERVICE_CALL_INITIAL_TIMEOUT, SERVICE_CALL_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            MyApplication.getInstance().getRequestQueue().add(request);
        }
    }





}


