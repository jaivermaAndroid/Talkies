package com.talkies.activities;

import static com.talkies.utils.Constants.STORAGE_USER_DETAILS_RESPONSE;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

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
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hbb20.CountryCodePicker;
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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "Login";
    CountryCodePicker ccp;
    int RC_SIGN_IN = 100;
    TextView tvSignUp;
    EditText mEdtPhoneLogin, mEdtPIN;
    Long number;
    ImageView ivInfo;
    CallbackManager callbackManager;
    MyPreferenceManager myPreferenceManager;
    Button buttonLoginOption, buttonLogin;
    TextView mForgotPIN;
    Dialog mProgressDialog;
    Boolean loginWithOTP = false;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_pin);
        ccp = findViewById(R.id.country_code_picker);
        tvSignUp = findViewById(R.id.tv_signup);
        gson = new Gson();
        mEdtPhoneLogin = findViewById(R.id.editTextPhoneLogin);
        mEdtPIN = findViewById(R.id.editTextPIN);
        ivInfo = findViewById(R.id.imageView_info);
        myPreferenceManager = new MyPreferenceManager(this);
        mProgressDialog = new Dialog(this, R.style.ProgressBarTheme);
        mProgressDialog.setCancelable(false);

        findViewById(R.id.tv_signup).setOnClickListener(this);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(this);
        findViewById(R.id.imageView_info).setOnClickListener(this);
        buttonLoginOption = findViewById(R.id.buttonLoginWithOption);
        mForgotPIN = findViewById(R.id.tv_forgot_pin);
        buttonLogin.setBackground(getDrawable(R.drawable.btn_bg_normal));
        buttonLoginOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonLogin.setBackground(getDrawable(R.drawable.btn_bg_normal));
                if (buttonLoginOption.getText().equals("Login with OTP")) {
                    loginWithOTP = true;
                    mForgotPIN.setVisibility(View.GONE);
                    mEdtPIN.setText("");
                    mEdtPhoneLogin.setText("");
                    mEdtPIN.setVisibility(View.GONE);
                    buttonLoginOption.setText("Login With PIN");
                } else {
                    loginWithOTP = false;
                    mEdtPIN.setText("");
                    mEdtPhoneLogin.setText("");
                    mForgotPIN.setVisibility(View.VISIBLE);
                    mEdtPIN.setVisibility(View.VISIBLE);
                    buttonLoginOption.setText("Login With OTP");
                }

            }
        });

        mForgotPIN.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(),ResetPinMobileActivity.class));
        });

        mEdtPhoneLogin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.length() > 1) {
                    buttonLogin.setBackground(getDrawable(R.drawable.btn_bg_normal));

                }
                if (loginWithOTP && s.length() > 1) {
                    buttonLogin.setBackground(getDrawable(R.drawable.bg_btn_focused));
                } else {
                    buttonLogin.setBackground(getDrawable(R.drawable.btn_bg_normal));
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mEdtPIN.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                if (s.length() > 1 && mEdtPhoneLogin.getText().length() == 10) {

                    buttonLogin.setBackground(getDrawable(R.drawable.bg_btn_focused));
                } else {
                    buttonLogin.setBackground(getDrawable(R.drawable.btn_bg_normal));
                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_signup:
                signUP();
            case R.id.buttonLogin:
                if (mEdtPhoneLogin.getText().toString() != null && !mEdtPhoneLogin.getText().toString().equalsIgnoreCase("")) {
                    number = Long.parseLong(mEdtPhoneLogin.getText().toString());

                    if (GeneralMethods.isNetworkAvailable(this)) {
                        loginToApp();
                    } else {
                        Toast.makeText(this, "Please Connect to Network", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
            case R.id.imageView_info:
                Intent mInfo = new Intent(LoginActivity.this, LoginSignUpInfoActivity.class);
                startActivity(mInfo);
                break;

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void signUP() {
        Intent mSignUp = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(mSignUp);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {

        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            updateUI(account);
        } catch (ApiException e) {
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
        }
    }

    private void updateUI(GoogleSignInAccount account) {

        String auth_token = account.getIdToken();
        getTokenAfterGoogleFacebooklogin(auth_token);
    }

    private JSONObject getRequestBodyForGetToken(String accessToken) {
        JSONObject object = new JSONObject();
        try {
            JSONObject objectToken = new JSONObject();
            JSONObject objectUser = new JSONObject();
            JSONObject objectTokenManager = new JSONObject();

            objectTokenManager.put("accessToken", accessToken);
            objectUser.put("stsTokenManager", objectTokenManager);
            objectToken.put("user", objectUser);
            object.put("token", objectToken);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return object;
    }

    public void getTokenAfterGoogleFacebooklogin(String token) {

        GeneralMethods mGeneralMethods = new GeneralMethods(this, this);
        mProgressDialog = mGeneralMethods.setupProgressDialog(this, "", "");
        mProgressDialog.show();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, AppURLs.getToken, getRequestBodyForGetToken(token),
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

                            boolean isProfileCompete = response.getBoolean("is_profile_complete");
                            if (isProfileCompete) {
                                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(i);
                                finish();
                            } else {
                                Intent i = new Intent(LoginActivity.this, CompleteProfileActivity.class);
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
                mProgressDialog.dismiss();
                if (error.toString().equalsIgnoreCase("com.android.volley.AuthFailureError")) {
                    Toast.makeText(getApplicationContext(), "Please enter valid Credentials", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
                //mProgressDialog.dismiss();

            }
        }) { //no semicolon or coma
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                //params.put("Cookie",myPreferenceManager.getCookies());

                return params;
            }

            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                try {
                    String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));

                    String cookies = response.headers.get("Set-Cookie");
                    String[] parts = cookies.split(";");
                    Constants.SESSION_ID = parts[0];

                    myPreferenceManager.setCookies(parts[0]);
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

    private void loginToApp() {
        GeneralMethods mGeneralMethods = new GeneralMethods(this, this);
        mProgressDialog = mGeneralMethods.setupProgressDialog(this, "", "");
        mProgressDialog.show();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, AppURLs.login, getJsonBodyLogin(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TAG", response.toString());
                        mProgressDialog.dismiss();
                        try {

                            String auth_token = response.getString("auth_token");
                            myPreferenceManager.setUserToken(auth_token);
                            Intent mLogin = new Intent(LoginActivity.this, OTPVerificationActivity.class);
                            mLogin.putExtra("phone", number);
                            mLogin.putExtra("auth_token", auth_token);
                            startActivity(mLogin);
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
            if (loginWithOTP) {
                json.put("mobile_number", number);

            } else {
                json.put("mobile_number", number);
                json.put("password", mEdtPIN.getText().toString());
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }


    private JSONObject getRequestBody() {
        JSONObject object = new JSONObject();


        return object;
    }

    private HashMap<String, String> getRequestHeaders() {
        HashMap<String, String> headersForRegisterUser = new HashMap<>();
        headersForRegisterUser.put("Content-Type", "application/x-www-form-urlencoded");
        //headersForRegisterUser.put("auth_token", "8db1a525-cb84-40c5-817a-d52e6bc35666");

        return headersForRegisterUser;
    }
}







