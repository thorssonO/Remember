package com.example.oscarthorsson.remember;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.SymbolTable;

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

    private static ReminderDBHandler instance;

    //private final static String isActivated = "ACTIVATION";

    public ReminderDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_LIST_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + ID + "ID"
                + title + "REMINDER_TITLE" + ")";
        //+ getItemName + "ITEM_NAME"
        //+ alarmDate + "ALARM_DATE"
        //alarmDate är valfri om vi hinner komma så långt, just nu skall en notis skickas om användaren inte når sin macadress.
        //+ isChecked + "CHECKED"


        String CREATE_ITEM_TABLE = "CREATE TABLE" + ITEM_TABLE_NAME + "("
                + ID + "ID"
                + getItemName + "ITEM_NAME"
                + isChecked + "CHECKED" + ")";

        db.execSQL(CREATE_LIST_TABLE);
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

        values.put("REMINDER_TITLE", reminderList.title());

        db.insert(TABLE_NAME,null, values);
        System.out.println("lägger i databasen" + title);
        db.close();
    }

    public void addReminderItemData (ReminderItem reminderItem){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("ITEM_NAME",reminderItem.getItemName());
        values.put("CHECKED",reminderItem.isChecked());

        db.insert(ITEM_TABLE_NAME,null, values);
        db.close();
    }

    public boolean updateReminders (Boolean isChecked){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put("CHECKED", isChecked);
        return db.update(TABLE_NAME,args,"CHECKED"+"="+isChecked,null)>0;
    }
}
