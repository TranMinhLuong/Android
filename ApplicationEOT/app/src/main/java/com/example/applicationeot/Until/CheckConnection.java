package com.example.applicationeot.Until;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class CheckConnection {
    public static boolean NetworkConnection(Context context){
        boolean ConnectionWifi = false;
        boolean ConnectionMobile = false;

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();
        for (NetworkInfo info : networkInfos){
            if(info.getTypeName().equalsIgnoreCase("WIFI"));
                if (info.isConnected())
                    ConnectionWifi = true;
            if (info.getTypeName().equalsIgnoreCase("MOBILE"));
                if (info.isConnected())
                    ConnectionMobile = true;
        }
        return ConnectionWifi || ConnectionMobile;
    }
    public static void Toast_Connection(Context context, String alarm){
        Toast.makeText(context, alarm, Toast.LENGTH_SHORT).show();
    }
}
