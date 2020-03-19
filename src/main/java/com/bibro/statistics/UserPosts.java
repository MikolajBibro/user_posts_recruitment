package com.bibro.statistics;

import com.bibro.http.response.User;
import lombok.Value;

@Value
public class UserPosts {

    User user;
    int posts;

    @Override
    public String toString() {
        return user.getUsername() + " napisał(a) " + posts + " postów";
    }
}