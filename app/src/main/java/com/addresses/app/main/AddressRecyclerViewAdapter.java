package com.addresses.app.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.addresses.R;
import com.addresses.datamodels.Address;

import java.util.List;

public class AddressRecyclerViewAdapter extends RecyclerView.Adapter<AddressRecyclerViewAdapter.ViewHolder> {

    private Context context;

    private List<Address> addressList;

    private MainActivity.AddressListRecyclerViewOnItemClickListener addressListRecyclerViewOnItemClickListener;

    public AddressRecyclerViewAdapter(Context context, List addressList, MainActivity.AddressListRecyclerViewOnItemClickListener addressListRecyclerViewOnItemClickListener) {
        this.context = context;
        this.addressList = addressList;
        this.addressListRecyclerViewOnItemClickListener = addressListRecyclerViewOnItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.address_item_cardview, parent, false);
        itemView.setOnClickListener(addressListRecyclerViewOnItemClickListener);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        Address address = addressList.get(position);

        // Populate user interface
        viewHolder.locationNameTextView.setText(address.getLocationNameString());
        viewHolder.streetAddressTextView.setText(address.getStreetAddressString());
        viewHolder.cityTextView.setText(address.getCityString() + ",");
        viewHolder.stateTextView.setText(address.getStateString());
        viewHolder.zipTextView.setText(address.getZipString());
    }

    @Override
    public int getItemCount() {
        return addressList.size();
    }

    /** ViewHolder pattern: Inner class needed to keep the references between widgets and data to improve the performance */
    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView locationNameTextView;
        TextView streetAddressTextView;
        TextView cityTextView;
        TextView stateTextView;
        TextView zipTextView;

        ViewHolder(View itemView) {
            super(itemView);

            locationNameTextView = itemView.findViewById(R.id.locationNameTextView);
            streetAddressTextView = itemView.findViewById(R.id.streetAddressTextView);
            cityTextView = itemView.findViewById(R.id.cityTextView);
            stateTextView = itemView.findViewById(R.id.stateTextView);
            zipTextView = itemView.findViewById(R.id.zipTextView);
        }
    }
}
