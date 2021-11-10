package com.talkies.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.talkies.R;
import com.talkies.utils.AppURLs;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ResetPinMobileActivity extends AppCompatActivity {
    String mobile;
    EditText edMobileno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pin_mobile);
        edMobileno = findViewById(R.id.editTextPhoneSignUp);

        Log.d("zzz", "RestPinMobileactivity");
        mobile = edMobileno.getText().toString();
        Click();
    }

    private void Click() {
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(v -> finish());
        AppCompatButton next = findViewById(R.id.buttonReset);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if (mobile.equals("") )
//                {
//
//                    Toast.makeText(getApplicationContext(),"Mobile number cannot be empty",Toast.LENGTH_SHORT).show();
//                }
//                else
                mobile = edMobileno.getText().toString();
                if (mobile.length() <10)
                {
                    Toast.makeText(getApplicationContext(),"Invalied mobile number",Toast.LENGTH_SHORT).show();
                }
                else {

                ResetPin(mobile);
//                sendDataToBackend();

                }
            }
        });
    }


    private void ResetPin(String mobile_) {
        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("mobile_number", mobile_);
//            jsonBody.put("password", "123456");

            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURLs.BASE_URL + "auth/password_reset/", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("zzz","Response:  "+response);

                    Toast.makeText(getApplicationContext(),"OTP sent to your registered mobile number",Toast.LENGTH_SHORT).show();
                    try {
                        JSONObject jsonObjecttoken=new JSONObject(response);
                        Intent intent = new Intent(getApplicationContext(), ResetPinActivity.class);
                        intent.putExtra("mobile_no", mobile);
                        intent.putExtra("token", jsonObjecttoken.getString("auth_token"));

                        Log.d("zzz","Mobile11:   "+mobile);
                        startActivity(intent);
                        finish();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

//                    Intent intent = new Intent();

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
            RequestQueue requestQueue = Volley.newRequestQueue(ResetPinMobileActivity.this);
            requestQueue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }





}