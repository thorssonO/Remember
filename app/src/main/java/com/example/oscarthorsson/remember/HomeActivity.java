package com.example.oscarthorsson.remember;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.Toolbar;

public class HomeActivity extends AppCompatActivity {

    private Button newReminders;
    private Button savedReminders;
    private Button settings;
    //private Context context;
    private EditText text;

    public void onClickNewRem(){
        newReminders = findViewById(R.id.newReminders);
        newReminders.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent newRemindersIntent = new Intent(HomeActivity.this, AddRemActivity.class);
                startActivity(newRemindersIntent);
            }
        });
    }

    public void onClickSavedReminders(){
        savedReminders = findViewById(R.id.savedReminders);
        savedReminders.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent savedRemindersIntent = new Intent(HomeActivity.this, SavedListsActivity.class);
                startActivity(savedRemindersIntent);
            }
        });
    }

    public void onClickSettings(){
        settings = findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent savedRemindersIntent2 = new Intent(HomeActivity.this, SettingsActivity.class);
                startActivity(savedRemindersIntent2);
            }
        });
    }

        //Ovan skall (?) användas för bakåt och settingsikon i en ActionBar

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_home);
            onClickNewRem();
            onClickSavedReminders();
            onClickSettings();
            //Toolbar settingsToolbar = (Toolbar) findViewById(R.id.settings_toolbar);
            //setSupportActionBar(settingsToolbar);

           /* BroadcastReceiver broadcastReceiver = new WifiBroadcastReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION);
            context.registerReceiver(broadcastReceiver, intentFilter);*/
    }

    protected void onStart() {
        super.onStart();

        BroadcastReceiver broadcast = new WifiBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        //WifiBroadcastReceiver broad = new WifiBroadcastReceiver();

        broadcast.onReceive(this, getIntent());
        intentFilter.addAction(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION);
        this.registerReceiver(broadcast, intentFilter);
    }
}
