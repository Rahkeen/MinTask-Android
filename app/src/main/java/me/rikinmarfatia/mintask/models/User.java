package me.rikinmarfatia.mintask.models;

/**
 * A model for a User
 */
public class User {

    private String name;
    private String email;
    private String g_token;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getGToken() {
        return g_token;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Email: " + email + ", G_Token: " + g_token;
    }
}
