package com.ex13;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

public class Util {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();
    static int j;

    public static User sendPost(URI uri, User user) throws IOException, InterruptedException {
        final String requestBody = GSON.toJson(user);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-type", "application/json")
                .build();
        final HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(response.body(), User.class);
    }

    public static User sendPut(URI uri, User user) throws IOException, InterruptedException {
        final String requestBody = GSON.toJson(user);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-type", "application/json")
                .build();
        final HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(response.body(), User.class);
    }

    public static Integer sendDelete(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .DELETE()
                .header("Content-type", "application/json")
                .build();
        final HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return response.statusCode();
    }

    public static List<User> sendGetAllUsers(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(uri)
                .GET().build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        final List<User> users = GSON.fromJson(response.body(), new TypeToken<List<User>>() {
        }.getType());
        return users;
    }

    public static User sendGet(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(uri)
                .GET().build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        final User user = GSON.fromJson(response.body(), User.class);
        return user;
    }

    public static User sendGet(URI uri, String username) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(uri)
                .GET().build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        final List<User> users = GSON.fromJson(response.body(), new TypeToken<List<User>>() {
        }.getType());
        for (int i = 0; i < users.toArray().length; i++) {
            if (users.get(i).getUsername().contains(username)) {
                return users.get(i);
            }
        }
        return null;
    }

    public static int maxId(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(uri)
                .GET().build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        final List<Posts> posts = GSON.fromJson(response.body(), new TypeToken<List<Posts>>() {
        }.getType());
        for (int i = 0; i < posts.toArray().length - 1; i++) {
            j = posts.get(i).getId();
            j = Math.max(posts.get(i).getId(), posts.get(i + 1).getId());
        }
        return j;
    }

    public static void sendGetAllComents(URI uri,int userId,int postId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(uri)
                .GET().build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        File mkd = new File(String.format("C:\\Users\\grafm\\IdeaProjects\\Unit13\\src\\resources"));
        mkd.mkdirs();
        File userXpostYcomments = new File(String.format("C:\\Users\\grafm\\IdeaProjects\\Unit13\\src\\resources\\user-%d-post-%d-comments.json",userId,postId));
        try (FileWriter writer = new FileWriter(userXpostYcomments)) {
            writer.write(response.body());
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Challenge> openChalleng(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(uri)
                .GET().build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        final List<Challenge> challenges = GSON.fromJson(response.body(), new TypeToken<List<Challenge>>() {
        }.getType());
        List<Challenge> openChalleng = challenges.stream().filter((o) -> !o.isCompleted()).collect(Collectors.toList());
        return openChalleng;
    }
}


