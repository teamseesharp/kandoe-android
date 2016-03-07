package com.example.kandoe.Utilities.API;

import com.example.kandoe.Model.Card;
import com.example.kandoe.Model.CardReview;
import com.example.kandoe.Model.ChatMessage;
import com.example.kandoe.Model.Organisation;
import com.example.kandoe.Model.Session;
import com.example.kandoe.Model.SubTheme;
import com.example.kandoe.Model.Theme;
import com.example.kandoe.Model.UserAccount;

import java.util.Date;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Thomas on 2016-02-16.
 */
public interface KandoeBackendAPI {

    //Card
    @GET("api/cards")
    Call<List<Card>> getCards();

    @GET("api/cards/{id}")
    Call<Card> getCardById(@Path("id") int id);

    @POST("api/cards")
    Call<Card> addCard(@Body Card card);


    //Account
    //Todo voorlopig komt de response niet overeen met de klasse..
    @GET("api/accounts")
    Call<UserAccount> getUsers();

    @GET ("api/accounts/{id}")
    Call<UserAccount> getUserById(@Path("id") int id);

    //TODO: deze klopt niet
    @PUT("api/accounts")
    Call<ResponseBody> changeAccount(@Body String oldPassword, @Body String newPassword, @Body String confirmPassword);


    //Session
    @GET ("api/sessions")
    Call<List<Session>> getAllSessions();

    @GET("api/sessions/{id}")
    Call<Session> getSessionById(@Path("id") int id);

    @GET ("api/sessions/by-organisation/{id}")
    Call<List<Session>> getSessionsByOrganisation(@Path("id") int id);

    @GET ("api/sessions/by-subtheme/{id}")
    Call<List<Session>> getSessionsBySubtheme(@Path("id") int id);

    @GET ("api/sessions/by-start-date/{date}")
    Call<List<Session>> getSessionsByStartDate(@Path("date") Date date);

    @GET ("api/sessions/by-end-date/{date}")
    Call<List<Session>> getSessionsByEndDate(@Path("date") Date date);


    //Organisation
    @GET("api/organisations")
    Call<List<Organisation>> getOrganisations();

    @GET("api/verbose/organisations")
    Call<List<Organisation>> getOrganisationsVerbose();

    @GET("api/organisations/{id}")
    Call<Organisation> getOrganisationById(@Path("id") int id);

    @GET("api/organisations/by-organiser/{id}")
    Call<List<Organisation>> getOrganisationsByOrganiserId(@Path("id") int id);


    //ChatMessage
    @GET("api/chat-messages")
    Call<List<ChatMessage>> getChatMessages();

    @GET("api/chat-messages/{id}")
    Call<ChatMessage> getChatById(@Path("id") int id);

    @POST("api/chat-messages")
    Call<ResponseBody> chat(@Body String chatmessage);

    @GET ("api/chat-messages/by-session/{id}")
    Call<List<ChatMessage>> getChatMessagesBySessionId(@Path("id") int id);


    //Theme
    @GET("api/themes")
    Call<List<Theme>> getThemes();

    @GET("api/themes/{id}")
    Call<Theme> getThemesById(@Path("id") int id);

    @GET ("api/themes/by-organisation/{id}")
    Call<Theme> getThemeByOrganisationId(@Path("id") int id);

    @GET ("api/themes/by-tag/{tag}")
    Call<List<Theme>>getThemesByTag(@Path("tag") String tag);


    //CardReview
    @GET("api/cardReviews")
    Call<List<CardReview>> getCardReviews();

    @GET("api/cardReviews/{id}")
    Call<CardReview> getCardReviewById(@Path("id") int id);

    @GET("api/card-reviews/by-card/{id}")
    Call<CardReview> getCardReviewByCardId(@Path("id") int id);



    //Subtheme
    @GET("api/subThemes")
    Call<List<SubTheme>> getSubThemes();

    @GET("api/subThemes/{id}")
    Call<SubTheme> getSubThemeById(@Path("id") int id);

    @GET("api/subthemes/by-theme/{id}")
    Call<List<SubTheme>>getSubthemesByThemeId(@Path("id") int id);
}


