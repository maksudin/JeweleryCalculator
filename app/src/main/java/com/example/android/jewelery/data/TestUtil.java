package com.example.android.jewelery.data;


import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.android.jewelery.db.HistoryReaderContract;

import java.util.ArrayList;
import java.util.List;

public class TestUtil {

    public static void insertFakeData(SQLiteDatabase db) {

        if (db == null) {
            return;
        }

        List<ContentValues> list = new ArrayList<ContentValues>();
        ContentValues cv = new ContentValues();
        cv.put(HistoryReaderContract.HistoryInputEntry.COLUMN_INPUT_AVA_WEIGHT, 6f);
//        cv.put(HistoryReaderContract.HistoryInputEntry.COLUMN_INPUT_AVA_PROBA, 585f);
//        cv.put(HistoryReaderContract.HistoryInputEntry.COLUMN_INPUT_AVA_COLOR, "Красный");
//        cv.put(HistoryReaderContract.HistoryInputEntry.COLUMN_INPUT_ADD_WEIGHT, 5f);
//        cv.put(HistoryReaderContract.HistoryInputEntry.COLUMN_INPUT_ADD_PROBA, 999.9f);
//        cv.put(HistoryReaderContract.HistoryInputEntry.COLUMN_INPUT_DESIRED_PROBA, 585f);
//        cv.put(HistoryReaderContract.HistoryInputEntry.COLUMN_INPUT_DESIRED_COLOR, "Жёлтый");
        list.add(cv);
        list.add(cv);
        list.add(cv);
        list.add(cv);

        // Insert all data in one transaction.
        try {
            db.beginTransaction();
            // Clear the table first.
            db.delete(HistoryReaderContract.HistoryInputEntry.TABLE_NAME, null, null);
            // Go through the list and add one by one.
            for (ContentValues c : list) {
                db.insert(HistoryReaderContract.HistoryInputEntry.TABLE_NAME, null, c);
            }

            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.v("Hi", "HELLLLOOOOOOOOO");
        } finally {
            db.endTransaction();
        }

    }
}
