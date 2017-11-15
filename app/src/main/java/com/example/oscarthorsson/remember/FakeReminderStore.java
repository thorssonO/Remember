package com.example.oscarthorsson.remember;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by patsy on 2017-11-15.
 */

public class FakeReminderStore
{

    List<ReminderList> reminders;

    public FakeReminderStore (){
        reminders= new ArrayList<>();
        ReminderItem item = new ReminderItem("W5w-lampa");
        List<ReminderItem> items = new ArrayList<>();
        items.add(item);
        ReminderList l = new ReminderList("Bilen", items, null);
    }

    public List<ReminderList> getReminders(){
        return reminders;
    }

    public List<ReminderItem> getItems(ReminderList list){
        return list.items();
    }


}
