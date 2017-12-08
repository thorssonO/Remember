package com.example.oscarthorsson.remember;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

/**
 * Created by Oscar Thorsson on 2017-12-07.
 */

public class WifiBroadcastReceiver extends BroadcastReceiver {

    private Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (WifiManager.SUPPLICANT_STATE_CHANGED_ACTION .equals(action)) {
            SupplicantState state = intent.getParcelableExtra(WifiManager.EXTRA_NEW_STATE);
            if (SupplicantState.isValidState(state)
                    && state == SupplicantState.COMPLETED) {

                boolean connected = checkConnectedToDesiredWifi();
            }
        }
    }

    //Detect if you are connected to a specific network
    private boolean checkConnectedToDesiredWifi() {
        boolean connected = false;

        String desiredMacAddress = "router mac address";

        WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);

        WifiInfo wifi = wifiManager.getConnectionInfo();
        if (wifi != null) {

            // get current router Mac address

            String bssid = wifi.getBSSID();
            connected = desiredMacAddress.equals(bssid);
        }
        return connected;
    }
}


