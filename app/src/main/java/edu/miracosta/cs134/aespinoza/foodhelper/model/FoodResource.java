package edu.miracosta.cs134.aespinoza.foodhelper.model;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.sql.Time;
import java.util.Date;
import java.util.Objects;

/**
 * FoodResource class made to represent a source of free or discounted food for those in need.
 * @Author Alvaro Espinoza Merida
 * Project - Final Project CS134
 */
public class FoodResource implements Parcelable {

    long id ;
    Location location;
    String organizationName;
    Date startDate;
    Date endDate;//optional for project for specific events that occur at random
    Time startTime;
    Time endTime;
    String eventDescription;
    int isDiscounted = 0;
    int isFree = 0;

    /**
     * Constructor:
     * FoodRespurce()- default constructor
     */
    public FoodResource() {
    }

    /**
     * Constructor:
     * FoodRespurce(String,long,String,String,String,String,String,double,double,String)- Creates a FoodResource object
     * with the name of the organization, the data for creating a
     * Location object, and an eventDescription
     */
    public FoodResource( long id,String organizationName, String name, String address, String city,
                         String state, String zipCode, String phone, double latitude, double longitude,
                         String eventDescription) {
        this.organizationName = organizationName;
        this.id = id ;
        this.location = new Location(id, name, address, city, state, zipCode, phone, latitude, longitude);
        this.eventDescription = eventDescription;

    }

    /**
     * Constructor:
     * FoodRespurce()- Creates a FoodResource object with the name of the organization, the data for creating a
     * Location object,  an event Description, check wheter food is free, check wheterer food is discounted
     */
    public FoodResource( long id,   String organizationName,  String name, String address, String city,
                         String state, String zipCode, String phone, double latitude, double longitude,
                         String eventDescription, int isDiscounted, int isFree) {
        this.organizationName = organizationName;
        this.id = id ;
        this.location = new Location(id, name, address, city, state, zipCode, phone, latitude, longitude);
        this.eventDescription = eventDescription;
        this.isDiscounted = isDiscounted;
        this.isFree = isFree;
    }


    /**
     * Constructor:
     * FoodRespurce()- Creates a FoodResource object with parcel as argument as a way of retrieving data
     * Location object,  an event Description, check wheter food is free, check wheterer food is discounted
     * */


    protected FoodResource(Parcel in) {
        location = in.readParcelable(Location.class.getClassLoader());
        this.id = location.getId() ;
        organizationName = in.readString();
        eventDescription = in.readString();
        isDiscounted = in.readInt();
        isFree = in.readInt();
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

    /**
     * Returns the FoodResource's id
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets new id for FoodResource
     * @param id new id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * String getOrganizationName()- returns string of the name of the organization/group
     *
     * @return instance variable organizationName
     */
    public String getOrganizationName() {
        return organizationName;
    }

    /**
     * void setOrganizationName(String)- assigns organizationName to the String argument
     *
     * @arg String - name of the organization
     */
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    /**
     * Location getLocation()- returns Location object that describes the location of food rescource
     *
     * @return Location - instacne variable location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * void setLocation(String)- assigns location instance variable to location argment
     *
     * @arg Location- description of the Location of the FoodResource
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Date getStartDate()- returns start Date object of the FoodResource Object
     *
     * @return instance variable startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * void setStartDate(Date)- assigns start Date object argument to the startDate
     *
     * @arg assigns StartDate instance variable to Date argument
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Date getEndDate()- returns end Date object of the FoodResource Object
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * void setEndDate(Date)- assigns end Date object argument to the endDate
     *
     * @arg Date - desctipton of the EndDate
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Time getStartTime(Date)- returns the startTime object
     *
     * @return instance variable startTime
     */
    public Time getStartTime() {
        return startTime;
    }

    /**
     * setStartTime(Time)- assigns start Time object argument to the startTime
     *
     * @arg Time - time object decribing start time
     */
    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    /**
     * Time getEndTime(Date)- returns the endTime object
     *
     * @return endTime instance variable
     */
    public Time getEndTime() {
        return endTime;
    }

    /**
     * void setEndTime(Time)- assigns end Time object argument to the endTime
     *
     * @arg sets instance variable to Time argument
     */
    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    /**
     * String getEventDescription()- returns the string eventDescription which describes our event
     *
     * @return instance variable eventDescription
     */
    public String getEventDescription() {
        return eventDescription;
    }

    /**
     * void setEventDescription()- sets string argument eventDescription to instance variable eventDescription
     *
     * @arg String - description of the event
     */
    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    /**
     * Boolean getDiscounted()- returns the Boolean instance variable isDiscounted
     *
     * @return instance variable isDiscounted
     */
    public int getDiscounted() {
        return isDiscounted;
    }

    /**
     * void setDiscounted(Boolean)- sets instance variable isDiscounted to Boolean argument
     *
     * @arg Boolean - argument if the FoodResource is discounted
     */
    public void setDiscounted(int discounted) {
        isDiscounted = discounted;
    }

    /**
     * Boolean getFree()- returns the instance variable Boolean isFree
     *
     * @return -isFree instance variable
     */
    public int getFree() {
        return isFree;
    }

    /**
     * void setFree()- sets Boolean argument to isFree instance variable
     *
     * @arg Boolean - argument if the FoodResource is free
     */
    public void setFree(int free) {
        isFree = free;
    }

    /**
     * String toString()- returns a string representatin of the FoodResource object
     *
     * @return string value of FoodResource
     */
    @Override
    public String toString() {
        return "Organization Name= " + organizationName +
                location.toString() +
                "Event Description=" + eventDescription;
    }

    /**
     * Returns a description of contents
     * @return int value of content.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Allows the Object to be passed along easily in intents.
     * @param parcel Parcelable item
     * @param i id
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeParcelable(location, i);
        parcel.writeString(organizationName);
        parcel.writeString(eventDescription);
        parcel.writeInt(isDiscounted);
        parcel.writeInt(isFree);
    }

    /**
     * Used to compare Two FoodResources for equivalence
     * @param o object to compare to
     * @return true or false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodResource that = (FoodResource) o;
        return isDiscounted == that.isDiscounted &&
                isFree == that.isFree &&
                location.equals(that.location) &&
                organizationName.equals(that.organizationName) &&
                startDate.equals(that.startDate) &&
                endDate.equals(that.endDate) &&
                startTime.equals(that.startTime) &&
                endTime.equals(that.endTime) &&
                eventDescription.equals(that.eventDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, organizationName, startDate, endDate, startTime, endTime, eventDescription, isDiscounted, isFree);
    }

    /**
     * void writeToParcel- writes FoodResource object data into a Parcel
     *
     * @arg Parcel - parcel data will be written to
     * @arg int -
     */

}
