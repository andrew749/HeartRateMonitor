package com.andrew749.heart_rate_monitor.Database;

import android.provider.BaseColumns;

/**
 * Created by andrew on 3/17/15.
 */
public class HistoryContract {
    public HistoryContract(){}
    public static abstract class Entry implements BaseColumns{
        public static final String TABLE_NAME="HISTORY";
        public static final String COLUMN_ID="ENTRYID";
        public static final String COLUMN_TITLE="title";
        public static final String COLUMN_VALUE="HEART RATE";
    }
}
