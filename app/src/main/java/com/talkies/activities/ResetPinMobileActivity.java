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
                mobile = edMobileno.getText().toString();
                ResetPin_2(mobile);
//                sendDataToBackend();
//                Intent intent = new Intent();
//                intent = new Intent(getApplicationContext(),ResetPinActivity.class);
//                intent.putExtra("monileno","mobile");
//                startActivity(intent);
            }
        });
    }



    private void ResetPin_2(String mobile) {
        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("mobile_number", "9792318592");
            jsonBody.put("password", "123456");
            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURLs.BASE_URL + "auth/login/password/", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                Log.d("zzz","Response:  "+response);
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