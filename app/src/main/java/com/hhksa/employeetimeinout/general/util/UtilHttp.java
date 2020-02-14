package com.hhksa.employeetimeinout.general.util;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class UtilHttp {

    public static boolean isNetworkAvailable(Activity context) {
        try {
            ConnectivityManager cm = (ConnectivityManager)
                    context.getSystemService(context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            return (networkInfo != null && networkInfo.isConnected());
        } catch (Exception ex) {
            return false;
        }
    }
}
