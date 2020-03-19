package com.bibro.http;

import com.bibro.http.response.Address;
import com.bibro.http.response.GeoLocalization;
import com.bibro.http.response.Post;
import com.bibro.http.response.User;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HttpJsonItemsTest {

    private WireMockServer wireMockServer;

    @BeforeEach
    public void setupWireMockServer() {
        wireMockServer = new WireMockServer();
        wireMockServer.start();
        configureFor("localhost", 8080);
    }

    @AfterEach
    public void stopWireMockServer() {
        wireMockServer.stop();
    }

    @Test
    public void givenUrlWithUsers_whenCallingList_returnList() throws IOException {
        //given
        String usersUrl = "http://localhost:8080/users";

        User user = new User(1, "Bret", new Address(new GeoLocalization("81.1496", "-37.3159")));

        stubFor(get(urlEqualTo("/users"))
                .willReturn(aResponse()
                        .withBody(new String(getClass().getResourceAsStream("/users.json").readAllBytes()))));

        //when
        List<User> users = new HttpJsonItems<>(User.class, usersUrl).list();

        //then
        assertEquals(10, users.size());
        assertEquals(user, users.get(0));
    }

    @Test
    public void givenUrlWithPosts_whenCallingList_returnList() throws IOException {
        //given
        String postsUrl = "http://localhost:8080/posts";

        Post post = new Post(1, 1, "sunt aut facere repellat provident occaecati excepturi optio reprehenderit");

        stubFor(get(urlEqualTo("/posts"))
                .willReturn(aResponse()
                        .withBody(new String(getClass().getResourceAsStream("/posts.json").readAllBytes()))));

        //when
        List<Post> posts = new HttpJsonItems<>(Post.class, postsUrl).list();

        //then
        assertEquals(100, posts.size());
        assertEquals(post, posts.get(0));
    }
}
