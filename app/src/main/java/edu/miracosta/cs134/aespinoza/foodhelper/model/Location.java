package edu.miracosta.cs134.aespinoza.foodhelper.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * The <code>Location</code> class represents a place where one can get a find a FoodResource including
 * all relevant information for the event.
 * @author Michael Paulding, repurposed by Alvaro Espinoza Merida & Alfredo Hernandez
 * Project - Final Project CS134
 */

public class Location implements Parcelable {
    private long mId;
    private String mName;
    private String mAddress;
    private String mCity;
    private String mState;
    private String mZipCode;
    private String mPhone;
    private double mLatitude;
    private double mLongitude;

    /**
     * Full Constructor
     * @param id unique id
     * @param name of location
     * @param address of location
     * @param city location is located in
     * @param state location is located in
     * @param zipCode location is located in
     * @param phone related to location
     * @param latitude coordinate of location
     * @param longitude coordination of location
     */
    public Location(long id, String name, String address, String city, String state, String zipCode, String phone, double latitude, double longitude) {
        mId = id;
        mName = name;
        mAddress = address;
        mCity = city;
        mState = state;
        mZipCode = zipCode;
        mPhone = phone;
        mLatitude = latitude;
        mLongitude = longitude;
    }

    /**
     * Constructor without id
     * @param name of location
     * @param address of location
     * @param city location is located in
     * @param state location is located in
     * @param zipCode location is located in
     * @param phone related to location
     * @param latitude coordinate of location
     * @param longitude coordination of location
     */
    public Location(String name, String address, String city, String state, String zipCode, String phone, double latitude, double longitude) {
        this(-1, name, address, city, state, zipCode, phone, latitude, longitude);
    }

    /**
     * Parcelable constructor of Location
     * @param in Location that has been put in a parcel
     */
    protected Location(Parcel in) {
        mId = in.readLong();
        mName = in.readString();
        mAddress = in.readString();
        mCity = in.readString();
        mState = in.readString();
        mZipCode = in.readString();
        mPhone = in.readString();
        mLatitude = in.readDouble();
        mLongitude = in.readDouble();
    }

    /**
     * Creates a Location based on Parcelable information.
     */
    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        /**
         * Creates a new Location array
         * @param size size of array
         * @return new Location array
         */
        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

    /**
     * Returns the id of the Location
     * @return id
     */
    public long getId() {
        return mId;
    }

    /**
     * Sets a new id for Location
     * @param id new id
     */
    public void setId(long id) {
        mId = id;
    }

    /**
     * Returns the name of the Location
     * @return name of Location
     */
    public String getName() {
        return mName;
    }

    /**
     * Sets a new name for Location
     * @param name new name
     */
    public void setName(String name) {
        mName = name;
    }

    /**
     * Returns the address of Location
     * @return address of Location
     */
    public String getAddress() {
        return mAddress;
    }

    /**
     * Sets a new address for Location
     * @param address new address
     */
    public void setAddress(String address) {
        mAddress = address;
    }

    /**
     * Returns the city the Location is in
     * @return Location's city
     */
    public String getCity() {
        return mCity;
    }

    /**
     * Sets a new city for Location
     * @param city new city
     */
    public void setCity(String city) {
        mCity = city;
    }

    /**
     * Returns the state the Location is in
     * @return Location's state
     */
    public String getState() {
        return mState;
    }

    /**
     * Sets a new state for Location
     * @param state new state
     */
    public void setState(String state) {
        mState = state;
    }

    /**
     * Returns the zip code of the Location
     * @return Location's zip code
     */
    public String getZipCode() {
        return mZipCode;
    }

    /**
     * Sets a new zip code for Location
     * @param zipCode new zip code
     */
    public void setZipCode(String zipCode) {
        mZipCode = zipCode;
    }

    /**
     * Returns the Location's phone
     * @return Location's phone
     */
    public String getPhone() {
        return mPhone;
    }

    /**
     * Sets a new phone for Location
     * @param phone new phone
     */
    public void setPhone(String phone) {
        mPhone = phone;
    }

    /**
     * Returns the Latitude of the Location
     * @return Location's latitude
     */
    public double getLatitude() {
        return mLatitude;
    }

    /**
     * Sets a new latitude for Location
     * @param latitude new latitude
     */
    public void setLatitude(double latitude) {
        mLatitude = latitude;
    }

    /**
     * Returns the longitude of the Location
     * @return Location's longitude
     */
    public double getLongitude() {
        return mLongitude;
    }

    /**
     * Sets a new longitude for Location
     * @param longitude new longitude
     */
    public void setLongitude(double longitude) {
        mLongitude = longitude;
    }

    /**
     * Returns the full address of the Location
     * @return Location's full address
     */
    public String getFullAddress()
    {
        return mAddress + "\n" + mCity + ", " + mState + "  " + mZipCode;
    }

    /**
     * Returns a String of the Location's latitude and longitude
     * @return Location's latitude and longitude
     */
    public String getFormattedLatLng()
    {
        String latLng = String.valueOf(Math.abs(mLatitude));
        latLng += ((mLatitude < 0.0) ? " S  " : " N  ");
        latLng += String.valueOf(Math.abs(mLongitude));
        latLng += ((mLongitude < 0.0) ? " W" : "E");
        return latLng;
    }

    /**
     * Returns a String representation of Location
     * @return Location as a String
     */
    @Override
    public String toString() {
        return "Location{" +
                "Id=" + mId +
                ", Name='" + mName + '\'' +
                ", Address='" + mAddress + '\'' +
                ", City='" + mCity + '\'' +
                ", State='" + mState + '\'' +
                ", Zip Code='" + mZipCode + '\'' +
                ", Phone='" + mPhone + '\'' +
                ", Latitude=" + mLatitude +
                ", Longitude=" + mLongitude +
                '}';
    }

    /**
     * Describe the Location's contents in an int value
     * @return Location's contents
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Writes a Location into a parcelable state
     * @param parcel Parcelable object
     * @param i useless
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(mId);
        parcel.writeString(mName);
        parcel.writeString(mAddress);
        parcel.writeString(mCity);
        parcel.writeString(mState);
        parcel.writeString(mZipCode);
        parcel.writeString(mPhone);
        parcel.writeDouble(mLatitude);
        parcel.writeDouble(mLongitude);
    }
}
