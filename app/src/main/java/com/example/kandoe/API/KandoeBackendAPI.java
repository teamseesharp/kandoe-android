package com.example.kandoe.API;

import com.example.kandoe.Model.User;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Thomas on 2016-02-16.
 */
public interface KandoeBackendAPI {
    @GET("user/{id}")
    Call<User> getUser(@Path("id") String user);





}


