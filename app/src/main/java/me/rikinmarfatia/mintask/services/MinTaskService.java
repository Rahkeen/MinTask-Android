package me.rikinmarfatia.mintask.services;

import me.rikinmarfatia.mintask.models.User;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Interface to connect to REST API
 */
public interface MinTaskService {
    @POST("/users")
    Call<User> createUser(@Body User user);

}
