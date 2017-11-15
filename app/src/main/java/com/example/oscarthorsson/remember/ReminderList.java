package com.example.oscarthorsson.remember;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by oscarthorsson on 2017-11-13.
 */

public class ReminderList {
    String title;
    List<ReminderItem> items;
    LocalDate alarmDate;

    public ReminderList(String title, List<ReminderItem> items, LocalDate alarmDate) {
        this.title = title;
        this.items = items;
        this.alarmDate = alarmDate;
    }

    public String title() {
        return title;
    }
    public List<ReminderItem> items() {
        return items;
    }
    public LocalDate getAlarmDate() {
        return alarmDate;
    }

    public String toString() {
        return title + " " + items + " (" + alarmDate + ")";
    }

    // Maybe a method which returns a List<ReminderItem> with the unchecked items

    public boolean areAllItemsChecked() {
        for(ReminderItem item : items) {
            if ( !item.isChecked()) {
                return false;
            }
        }
        return true;
    }
}
