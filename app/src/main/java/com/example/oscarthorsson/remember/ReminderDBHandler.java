package com.example.oscarthorsson.remember;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;

/**
 * Created by Oscar Thorsson on 2017-12-13.
 */

public class ReminderDBHandler extends SQLiteOpenHelper {


    private final static String DATABASE_NAME = "DATABASE_NAME";
    private final static int DATABASE_VERSION = 1;


    private final static String TABLE_NAME = "REMINDERS";
    private final static String ITEM_TABLE_NAME = "ITEM_TABLE";


    //Kolumner:
    private final static String title = "REMINDER_TITLE";
    private final static String getItemName = "ITEM_NAME";
    private final static String alarmDate = "ALARM_DATE";
    private final static String isChecked = "CHECKED";
    private final static String ID = "ID";

    //private final static String isActivated = "ACTIVATION";

    public ReminderDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + title + "REMINDER_TITLE"
                + ID + "ID" + ")";
                //+ getItemName + "ITEM_NAME"
                //+ alarmDate + "ALARM_DATE"
                //+ isChecked + "CHECKED"


        String CREATE_ITEM_TABLE ="CREATE TABLE" + ITEM_TABLE_NAME + "("
                + getItemName + "ITEM_NAME"
                + isChecked + "CHECKED"
                + ID+"ID" +")";

        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_ITEM_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ITEM_TABLE_NAME);
        onCreate(db);

    }

    public void addReminderData(ReminderList reminderList){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("REMINDER_TITLE",reminderList.title());
        db.insert(TABLE_NAME,null,values);
        db.close();

    }
    public void addReminderItemData (ReminderItem reminderItem){
      SQLiteDatabase db = this.getWritableDatabase();
      ContentValues values = new ContentValues();
      values.put("CHECKED",reminderItem.isChecked());
      values.put("ITEM_NAME",reminderItem.getItemName());
      db.insert(ITEM_TABLE_NAME,null,values);
      db.close();
    }

    public boolean updateReminders (Boolean isChecked){
      SQLiteDatabase db = this.getWritableDatabase();
      ContentValues args = new ContentValues();
     args.put("CHECKED", isChecked);
     return db.update(TABLE_NAME,args,"CHECKED"+"="+isChecked,null)>0;
    }
}
