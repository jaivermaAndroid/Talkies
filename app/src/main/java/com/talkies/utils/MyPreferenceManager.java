package com.talkies.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class MyPreferenceManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "talkies-welcome";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    private static final String USER_TOKEN = "USER_TOKEN";
    private static final String COOKIE = "Cookie";
    private static final String NOTIFICATION_ENABLED = "NOTIFICATION_ENABLED";

    public MyPreferenceManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public void setCookies(String cookie) {
        editor.putString(COOKIE, cookie);
        editor.commit();
    }

    public String getCookies() {
        return pref.getString(COOKIE, "");
    }



    public void setNotificationEnable(boolean notificationEnable) {
        editor.putBoolean(NOTIFICATION_ENABLED, notificationEnable);
        editor.commit();
    }

    public boolean getNotificationEnable() {
        return pref.getBoolean(NOTIFICATION_ENABLED, true);
    }


    public void setUserToken(String userToken) {
        editor.putString(USER_TOKEN, userToken);
        editor.commit();
    }

    public String getUserToken() {
        return pref.getString(USER_TOKEN, "");
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }
}
