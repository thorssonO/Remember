package com.example.oscarthorsson.remember;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SavedListsActivity extends AppCompatActivity {
    ExpandableListAdapter listAdapter;
    ArrayList <String> dataHeader;
    HashMap<String, ReminderList> theHashMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_lists);
        dataHeader = new ArrayList<>();
        ReminderDBHandler rd = new ReminderDBHandler(this);
        System.out.println("Listan från db: " + rd.getReminders());
        Map<String, List<ReminderItem>> map = rd.getReminders();
        for(String title : map.keySet()) {
            ReminderList remList = new ReminderList(title,map.get(title), null);
            theHashMap.put(title, remList);
            dataHeader.add(title);
        }
        view();
    }

    public void view(){
        ExpandableListView listView = findViewById(R.id.test);
        listAdapter = new ExpandableListViewAdapter(this, dataHeader, theHashMap);
        listView.setAdapter(listAdapter);
    }
}