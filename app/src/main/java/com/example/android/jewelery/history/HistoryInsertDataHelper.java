package com.example.android.jewelery.history;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.android.jewelery.data.MainData;
import com.example.android.jewelery.db.HistoryReaderContract;

import static com.example.android.jewelery.db.HistoryReaderContract.*;

public class HistoryInsertDataHelper {

    public static void insertData(SQLiteDatabase db, MainData data) {

        if (db == null) return;

        ContentValues cv = new ContentValues();
        cv.put(HistoryInputEntry.COLUMN_INPUT_AVA_WEIGHT, data.avaWeight);
        cv.put(HistoryInputEntry.COLUMN_INPUT_AVA_PROBA, data.avaProba);
        cv.put(HistoryInputEntry.COLUMN_INPUT_AVA_COLOR, data.avaColor);
        cv.put(HistoryInputEntry.COLUMN_INPUT_ADD_WEIGHT, data.extraWeight);
        cv.put(HistoryInputEntry.COLUMN_INPUT_ADD_PROBA, data.extraProba);
        cv.put(HistoryInputEntry.COLUMN_INPUT_DESIRED_PROBA, data.desiredProba);
        cv.put(HistoryInputEntry.COLUMN_INPUT_DESIRED_COLOR, data.desiredColor);


        // Insert all data in one transaction.
        try {
            db.beginTransaction();
            // Clear the table first.
//            db.delete(HistoryReaderContract.HistoryInputEntry.TABLE_NAME, null, null);
            // Go through the list and add one by one.
            db.insert(HistoryInputEntry.TABLE_NAME, null, cv);
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (SQLException e) {
            Log.v(HistoryInsertDataHelper.class.getSimpleName(), "Db insertData transaction failed");
        }
    }

    public static void deleteOneRow(SQLiteDatabase db) {

        db.beginTransaction();

        Cursor cursor = db.query(
                HistoryInputEntry.TABLE_NAME,
                null, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            String rowId = cursor.getString(cursor.getColumnIndex(HistoryInputEntry._ID));
            db.delete(HistoryInputEntry.TABLE_NAME,
                    HistoryInputEntry._ID + "=?",
                    new String[]{rowId});
        }

        db.setTransactionSuccessful();
        db.endTransaction();

    }

    public static void deleteAllRows(SQLiteDatabase db) {

        try {
            db.beginTransaction();
            // Clear the table first.
            db.delete(HistoryReaderContract.HistoryInputEntry.TABLE_NAME, null, null);
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (SQLException e) {
            Log.v(HistoryInsertDataHelper.class.getSimpleName(), "Db deleteAllRows transaction failed");
        }
    }




}


