package com.example.oscarthorsson.remember;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.media.CamcorderProfile.get;


public class SavedListsActivity extends AppCompatActivity {
    ExpandableListAdapter listAdapter;
     ArrayList <String> dataHeader;
     HashMap<String, List<ReminderList>> theHashMap = new HashMap<String, List<ReminderList>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_lists);
        FakeReminderStore savedL = FakeReminderStore.getInstance();
        List <ReminderList> savedLil = savedL.getReminders();
        dataHeader= new ArrayList<>();


       for(int i= 0; i<savedLil.size(); i++){
           List<ReminderList> heeD = new ArrayList<>();
           heeD.add(savedLil.get(i));
           dataHeader.add(savedL.toStringTitel());
           theHashMap.put(heeD.toString(), savedLil);
          theHashMap.put(dataHeader.get(i),heeD);
       }

view();
    }

public void view(){

    ExpandableListView listView = findViewById(R.id.test);
    listAdapter = new ExpandableListViewAdapter(this,dataHeader,theHashMap);
    listView.setAdapter(listAdapter);


}
    }




