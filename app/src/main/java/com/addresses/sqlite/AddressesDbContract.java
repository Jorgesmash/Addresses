package com.addresses.sqlite;

import android.provider.BaseColumns;

public final class AddressesDbContract {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private
    private AddressesDbContract() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "addresses";
        public static final String COLUMN_NAME_LOCATION_NAME = "location_name";
        public static final String COLUMN_NAME_STREET_ADDRESS = "street_address";
        public static final String COLUMN_NAME_CITY = "city";
        public static final String COLUMN_NAME_STATE = "state";
        public static final String COLUMN_NAME_ZIP = "zip";
    }
}
