package com.example.oscarthorsson.remember;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.SymbolTable;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Oscar Thorsson on 2017-12-13.
 */

public class ReminderDBHandler extends SQLiteOpenHelper {


    private final static String DATABASE_NAME = "DATABASE_NAME";
    private final static int DATABASE_VERSION = 8;


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
                + ID + " integer primary key, "
                + title + " text" + ")";
        //+ getItemName + "ITEM_NAME"
        //+ alarmDate + "ALARM_DATE"
        //alarmDate är valfri om vi hinner komma så långt, just nu skall en notis skickas om användaren inte når sin macadress.
        //+ isChecked + "CHECKED"


        String CREATE_ITEM_TABLE = "CREATE TABLE " + ITEM_TABLE_NAME + "("
                + ID + " intger, "
                + getItemName + " text, "
                + isChecked + " tinyint" + ")";

        db.execSQL(CREATE_LIST_TABLE);
        db.execSQL(CREATE_ITEM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ITEM_TABLE_NAME);
        onCreate(db);
    }

    public void addReminderData(String title){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("REMINDER_TITLE", title);

        db.insert(TABLE_NAME,null, values);
        System.out.println("lägger i databasen" + title);
        db.close();
    }

    public String getReminderID(String title) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectRem = "SELECT ID FROM Reminders where reminder_title = '" + title + "'";
        Cursor cursor = db.rawQuery(selectRem, null);
        if (cursor.moveToFirst()) {
            return cursor.getString(0);
        }
        return "0";
    }
    public void addReminderItemData (ReminderItem reminderItem, String id){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("ITEM_NAME", reminderItem.getItemName());
        values.put("CHECKED", reminderItem.isChecked());
        values.put("ID", id);
        db.insert(ITEM_TABLE_NAME,null, values);
        db.close();
    }

    public boolean updateReminders (Boolean isChecked){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put("CHECKED", isChecked);
        return db.update(TABLE_NAME, args,"CHECKED" + "=" + isChecked,null) > 0;
    }

    public Map<String, List<ReminderItem>> getReminders(){
        Map<String, List<ReminderItem>> result = new HashMap<>();
        List<ReminderItem> reminderList= new ArrayList<ReminderItem>();
        String selectRem = "SELECT " + title + " , " + getItemName+ ", "+isChecked+" FROM " + TABLE_NAME + " natural join " +
                ITEM_TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectRem, null);
        String thisTitle="";
        if (cursor.moveToFirst()) {
            do {
                if(cursor.getString(0).equals(thisTitle)) {
                    ReminderItem item = new ReminderItem(cursor.getString(1));
                    item.setChecked(cursor.getInt(2) == 1);
                    reminderList.add(item);
                } else {
                    reminderList=new ArrayList<ReminderItem>();
                    ReminderItem item = new ReminderItem(cursor.getString(1));
                    item.setChecked(cursor.getInt(2) == 1);
                    reminderList.add(item);
                    result.put(cursor.getString(0), reminderList);
                }
                thisTitle = cursor.getString(0);
                //ReminderList lista = new ReminderList();
                //lista.set_id(Integer.parseInt();
                //lista.setTitle(cursor.getString(1));



            } while (cursor.moveToNext());
        } cursor.close();
        return result;
    }
}
