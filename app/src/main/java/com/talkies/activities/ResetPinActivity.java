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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.talkies.R;
import com.talkies.utils.AppURLs;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ResetPinActivity extends AppCompatActivity {
    EditText enterPin, newPin, reOtp;
    AppCompatButton buttonReset;
    String pin, nPin, rPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pin);

        Log.d("zzz","ResetPinActivity");
        enterPin = findViewById(R.id.otp);
        newPin = findViewById(R.id.newPin);
        reOtp = findViewById(R.id.reOtp);
        buttonReset = findViewById(R.id.buttonReset);

        Intent intent= new  Intent();
        intent.getStringExtra("");
        buttonReset.setOnClickListener(v -> {
            pin = enterPin.getText().toString();
            nPin = newPin.getText().toString();
            rPin = reOtp.getText().toString();

            if (pin.trim().equals("")) {
                newPin.setError("Please Enter Otp");
            } else if (nPin.trim().equals("")) {
                newPin.setError("Please Enter New Pin");
            } else if (rPin.trim().equals("")) {
                reOtp.setError("Please Enter Re-Enter Otp");
            }
            ResetApi(pin, newPin, rPin);
        });
    }

    private void ResetApi(String pin, EditText newPin, String rPin) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppURLs.BASE_URL+"auth/login/password/",
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String checkstatus = jsonObject.optString("status");
                        String message = jsonObject.optString("message");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, volleyError -> {
            Log.e("alert", "error" + volleyError);
            Toast.makeText(ResetPinActivity.this, ""+volleyError, Toast.LENGTH_SHORT).show();
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("mobile_number", pin);
                params.put("password", nPin);
                params.put("device_id", rPin);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("accept", "application/json");
                return headers;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(ResetPinActivity.this);
        requestQueue.add(stringRequest);
    }

}