package com.talkies.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.annotation.NonNull;

/**
 * Check network connectivity.
 */
public class Connectivity {

    private Context context;

    public Connectivity(@NonNull final Context c) {
        this.context = c;
    }

    /**
     * Get the network info.
     *
     * @return returns {@link NetworkInfo}.
     */
    private NetworkInfo getNetworkInfo() {
        final ConnectivityManager connectivityManager = (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            return connectivityManager.getActiveNetworkInfo();
        } else {
            return null;
        }
    }

    /**
     * Check if there is any connectivity.
     *
     * @return boolean true if connected else false.
     */
    public boolean isConnected() {
        final NetworkInfo networkInfo = this.getNetworkInfo();
        return ((networkInfo != null) && networkInfo.isConnected());
    }
}
