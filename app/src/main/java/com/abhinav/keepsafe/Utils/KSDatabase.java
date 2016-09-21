package com.abhinav.keepsafe.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by abhinav.sharma on 9/21/2016.
 */
public class KSDatabase extends SQLiteOpenHelper {

    public static final class TableEntries implements BaseColumns {
        public static final String DATABASE_NAME = "KS_DATABASE";
        public static final String TABLE_NAME = "ACCOUNTS";
        public static final String COL_ITEM_NAME = "ITEM_NAME";
        public static final String COL_ITEM_TYPE = "ITEM_TYPE";
        public static final String COL_ITEM_PASSWORD = "ITEM_PASSWORD";
        public static final String COL_ITEM_TRAN_PASSWORD = "ITEM_TRAN_PASSWORD";
        public static final int DB_VERSION = 1;
    }

    private static KSDatabase ksDatabase = null;
    private static final String QUERY_CREATE_TABLE = "CREATE TABLE " + TableEntries.TABLE_NAME +
            "("
            + TableEntries._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TableEntries.COL_ITEM_NAME + " TEXT, "
            + TableEntries.COL_ITEM_TYPE + " TEXT, "
            + TableEntries.COL_ITEM_PASSWORD + " TEXT, "
            + TableEntries.COL_ITEM_TRAN_PASSWORD + " TEXT, "
            + " )";
    private static final String GET_ALL_ITEMS = "SELECT * FROM " + TableEntries.TABLE_NAME;

    public static KSDatabase getInstance(Context context){
        if (ksDatabase == null) {
            ksDatabase = new KSDatabase(context, TableEntries.DATABASE_NAME, null, TableEntries.DB_VERSION);
        }

        return ksDatabase;
    }

    private KSDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(QUERY_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
