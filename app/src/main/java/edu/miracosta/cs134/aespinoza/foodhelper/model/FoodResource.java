package edu.miracosta.cs134.aespinoza.foodhelper.model;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.sql.Time;
import java.util.Date;
public class FoodResource implements Parcelable {

    String organizationName;
    Location location;
    Date startDate;
    Date endDate;//optional for project for specific events that occur at random
    Time startTime;
    Time endTime;
    String eventDescription;
    Boolean isDiscounted = false;
    Boolean isFree = false;

    /**
     * Constructor:
     * FoodRespurce()- default constructor
     * */
    public FoodResource(){}
    /**
     * Constructor:
     * FoodRespurce(String,long,String,String,String,String,String,double,double,String)- Creates a FoodResource object
     * with the name of the organization, the data for creating a
     * Location object, and an eventDescription
     * */
    public FoodResource(String organizationName,long id, String name, String address, String city,
                        String state, String zipCode, String phone, double latitude, double longitude,
                        String eventDescription)
    {
        this.organizationName = organizationName;
        this.location = new Location(id,name,address,city,state,zipCode,phone,latitude,longitude);
        this.eventDescription = eventDescription;

    }

    /**
     * Constructor:
     * FoodRespurce()- Creates a FoodResource object with the name of the organization, the data for creating a
     * Location object,  an event Description, check wheter food is free, check wheterer food is discounted
     * */
    public FoodResource(String organizationName,long id, String name, String address, String city,
                        String state, String zipCode, String phone, double latitude, double longitude,
                        String eventDescription,Boolean isDiscounted,Boolean isFree)
    {
        this.organizationName = organizationName;
        this.location = new Location(id,name,address,city,state,zipCode,phone,latitude,longitude);
        this.eventDescription = eventDescription;
        this.isDiscounted = isDiscounted;
        this.isFree = isFree;
    }

    protected FoodResource(Parcel in) {
        organizationName = in.readString();
        location = in.readParcelable(Location.class.getClassLoader());
        eventDescription = in.readString();
        byte tmpIsDiscounted = in.readByte();
        isDiscounted = tmpIsDiscounted == 0 ? null : tmpIsDiscounted == 1;
        byte tmpIsFree = in.readByte();
        isFree = tmpIsFree == 0 ? null : tmpIsFree == 1;
    }

    /**
     * getOrganizationName()- returns string of the name of the organization/group
     * */

    public String getOrganizationName() {
        return organizationName;
    }
    /**
     * setOrganizationName(String)- assigns organizationName to the String argument
     * */
    public void setOrganizationName(String organizationName){
        this.organizationName = organizationName;
    }
    /**
     * getOrganizationName()- returns string of the name of the organization/group
     * */
    public Location getLocation() {
        return location;
    }
    /**
     * setOrganizationName(String)- assigns organizationName to the String argument
     * */
    public void setLocation(Location location) {
        this.location = location;
    }
    /**
     * getStartDate()- returns Date object of the FoodResource Object
     * */
    public Date getStartDate() {
        return startDate;
    }
    /**
     * setStartDate(Date)- assigns Date object argument to the startDate
     * */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }


    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public Boolean getDiscounted() {
        return isDiscounted;
    }

    public void setDiscounted(Boolean discounted) {
        isDiscounted = discounted;
    }

    public Boolean getFree() {
        return isFree;
    }

    public void setFree(Boolean free) {
        isFree = free;
    }

    @Override
    public String toString(){
        return "Organization Name= "+organizationName+
                location.toString()+
                "Event Description="+eventDescription;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i){
        parcel.writeString(organizationName);
        parcel.writeString(eventDescription);
        parcel.writeBoolean(isDiscounted);
        parcel.writeBoolean(isFree);

    }

    public static final Creator<FoodResource> CREATOR = new Creator<FoodResource>() {
        @Override
        public FoodResource createFromParcel(Parcel in) {
            return new FoodResource(in);
        }

        @Override
        public FoodResource[] newArray(int size) {
            return new FoodResource[size];
        }
    };
}
