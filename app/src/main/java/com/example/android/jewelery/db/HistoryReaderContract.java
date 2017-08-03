package com.example.android.jewelery.db;

import android.provider.BaseColumns;

/**
 * Created by milju on 7/31/2017.
 */

public class HistoryReaderContract {

    private HistoryReaderContract() {}

    public static class HistoryInputEntry implements BaseColumns {
        public static final String TABLE_NAME = "inputData";
        public static final String COLUMN_INPUT_AVA_WEIGHT = "avaWeight";
        public static final String COLUMN_INPUT_AVA_PROBA = "avaProba";
        public static final String COLUMN_INPUT_AVA_COLOR = "avaColor";
        public static final String COLUMN_INPUT_ADD_WEIGHT = "addWeight";
        public static final String COLUMN_INPUT_ADD_PROBA = "addProba";
        public static final String COLUMN_INPUT_DESIRED_PROBA = "desiredProba";
        public static final String COLUMN_INPUT_DESIRED_COLOR = "desiredColor";
    }

    public static class HistoryResultEntry implements BaseColumns {
        public static final String TABLE_NAME = "resultsData";
        public static final String COLUMN_RESULT_FINAL_WEIGHT = "finalWeight";
        public static final String COLUMN_RESULT_AVA_COPPER = "avaCopper";
        public static final String COLUMN_RESULT_FINAL_COPPER = "finalCopper";
        public static final String COLUMN_RESULT_AVA_SILVER = "avaSilver";
        public static final String COLUMN_RESULT_FINAL_SILVER = "finalSilver";

    }
}
