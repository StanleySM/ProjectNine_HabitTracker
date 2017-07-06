package com.example.android.projectnine_habittracker.Data;

import android.provider.BaseColumns;

/**
 * Created by StanleyPC on 6. 7. 2017.
 */

public class HabitContractor {

    public static final class HabitWrite implements BaseColumns {

        public final static String HABITS_TABLE_NAME = "habits";
        public final static String _ID = BaseColumns._ID;
        public final static String HABITS_NAME = "name";
        public final static String HABITS_FREQUENCY = "frequency";
        public final static String HABITS_DATE = "date";
        public final static String HABITS_FINISHED = "done";
        public static final int HABITS_FINISHED_YES = 1;
        public static final int HABITS_FINISHED_NO = 0;



    }
}