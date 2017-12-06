package com.example.oscarthorsson.remember;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SavedListsActivity extends AppCompatActivity {
    private final String LOG_TAG = FakeReminderStore.class.getName();
    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List <ReminderList> dataHeader;
    private HashMap<String,List<String>> listHash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_lists);

        view();
    }


public void view(){

    FakeReminderStore goran = FakeReminderStore.getInstance();
    List<ReminderList> test = goran.getReminders();
    ExpandableListView listView = findViewById(R.id.test);
    listAdapter = new ExpandableListViewAdapter(this,test, listHash);
    listView.setAdapter(listAdapter);
    listView.setOnItemClickListener(new ListView.OnItemClickListener(){

    @Override

    public void onItemClick(AdapterView <?> parent, final View view, int position, long id){
        Log.d(LOG_TAG, "item clicked, pos:" + position + " id: " + id);
        Intent appInfo = new Intent(SavedListsActivity.this, OpenListActivity.class);
        startActivity(appInfo);
        }
    });}
}



