package com.talkies.activities;

import static com.talkies.utils.Constants.STORAGE_USER_DETAILS_RESPONSE;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.talkies.R;
import com.talkies.model.UserDetails;
import com.talkies.utils.AppURLs;
import com.talkies.utils.GeneralMethods;
import com.talkies.utils.MyPreferenceManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import io.paperdb.Paper;

public class CompleteProfileActivity extends AppCompatActivity implements View.OnClickListener {


    EditText mFirstName, mEmail;
    Button mBtnCompleteProfile;
    Dialog mProgressDialog;
    String auth_token;
    private Gson gson;
    private MyPreferenceManager myPreferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_profile);
        myPreferenceManager = new MyPreferenceManager(this);
        mProgressDialog = new Dialog(this, R.style.ProgressBarTheme);
        mProgressDialog.setCancelable(false);
        mFirstName = findViewById(R.id.editTextFullNameCompleteProfile);
        mEmail = findViewById(R.id.editTextEmailCompleteProfile);
        mBtnCompleteProfile = findViewById(R.id.buttonCompleteProfile);
        gson = new Gson();
        mBtnCompleteProfile.setOnClickListener(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            auth_token = extras.getString("auth_token");
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonCompleteProfile:
                if (!mEmail.getText().toString().equalsIgnoreCase("") && !mFirstName.getText().toString().equalsIgnoreCase("")) {

                    completeProfile();
                } else {
                    Toast.makeText(this, "Please enter the details", Toast.LENGTH_SHORT).show();
                }


        }
    }


    private void completeProfile() {
        GeneralMethods mGeneralMethods = new GeneralMethods(this, this);
        mProgressDialog = mGeneralMethods.setupProgressDialog(this, "", "");
        mProgressDialog.show();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, AppURLs.completeUserProfile, getJsonBodyCompleteUserProfile(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TAG", response.toString());
                        mProgressDialog.dismiss();
                        try {

                            String is_verified = response.getString("is_verified");
                            getUserDetails(auth_token);

                            Intent i = new Intent(CompleteProfileActivity.this, MainActivity.class);
                            startActivity(i);
                            finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
                if (error.toString().equalsIgnoreCase("com.android.volley.AuthFailureError")) {
                    Toast.makeText(getApplicationContext(), "Please enter valid Credentials", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
                mProgressDialog.dismiss();

            }
        }) { //no semicolon or coma
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Cookie", myPreferenceManager.getCookies());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);

    }


    private JSONObject getJsonBodyCompleteUserProfile() {

        String name = mFirstName.getText().toString();
        String email = mEmail.getText().toString();
        JSONObject json = new JSONObject();
        try {
            json.put("first_name", name);
            json.put("email", email);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    private JSONObject getRequestBody() {
        JSONObject object = new JSONObject();


        return object;
    }

    private void getUserDetails(String auth_token) {


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, AppURLs.getUserDetails, getRequestBody(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TAG", response.toString());
                        mProgressDialog.dismiss();
                        try {

                            // response.getString("auth_token");


                            String jsonFileString = response.toString();
                            Type type = new TypeToken<UserDetails>() {
                            }.getType();
                            UserDetails uiConfig = gson.fromJson(jsonFileString, type);
                            Paper.book().write(STORAGE_USER_DETAILS_RESPONSE, uiConfig);
                            myPreferenceManager.setFirstTimeLaunch(false);

                            boolean isProfileCompete = response.getBoolean("is_profile_complete");
                            if (isProfileCompete) {
                                Intent i = new Intent(CompleteProfileActivity.this, MainActivity.class);
                                startActivity(i);
                                finish();
                            } else {

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
                if (error.toString().equalsIgnoreCase("com.android.volley.AuthFailureError")) {
                    Toast.makeText(getApplicationContext(), "Please enter valid Credentials", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
                mProgressDialog.dismiss();

            }
        }) { //no semicolon or coma
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Cookie", myPreferenceManager.getCookies());

                return params;
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
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);

    }

}