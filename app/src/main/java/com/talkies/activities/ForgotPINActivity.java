package com.talkies.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.hbb20.CountryCodePicker;
import com.talkies.R;
import com.talkies.utils.AppURLs;
import com.talkies.utils.GeneralMethods;
import com.talkies.utils.MyPreferenceManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ForgotPINActivity extends AppCompatActivity implements View.OnClickListener {

    EditText mEdtPhoneSignUp;
    Long number;
    CountryCodePicker ccp;
    String countryCode;
    Dialog mProgressDialog;
    MyPreferenceManager myPreferenceManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myPreferenceManager = new MyPreferenceManager(this);
        mProgressDialog = new Dialog(this, R.style.ProgressBarTheme);
        mProgressDialog.setCancelable(false);

        setContentView(R.layout.activity_signup);
        ccp = findViewById(R.id.country_code_picker);
        findViewById(R.id.tv_login).setOnClickListener(this);
        findViewById(R.id.buttonSignup).setOnClickListener(this);
        mEdtPhoneSignUp= findViewById(R.id.editTextPhoneSignUp);
        findViewById(R.id.imageView_info).setOnClickListener(this);
        ccp.getSelectedCountryCode();
    }

    private void signUP(Integer phone) {
        Intent mSignUp= new Intent(ForgotPINActivity.this, OTPVerificationActivity.class);
        mSignUp.putExtra("phone",phone);
        startActivity(mSignUp);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSignup:
                if(mEdtPhoneSignUp.getText().toString()!=null)
                {
                    number = Long.parseLong(mEdtPhoneSignUp.getText().toString());
                    countryCode = ccp.getSelectedCountryCode();
                    if(GeneralMethods.isNetworkAvailable(this)) {
                        signupToApp();
                    }
                }
                break;
            case R.id.tv_login:
                Intent mLogin= new Intent(ForgotPINActivity.this, LoginActivity.class);
                startActivity(mLogin);
                break;
            case R.id.imageView_info:
                Intent mInfo = new Intent(ForgotPINActivity.this, LoginSignUpInfoActivity.class);
                startActivity(mInfo);
                break;
        }
    }


    private void signupToApp() {
        GeneralMethods mGeneralMethods = new GeneralMethods(this,this);
        mProgressDialog = mGeneralMethods.setupProgressDialog(this, "","");
        mProgressDialog.show();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, AppURLs.signUp, getJsonBodyLogin(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TAG", response.toString());
                        mProgressDialog.dismiss();
                        String message = response.optString("detail");
                        try {

                            String auth_token = response.getString("auth_token");
                            myPreferenceManager.setUserToken(auth_token);
                            Intent mLogin = new Intent(ForgotPINActivity.this, OTPVerificationActivity.class);
                            mLogin.putExtra("phone",number);
                            mLogin.putExtra("auth_token",auth_token);
                            startActivity(mLogin);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
                if(error.toString().equalsIgnoreCase("com.android.volley.AuthFailureError"))
                {
                    Toast.makeText(getApplicationContext(),"Please enter valid Credentials",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"You are already register Please login",Toast.LENGTH_SHORT).show();
                }



                mProgressDialog.dismiss();

            }
        }) { //no semicolon or coma
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json; charset=utf-8");
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);

    }

    private JSONObject getJsonBodyLogin() {

        JSONObject json = new JSONObject();
        try {
            json.put("mobile_number",mEdtPhoneSignUp.getText().toString());
            json.put("country_code","+"+countryCode);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
}