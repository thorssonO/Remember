package com.example.oscarthorsson.remember;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class OpenListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_list);
        showList();
    }

    public void showList(){

        FakeReminderStore sara = FakeReminderStore.getInstance();
        List<ReminderList> test = sara.getReminders();
        ListView listView = findViewById(R.id.test2);
        ArrayAdapter adapter = new ArrayAdapter<ReminderList>(this,android.R.layout.simple_expandable_list_item_1,test);
        listView.setAdapter(adapter);
    }
}
