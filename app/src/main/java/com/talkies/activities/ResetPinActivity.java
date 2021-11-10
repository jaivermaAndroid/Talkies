package com.talkies.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.talkies.R;
import com.talkies.utils.AppURLs;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class ResetPinActivity extends AppCompatActivity {
    EditText otp, newPin, reEnterPin;
    AppCompatButton buttonReset;
    String str_otp, str_nPin, str_rPin,mobile,token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pin);

        Log.d("zzz","ResetPinActivity");
        otp = findViewById(R.id.otp);
        newPin = findViewById(R.id.newPin);
        reEnterPin = findViewById(R.id.reEnterPin);
        buttonReset = findViewById(R.id.buttonReset);

        Intent intent= new  Intent();
      mobile=  getIntent().getStringExtra("mobile_no");
      token=  getIntent().getStringExtra("token");

        Log.d("zzz","token:   "+token);

        buttonReset.setOnClickListener(v -> {
            str_otp = otp.getText().toString();
            str_nPin = newPin.getText().toString();
            str_rPin = reEnterPin.getText().toString();

            if (str_otp.trim().equals("")) {
                newPin.setError("Please Enter Otp");
            } else if (str_nPin.trim().equals("")) {
                newPin.setError("Please Enter New Pin");
            } else if (str_rPin.trim().equals("")) {
                reEnterPin.setError("Please Enter Re-Enter Otp");
            }

            if (str_nPin.equals(str_rPin)) {
                ResetPinConfirm_2(token, str_nPin, str_rPin,str_otp);
            }
            else {
                Toast.makeText(getApplicationContext(),"pin dose not match",Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void ResetPin_2(String mobile,String newPin,String rPin) {
        try {

            Log.d("zzz","Mobile :  "+mobile+":   password:  "+newPin);
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("mobile_number",mobile );
            jsonBody.put("password", newPin);
            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURLs.BASE_URL + "auth/login/password/", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("zzz","Response:  "+response);
//                    ResetPinConfirm_2();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return String.format("application/json; charset=utf-8");
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                                requestBody, "utf-8");
                        return null;
                    }
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(ResetPinActivity.this);
            requestQueue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void ResetPinConfirm_2(String token,String pass1,String pass2,String otp) {
        try {

            Log.d("zzz","auth_token :  "+token+":   password 2:  "+reEnterPin+"Password 1   : "+newPin+"Otp  "+otp);
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("auth_token",token );
            jsonBody.put("password2", pass2);
            jsonBody.put("password1", pass1);
            jsonBody.put("token", otp);
            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURLs.BASE_URL + "auth/password_reset/confirm/", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject jsonObject=new JSONObject(response);
                        String message=jsonObject.getString("detail");
                        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                        Intent mLogin = new Intent(ResetPinActivity.this, LoginActivity.class);
                        startActivity(mLogin);
                        finish();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    Log.d("zzz","ResetPinConfirm_2:  "+response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return String.format("application/json; charset=utf-8");
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                                requestBody, "utf-8");
                        return null;
                    }
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(ResetPinActivity.this);
            requestQueue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }




//    private void ResetApi(String pin, EditText newPin, String rPin) {
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURLs.BASE_URL+"auth/login/password/",
//                response -> {
//                    try {
//                        JSONObject jsonObject = new JSONObject(response);
//                        String checkstatus = jsonObject.optString("status");
//                        String message = jsonObject.optString("message");
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }, volleyError -> {
//            Log.e("alert", "error" + volleyError);
//            Toast.makeText(ResetPinActivity.this, ""+volleyError, Toast.LENGTH_SHORT).show();
//        }) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("mobile_number", pin);
//                params.put("password", nPin);
//                params.put("device_id", rPin);
//                return params;
//            }
//
//            @Override
//            public Map<String, String> getHeaders() {
//                HashMap<String, String> headers = new HashMap<>();
//                headers.put("accept", "application/json");
//                return headers;
//            }
//        };
//        RequestQueue requestQueue = Volley.newRequestQueue(ResetPinActivity.this);
//        requestQueue.add(stringRequest);
//    }

}