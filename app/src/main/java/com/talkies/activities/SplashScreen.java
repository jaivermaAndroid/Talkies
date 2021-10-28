package com.talkies.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.talkies.R;
import com.talkies.utils.MyPreferenceManager;

public class SplashScreen extends Activity {
    MyPreferenceManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        prefManager = new MyPreferenceManager(getApplicationContext());
        new Handler().postDelayed(() -> {
            if(prefManager.getCookies().equalsIgnoreCase(""))
            {
                Intent i = new Intent(SplashScreen.this, WelcomeActivity.class);
                startActivity(i);
            }else{
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
            }
            finish();
        }, 3000);
    }
}