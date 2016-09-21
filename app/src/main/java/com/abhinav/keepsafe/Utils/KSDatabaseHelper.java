package com.abhinav.keepsafe.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhinav.sharma on 9/21/2016.
 */
public class KSDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = KSDatabaseHelper.class.getSimpleName();

    public static final class TableEntries implements BaseColumns {
        public static final String DATABASE_NAME = "KS_DATABASE";
        public static final String TABLE_NAME = "ACCOUNTS";
        public static final String COL_ITEM_NAME = "ITEM_NAME";
        public static final String COL_ITEM_TYPE = "ITEM_TYPE";
        public static final String COL_ITEM_PASSWORD = "ITEM_PASSWORD";
        public static final String COL_ITEM_TRAN_PASSWORD = "ITEM_TRAN_PASSWORD";
        public static final int DB_VERSION = 1;
    }

    private static KSDatabaseHelper ksDatabaseHelper = null;
    private static final String QUERY_CREATE_TABLE = "CREATE TABLE " + TableEntries.TABLE_NAME +
            "("
            + TableEntries._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TableEntries.COL_ITEM_NAME + " TEXT, "
            + TableEntries.COL_ITEM_TYPE + " TEXT, "
            + TableEntries.COL_ITEM_PASSWORD + " TEXT, "
            + TableEntries.COL_ITEM_TRAN_PASSWORD + " TEXT, "
            + " )";
    private static final String GET_ALL_ITEMS = "SELECT * FROM " + TableEntries.TABLE_NAME;

    public static KSDatabaseHelper getInstance(Context context) {
        if (ksDatabaseHelper == null) {
            ksDatabaseHelper = new KSDatabaseHelper(context, TableEntries.DATABASE_NAME, null, TableEntries.DB_VERSION);
        }

        return ksDatabaseHelper;
    }

    private KSDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(QUERY_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<AccountModel> getAllItems(KSDatabaseHelper ksDatabaseHelper) {
        SQLiteDatabase database = ksDatabaseHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery(GET_ALL_ITEMS, null);
        return getAccountModelFromCursor(cursor);
    }

    private List<AccountModel> getAccountModelFromCursor(Cursor cursor) {
        List<AccountModel> accountModelList = null;
        if (cursor != null && cursor.getCount() > 0) {
             accountModelList = new ArrayList<>();
            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                AccountModel ac = new AccountModel();
                ac.setAccountID(cursor.getString(cursor.getColumnIndex(TableEntries._ID)));
                ac.setAccountType(cursor.getString(cursor.getColumnIndex(TableEntries.COL_ITEM_TYPE)));
                ac.setAccountName(cursor.getString(cursor.getColumnIndex(TableEntries.COL_ITEM_NAME)));
                ac.setAccountPassword(cursor.getString(cursor.getColumnIndex(TableEntries.COL_ITEM_PASSWORD)));
                ac.setAccountTranPassword(cursor.getString(cursor.getColumnIndex(TableEntries.COL_ITEM_TRAN_PASSWORD)));
                accountModelList.add(ac);
            }
        }

        return accountModelList;
    }

    public void saveAccountData(KSDatabaseHelper ksDatabaseHelper, AccountModel accountModel){
        SQLiteDatabase database = ksDatabaseHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableEntries.COL_ITEM_TYPE, accountModel.getAccountType());
        cv.put(TableEntries.COL_ITEM_NAME, accountModel.getAccountName());
        cv.put(TableEntries.COL_ITEM_PASSWORD, accountModel.getAccountPassword());
        cv.put(TableEntries.COL_ITEM_TRAN_PASSWORD, accountModel.getAccountTranPassword());

        database.insert(TableEntries.TABLE_NAME, null, cv);
        Log.e(TAG, "saveAccountData: item added for "+ accountModel.getAccountName());
    }
}
