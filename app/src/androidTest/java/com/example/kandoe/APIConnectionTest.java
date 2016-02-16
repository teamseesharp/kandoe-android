package com.example.kandoe;

import com.example.kandoe.API.APIServiceGenerator;
import com.example.kandoe.API.KandoeBackendAPI;
import com.example.kandoe.Model.User;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

/**
 * Created by Thomas on 2016-02-16.
 */
public class APIConnectionTest {
    Call<User> call;
    boolean succes;

    @Test
    public void apiConnection() {

        // Create a very simple REST adapter which points the GitHub API endpoint.
        KandoeBackendAPI service = APIServiceGenerator.createService(KandoeBackendAPI.class);

        // Fetch and print a list of the contributors to this library.
        Call<User> call = service.getUser("1");
        System.out.println("test voorbereid");
        System.out.println("test starten");

        try {
            succes = call.execute().isSuccess();
            //assertEquals("User id must be 1", 1, user.getId());

        } catch (IOException e) {
            // handle errors
        }
        fail();


    }


}
