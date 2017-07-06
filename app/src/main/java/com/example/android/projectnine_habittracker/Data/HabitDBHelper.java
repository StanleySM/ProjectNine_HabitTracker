package com.example.android.projectnine_habittracker.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by StanleyPC on 6. 7. 2017.
 */

public class HabitDBHelper extends SQLiteOpenHelper {
        public static final String LOG_TAG = HabitDBHelper.class.getSimpleName();
        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "habits_tracker.db";


        public HabitDBHelper(Context datas){
            super(datas, DATABASE_NAME, null, DATABASE_VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase db){
            Log.i("onCreate", "True");
            String SQL_CREATE_HABITS_TABLE = "CREATE TABLE "
                    + HabitContractor.HabitWrite.HABITS_TABLE_NAME + " ("
                    + HabitContractor.HabitWrite._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + HabitContractor.HabitWrite.HABITS_NAME + " TEXT NOT NULL, "
                    + HabitContractor.HabitWrite.HABITS_FREQUENCY + " INTEGER NOT NULL, "
                    + HabitContractor.HabitWrite.HABITS_DATE + " TEXT NOT NULL, "
                    + HabitContractor.HabitWrite.HABITS_FINISHED + " INTEGER NOT NULL DEFAULT 0);";

            db.execSQL(SQL_CREATE_HABITS_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL("DELETE TABLE IF EXIST " + HabitContractor.HabitWrite.HABITS_TABLE_NAME);
            onCreate(db);


}}

