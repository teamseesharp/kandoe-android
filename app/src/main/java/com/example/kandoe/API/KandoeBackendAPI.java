package com.example.kandoe.API;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Thomas on 2016-02-16.
 */
public interface KandoeBackendAPI {

    @GET("api/Organisation")
    Call<ResponseBody> getOrganisation();





}


