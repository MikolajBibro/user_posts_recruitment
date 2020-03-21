package com.bibro.http;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class HttpJsonItems<T> {

    private final HttpClient httpClient;
    private final Gson gson;
    private final Class<T> clazz;
    private final String url;

    public HttpJsonItems(Class<T> clazz, String url) {
        this(HttpClient.newHttpClient(), new Gson(), clazz, url);
    }

    public List<T> list() {
        String json = getResponse(url);
        Type type = TypeToken.getParameterized(ArrayList.class, clazz).getType();
        return gson.fromJson(json, type);
    }

    private String getResponse(String url) {
        return Try.of(() -> tryGetResponse(url)).get();
    }

    private String tryGetResponse(String url) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request =  HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }
}
