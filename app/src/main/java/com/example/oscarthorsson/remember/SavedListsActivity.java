package com.example.oscarthorsson.remember;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class SavedListsActivity extends AppCompatActivity {
    private ExpandableListAdapter listAdapter;
    private List <ReminderList> dataHeader;
    private HashMap<List<ReminderList>, List<String>> theHashMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_lists);
        ExpandableListView listView = findViewById(R.id.test);
        listAdapter = new ExpandableListViewAdapter(this,dataHeader,theHashMap);
        listView.setAdapter(listAdapter);


    }


    }




