package com.example.oscarthorsson.remember;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SavedListsActivity extends AppCompatActivity {

    // skapa en ReminderSStore
    // be den om en lista mwd ReminderList-objekt
    // slång i den listam i "new ArrayAdapter"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FakeReminderStore goran = new FakeReminderStore ();
        List<ReminderList> test = goran.getReminders();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_lists);
        ListView listView =(ListView) findViewById(R.id.items);
        ArrayAdapter adapter = new ArrayAdapter<ReminderList>(this,android.R.layout.simple_list_item_1, test);
        listView.setAdapter(adapter); }

    public void showSavedLists(){


    }

    //public void inItemClick(AdapterView <> parent, final View view, int position, long id){}
}


