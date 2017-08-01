package com.example.android.jewelery.db;

import android.provider.BaseColumns;

/**
 * Created by milju on 7/31/2017.
 */

public class HistoryReaderContract {

    private HistoryReaderContract() {}

    public static class HistoryInputEntry implements BaseColumns {
        public static final String TABLE_NAME = "inputdata";
        public static final String COLUMN_INPUT_AVA_WEIGHT = "avaweight";
        public static final String COLUMN_INPUT_AVA_PROBA = "avaproba";
        public static final String COLUMN_INPUT_AVA_COLOR = "avacolor";
        public static final String COLUMN_INPUT_ADD_WEIGHT = "addweight";
        public static final String COLUMN_INPUT_ADD_PROBA = "addproba";
        public static final String COLUMN_INPUT_DESIRED_PROBA = "desiredproba";
        public static final String COLUMN_INPUT_DESIRED_COLOR = "desiredcolor";
    }

    public static class HistoryResultEntry implements BaseColumns {
        public static final String TABLE_NAME = "resultsdata";
        public static final String COLUMN_RESULT_FINAL_WEIGHT = "finalweight";
        public static final String COLUMN_RESULT_AVA_COPPER = "avacopper";
        public static final String COLUMN_RESULT_FINAL_COPPER = "finalcopper";
        public static final String COLUMN_RESULT_AVA_SILVER = "avasilver";
        public static final String COLUMN_RESULT_FINAL_SILVER = "finalsilver";

    }
}
