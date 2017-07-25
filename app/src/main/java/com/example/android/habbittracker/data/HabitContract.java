package com.example.android.habbittracker.data;

import android.provider.BaseColumns;

public final class HabitContract {

    private HabitContract() {
    }

    public static final class HabitEntry implements BaseColumns {

        public final static String TABLE_NAME = "habits";

        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_HABIT_NAME = "name";

        public final static String COLUMN_HABIT_FREQUENCY = "frequency";

        public final static String COLUMN_HABIT_TYPE = "type";

        public static final int TYPE_OTHER = 0;
        public static final int TYPE_PHYSICAL = 1;
        public static final int TYPE_MENTAL = 2;
    }

}
