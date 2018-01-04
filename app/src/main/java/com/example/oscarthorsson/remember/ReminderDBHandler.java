package com.example.oscarthorsson.remember;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Oscar Thorsson on 2017-12-13.
 */

public class ReminderDBHandler extends SQLiteOpenHelper {


    private final static String DATABASE_NAME = "DATABASE_NAME";
    private final static int DATABASE_VERSION = 19;

    //Tabeller
    private final static String TABLE_NAME = "REMINDERS";
    private final static String ITEM_TABLE_NAME = "ITEM_TABLE";
    private final static String MAC_TABLE_NAME = "MAC_TABLE";

    //Kolumner:
    private final static String title = "REMINDER_TITLE";
    private final static String getItemName = "ITEM_NAME";
    private final static String alarmDate = "ALARM_DATE";
    private final static String isChecked = "CHECKED";
    private final static String ID = "ID";
    private final static String MAC = "MAC_ADDRESS";
    private final static String MAC_ADDRESS = "02:00:00:00:00:00";

    public ReminderDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_LIST_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + ID + " integer primary key, "
                + title + " text" + ")";

        //+ alarmDate + "ALARM_DATE"
        //alarmDate är valfri om vi hinner komma så långt, just nu skall en notis skickas om användaren inte når sin macadress.

        String CREATE_ITEM_TABLE = "CREATE TABLE " + ITEM_TABLE_NAME + "("
                + ID + " integer, "
                + getItemName + " text, "
                + isChecked + " tinyint" + ")";

        String CREATE_MAC_TABLE = "CREATE TABLE " + MAC_TABLE_NAME + "("
                + MAC + " text" + ")";

        db.execSQL(CREATE_LIST_TABLE);
        db.execSQL(CREATE_ITEM_TABLE);
        db.execSQL(CREATE_MAC_TABLE);
    }

    //nedan metod körs ifall databasversionen ändrats
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ITEM_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MAC_TABLE_NAME);
        onCreate(db);
    }

    //metod för att hämta id
    public String getReminderID(String title) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectRem = "SELECT ID FROM Reminders where reminder_title = '" + title + "'";
        Cursor cursor = db.rawQuery(selectRem, null);

        if (cursor.moveToFirst()) {
            return cursor.getString(0);
        }
        cursor.close();
        return "0";
    }

    //anropas från AddRemactivty
    //används för att lägga till titlen på en reminder
    //i sqlite-databasen
    public void addReminderData(String title){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("REMINDER_TITLE", title);

        db.insert(TABLE_NAME,null, values);
        System.out.println("lägger i databasen" + title);
        db.close();
    }

    //anropas från AddRemactivty
    //används för att lägga till resterande variabler utöver
    //titeln vid en remider, dvs. namn på reminder-item,
    //huruvida den är icheckad (kommer senare) och ett ID
    public void addReminderItemData (ReminderItem reminderItem, String id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("ITEM_NAME", reminderItem.getItemName());
        values.put("CHECKED", reminderItem.isChecked());
        values.put("ID", id);
        db.insert(ITEM_TABLE_NAME,null, values);
        db.close();
    }

    public void addMac(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("MAC_ADDRESS", MAC_ADDRESS);
        System.out.println("nu lägger vi till macadressen i databasen " + MAC_ADDRESS);
        db.insert(MAC_TABLE_NAME, null, values);
        db.close();
    }

    //ovanstående och nedanstående metoder anävnds för att
    //lägga till och hämta ut macadressen från databasen

    public String getMac() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectRem = "SELECT * FROM MAC_TABLE;" ;
        Cursor cursor = db.rawQuery(selectRem, null);

        if (cursor.moveToFirst()) {
            return cursor.getString(0);
        }
        cursor.close();
        return "0";
    }


    /*public boolean updateReminderItems (Boolean isChecked){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();

        args.put("CHECKED", isChecked);
        return db.update(ITEM_TABLE_NAME, args,"CHECKED" + "=" + isChecked,null) > 0;
    }*/


    //Följande getReminders(), används i savedlistsactivity
    //för att hämta ut samtliga reminders ur både tabellen
    //som innehåller titlar och den som innehåller id,
    //remideritems och isChecked.
    public Map<String, List<ReminderItem>> getReminders(){

        Map<String, List<ReminderItem>> result = new HashMap<>();
        List<ReminderItem> reminderList= new ArrayList<ReminderItem>();
        String selectRem = "SELECT " + title + " , " + getItemName+ ", "+ isChecked +" FROM " + TABLE_NAME
                + " natural join " + ITEM_TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectRem, null);
        String thisTitle = " ";

        if (cursor.moveToFirst()) {
            do {

                //note to self: första kolumen är columnIndex 0, som array..
                if (cursor.getString(0).equals(thisTitle)) {
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

            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }
}