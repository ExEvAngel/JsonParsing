package com.angel.jsonparsingexamle;

import java.util.ArrayList;

/**
 * Created by Angel on 9/23/2016.
 */

public interface CityListener {

    public void addCity(City city);
    public ArrayList<City> getAllCity();
    public int getCityCount();
}
