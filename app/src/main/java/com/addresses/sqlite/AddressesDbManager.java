package com.addresses.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.addresses.datamodels.Address;

import java.util.ArrayList;
import java.util.List;

import static android.provider.BaseColumns._ID;
import static com.addresses.sqlite.AddressesDbContract.FeedEntry.COLUMN_NAME_CITY;
import static com.addresses.sqlite.AddressesDbContract.FeedEntry.COLUMN_NAME_LOCATION_NAME;
import static com.addresses.sqlite.AddressesDbContract.FeedEntry.COLUMN_NAME_STATE;
import static com.addresses.sqlite.AddressesDbContract.FeedEntry.COLUMN_NAME_STREET_ADDRESS;
import static com.addresses.sqlite.AddressesDbContract.FeedEntry.COLUMN_NAME_ZIP;
import static com.addresses.sqlite.AddressesDbContract.FeedEntry.TABLE_NAME;

public class AddressesDbManager {

    private static AddressesDbManager addressesDbManager;
    private static AddressesDbHelper addressesDbHelper;

    public static void newInstance(Context context) {

        if (addressesDbManager == null) {

            addressesDbManager = new AddressesDbManager();
            addressesDbHelper = new AddressesDbHelper(context);

            // If you want to have some preloaded addresses, please uncomment
//            fillAddressesDB();
        }
    }

    private static void fillAddressesDB() {

        // Create a new Address map of values and insert the new row
        createData("Home", "4935 Enclave Blvd.", "Westerville", "Ohio", "43081");

        // Create a new Address map of values and insert the new row
        createData("Work", "1 Morse Rd.", "Columbus", "Ohio", "43230");

        // Create a new Address map of values and insert the new row
        createData("Parents", "2406 Meredith Dr.", "Columbus", "Ohio", "43219");

        // Create a new Address map of values and insert the new row
        createData("Brother", "2096 Sumac Loop S", "Columbus", "Ohio", "43229");

        // Create a new Address map of values and insert the new row
        createData("Sister", "260 Pingree Dr.", "Worthington", "Ohio", "43085");
    }

    public static long createData(String locationNameString, String streetAddressString, String cityString, String stateString, String zipString) {

        // Gets the data repository in write mode
        SQLiteDatabase sqLiteDatabase = addressesDbHelper.getWritableDatabase();

        // Create a new Address map of values and insert the new row
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_LOCATION_NAME, locationNameString);
        values.put(COLUMN_NAME_STREET_ADDRESS, streetAddressString);
        values.put(COLUMN_NAME_CITY, cityString);
        values.put(COLUMN_NAME_STATE, stateString);
        values.put(COLUMN_NAME_ZIP, zipString);

        long newRowId = sqLiteDatabase.insert(TABLE_NAME, null, values);

        return newRowId;
    }

    public static void updateData(int id, String locationNameString, String streetAddressString, String cityString, String stateString, String zipString) {

        // Gets the data repository in write mode
        SQLiteDatabase sqLiteDatabase = addressesDbHelper.getWritableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_LOCATION_NAME, locationNameString);
        values.put(COLUMN_NAME_STREET_ADDRESS, streetAddressString);
        values.put(COLUMN_NAME_CITY, cityString);
        values.put(COLUMN_NAME_STATE, stateString);
        values.put(COLUMN_NAME_ZIP, zipString);

        // Which row to update, based on the ID
        String whereClause = _ID + " =" + id;

        int rowsAffected = sqLiteDatabase.update(TABLE_NAME, values, whereClause, null);
        Log.d("rowsAffected", "" + rowsAffected);
    }

    public static List<Address> getAllData() {

        List<Address> addressList = new ArrayList<Address>();

        String queryString = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase sqLiteDatabase = addressesDbHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(queryString, null);
        while (cursor.moveToNext()) {

            int idColumnIndex = cursor.getColumnIndex(_ID);
            int locationNameColumnIndex = cursor.getColumnIndex(COLUMN_NAME_LOCATION_NAME);
            int streetAddressColumnIndex = cursor.getColumnIndex(COLUMN_NAME_STREET_ADDRESS);
            int cityColumnIndex = cursor.getColumnIndex(COLUMN_NAME_CITY);
            int stateColumnIndex = cursor.getColumnIndex(COLUMN_NAME_STATE);
            int zipColumnIndex = cursor.getColumnIndex(COLUMN_NAME_ZIP);

            int id = cursor.getInt(idColumnIndex);
            String locationNameString = cursor.getString(locationNameColumnIndex);
            String streetAddressString = cursor.getString(streetAddressColumnIndex);
            String cityString = cursor.getString(cityColumnIndex);
            String stateString = cursor.getString(stateColumnIndex);
            String zipString = cursor.getString(zipColumnIndex);

            Address address = new Address();
            address.setId(id);
            address.setLocationNameString(locationNameString);
            address.setStreetAddressString(streetAddressString);
            address.setCityString(cityString);
            address.setStateString(stateString);
            address.setZipString(zipString);

            addressList.add(address);
        }

        return addressList;
    }

    public static void deleteData(int id) {

        // Gets the data repository in write mode
        SQLiteDatabase sqLiteDatabase = addressesDbHelper.getWritableDatabase();

        // Which row to delete, based on the ID
        String whereClause = _ID + " =" + id;

        int rowsAffected = sqLiteDatabase.delete(TABLE_NAME, whereClause, null);
        Log.d("rowsAffected", "" + rowsAffected);
    }

    public static void destroy() {
        addressesDbHelper.close();
    }
}
