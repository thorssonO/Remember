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
        FakeReminderStore savedL = FakeReminderStore.getInstance();
        //List <ReminderList> savedLil = savedL.getReminders();
        System.out.println("Before fetching data...");
        dataHeader = new ArrayList<>();
        ReminderDBHandler rd = new ReminderDBHandler(this);
        System.out.println("Listan från db: " + rd.getReminders());
        Map<String, List<ReminderItem>> map = rd.getReminders();
        for(String title : map.keySet()) {
            ReminderList remList = new ReminderList(title,map.get(title), null);
            theHashMap.put(title, remList);
            dataHeader.add(title);
            System.out.println("hej");
            System.out.println("hashmap: " + theHashMap);
            System.out.println("dataheader: " + dataHeader);
        }
        view();
    }

    public void view(){
        System.out.println("hashmap: " + theHashMap);
        System.out.println("dataheader: " + dataHeader);
        ExpandableListView listView = findViewById(R.id.test);
        listAdapter = new ExpandableListViewAdapter(this, dataHeader, theHashMap);
        listView.setAdapter(listAdapter);
    }
}

//LÅG NEDANFÖR FOR-LOOPEN I ONCREATE()
/*
       for(int i= 0; i<savedLil.size(); i++){
           List<ReminderList> heeD = new ArrayList<>();
           heeD.add(savedLil.get(i));
           dataHeader.add(savedL.toStringTitel());
           theHashMap.put(heeD.toString(), savedLil);
          theHashMap.put(dataHeader.get(i),heeD);
       }
*/