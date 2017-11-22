package com.example.oscarthorsson.remember;


import android.support.constraint.solver.LinearSystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by patsy on 2017-11-15.
 */

public class FakeReminderStore
{
    private static FakeReminderStore instance;

    List<ReminderList> reminders;
    List<ReminderItem> items;
    List<ReminderTitle> titel;

    public static FakeReminderStore getInstance() {
        if (instance == null ) {
            instance = new FakeReminderStore();
        }
        return instance;
    }
    private FakeReminderStore (){
        reminders= new ArrayList<>();
        titel=new ArrayList<>();
    }

    public void addReminderList(ReminderList list) {
        reminders.add(list);
    }

    public List<ReminderList> getReminders(){
        return reminders;
    }

    public List<ReminderItem>getItems(){
        return items;
    }

    public void addTitle (ReminderTitle title){
        titel.add(title);
    }
    public List <ReminderTitle> getReminderListTitles(){

        return titel;
    }

}
