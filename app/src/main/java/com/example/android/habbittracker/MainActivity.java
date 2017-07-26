package com.example.android.habbittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.android.habbittracker.data.HabitContract;
import com.example.android.habbittracker.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new HabitDbHelper(this);

        writeToDatabase();
        readFromDatabase();
    }

    private void readFromDatabase() {

        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        String[] projection = {
                HabitContract.HabitEntry._ID,
                HabitContract.HabitEntry.COLUMN_HABIT_NAME,
                HabitContract.HabitEntry.COLUMN_HABIT_FREQUENCY,
                HabitContract.HabitEntry.COLUMN_HABIT_TYPE};

        Cursor cursor = database.query(
                HabitContract.HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        TextView viewHabitTextView = (TextView) findViewById(R.id.view_habit);

        try {
            viewHabitTextView.setText("You've logged " + cursor.getCount() + " habits.\n\n");
            viewHabitTextView.append(HabitContract.HabitEntry._ID + " - "
                    + HabitContract.HabitEntry.COLUMN_HABIT_NAME + " - "
                    + HabitContract.HabitEntry.COLUMN_HABIT_FREQUENCY + " - "
                    + HabitContract.HabitEntry.COLUMN_HABIT_TYPE + "\n");

            int idColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_HABIT_NAME);
            int frequencyColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_HABIT_FREQUENCY);
            int typeColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_HABIT_TYPE);

            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                int currentFrequency = cursor.getInt(frequencyColumnIndex);
                int currentType = cursor.getInt(typeColumnIndex);

                viewHabitTextView.append("\n" + currentID + " - "
                        + currentName + " - "
                        + currentFrequency + "times per day" + " - "
                        + currentType);
            }
        } finally {
            cursor.close();
        }
    }

    private void writeToDatabase() {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(HabitContract.HabitEntry.COLUMN_HABIT_NAME, "Think Positively");
        contentValues.put(HabitContract.HabitEntry.COLUMN_HABIT_TYPE, HabitContract.HabitEntry.TYPE_MENTAL);
        contentValues.put(HabitContract.HabitEntry.COLUMN_HABIT_FREQUENCY, 5);


        database.insert(HabitContract.HabitEntry.TABLE_NAME, null, contentValues);
    }

}
