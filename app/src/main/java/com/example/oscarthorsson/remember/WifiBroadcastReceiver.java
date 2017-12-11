package com.example.oscarthorsson.remember;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.widget.EditText;

/**
 * Created by Oscar Thorsson on 2017-12-07.
 */

public class WifiBroadcastReceiver extends BroadcastReceiver {

    //private Context context;
    private boolean connected = false;
    private String macAddress;
    private String bssid;
    private EditText text;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (WifiManager.SUPPLICANT_STATE_CHANGED_ACTION .equals(action)) {
            SupplicantState state = intent.getParcelableExtra(WifiManager.EXTRA_NEW_STATE);

            if (SupplicantState.isValidState(state)
                    && state == SupplicantState.COMPLETED) {
                //connected = checkConnectedToWifi();
            }
        }
    }

    /*private boolean checkConnectedToWifi() {

        macAddress = "Mac-adressen";
        WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifi = wifiManager.getConnectionInfo();

        if (wifi != null) {

            bssid = wifi.getBSSID();
            //Sparar macadressen i strängen
            connected = macAddress.equals(bssid);
        }
        System.out.println("Macadressen är: " + bssid);
        return connected;
    }*/
}


