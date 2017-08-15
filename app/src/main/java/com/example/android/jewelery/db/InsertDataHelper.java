package com.example.android.jewelery.db;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.android.jewelery.data.MainData;

import static android.R.attr.data;
import static com.example.android.jewelery.db.HistoryReaderContract.*;

public class InsertDataHelper {

    public static void insertHistoryData(SQLiteDatabase db, MainData data) {

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
            db.insert(HistoryInputEntry.TABLE_NAME, null, cv);
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (SQLException e) {
            Log.v(InsertDataHelper.class.getSimpleName(), "Db insertHistoryData transaction failed");
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
            db.delete(HistoryInputEntry.TABLE_NAME, null, null);
            db.delete(HistoryResultEntry.TABLE_NAME, null, null);
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (SQLException e) {
            Log.v(InsertDataHelper.class.getSimpleName(), "Db deleteAllRows transaction failed");
        }
    }

    public static void insertResultData(SQLiteDatabase db, MainData data) {

        if (db == null) return;

        ContentValues cv = new ContentValues();
        cv.put(HistoryResultEntry.COLUMN_RESULT_FINAL_WEIGHT, data.finalWeight);
        cv.put(HistoryResultEntry.COLUMN_RESULT_AVA_WEIGHT, data.avaWeight);
//        cv.put(HistoryResultEntry.COLUMN_RESULT_AVA_COPPER, data.avaCopper);
        cv.put(HistoryResultEntry.COLUMN_RESULT_FINAL_COPPER, data.finalCopper);
//        cv.put(HistoryResultEntry.COLUMN_RESULT_AVA_SILVER, data.avaSilver);
        cv.put(HistoryResultEntry.COLUMN_RESULT_FINAL_SILVER, data.finalSilver);

        try {
            db.beginTransaction();
            db.insert(HistoryResultEntry.TABLE_NAME, null, cv);
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (SQLException e) {
            Log.v(InsertDataHelper.class.getSimpleName(), "Db insertResultData transaction failed");
        }

    }



}

