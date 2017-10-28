package com.addresses.app.addressform;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.addresses.R;
import com.addresses.sqlite.AddressesDbManager;

import static android.provider.BaseColumns._ID;
import static com.addresses.sqlite.AddressesDbContract.FeedEntry.COLUMN_NAME_CITY;
import static com.addresses.sqlite.AddressesDbContract.FeedEntry.COLUMN_NAME_LOCATION_NAME;
import static com.addresses.sqlite.AddressesDbContract.FeedEntry.COLUMN_NAME_STATE;
import static com.addresses.sqlite.AddressesDbContract.FeedEntry.COLUMN_NAME_STREET_ADDRESS;
import static com.addresses.sqlite.AddressesDbContract.FeedEntry.COLUMN_NAME_ZIP;

public class AddressFormActivity extends AppCompatActivity {

    /* Constants to define the database operation type */
    public static final String OPERATION = "OPERATION";
    public static final String CREATING = "CREATING";
    public static final String EDITING = "EDITING";

    private String operationString;

    /* ID of this particular Address row */
    private int id;

    /* Widgets */
    private EditText locationNameEditText;
    private EditText streetAddressEditText;
    private EditText cityEditText;
    private EditText stateEditText;
    private EditText zipEditText;

    private Button deleteButton;
    private Button okButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addressform_activity);

        locationNameEditText = (EditText) findViewById(R.id.locationNameEditText);
        streetAddressEditText = (EditText) findViewById(R.id.streetAddressEditText);
        cityEditText = (EditText) findViewById(R.id.cityEditText);
        stateEditText = (EditText) findViewById(R.id.stateEditText);
        zipEditText = (EditText) findViewById(R.id.zipEditText);

        deleteButton = (Button) findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new DeleteButtonOnClickListener());

        okButton = (Button) findViewById(R.id.okButton);
        okButton.setOnClickListener(new OKButtonOnClickListener());

        // Get the extras from the Intent
        Bundle bundle = getIntent().getExtras();

        operationString = bundle.getString(OPERATION);
        if (operationString.equals(CREATING)) {
            deleteButton.setVisibility(View.GONE);

        } else if (operationString.equals(EDITING)) {
            deleteButton.setVisibility(View.VISIBLE);

            id = bundle.getInt(_ID);
            locationNameEditText.setText(bundle.getString(COLUMN_NAME_LOCATION_NAME));
            streetAddressEditText.setText(bundle.getString(COLUMN_NAME_STREET_ADDRESS));
            cityEditText.setText(bundle.getString(COLUMN_NAME_CITY));
            stateEditText.setText(bundle.getString(COLUMN_NAME_STATE));
            zipEditText.setText(bundle.getString(COLUMN_NAME_ZIP));
        }
    }

    private class OKButtonOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            if (operationString.equals(CREATING)) {

                AddressesDbManager.createData(
                        locationNameEditText.getText().toString(),
                        streetAddressEditText.getText().toString(),
                        cityEditText.getText().toString(),
                        stateEditText.getText().toString(),
                        zipEditText.getText().toString());

            } else if (operationString.equals(EDITING)) {

                AddressesDbManager.updateData(
                        id,
                        locationNameEditText.getText().toString(),
                        streetAddressEditText.getText().toString(),
                        cityEditText.getText().toString(),
                        stateEditText.getText().toString(),
                        zipEditText.getText().toString());
            }

            finish();
        }
    }

    private class DeleteButtonOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            AddressesDbManager.deleteData(id);

            finish();
        }
    }
}
