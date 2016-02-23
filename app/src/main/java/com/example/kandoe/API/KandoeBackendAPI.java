package com.example.kandoe.API;

import com.example.kandoe.Model.Card;
import com.example.kandoe.Model.CardReview;
import com.example.kandoe.Model.Chat;
import com.example.kandoe.Model.Organisation;
import com.example.kandoe.Model.Session;
import com.example.kandoe.Model.SubTheme;
import com.example.kandoe.Model.Theme;
import com.example.kandoe.Model.User;

import org.json.JSONObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Thomas on 2016-02-16.
 */
public interface KandoeBackendAPI {

    //Card
    @GET("api/card")
    Call<List<Card>> getCards();

    @GET("api/card/{id}")
    Call<Card> getCardDetails(@Path("id") int id);

    @POST("api/card")
    Call<Card> playCard(@Body Card card);


    //Account
    //Todo voorlopig komt de response niet overeen met de klasse..
    @GET("api/account/userInfo")
    Call<User> getUserInfo();

    @POST("api/account/logout")
    Call<ResponseBody> logout();

    @POST("api/account/changePassword")
    Call<ResponseBody> changePassword(@Body String oldPassword, @Body String newPassword, @Body String confirmPassword);

    @POST("api/account/setPassword")
    Call<ResponseBody> setPassword(@Body String newPassword, @Body String confirmPassword);

    @POST("api/account/addExternalLogin")
    Call<ResponseBody> addExternalLogin(@Body String externalAccessToken);

    @POST("api/account/removeLogin")
    Call<ResponseBody> removeLogin(@Body String loginProvider, @Body String providerKey);

    @POST("api/account/register")
    Call<ResponseBody> register(@Body String email, @Body String password, @Body String confirmPassword);


    //Session
    @GET("api/session")
    Call<List<Session>> getSessions();

    @GET("api/session/id")
    Call<Session> getSessionDetails(@Path("id") int id);


    //Organisation
    @GET("api/organisations")
    Call<ResponseBody> getOrganisations();

    @GET("api/organisation/{id}")
    Call<Organisation> getOrganisation(@Path("id") int id);


    //ChatMessage
    @GET("api/chatMessage")
    Call<List<Chat>> getChats();

    @GET("api/chatMessage/{id}")
    Call<Chat> getChat(@Path("id") int id);

    @POST("api/chatMessage")
    Call<ResponseBody> chat(@Body String chatmessage);



    //Theme
    @GET("api/theme")
    Call<List<Theme>> getTheme();

    @GET("api/theme/{id}")
    Call<Theme> getTheme(@Path("id") int id);


    //CardReview
    @GET("api/cardReview")
    Call<List<CardReview>> getCardReview();

    @GET("api/cardReview/{id}")
    Call<CardReview> getCardReview(@Path("id") int id);



    //Subtheme
    @GET("api/subTheme")
    Call<List<SubTheme>> getSubThemes();

    @GET("api/subTheme/{id}")
    Call<SubTheme> getSubTheme(@Path("id") int id);




}


