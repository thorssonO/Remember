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
    public List<ReminderItem> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_lists);
        ListView listView =(ListView) findViewById(R.id.items);
        ArrayAdapter adapter=new ArrayAdapter<ReminderItem>(this,android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter); }

    public void showSavedLits(){





    }

    //public void inItemClick(AdapterView <> parent, final View view, int position, long id){}
}


