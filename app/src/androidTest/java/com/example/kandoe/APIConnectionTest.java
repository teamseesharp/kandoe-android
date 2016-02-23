package com.example.kandoe;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.widget.Toast;

import com.example.kandoe.API.APIServiceGenerator;
import com.example.kandoe.API.KandoeBackendAPI;
import com.example.kandoe.Activity.LoginActivity;
import com.example.kandoe.Model.User;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

/**
 * Created by Thomas on 2016-02-16.
 */
@RunWith(AndroidJUnit4.class)
public class APIConnectionTest {

      Response<ResponseBody> body;
    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule<>(
            LoginActivity.class);

    @Test
    public void apiConnection() {


        // Create a very simple REST adapter which points the  API endpoint.
        KandoeBackendAPI service = APIServiceGenerator.createService(KandoeBackendAPI.class);

        // Fetch and print a list of the contributors to this library.
        Call<ResponseBody> jsonObjectCall = service.getOrganisations();

        try {
            body = jsonObjectCall.execute();
            mActivityRule.getActivity().setAPIconnection(body.isSuccess());

        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("APITEST CODE", String.valueOf(body.code()));
        assertEquals(mActivityRule.getActivity().isAPIconnection(), true);


    }

}
