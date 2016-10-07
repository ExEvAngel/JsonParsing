package com.angel.jsonparsingexamle;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Angel on 9/23/2016.
 */

public class City implements Parcelable {
    private int id;
    private String name;
    private String state;
    private String description;

    public City (){
    }

    public City (String name, String state, String description){
        this.name = name;
        this.state = state;
        this.description = description;
    }

    public City (int id, String name, String state, String description){
        this.id = id;
        this.name = name;
        this.state = state;
        this.description = description;
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName (String name){
        this.name = name;
    }

    public String getState(){
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public static final Parcelable.Creator<City> CREATOR = new Creator<City>() {

        public City createFromParcel(Parcel source) {
            City city = new City();
            city.setName(source.readString());
            city.setDescription(source.readString());
            city.setState(source.readString());
            city.id = source.readInt();
            return city;
        }

        public City[] newArray(int size){
            return new City[size];
        }

    };

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags){
        parcel.writeString(name);
        parcel.writeString(state);
        parcel.writeString(description);
    }

}
