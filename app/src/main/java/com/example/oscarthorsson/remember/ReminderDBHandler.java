package com.example.oscarthorsson.remember;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Oscar Thorsson on 2017-12-13.
 */

public class ReminderDBHandler extends SQLiteOpenHelper {


    private final static String DATABASE_NAME = "DATABASE_NAME";
    private final static int DATABASE_VERSION = 1;


    private final static String TABLE_NAME = "REMINDERS";
    //Kolumner:
    private final static String title = "REMINDER_TITLE";
    //private final static String
    //private final static String
    //private final static Boolean

    public ReminderDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION );

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + "title" + ")";

                db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
}
