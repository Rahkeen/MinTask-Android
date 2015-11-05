package me.rikinmarfatia.mintask.services;

import me.rikinmarfatia.mintask.models.User;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Interface to connect to REST API
 */
public interface MinTaskService {
    @GET("/users")
    Call<User> getUser();
}
