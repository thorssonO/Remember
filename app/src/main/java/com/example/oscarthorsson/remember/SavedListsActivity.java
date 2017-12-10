package com.example.oscarthorsson.remember;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.util.List;

public class SavedListsActivity extends AppCompatActivity {
    private final String LOG_TAG = FakeReminderStore.class.getName();
    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List <ReminderList> dataHeader;
    private SparseArray <ReminderList> groups = new SparseArray<ReminderList>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_lists);

        view();
        createData();
    }


public void view(){

    FakeReminderStore goran = FakeReminderStore.getInstance();
    List<ReminderList> test = goran.getReminders();
    ExpandableListView listView = findViewById(R.id.test);
    listAdapter = new ExpandableListViewAdapter(this,groups);
    listView.setAdapter(listAdapter);

    }
    public void createData() {
        for (int j = 0; j < 5; j++) {
            FakeReminderStore itemsExpandable = FakeReminderStore.getInstance();
            //Group group = new Group("test"+j); {
            }
            for (int i = 0; i < 5; i++) {
                //Group;
            }
            //groups.append(j, group);
        }
    }



