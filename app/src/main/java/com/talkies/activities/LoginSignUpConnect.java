package com.talkies.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.talkies.R;

public class LoginSignUpConnect  extends AppCompatActivity implements View.OnClickListener {



    ImageView ivBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_profile);

        //findViewById(R.id.imageView_back_sing_up_login).setOnClickListener(this);




    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.imageView_back_sing_up_login:
//                this.finish();
//                break;
        }
    }
}