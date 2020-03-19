package com.bibro.statistics;

import com.bibro.http.response.Address;
import com.bibro.http.response.GeoLocalization;
import com.bibro.http.response.Post;
import com.bibro.http.response.User;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatisticsTest {

    private Statistics statistics;

    @Test
    public void given2PostsWithTheSameTitle_whenCallingGetNonUniquePosts_returnTitle() {
        //given
        List<Post> posts = Arrays.asList(
                new Post(1, 1, "test"), new Post(2, 2, "test1"),
                new Post(1, 3, "test"), new Post(1, 4, "title1"),
                new Post(5, 5, "test2"));

        //when
        statistics = new Statistics(Collections.emptyList(), posts);
        List<String> titles = statistics.getNonUniquePosts();

        //then
        assertEquals(1, titles.size());
        assertEquals("test", titles.get(0));
    }

    @Test
    public void givenUserWith4Posts_whenCallingCountUserPosts_return4() {
        //given
        List<User> users = Collections.singletonList(
                new User(1, "user", new Address(new GeoLocalization("1", "2"))));

        List<Post> posts = Arrays.asList(
                new Post(1, 1, "p1"), new Post(1, 2, "p2"),
                new Post(1, 3, "p3"), new Post(1, 4, "p4"));

        //when
        statistics = new Statistics(users, posts);
        List<UserPosts> userPosts = statistics.countUserPosts();

        //then
        assertEquals(users.get(0), userPosts.get(0).getUser());
        assertEquals(4, userPosts.get(0).getPosts());
    }

    @Test
    public void givenUsers_whenCallingFindClosestUser_returnClosest() {
        //given
        List<User> users = Arrays.asList(
                new User(1, "user1", new Address(new GeoLocalization("11", "21"))),
                new User(2, "user2", new Address(new GeoLocalization("11", "22"))),
                new User(3, "user3", new Address(new GeoLocalization("11", "23"))),
                new User(4, "user4", new Address(new GeoLocalization("54", "2"))));

        //when
        statistics = new Statistics(users, Collections.emptyList());
        Map<User, User> result = statistics.findClosestUsers();
        User closest = result.get(users.get(0));

        //then
        assertEquals(users.get(1), closest);
    }
}
