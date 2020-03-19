package com.bibro.http.response;

import com.bibro.statistics.Distance;
import lombok.*;

@Value
public class User {

    int id;
    String username;
    Address address;

    public double distanceBetweenUser(User user) {
        double longitude1 = Double.parseDouble(this.getAddress().getGeo().getLng());
        double latitude1 = Double.parseDouble(this.getAddress().getGeo().getLat());
        double longitude2 = Double.parseDouble(user.getAddress().getGeo().getLng());
        double latitude2 = Double.parseDouble(user.getAddress().getGeo().getLat());

        return Distance.calculate(longitude1, latitude1, longitude2, latitude2);
    }

}
