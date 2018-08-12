package com.example.chenrui.favoriteitem;


import android.graphics.Bitmap;

import java.util.List;

/**
 * A class for restaurant, which contains all information of a restaurant.
 */
public class Restaurant {
    /**
     * All data for a restaurant.
     */
    private String name;
    private String address;
    private String type;
    private double lat;
    private double lng;
    private Bitmap thumbnail;
    private Bitmap rating;
    private List<String> categories;
    private double stars;

    /**
     * Constructor
     *
     * @param name name of the restaurant
     */
    public Restaurant(String name, String address, String type, double lat, double lng,
                      Bitmap thumbnail, double stars) {
        this.name = name;
        this.address = address;
        this.type = type;
        this.thumbnail = thumbnail;
        this.lng = lng;
        this.lat = lat;
        this.stars = stars;

    }

    /**
     * Getters for private attributes of Restaurant class.
     */
    public String getName() { return this.name; }
    public String getAddress() { return this.address; }
    public String getType() { return this.type; }
    public Bitmap getThumbnail() { return this.thumbnail; }
    public double getLat() { return lat; }
    public double getLng() { return lng; }
    public Bitmap getRating() { return rating; }
    public double getStars() {
        return stars;
    }

}
