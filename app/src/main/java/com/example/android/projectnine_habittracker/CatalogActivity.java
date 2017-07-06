package com.example.android.projectnine_habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.android.projectnine_habittracker.Data.HabitContractor;
import com.example.android.projectnine_habittracker.Data.HabitDBHelper;


public class CatalogActivity extends AppCompatActivity {

    private HabitDBHelper mDBHELPER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        mDBHELPER = new HabitDBHelper(this);
    }

    private Cursor readDBASE() {
        SQLiteDatabase dBase = mDBHELPER.getReadableDatabase();

        String[] projection = {
                HabitContractor.HabitWrite._ID,
                HabitContractor.HabitWrite.HABITS_NAME,
                HabitContractor.HabitWrite.HABITS_FREQUENCY,
                HabitContractor.HabitWrite.HABITS_DATE,
                HabitContractor.HabitWrite.HABITS_FINISHED
        };

        Cursor cursorVar = dBase.query(
                HabitContractor.HabitWrite.HABITS_TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        return cursorVar;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.send_fake_data:
                writeLINE();
                Cursor line = readDBASE();
                showDBASE(line);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDBASE(Cursor cursor) {
        TextView displayView = (TextView) findViewById(R.id.first_text);

        try {
            displayView.setText("Now it is done " + cursor.getCount() + " tasks.\n\n\n");
            displayView.append(" < " +
                    HabitContractor.HabitWrite._ID + " || " +
                    HabitContractor.HabitWrite.HABITS_NAME + " || " +
                    HabitContractor.HabitWrite.HABITS_FREQUENCY + " || " +
                    HabitContractor.HabitWrite.HABITS_DATE + " || " +
                    HabitContractor.HabitWrite.HABITS_FINISHED + " > " + "\n");

            int idOfHabit = cursor.getColumnIndex(HabitContractor.HabitWrite._ID);
            int nameOfHabit = cursor.getColumnIndex(HabitContractor.HabitWrite.HABITS_NAME);
            int frequencyOfHabit = cursor.getColumnIndex(HabitContractor.HabitWrite.HABITS_FREQUENCY);
            int dateOfHabit = cursor.getColumnIndex(HabitContractor.HabitWrite.HABITS_DATE);
            int doneOrNot = cursor.getColumnIndex(HabitContractor.HabitWrite.HABITS_FINISHED);

            while (cursor.moveToNext()) {
                int habitID = cursor.getInt(idOfHabit);
                String habitNAME = cursor.getString(nameOfHabit);
                int habitFREQUENCY = cursor.getInt(frequencyOfHabit);
                String habitDATE = cursor.getString(dateOfHabit);
                int habitDONE = cursor.getInt(doneOrNot);

                displayView.append(("\n" + " < " +
                        habitID + " || " +
                        habitNAME + " || " +
                        habitFREQUENCY + " || " +
                        habitDATE + " || " +
                        habitDONE + " > "));
            }
        } finally {
            cursor.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    private void writeLINE() {
        SQLiteDatabase dBase = mDBHELPER.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitContractor.HabitWrite.HABITS_NAME, "Dog walking");
        values.put(HabitContractor.HabitWrite.HABITS_FREQUENCY, 2);
        values.put(HabitContractor.HabitWrite.HABITS_DATE, "07/07/2017");
        values.put(HabitContractor.HabitWrite.HABITS_FINISHED, HabitContractor.HabitWrite.HABITS_FINISHED_YES);

        dBase.insert(HabitContractor.HabitWrite.HABITS_TABLE_NAME, null, values);
    }


}