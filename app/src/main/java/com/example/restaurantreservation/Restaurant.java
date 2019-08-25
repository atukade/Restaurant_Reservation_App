package com.example.restaurantreservation;

import androidx.annotation.NonNull;

public class Restaurant {
    String restaurantName;
    String restaurantId;
    String restaurantDescription;

    public Restaurant() {
    }

    public Restaurant(String restaurantName, String restaurantId, String restaurantDescription) {
        this.restaurantName = restaurantName;
        this.restaurantId = restaurantId;
        this.restaurantDescription = restaurantDescription;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public String getRestaurantDescription() {
        return restaurantDescription;
    }

    @NonNull
    @Override
    public String toString() {
        String x = restaurantId + " " + getRestaurantName() + " " + getRestaurantDescription();
        return x;
    }
}
