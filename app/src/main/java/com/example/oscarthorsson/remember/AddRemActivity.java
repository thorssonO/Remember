package com.example.oscarthorsson.remember;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AddRemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rem);
        //Hämta ut knappen add item
        // sätt en onClick som programmatiskt skapar en EditText
        // och "addar" den till denna activitys layout
        // (ni rekommenderas att byta layout till linear och vertical för
        // det är mkt enklare)


        // Hämta ut add_reminder_button
        // sätt en onClick-grej som hämtar ut name och alla
        // items - dvs hämta och loopa över alla EditText som lagts till av
        // add item-knappen, läs texten och skapa för varje en ReminderItem.
        // Sedan skapar ni en ReminderList som tar som argument name och listan
        // och alarmdate (ni måste lägga till en widget för datum).

        //Skicka dem till en singleton (senare - lagra den i databas i stället)

    }
}
