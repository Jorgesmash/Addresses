package com.addresses.datamodels;

public class Address {

    private int id;
    private String locationNameString;
    private String streetAddressString;
    private String cityString;
    private String stateString;
    private String zipString;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocationNameString() {
        return locationNameString;
    }

    public void setLocationNameString(String locationNameString) {
        this.locationNameString = locationNameString;
    }

    public String getStreetAddressString() {
        return streetAddressString;
    }

    public void setStreetAddressString(String streetAddressString) {
        this.streetAddressString = streetAddressString;
    }

    public String getCityString() {
        return cityString;
    }

    public void setCityString(String cityString) {
        this.cityString = cityString;
    }

    public String getStateString() {
        return stateString;
    }

    public void setStateString(String stateString) {
        this.stateString = stateString;
    }

    public String getZipString() {
        return zipString;
    }

    public void setZipString(String zipString) {
        this.zipString = zipString;
    }
}
