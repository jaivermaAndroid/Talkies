package com.talkies.activities;

import static com.talkies.utils.Constants.STORAGE_USER_DETAILS_RESPONSE;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.mukesh.OtpView;
import com.talkies.R;
import com.talkies.model.UserDetails;
import com.talkies.utils.AppURLs;
import com.talkies.utils.Constants;
import com.talkies.utils.GeneralMethods;
import com.talkies.utils.MyPreferenceManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import io.paperdb.Paper;

public class OTPVerificationActivity extends AppCompatActivity implements View.OnClickListener {


    TextView mSubtitle,timer,tv_resend_otp;
    Button mBtnVerify;
//    EditText editTextEnterOTP;
    Dialog mProgressDialog;
    GeneralMethods mGeneralMethods;
    MyPreferenceManager prefManager;
    Long number;
    Integer token;
    String auth_token;
    ImageView mIvBack, mIvInfo;
    private Gson gson;
    OtpView editTextEnterOTP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);
        mSubtitle = findViewById(R.id.tv_title_otp_subtitle);
        mIvBack = findViewById(R.id.imageView_back_otp);
        mIvInfo = findViewById(R.id.imageView_info_otp_verification);
        timer = findViewById(R.id.timer);
        tv_resend_otp = findViewById(R.id.tv_resend_otp);
        editTextEnterOTP = findViewById(R.id.otpView);
        prefManager = new MyPreferenceManager(this);
        gson = new Gson();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            number = extras.getLong("phone");
            auth_token = extras.getString("auth_token");
            mSubtitle.setText("A one time verification code has been sent to " + number);
        }

        findViewById(R.id.buttonVerify).setOnClickListener(this);
        mIvBack.setOnClickListener(this);
        mIvInfo.setOnClickListener(this);
        tv_resend_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editTextEnterOTP.getText().toString().equalsIgnoreCase("")) {
                    if (GeneralMethods.isNetworkAvailable(getApplicationContext())) {
                        optVerification();
                    } else {
                        Toast.makeText(getApplicationContext(), "Please Connect to Network", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        OtpView();
    }

   private void OtpView(){
       tv_resend_otp.setVisibility(View.GONE);
       timer.setVisibility(View.VISIBLE);
       new CountDownTimer(30000, 1000) {
           public void onTick(long millisUntilFinished) {
               timer.setText("(" + millisUntilFinished / 1000+")");
               //here you can have your logic to set text to edittext
           }
           public void onFinish() {
               tv_resend_otp.setVisibility(View.VISIBLE);
               timer.setVisibility(View.GONE);
           }

       }.start();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonVerify:
                if (!editTextEnterOTP.getText().toString().equalsIgnoreCase("")) {
                    if (GeneralMethods.isNetworkAvailable(this)) {
                        optVerification();
                    } else {
                        Toast.makeText(this, "Please Connect to Network", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.imageView_back_otp:
                this.finish();
                break;
            case R.id.imageView_info_otp_verification:
                Intent mInfo = new Intent(OTPVerificationActivity.this, LoginSignUpInfoActivity.class);
                startActivity(mInfo);
                break;
            case R.id.tv_resend_otp:

                break;
        }
    }


    private void optVerification() {
        OtpView();
//        tv_resend_otp.setVisibility(View.GONE);
        GeneralMethods mGeneralMethods = new GeneralMethods(this, this);
        mProgressDialog = mGeneralMethods.setupProgressDialog(this, "", "");
        mProgressDialog.show();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, AppURLs.otpVerifyLogin, getJsonBodyLoginOTPVerify(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TAG", response.toString());
                        //mProgressDialog.dismiss();
                        try {

                            String name = response.getString("name");
                            getUserDetails(auth_token);
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
                }
                mProgressDialog.dismiss();

            }
        }) { //no semicolon or coma
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }

            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                try {
                    String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));

                    String cookies = response.headers.get("Set-Cookie");
                    String[] parts = cookies.split(";");
                    Constants.SESSION_ID = parts[0];

                    prefManager.setCookies(parts[0]);
                    Response<JSONObject> header = Response.success(new JSONObject(jsonString),
                            HttpHeaderParser.parseCacheHeaders(response));


                    Log.e("parseNetworkResponse: ", cookies);
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
                            prefManager.setFirstTimeLaunch(false);

                            boolean isProfileCompete = response.getBoolean("is_profile_complete");
                            if (isProfileCompete) {
                                Intent i = new Intent(OTPVerificationActivity.this, MainActivity.class);
                                startActivity(i);
                                finish();
                            } else {
                                Intent i = new Intent(OTPVerificationActivity.this, CompleteProfileActivity.class);
                                i.putExtra("auth_token", auth_token);
                                startActivity(i);
                                finish();
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
                params.put("Cookie", prefManager.getCookies());

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


    private JSONObject getRequestBody() {
        JSONObject object = new JSONObject();


        return object;
    }

    private JSONObject getJsonBodyLoginOTPVerify() {

        Integer otp = Integer.parseInt(editTextEnterOTP.getText().toString());
        JSONObject json = new JSONObject();
        try {
            json.put("auth_token", auth_token);
            json.put("token", otp);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    private HashMap<String, String> getRequestHeaders() {
        HashMap<String, String> headersForRegisterUser = new HashMap<>();
        headersForRegisterUser.put("Content-Type", "application/x-www-form-urlencoded");
        //headersForRegisterUser.put("auth_token", "8db1a525-cb84-40c5-817a-d52e6bc35666");

        return headersForRegisterUser;
    }

}
