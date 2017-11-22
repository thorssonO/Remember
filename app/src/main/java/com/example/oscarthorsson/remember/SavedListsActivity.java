package com.example.oscarthorsson.remember;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SavedListsActivity extends AppCompatActivity {
    private final String LOG_TAG = FakeReminderStore.class.getName();
    // skapa en ReminderSStore
    // be den om en lista mwd ReminderList-objekt
    // slång i den listam i "new ArrayAdapter"

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_lists);
        view();
        System.out.println("hej2");
    }


public void view (){
    FakeReminderStore goran = FakeReminderStore.getInstance();
    List<ReminderList> test = goran.getReminders();
    ListView listView = findViewById(R.id.test);
    ArrayAdapter adapter = new ArrayAdapter<ReminderList>(this,android.R.layout.activity_list_item,test);
    listView.setAdapter(adapter);
    listView.setOnItemClickListener(new ListView.OnItemClickListener(){

    @Override
    public void onItemClick(AdapterView <?> parent, final View view, int position, long id){
        Log.d(LOG_TAG, "item clicked, pos:" + position + " id: " + id);

        }
    });}
}


