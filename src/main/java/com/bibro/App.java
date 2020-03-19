package com.bibro;

import com.bibro.http.response.Post;
import com.bibro.http.response.User;
import com.bibro.statistics.Statistics;
import com.bibro.http.HttpJsonItems;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<User> users = new HttpJsonItems<>(User.class,"https://jsonplaceholder.typicode.com/users").list();
        List<Post> posts = new HttpJsonItems<>(Post.class,"https://jsonplaceholder.typicode.com/posts").list();

        Statistics statistics = new Statistics(users, posts);

        System.out.println("Posty uzytkownikow");
        statistics.countUserPosts().forEach(System.out::println);

        System.out.println("Najblizsi uzytkownicy");
        statistics.findClosestUsers().forEach((k, v) -> System.out.println("dla " + k + " najblizszy to " + v));

        System.out.println("Nieunikalne tytuly");
        statistics.getNonUniquePosts().forEach(System.out::println);
    }
}
