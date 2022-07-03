package com.ex13;

import java.io.IOException;
import java.net.URI;
import java.util.List;

public class Main {
    private static final String ADRESATE = "https://jsonplaceholder.typicode.com/users";
    private static final String COMENT = "https://jsonplaceholder.typicode.com/posts";

    public static void main(String[] args) throws IOException, InterruptedException {
        int id;
        User Pavlo = new User();
        Pavlo.setName("Pavlo");
        Pavlo.setUsername("Graf");
        Pavlo.setEmail("grafmk1523@gmail.com");
        Pavlo.setAdress(new Adress("Soborna", "Apt.103", "Kyiv", 41821,
                new Geo((float) 50.23, (float) 30.19)));
        Pavlo.setPhone("063-11-12-29");
        Pavlo.setWebsite("www.graf.com.ua");
        Pavlo.setCompany(new Company("GrafCo", "THE TASK WILL BE COMPLETED",
                "harness real-time e-markets"));

        final User createUser = Util.sendPost(URI.create(ADRESATE), Pavlo);
        System.out.println("createUser = " + createUser);

        final User updateUser = Util.sendPut(URI.create(String.format("%s/%d", ADRESATE, id = 9)), Pavlo);
        System.out.println("updateUser = " + updateUser);

        final Integer deleteUser = Util.sendDelete(URI.create(String.format("%s/%d", ADRESATE, id = 10)));
        System.out.println("deleteUser = " + deleteUser);

        final List<User> getAllUsersUsers = Util.sendGetAllUsers(URI.create(ADRESATE));
        System.out.println("getUsers = " + getAllUsersUsers);

        final User getUserForID = Util.sendGet(URI.create(String.format("%s/%d", ADRESATE, id = 8)));
        System.out.println("getUser = " + getUserForID);

        final User getUserForUserName = Util.sendGet(URI.create(ADRESATE), "Maxime_Nienow");
        System.out.println("getUserForUserName = " + getUserForUserName);

        Util.sendGetAllComents(URI.create(String.format("%s/%d/comments",
                COMENT, id = Util.maxId(URI.create(COMENT)))));

        final List<Challenge> todos = Util.openChalleng(URI.create(String.format("%s/%d/todos", ADRESATE, id = 8)));
        System.out.println("openChallenge = " + todos);
    }
}
