package com.example.oscarthorsson.remember;

import java.util.Date;
import java.util.List;

/**
 * Created by oscarthorsson on 2017-11-13.
 */

public class ReminderList {
    String title;
    List<ReminderItem> items;
    List <ReminderTitle> titles;

    //LocalDate alarmDate;  TODO: find out if we can move up to API-level 26
    Date alarmDate;
    private int id;

    //Tom Konstruktor
    public ReminderList(){
    }

    //"Full" konstruktor
    public ReminderList(String title, List<ReminderItem> items, Date alarmDate) {
        super();
        this.title = title;
        this.items = items;
        this.alarmDate = alarmDate;
    }
/*
    //En-parameterskonstruktor
    public ReminderList(String title){
        super();
        this.title = title;
    }
*/
    public String title() {
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public List<ReminderItem> items() {
        return items;
    }

    public Date getAlarmDate() {
        return alarmDate;
    }

    public String toString() {
        return  ""+title+""+ items+""+alarmDate;
    }

    /*public boolean areAllItemsChecked() {
        for(ReminderItem item : items) {
            if ( !item.isChecked()) {
                return false;
            }
        }
        return true;
    }*/

}
