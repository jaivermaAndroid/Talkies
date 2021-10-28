package com.talkies.activities;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.talkies.R;
import com.talkies.callbacks.ProfileDetailCallBack;
import com.talkies.managers.ProfileDetailManager;
import com.talkies.model.connecttv.ConnectTv;
import com.talkies.utils.GeneralMethods;
import com.talkies.utils.MyPreferenceManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

public class ConnectTvActivity extends AppCompatActivity implements  View.OnClickListener , ProfileDetailCallBack {


    final static String DATA_FLAG = "DATA_FLAG";
    LinearLayoutManager linearLayoutManager;
    RecyclerView mFAQList;
    ProfileDetailCallBack mProfileDetailCallBack;
    GeneralMethods mGeneralMethods;
    Gson gson;
    EditText mConnectCode;
    AppCompatButton mConnectButton;
    TextView tv_connected_tv_title;
    LinearLayout mLinearLayoutNotConnected,mLinearLayoutConnectedDevices;
    public static Toolbar toolAppBar;
    // Current playback position (in milliseconds).

    // Tag for the instance state bundle.
    public static TextView toolBarText;
    MyPreferenceManager prefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_connect_tv);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        toolAppBar = findViewById(R.id.topAppBar);
        toolBarText = findViewById(R.id.tv_toolbar_title);
        prefManager= new MyPreferenceManager(this);
        setSupportActionBar(toolAppBar);
        //toolbar.setTitle("Home");
        toolBarText.setText("Connect TV");
        toolAppBar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_back));
        toolAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                finish();
            }
        });
        gson = new Gson();
        mGeneralMethods = new GeneralMethods(this,this);
        mProfileDetailCallBack = this;
        mConnectButton=findViewById(R.id.buttonConnectTVWithCode);
        mLinearLayoutConnectedDevices=findViewById(R.id.connectedDevices);
        mLinearLayoutNotConnected=findViewById(R.id.notConnected);
        tv_connected_tv_title=findViewById(R.id.tv_connected_tv_title);

        mConnectCode=findViewById(R.id.connectTvCode);
        mConnectButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonConnectTVWithCode:

                if(mConnectCode.getText().toString()!=null&& !mConnectCode.getText().toString().equalsIgnoreCase(""))
                {
                    new ProfileDetailManager(this, mProfileDetailCallBack,prefManager).connectTV(mGeneralMethods, mConnectCode.getText().toString());

                }else {
                    Toast.makeText(this,"Please enter the code",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onSuccessFAQ(JSONArray result) {

    }

    @Override
    public void onSuccessFAQ(String result) {

    }

    @Override
    public void onFailureFAQ() {

    }

    @Override
    public void onSuccessUpdateName(JSONObject result) {

    }

    @Override
    public void onSuccessUpdateName(JSONArray result) {

    }

    @Override
    public void onSuccessUpdateName(String result) {

    }

    @Override
    public void onFailureUpdateName() {

    }

    @Override
    public void onSuccessIssueList(JSONArray result) {

    }

    @Override
    public void onSuccessIssueList(String result) {

    }

    @Override
    public void onFailureIssueList() {

    }

    @Override
    public void onSuccessRentalDetailsList(JSONArray result) {

    }

    @Override
    public void onSuccessRentalDetailsList(JSONObject result) {

    }

    @Override
    public void onSuccessRentalDetailsList(String result) {

    }

    @Override
    public void onFailureRentalDetailsList() {

    }

    @Override
    public void onSuccessSubmitQuery(JSONObject result) {

    }

    @Override
    public void onFailSubmitQuery() {

    }

    @Override
    public void onSuccessSubscriptionList(JSONArray result) {

    }

    @Override
    public void onSuccessSubscriptionList(JSONObject result) {

    }

    @Override
    public void onSuccessSubscriptionList(String result) {

    }

    @Override
    public void onFailureSubscriptionList() {

    }

    @Override
    public void onLogoutSuccess() {

    }

    @Override
    public void onLogoutFailure() {

    }

    @Override
    public void onSuccessRazorKeyResult(JSONObject result) {

    }

    @Override
    public void onFailureRazorKeyResult() {

    }

    @Override
    public void onSuccessConnectTv(JSONObject result) {
        Type collectionType = new TypeToken<ConnectTv>(){}.getType();

        ConnectTv connectTv = gson.fromJson(result.toString(), collectionType);
        if(result!=null)
        {
            try {
                JSONObject dataObj=  result.getJSONObject("data");
                JSONObject device=  dataObj.getJSONObject("device");
                String deviceName=device.getString("name");
                String deviceId=device.getString("id");
                tv_connected_tv_title.setText(deviceName);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        mLinearLayoutNotConnected.setVisibility(View.GONE);

        mLinearLayoutConnectedDevices.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccessConnectTv(String result) {

        if(result!=null)
        {
            try {
                JSONObject resultObje=  new JSONObject(result);

                JSONObject dataObj=  resultObje.getJSONObject("data");
                dataObj.getString("device");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onFailure() {

    }
}
