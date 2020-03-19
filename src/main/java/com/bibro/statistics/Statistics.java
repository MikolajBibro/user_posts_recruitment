package com.bibro.statistics;

import com.bibro.http.response.Post;
import com.bibro.http.response.User;
import lombok.AllArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;

@AllArgsConstructor
public class Statistics {

    private List<User> users;
    private List<Post> posts;

    public List<String> getNonUniquePosts() {
        return posts
                .stream()
                .collect(Collectors.groupingBy(Post::getTitle, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(x -> x.getValue() != 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public List<UserPosts> countUserPosts() {
        List<UserPosts> userPosts = new ArrayList<>();

        Map<Integer, User> usersToIdMap = users
                .stream()
                .collect(Collectors.toMap(User::getId, identity()));

        posts
                .stream()
                .collect(Collectors.groupingBy(Post::getUserId, Collectors.counting()))
                .forEach((k, v) -> userPosts.add(new UserPosts(usersToIdMap.get(k), v.intValue())));

        return userPosts;
    }

    public Map<User, User> findClosestUsers() {
        return users
                .stream()
                .collect(Collectors.toMap(identity(), this::findClosestUser));
    }

    public User findClosestUser(User user) {
        Comparator<User> comparator = Comparator.comparing(u -> u.distanceBetweenUser(user));

        return users
                .stream()
                .filter(u -> !u.equals(user))
                .min(comparator)
                .orElseThrow(NoSuchElementException::new);
    }



}

