package com.talkies.activities;

import static com.talkies.utils.Constants.STORAGE_LOGIN_SUCCESS_RESPONSE;
import static com.talkies.utils.Constants.STORAGE_USER_DETAILS_RESPONSE;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultWithDataListener;
import com.talkies.R;
import com.talkies.fragments.HomeFragment;
import com.talkies.fragments.NotificationFragment;
import com.talkies.fragments.ProfileFragment;
import com.talkies.fragments.SearchFragment;
import com.talkies.model.UserDetails;

import io.paperdb.Paper;


public class MainActivity extends AppCompatActivity implements PaymentResultWithDataListener {
    public static Toolbar toolAppBar;
    public static TextView toolBarText;
    HomeFragment homeFragment;
    SearchFragment searchFragment;
    NotificationFragment notificationFragment;
    ProfileFragment profileFragment;
    UserDetails storageLoginSucessResponses;
    FrameLayout main_FL;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        toolAppBar = findViewById(R.id.topAppBar);
        toolBarText = findViewById(R.id.tv_toolbar_title);
        setSupportActionBar(toolAppBar);
        toolBarText.setText("Talkies");
        homeFragment = new HomeFragment();
        searchFragment = new SearchFragment();
        notificationFragment = new NotificationFragment();
        profileFragment = new ProfileFragment();
        main_FL = findViewById(R.id.main_FL);
        fragmentReplacer(R.id.main_FL, homeFragment, "HomeFragment");
        Paper.book().read(STORAGE_LOGIN_SUCCESS_RESPONSE);
        storageLoginSucessResponses = Paper.book().read(STORAGE_USER_DETAILS_RESPONSE);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_home:
                    fragmentReplacer(R.id.main_FL, homeFragment, "HomeFragment");
                    break;
                case R.id.action_search:
                    fragmentReplacer(R.id.main_FL, searchFragment, "SearchFragment");
                    break;
                case R.id.action_notification:
                    fragmentReplacer(R.id.main_FL, notificationFragment, "NotificationFragment");
                    break;
                case R.id.action_profile:
                    fragmentReplacer(R.id.main_FL, profileFragment, "ProfileFragment");
                    break;
            }
            return true;
        });
    }

    public void fragmentReplacer(int containerId, Fragment fragment, String tag) {
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerId, fragment, tag);
        fragmentTransaction.commit();
    }

    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {

    }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {

    }
    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}