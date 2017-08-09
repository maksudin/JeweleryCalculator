package com.example.android.jewelery.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.jewelery.db.HistoryReaderContract.*;

/**
 * Created by milju on 7/31/2017.
 */

public class DbHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "history.db";
    private static final int DATABASE_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_HISTORY_INPUT_TABLE = "CREATE TABLE " +
                HistoryInputEntry.TABLE_NAME + "(" +
                HistoryInputEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                HistoryInputEntry.COLUMN_INPUT_AVA_WEIGHT + " FLOAT NOT NULL, " +
                HistoryInputEntry.COLUMN_INPUT_AVA_PROBA + " FLOAT NOT NULL, " +
                HistoryInputEntry.COLUMN_INPUT_AVA_COLOR + " TEXT NOT NULL, " +
                HistoryInputEntry.COLUMN_INPUT_ADD_WEIGHT + " FLOAT NOT NULL, " +
                HistoryInputEntry.COLUMN_INPUT_ADD_PROBA + " FLOAT NOT NULL, " +
                HistoryInputEntry.COLUMN_INPUT_DESIRED_PROBA + " FLOAT NOT NULL, " +
                HistoryInputEntry.COLUMN_INPUT_DESIRED_COLOR + " TEXT NOT NULL" +
                "); ";

        final String SQL_CREATE_HISTORY_RESULT_TABLE = "CREATE TABLE " +
                HistoryResultEntry.TABLE_NAME + " (" +
                HistoryResultEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                HistoryResultEntry.COLUMN_RESULT_FINAL_WEIGHT + " FLOAT NOT NULL, " +
                HistoryResultEntry.COLUMN_RESULT_AVA_COPPER + " FLOAT NOT NULL, " +
                HistoryResultEntry.COLUMN_RESULT_FINAL_COPPER + " FLOAT NOT NULL, " +
                HistoryResultEntry.COLUMN_RESULT_AVA_SILVER + " FLOAT NOT NULL, " +
                HistoryResultEntry.COLUMN_RESULT_FINAL_SILVER + " FLOAT NOT NULL, " +
                " FOREIGN KEY(" + HistoryResultEntry._ID + ") REFERENCES " +
                HistoryInputEntry.TABLE_NAME + "(" + HistoryInputEntry._ID + ")" +
                "); ";
        db.execSQL(SQL_CREATE_HISTORY_INPUT_TABLE);
        db.execSQL(SQL_CREATE_HISTORY_RESULT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + HistoryInputEntry.TABLE_NAME);
        onCreate(db);
    }
}
