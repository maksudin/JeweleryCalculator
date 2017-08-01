package com.example.android.jewelery.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.jewelery.db.HistoryReaderContract.*;

/**
 * Created by milju on 7/31/2017.
 */

public class HistoryDbHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "history.db";
    private static final int DATABASE_VERSION = 1;

    public HistoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // TODO (1) Сделать несколько запросов для создания 2 таблиц.
//        final String SQL_CREATE_HISTORY_TABLE = "CREATE TABLE" + HistoryInputEntry.TABLE_NAME + " (" +
//                HistoryInputEntry._ID + "INTEGER PRIMARY KEY AUTOINCREMENT," +
//                HistoryInputEntry.COLUMN_INPUT_AVA_WEIGHT + " FLOAT NOT NULL, " +


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
