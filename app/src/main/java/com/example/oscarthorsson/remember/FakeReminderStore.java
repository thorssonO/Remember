package com.example.oscarthorsson.remember;

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
    List<ReminderTitle> titles;

    public static FakeReminderStore getInstance() {
        if (instance == null ) {
            instance = new FakeReminderStore();
        }
        return instance;
    }
    private FakeReminderStore (){
        reminders= new ArrayList<>();
        titles=new ArrayList<>();
    }

    public void addReminderList(ReminderList list) {
        reminders.add(list);
    }

    public void addTitle (ReminderTitle titles2){
        titles.add(titles2);
    }

    public List<ReminderList> getReminders(){
        return reminders;
    }

    public List<ReminderItem> getItems(){

        return items;
    }

    public List <ReminderTitle> getReminderListTitles(){

        return titles;
    }

    public String toString() {
        return titles + " " + items;
    }

    public String toStringTitel() {
        return ""+titles;
    }

}
