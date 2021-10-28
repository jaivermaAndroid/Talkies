package com.talkies.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.talkies.R;

public class LoginSignUpInfoActivity extends Activity implements View.OnClickListener {

    ImageView ivBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup_info);

        ivBack = findViewById(R.id.imageView_back_sing_up_login_info);
        ivBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView_back_sing_up_login_info:
                finish();
                break;
        }
    }
}