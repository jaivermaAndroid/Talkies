package com.talkies.callbacks;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public interface BaseServiceCallBack {
    void onSuccess(JSONObject result) throws JSONException;
    void onSuccess(JSONArray result) throws JSONException;
    void onSuccess(String result) throws JSONException;
    void onErrorResponse(String errorString);

    void onBadResponse();

    void getError(VolleyError error);
}
