package com.sample.gpd.myfavoritewine.service.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by kauesantoja on 03/10/2015.
 */
public class NetworkUtil {

    public static boolean isConnectionAvailable (Context context){
        boolean result = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getActiveNetworkInfo() != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            result = networkInfo != null && networkInfo.isConnectedOrConnecting();
        }
        return result;
    }
}
