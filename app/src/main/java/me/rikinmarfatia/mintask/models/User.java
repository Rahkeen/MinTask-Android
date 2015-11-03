package me.rikinmarfatia.mintask.models;

/**
 * A model for a User
 */
public class User {
    private String name;
    private String email;
    private String g_token;
    private Task[] tasks;

    public User(String email) {
        this.email = email;
    }
}
