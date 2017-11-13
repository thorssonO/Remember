package com.example.oscarthorsson.remember;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    private Button newReminders;

    public void onClickNewRem(){
        newReminders = (Button)findViewById(R.id.newReminders);
        newReminders.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent newRemindersIntent = new Intent(HomeActivity.this, AddRemActivity.class);
                startActivity(newRemindersIntent);
            }
        });
    }

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_home);
            onClickNewRem();
            //Toolbar settingsToolbar = (Toolbar) findViewById(R.id.settings_toolbar);
            //setSupportActionBar(settingsToolbar);
    }
}
