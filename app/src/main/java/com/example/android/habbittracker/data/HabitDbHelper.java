package com.example.android.habbittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HabitDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "habits.db";

    private static final int DATABASE_VERSION = 1;

    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_HABITS_TABLE = "CREATE TABLE " + HabitContract.HabitEntry.TABLE_NAME + " ("
                + HabitContract.HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitContract.HabitEntry.COLUMN_HABIT_NAME + " TEXT NOT NULL, "
                + HabitContract.HabitEntry.COLUMN_HABIT_FREQUENCY + " INTEGER, "
                + HabitContract.HabitEntry.COLUMN_HABIT_TYPE + " INTEGER NOT NULL DEFAULT 0);";

        db.execSQL(SQL_CREATE_HABITS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        We don't need this yet.
    }
}
