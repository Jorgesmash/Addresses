package com.addresses.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AddressesDbHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + AddressesDbContract.FeedEntry.TABLE_NAME + " (" +
                    AddressesDbContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    AddressesDbContract.FeedEntry.COLUMN_NAME_LOCATION_NAME + " TEXT," +
                    AddressesDbContract.FeedEntry.COLUMN_NAME_STREET_ADDRESS + " TEXT," +
                    AddressesDbContract.FeedEntry.COLUMN_NAME_CITY + " TEXT," +
                    AddressesDbContract.FeedEntry.COLUMN_NAME_STATE + " TEXT," +
                    AddressesDbContract.FeedEntry.COLUMN_NAME_ZIP + " TEXT)";

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Addresses.db";

    public AddressesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
